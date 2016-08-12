<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/static/common/default.jsp"%>

<!-- page specific styles -->
<link rel="stylesheet" href="${ctx}/static/bootstrap-ace/css/jquery-ui.min.css" />

<!-- page specific scripts -->
<script src="${ctx}/static/bootstrap-ace/js/jquery-ui.min.js"></script>
<script src="${ctx}/static/bootstrap-ace/js/bootbox.min.js"></script>

<ul class="nav ace-nav">
	<li class="purple">
		<a data-toggle="dropdown" class="dropdown-toggle" href="#">
			<i class="ace-icon fa fa-bell icon-animated-bell"></i>
			<span class="badge badge-important">0</span>
		</a>

		<ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
			<li class="dropdown-header">
				<i class="ace-icon fa fa-exclamation-triangle"></i>
				0 没有信息
			</li>

			<li class="dropdown-footer">
				<a href="#">
					查看全部消息
					<i class="ace-icon fa fa-arrow-right"></i>
				</a>
			</li>
		</ul>
	</li>

	<li class="light-blue">
		<a data-toggle="dropdown" href="#" class="dropdown-toggle">
			<img class="nav-user-photo" src="${ctx}/static/bootstrap-ace/avatars/user.jpg" alt="Jason's Photo" />
			<span class="user-info" id="username">
				<small>欢迎</small>
				
			</span>
			<span id="userSessionId" style="display: none;"></span>
			<span id="userInfoId" style="display: none;"></span>
			<i class="ace-icon fa fa-caret-down"></i>
		</a>

		<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
			<li>
				<a href="#" onclick="editPassword()">
					<i class="ace-icon fa fa-cog"></i>
					设置
				</a>
			</li>

			<li>
				<a href="#" onclick="showInfo()">
					<i class="ace-icon fa fa-user"></i>
					信息
				</a>
			</li>

			<li class="divider"></li>

			<li>
				<a href="${ctx}/logout" >
					<i class="ace-icon fa fa-power-off"></i>
					退出
				</a>
			</li>
		</ul>
	</li>
</ul>

<script type="text/javascript">
	$(document).ready(function(){ 
		$.ajax({
	        type: "POST",
	        dataType: "json",
	        url: '${ctx}/session',
	        success: function (msg) {
	        	if(msg.status!='0'){
	        		window.location.href = '${ctx}'+ msg.data;
	        	}else{
	        		var user = msg.data;
	        		
	        		$('#userSessionId').html(user.id);
	        		$('#userInfoId').html(user.userInfoId);
	        		$('#username').html("<small>欢迎</small>"+user.userInfo.userName);
		        	
	        	} 
	        },
	        error: function(data) {
	            alert("errorssss:"+data.error);
	         }
	    });
		
	}); 
	
	
	//用户基本信息展示
	function showInfo() {
		var dialog = $('<div></div>').dialog({
			closed:false,
            cache:false,
			modal:true,
			title:"用户信息",
			title_html:true,
			buttons:[ 
					{
						html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
						"class" : "btn btn-info btn-xs",
						click: function() {
							$( this ).dialog( "close" );
								
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
			create: function( event, ui ) {$(this).load("<c:url value='/sysUserInfo/toShow'/>");},
			close:function() {
                $(this).dialog("destroy");
            }
		});
	}
	
	//修改密码
	function editPassword() {
		var dialog = $('<div></div>').dialog({
			closed:false,
            cache:false,
			modal:true,
			title:"密码修改",
			title_html:true,
			buttons:[ 
					{
						html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 提交",
						"class" : "btn btn-info btn-xs",
						click: function() {
							if(sysModifyPassword()){
								messagePrompt("密码修改成功");
								$( this ).dialog( "close" );
							}
								
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
			create: function( event, ui ) {$(this).load("<c:url value='/sysUserInfo/toPassword/'/>");},
			close:function() {
                $(this).dialog("destroy");
            }
		});
	}
	
	//消息提示
	function messagePrompt(message){
			bootbox.dialog({
				closed:false,
				message: "<span class='bigger-110'>"+message+"</span>",
				buttons: 			
				{
					"click" :
					{
						"label" : "确认",
						"className" : "btn-sm btn-primary",
						"callback": function() {
						}
					}
				}
			});
	}

</script>
