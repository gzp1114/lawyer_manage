<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
	
		<div class="page-header">
		<div  class="form-group">
			<form  id="sysDictionaryValueSearchForm" >
                   <label class="col-sm-1 control-label no-padding-right" for="form-field-1"> 租户编号： </label> 
                   <input type="text" id="search_tenantId" name="search_tenantId" placeholder="租户编号" class="col-xs-10 col-sm-2"  value=""/> 
                   <label class="col-sm-1 control-label no-padding-right" for="form-field-1"> 字典名称： </label> 
                   <input type="text" id="search_name" name="search_name" placeholder="名称" class="col-xs-10 col-sm-2"  value=""/> 
                   <label class="col-sm-1 control-label no-padding-right" for="form-field-1"> 字典值： </label> 
                   <input type="text" id="search_value" name="search_value" placeholder="字典值" class="col-xs-10 col-sm-2"  value=""/> 
				
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="btn btn-sm btn-success"  id="sysDictionaryValueSearch">
					<i class="ace-icon fa fa-search bigger-110"></i>
					<span class="bigger-110 no-text-shadow">查 询</span>
				</button>
			</form>
		</div>
	</div><!-- /.page-header -->

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

		<div class="row">
		<div class="col-xs-12">
	
			<table id="grid-table"></table>
	
			<div id="grid-pager"></div>
	
		</div><!-- /.col -->
	</div><!-- /.row -->

	<script type="text/javascript">
		
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
				url: "${ctx}/sysDictionaryValue/search",
				datatype: 'json',
				mtype: 'post',
				height: 'auto',
				width: 'auto',
				colNames:[
						"租户编号",
						"字典类型",
						"字典名称",
						"字典值",
						"排序",
						"状态",
						"备注",
						"创建时间",
						'操作'],
				colModel:[
					{name:'tenantId',width:100, sortable:false},
					{name:'refTypeName',width:100, sortable:false},
					{name:'name',width:100, sortable:false},
					{name:'value',width:100, sortable:false},
					{name:'index',width:100, sortable:false},
					{name:'status',width:50, sortable:false,formatter:function(cellvalue, options, rowObject){  if(cellvalue=="1"){ return "启用";} else return "停用";}},
					{name:'note',width:100, sortable:false},
					{name:'createTime',width:100, sortable:false},
					{name:'operation',  width:30, sortable:false,formatter: function (value, grid, rows, state) { return rowOperation(value, grid, rows, state);  }}
				], 
				viewrecords : true,
				rowNum:10,
				rowList:[10,20,30],
				pager : pager_selector,
				altRows: true,
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
			
			$("#t_grid-table").append('   <button id="sysUserInfoAddButton" class="btn btn-sm btn-info">'+
					'<i class="ace-icon fa fa-plus-square bigger-110"></i>'+
					'<span class="bigger-110 no-text-shadow"  >增加</span>'+
				'</button>');
		
			
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
		});
		//构建表结束
		
	//数据行操作功能
	function rowOperation(value, grid, curRow, state){
		var opeStr = "<div title=\"编辑所选记录\" style=\"float:left;cursor:pointer;\" class=\"ui-pg-div ui-inline-edit\" id=\"jEditButton_1\" onclick=\"sysUserInfoModifyRow("+curRow.id+");\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\"><span class=\"ui-icon ui-icon-pencil\"></span></div>";
		//"<div title=\"删除所选记录\" style=\"float:left;margin-left:5px;\" class=\"ui-pg-div ui-inline-del\" id=\"jDeleteButton_1\" onclick=\"sysUserInfoDeleteRow("+curRow.id+");\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\"><span class=\"ui-icon ui-icon-trash\"></span></div>";
		return opeStr;
	}
		
	//修改字典类型信息
	function sysUserInfoModifyRow(id){
		if(id == null){
			messagePrompt('请选择需要修改记录！');
			return false;
		}
		var dialog = $('<div></div>').dialog({
			width:700,
			height:450,
			closed:false,
            cache:false,
			modal:true,
			title:"<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-pencil-square-o'></i> 修改字典值信息</h4></div>",
			title_html:true,
			buttons:[ 
					{
						html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 提交",
						"class" : "btn btn-info btn-xs",
						click: function() {
							sysDictionaryValueModify(); 
							$( this ).dialog( "close" );
							return search();
						}
					},
					{
						html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
						"class" : "btn btn-xs",
						click: function() {
							$( this ).dialog( "close" );
						}
					}
			],
			create: function( event, ui ) {$(this).load("<c:url value='/sysDictionaryValue/toModify/"+id+"'/>");},
			close:function() {
                $(this).dialog("destroy");
            }
		});
	}
	
	//字典类型信息增加
	$( "#sysUserInfoAddButton" ).on('click', function(e) {
		
		var dialog = $('<div></div>').dialog({
			width:700,
			height:450,
			closed:false,
            cache:false,
			modal:true,
			title:"<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-plus-square'></i> 字典值新增</h4></div>",
			title_html:true,
			buttons:[ 
					{
						html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 提交",
						"class" : "btn btn-info btn-xs",
						click: function() {
							
							sysDictionaryValueAdd(); 
							
							$( this ).dialog( "close" );
							
							return search();
								
						}
					}
					,
					{
						html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
						"class" : "btn btn-xs",
						click: function() {
							$( this ).dialog( "close" );
						}
					}
			],
			create: function( event, ui ) {$(this).load("<c:url value='/sysDictionaryValue/toAdd'/>");},
			close:function() {
                $(this).dialog("destroy");
            }
		});
	});
	//查询按键操作事件
	$( "#sysDictionaryValueSearch" ).on('click', function(e) {
		return search();
	});
	
	//字典类型信息查询
	function search(){
		$("#grid-table").jqGrid('setGridParam',{
        	postData:{"search_tenantId":$("#search_tenantId").val(),"search_value":$("#search_value").val(),"search_name":$("#search_name").val()},
    	}).trigger('reloadGrid',[{ current: false }]); 
		return false;
	}
	</script>
