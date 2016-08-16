<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>新建菜单</title>
		<style type="text/css">
		.select_option{
			width: 185px;
			z-index: 1;
			position: absolute;
		}
		.select_showbox{
			width: 180px;
		}
		.form_textarea{
			max-width: 400px;
			max-height: 100px;
			min-width: 400px;
			min-height: 100px;
			border: 1px solid #DFE7F0;
		}
		</style>
		<link type="text/css" rel="stylesheet" href="${ctx}/static/threadui/css/select.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/static/tools/jquery-validationEngine/validationEngine.jquery.css"></link>
	</head>
	<body>
		<div class="cinema_tip">
		    <div class="cinema_subject clearfix">
		        <div class="cinema_title">
		            <a href="javascript:void(0);">用户中心</a> >
		            <a id="menuFun_list_href" href="${ctx}/sysMenuFunction/toSearch">菜单管理</a> >
		            <a href="javascript:void(0);" class="cinema_option">新建菜单</a>
		        </div>
		        <a id="bak_toSearch_href" href="${ctx}/sysMenuFunction/toSearch?search_parentId=${sysMenuFunction.id}" class="movie_return">返回列表</a>
		    </div>
		</div>
		<div class="cinema_list">
			<form class="form-horizontal" role="form" onsubmit="return sysMenuFunctionAdd();" id="sysMenuFunctionAddForm" name="sysMenuFunctionAddForm" >
				<div class="movie_list">
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">上一级菜单：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="parentName"  class="form-control" type="text" disabled="disabled" value="${sysMenuFunction.menuName}">
								<input id="parentId" name="parentId" placeholder="上一级菜单主键" class="form-control" type="hidden" value="${sysMenuFunction.id}">
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>菜单名：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="menuName" name="menuName" maxlength="100" class="validate[required,maxSize[100]]" type="text" value=""/>
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>菜单类型：</p>
						</dt>
						<dd>
                            <div class="cinema_select mleft_15">
                            	<select name="menuType" id="menuType" class="fufong">
									<option value="1" selected="selected">模块</option>
									<option value="2">菜单</option>
									<option value="3">功能</option>
								</select>
                            </div>
                        </dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>是否有效: </p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type clearfix">
								<div>
									<label>
										<input style="width: 20px;"  type="radio" class="ace" id="isValid" name="isValid" value="1" checked="checked">
										<span class="lbl">有效</span>
									</label>
									<label>
										<input style="width: 20px;"  type="radio" class="ace" id="isValid" name="isValid" value="0">
										<span class="lbl">无效</span>
									</label>
								</div>
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">菜单URL：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input style="width: 400px;" id="menuUrl" name="menuUrl" maxlength="200" class="form-control" type="text" value=""/>
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;padding-left: 0px">菜单功能说明：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<textarea rows="6" cols="60" class="form_textarea" id="menuInfo" name="menuInfo" maxlength="400" ></textarea>
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">备注：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<textarea rows="6" cols="60" class="form_textarea" id="note" name="note" maxlength="400" ></textarea>
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<a class="movie_continue">
						<input type="submit" name="submit" id="submit" value="提交" class="cinema_inquiry" />
					</a>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="${ctx}/static/threadui/datejs/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/static/threadui/js/jquery.select.js"></script>
		<script type="text/javascript" src="${ctx}/static/threadui/js/select_style.js"></script>
		<script type="text/javascript" src="${ctx}/static/tools/jquery-validationEngine/jquery.validationEngine.js"></script>
		<script type="text/javascript" src="${ctx}/static/tools/jquery-validationEngine/jquery.validationEngine-cn.js"></script>
		<script type="text/javascript">
			//增加菜单功能
			function sysMenuFunctionAdd(){
				if($("#sysMenuFunctionAddForm").validationEngine("validate")){
					var menuType = $("#menuType").val();
					var urlNotExist = true;
					if(menuType!=undefined&&menuType.trim()!=""&&(menuType==3||menuType=="3")){
						var menuUrl = $("#menuUrl").val();
						$.ajax({
					        type: "POST",
					        dataType: "json",
					        async: false,
					        url: '${ctx}/sysMenuFunction/checkMenuUrl',
					        data: {search_menuType:menuType,search_menuUrl:menuUrl.trim()},
					        success: function (data) {
					        	if(data.status!='0'&&data.status!=0){
					        		alert(data.error);
					        		urlNotExist = false;
					        	}else{
						        	urlNotExist = true;
					        	}
				        		return false;
					        },
					        error: function(data) {
					            alert("error:"+data.error);
					            return false;
					        }
					    });
					}
					if(urlNotExist){
						$.ajax({
					        type: "POST",
					        dataType: "json",
					        async: false,
					        url: '${ctx}/sysMenuFunction/add',
					        data: $('#sysMenuFunctionAddForm').serialize(),
					        success: function (data) {
					        	if(data.status!='0'){
					        		alert(data.error);
					        		return false;
					        	}
					        	alert("保存成功！");
								window.location.href = $("#bak_toSearch_href").attr("href");
								return false;
					        },
					        error: function(data) {
					            alert("error:"+data.error);
					        }
					    });
					}
				}
				return false;
			}
			$("#sysMenuFunctionAddForm").validationEngine();
		</script>
	</body>
</html>
