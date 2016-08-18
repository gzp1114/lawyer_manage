<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>债权信息管理</title>
	    <style type="text/css">
			.form_textarea{
			max-width: 400px;
			max-height: 100px;
			min-width: 400px;
			min-height: 100px;
			border: 1px solid #DFE7F0;
		}
	    </style>
	    <link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/bootstrap.min.css"></link>
		<link type="text/css" rel="stylesheet" href="${ctx}/static/tools/jquery-validationEngine/validationEngine.jquery.css"></link>
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
								<input id="debtorname" type="text" name="debtorname" value="" class="validate[required,maxSize[100]]" maxlength="100" /><span>（输入可查询）</span>
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
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">组织结构代码：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="organizationCode" type="text" name="organizationCode" value="" maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">注册号：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="registerNumber" type="text" name="registerNumber" value=""  maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">公司类型：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="type" type="text" name="type" value="" maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">法人：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="corporateName" type="text" name="corporateName" value="" maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">法人身份证号：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="corporateCardnum" type="text" name="corporateCardnum" value="" maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">成立日期：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="establishDate" type="text" name="establishDate" value="" onclick="laydate()" maxlength="20" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">注册资金：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="registerCapital" type="text" name="registerCapital" value=""  maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">营业开始日期：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="businessStartDate" type="text" name="businessStartDate" value="" onclick="laydate()" maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">营业结束日期：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="businessEndDate" type="text" name="businessEndDate" value="" onclick="laydate()"  maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">登记机关：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="registrationAuthority" type="text" name="registrationAuthority" value=""  maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">公司地址：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="address" type="text" name="address" value=""  maxlength="50" />
							</div>
						</dd>
					</dl>
					<div class="clearfix"></div>
					<dl class="movie_fill">
						<dt class="movie_word clearfix">
							<p style="background-image: none;">经营状态：</p>
						</dt>
						<dd class="mleft_99">
							<div class="movie_type cinema_feed">
								<input id="operateStatus" type="text" name="operateStatus" value=""  maxlength="50" />
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
		
		<script type="text/javascript" src="${ctx}/static/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="${ctx}/static/bootstrap/bootstrap-suggest.js"></script>
		<script type="text/javascript" src="${ctx}/static/tools/jquery-validationEngine/jquery.validationEngine.js"></script>
		<script type="text/javascript" src="${ctx}/static/tools/jquery-validationEngine/jquery.validationEngine-cn.js"></script>
		<script type="text/javascript" src="${ctx}/static/tools/laydate/laydate.js"></script>
		<script type="text/javascript">
		
			var baiduBsSuggest = $("#debtorname").bsSuggest({
				indexId: 2,             //data.value 的第几个数据，作为input输入框的内容
		        indexKey: 1,            //data.value 的第几个数据，作为input输入框的内容
				showBtn: false,
				allowNoKeyword: false,   //是否允许无关键字时请求数据。为 false 则无输入时不执行过滤请求
		        multiWord: true,         //以分隔符号分割的多关键字支持
		        separator: ",",          //多关键字支持时的分隔符，默认为空格
		        getDataMethod: "url",    //获取数据的方式，总是从 URL 获取
		        url: '${ctx}/sysDebtorCompany/select?name=', /*优先从url ajax 请求 json 帮助数据，注意最后一个参数为关键字请求参数*/
		        //jsonp: 'callback',                      //如果从 url 获取数据，并且需要跨域，则该参数必须设置
		        processData: function (json) {    // url 获取数据时，对数据的处理，作为 getData 的回调函数
		        	var i, len, data = {value: []};
		            if (!json || !json.data || json.data.length === 0) {
		                return false;
		            }
	
		            len = json.data.length;
		            jsonStr = "{'value':[";
		            for (i = 0; i < len; i++) {
		            	data.value.push({
		                    "Id": json.data[i].id,
		                    "name": json.data[i].name
		                });
		            }
		            data.defaults = 'debtor';
	
		            //字符串转化为 js 对象
		            return data;
		        }
		    }).on('onDataRequestSuccess', function (e, result) {
		        //console.log('onDataRequestSuccess: ', result);
		    }).on('onSetSelectValue', function (e, keyword, data) {
		        //console.log('onSetSelectValue: ', keyword, data);
		        $('#debtorid').val(data.Id);
		    }).on('onUnsetSelectValue', function (e) {
		        //console.log("onUnsetSelectValue");
		    });
		
			function sysClaimCompanyAdd(){
				if($("#sysClaimCompanyAddForm").validationEngine("validate")){
					var checkRoles = $(".label-success").find(".roleId");
					var roleids = "";
					for (var i = 0; i < checkRoles.length; i++) {
						roleids += $(checkRoles[i]).val() + ",";
					}
					$.ajax({
				        type: "POST",
				        dataType: "json",
				        async: false,
				        url: '${ctx}/sysClaimCompany/add',
				        data: $('#sysClaimCompanyAddForm').serialize(),
				        success: function (data) {
				        	if(data!=undefined&&data.status!=undefined&&data.status.trim()=='0'){
				        		alert("保存成功！");
				        		window.location.href = $("#userinfo_list_href").attr("href");
				        	}else{
				        		alert(data.error);
				        	}
				        },
				        error: function(data) {
			        		alert(data.error);
				         }
				    });
					return false;
				}
			};
			
			$("#sysClaimCompanyAddForm").validationEngine();
		</script>
	</body>
</html>