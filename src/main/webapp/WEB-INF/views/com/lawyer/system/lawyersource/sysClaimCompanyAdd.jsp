<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>债权信息管理</title>
	    <style type="text/css">
			.select_option{
				width: 180px;
				z-index: 1;
				position: absolute;
			}
			.select_showbox{
				width: 175px;
			}
			.checkRole{
				width: 45%;
				float: left;
				border-radius: 3px;
				box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
				cursor: text;
				line-height:22px;
				padding: 4px 6px;
				border: 1px solid #CAC5C5;
				margin-top: 5px;
				min-height: 100px;
				height: auto;
			}
			.checkRole attr{
				color: red;
			}
			.tag{
				color: white;
				margin-right: 2px;
				padding: 0.2em 0.6em 0.3em;
				border-radius: 0.25em;
				color: white;
				font-size: 95%;
				line-height: 20px;
				text-align: center;
				white-space: nowrap;
				margin-top: 1px;
			}
			.label-success{
				background-color: #29C129;
			}
			.label-default{
				background-color: #7E7575;
			}
			.tag [class="tag-remove"]::after{
				content: "x";
				padding: 0.2px;
				cursor: pointer;
				font-weight: bold;
				margin-left: 5px;
			}
	    </style>
		<link type="text/css" rel="stylesheet" href="${ctx}/static/tools/jquery-validationEngine/validationEngine.jquery.css"></link>
		<link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/bootstrap.min.css"></link>
	</head>
	<body>
		<div class="cinema_tip">
		    <div class="cinema_subject clearfix">
		        <div class="cinema_title">
		            <a href="javascript:void(0);">案源中心</a> >
		            <a id="userinfo_list_href" href="${ctx}/sysClaimCompany/toSearch">债权信息管理</a> >
		            <a href="javascript:void(0);" class="cinema_option">新建债权信息</a>
		        </div>
		        <a href="${ctx}/sysClaimCompany/toSearch" class="movie_return">返回列表</a>
		    </div>
		</div>
		<div class="cinema_list">
			<form class="form-horizontal" onsubmit="return sysClaimCompanyAdd();" role="form" id="sysClaimCompanyAddForm" name="sysClaimCompanyAddForm" >
				<div class="movie_list">
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>债务主体名称：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed input-group">
								<input id="debtorid" type="hidden" name="debtorid" value="" class="validate[required,maxSize[100]]" maxlength="100" />
								<input id="debtorname" type="text" name="debtorname" value="" class="validate[required,maxSize[100]]" maxlength="100" />
		                        <div class="input-group-btn">
		                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
		                            </ul>
		                        </div>
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p>主体名称：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="name" type="text" name="name" value="" class="validate[required,maxSize[100]]" maxlength="100" />
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
		
		<script type="text/javascript" src="${ctx}/static/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="${ctx}/static/bootstrap/bootstrap-suggest.js"></script>
		<script type="text/javascript" src="${ctx}/static/tools/jquery-validationEngine/jquery.validationEngine.js"></script>
		<script type="text/javascript" src="${ctx}/static/tools/jquery-validationEngine/jquery.validationEngine-cn.js"></script>
		<script type="text/javascript">
		
			var baiduBsSuggest = $("#debtorname").bsSuggest({
				showBtn: false,
				allowNoKeyword: false,   //是否允许无关键字时请求数据。为 false 则无输入时不执行过滤请求
		        multiWord: true,         //以分隔符号分割的多关键字支持
		        separator: ",",          //多关键字支持时的分隔符，默认为空格
		        getDataMethod: "url",    //获取数据的方式，总是从 URL 获取
		        url: 'http://unionsug.baidu.com/su?p=3&t='+ (new Date()).getTime() +'&wd=', /*优先从url ajax 请求 json 帮助数据，注意最后一个参数为关键字请求参数*/
		        jsonp: 'cb',                      //如果从 url 获取数据，并且需要跨域，则该参数必须设置
		        processData: function (json) {    // url 获取数据时，对数据的处理，作为 getData 的回调函数
		            var i, len, data = {value: []};
		            if (!json || !json.s || json.s.length === 0) {
		                return false;
		            }
	
		            len = json.s.length;
	
		            jsonStr = "{'value':[";
		            for (i = 0; i < len; i++) {
		                data.value.push({
		                    word: json.s[i]
		                });
		            }
		            data.defaults = 'baidu';
	
		            //字符串转化为 js 对象
		            return data;
		        }
		    }).on('onDataRequestSuccess', function (e, result) {
		        console.log('onDataRequestSuccess: ', result);
		    }).on('onSetSelectValue', function (e, keyword, data) {
		        console.log('onSetSelectValue: ', keyword, data);
		    }).on('onUnsetSelectValue', function (e) {
		        console.log("onUnsetSelectValue");
		    });
		
			function sysClaimCompanyAdd(){
				if($("#sysClaimCompanyAddForm").validationEngine("validate")){
					var result = true;
					var checkRoles = $(".label-success").find(".roleId");
					var roleids = "";
					for (var i = 0; i < checkRoles.length; i++) {
						roleids += $(checkRoles[i]).val() + ",";
					}
					$("#roleids").val(roleids);
					$.ajax({
				        type: "POST",
				        dataType: "json",
				        async: false,
				        url: '${ctx}/sysClaimCompany/add',
				        data: $('#sysClaimCompanyAddForm').serialize(),
				        success: function (data) {
				        	if(data!=undefined&&data.status!=undefined&&data.status.trim()=='0'){
				        		result = true;
				        		alert("保存成功！");
				        		window.location.href = $("#userinfo_list_href").attr("href");
				        	}else{
					        	result = false;
				        		alert(data.error);
				        	}
				        },
				        error: function(data) {
				        	result = false;
			        		alert(data.data);
				         }
				    });
					return result;
				}
			}
			
			//$("#sysClaimCompanyAddForm").validationEngine();
		</script>
	</body>
</html>