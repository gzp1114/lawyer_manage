<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<script type="text/javascript">
$(document).ready(
	function() {
		//iframe加载响应，初始页面时也有一次，此时data为null。
		$("#myTarget").load(
			function() {
				var data = $(window.frames['myTarget'].document.body).html();
				if(data!=""){
					//若iframe携带返回数据
					$("#page-content").load("<c:url value='/autoDevelop/parsePDM?fileName="+data+"' />");
				}
			});
	});
</script>
<div class="page-header">
	<div  class="form-group">
		<form id="fileUploadForm" name="fileUploadForm" action="autoDevelop/uploadPDM" method="post"
			enctype="multipart/form-data" target="myTarget">
			<input type="file" name="myfile" >
			
			<button id="fileUploadFormButton" class="btn btn-sm btn-info">
					<span class="glyphicon 	glyphicon glyphicon-upload" aria-hidden="true"></span>
					<span class="bigger-110 no-text-shadow"  >上  传PowerDesigner文件</span>
					</button>
					
		</form>
		<iframe id="myTarget" name="myTarget" style="display: none;"></iframe>
	</div>
	<div class="form-group">
		<div id="download"></div>
	</div>
</div><!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">

		<table id="grid-table"></table>

		<div id="grid-pager"></div>

	</div><!-- /.col -->
</div><!-- /.row -->

<!-- page specific styles -->
<link rel="stylesheet" href="${ctx}/static/bootstrap-ace/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/static/bootstrap-ace/css/ui.jqgrid.css" />
<link rel="stylesheet" href="${ctx}/static/bootstrap-ace/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/static/bootstrap-ace/css/font-awesome.min.css" />

<!-- ace styles -->
<link rel="stylesheet" href="${ctx}/static/bootstrap-ace/css/ace.min.css" />

<!-- page specific scripts -->
<script src="${ctx}/static/bootstrap-ace/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${ctx}/static/bootstrap-ace/js/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="${ctx}/static/bootstrap-ace/js/jquery-ui.min.js"></script>
<script src="${ctx}/static/bootstrap-ace/js/bootbox.min.js"></script>

<style type="text/css">
	.ui-jqgrid .ui-userdata{height:39px}
	.page-header{padding-bottom:0px}
	.page-header{margin:0 0 4px}
	.btn-sm{padding:3px 9px}
	.ace-nav{height:0}
</style>

<script type="text/javascript">
var str = '${list}';
var jsonData = eval('(' + str + ')');
var lastSel;  

	//上传文件
	$( "#fileUploadFormButton" ).on('click', function(e) {
		$( "#fileUploadForm" ).submit();
	});
	//构建表
	jQuery(function($) {
	
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
			
			//resize to fit page size
			$(window).on('resize.jqGrid', function () {
				$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width() );
		    })
			//resize on sidebar collapse/expand
			var parent_column = $(grid_selector).closest('[class*="col-"]');
			$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
				if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
					$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
				}
		    })
			
			jQuery(grid_selector).jqGrid({
				data: jsonData,
				datatype: "local",
				mtype: 'post',
				height: 'auto',
				width: 'auto',
				colNames:['表编码','表名','项目名','备注'],
				colModel:[
					{name:'code',  width:50, sortable:false},
					{name:'name', width:100, sortable:false},
					{name:'projectName',width:100, sortable:false},
					{name:'comment',  width:80, sortable:false}
				], 
				viewrecords : true,
				rowNum:10,
				rowList:[10,20,30],
				pager : pager_selector,
				altRows: true,
				multiboxonly: true,
				multiselect: true,
				loadComplete : function() {
					var table = this;
					setTimeout(function(){
						updatePagerIcons(table);
					}, 0);
				},
				jsonReader:{
					"root":"data.rows",
					"page":"data.page",
				    "total":"data.total",
				    "records":"data.records",
				    "repeatitems": false,
				    "id":"id"},
				toolbar: [true,"top"]
			});
			
			$(window).triggerHandler('resize.jqGrid');
			jQuery(grid_selector).jqGrid('navGrid',pager_selector,{edit:false,add:false,del:false,search:false,refresh:false});
			
			
			$("#t_grid-table").append('   <button id="bootbox-regular" class="btn btn-sm btn-info">'+
					'<span class="glyphicon 	glyphicon-cog" aria-hidden="true"></span>'+
					'<span class="bigger-110 no-text-shadow"  >生成开发环境</span>'+
				'</button>');
			
			//$("#t_grid-table").append('<button class="btn" id="bootbox-regular">生成开发环境</button>');
			
			function updatePagerIcons(table) {
				var replacement = 
				{
					'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
					'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
					'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
					'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
				};
				$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
					var icon = $(this);
					var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
					
					if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
				})
			}
			
			//override dialog's title function to allow for HTML titles
			$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
				_title: function(title) {
					var $title = this.options.title || '&nbsp;'
					if( ("title_html" in this.options) && this.options.title_html == true )
						title.html($title);
					else title.text($title);
				}
			}));
		
			$("#bootbox-regular").on(ace.click_event, function() {
				var tbNames = "";
				var rowData = jQuery(grid_selector).jqGrid('getGridParam','selarrrow');
			    if(rowData.length) {
			        for(var i=0;i<rowData.length;i++){
			           var name= jQuery(grid_selector).jqGrid('getCell',rowData[i],'code');//name是colModel中的一属性
			           tbNames+=","+name;
			        }
			    }
			    if(tbNames==""){
			    	alert("请选择表名");
			    	return;
			    }
			    
				bootbox.prompt("请输入包路径", function(packageName) {
					console.log(packageName+"----"+tbNames);
					if (packageName === null) {
						alert("包路径呢？");
						return ;
					}
					$.ajax({
                        type: "post", 
                        async: false,
                        url: "${ctx}/autoDevelop/engine?tableNames="+tbNames+"&fileName=${pdmFile}"+"&packagePath="+packageName, 
                        dataType: "text", 
                        success: function (data) {
                        	console.log(data);
                        	$("#t_grid-table").append("  <a class='btn btn-sm btn-info' href='${ctx}/autoDevelop/download?fileName="+data+"'><span class=\"glyphicon glyphicon glyphicon-download\" aria-hidden=\"true\"></span>下载代码文件</a> ");
                        	//$("#download").html("<a class='btn btn-sm btn-info' href='${ctx}/autoDevelop/download?fileName="+data+"'><span class=\"glyphicon glyphicon glyphicon-download\" aria-hidden=\"true\"></span>下载代码文件</a> ");
                        },error: function(e) { 
                        	alert("系统错误"); 
                        } 
                	});
				});
			});
	});
</script>
