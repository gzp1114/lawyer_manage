
(function($){
    $.fn.validationEngineLanguage = function(){
    };
    $.validationEngineLanguage = {
        newLang: function(){
            $.validationEngineLanguage.allRules = {
                "required": { // Add your regex rules here, you can take telephone as an example
					"regex":"none",
					"alertText":"* 此栏为必填项",
					"alertTextCheckboxMultiple":"· 请选择一个选项",
					"alertTextCheckboxe":"· 此选择框为必选"
                },
            	"dateRange":{
					"regex":"none",
					"alertText":"* 无效的 ",
					"alertText2":" 时间范围"
				},
				"dateTimeRange":{
					"regex":"none",
					"alertText":"* 无效的 ",
					"alertText2":" 时间范围"
				},
                "minSize": {
                    "regex": "none",
                    "alertText": "· 输入不可少于 ",
                    "alertText2": " 个字符"
                },
                "maxSize": {
                    "regex": "none",
                    "alertText": "· 输入不可超过 ",
                    "alertText2": " 个字符"
                },
                "minByteSize": {
                    "regex": "none",
                    "alertText": "· 输入不可少于 ",
                    "alertText2": " 个字节"
                },
                "maxByteSize": {
                    "regex": "none",
                    "alertText": "· 输入不可超过 ",
                    "alertText2": " 个字节"
                },
                "min": {
                    "regex": "none",
                    "alertText": "· 最小值为 "
                },
                "max": {
                    "regex": "none",
                    "alertText": "· 最大值为 "
                },
                "past": {
                    "regex": "none",
                    "alertText": "· 日期不可后于 "
                },
                "future": {
                    "regex": "none",
                    "alertText": "· 日期不可早于 "
                },	
                "maxCheckbox": {
						"regex":"none",
						"alertText":"· 复选框超过最大可选数"
                },
                "minCheckbox": {
						"regex":"none",
						"alertText":"· 请至少选择 ",
						"alertText2":" 项"
				},
                "equals": {
                    "regex": "none",
                    "alertText": "· 两次输入的密码不同"
                },
                "phone": {
                    // credit: jquery.h5validate.js / orefalo
                    "regex": /^([\+][0-9]{1,3}[ \.\-])?([\(]{1}[0-9]{2,6}[\)])?([0-9 \.\-\/]{3,20})((x|ext|extension)[ ]?[0-9]{1,4})?$/,
					"alertText":"· 电话号码格式有误"
                },
				"mobilenumber":{
					"regex":/^1[34578]\d{9}$|^1060[1-9]\d{1,2}\d{7,8}$/,
//					"regex":/^\d{11}$/,
					"alertText":"· 手机号码格式有误"
				},
				"mobilenumber2":{
					"regex":/^\d{11,11}$/,
					"alertText":"· 手机号码必须是11位数字！"
				},
                "email": {
                    // Simplified, was not working in the Iphone browser
                    "regex": /^([A-Za-z0-9_\-\.\'])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,6})$/,
					"alertText":"· 电子邮件地址格式有误"
                },
                "integer": {
                    "regex": /^[\-\+]?\d+$/,
                    "alertText": "· 数字格式有误"
                },
                "number": {
                    // Number, including positive, negative, and floating decimal. credit: orefalo
                    "regex": /^[\-\+]?(([0-9]+)([\.,]([0-9]+))?|([\.,]([0-9]+))?)$/g,
                    "alertText": "· 数字格式有误"
                },
                "date": {
                    // Date in ISO format. Credit: bassistance
                    "regex": /^\d{4}[\/\-]\d{1,2}[\/\-]\d{1,2}$/,
                         "alertText":"· 日期格式有误，日期格式为：YYYY-MM-DD"
                },
                "ipv4": {
                    "regex": /^([1-9][0-9]{0,2})+\.([1-9][0-9]{0,2})+\.([1-9][0-9]{0,2})+\.([1-9][0-9]{0,2})+$/,
                    "alertText": "· IP地址格式有误"
                },
                "url": {
                    "regex": /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\·\+,;=]|:)·@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])·([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])·([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d·)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\·\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\·\+,;=]|:|@)·)·)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\·\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)·)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\·\+,;=]|:|@)|\/|\?)·)?$/,
                    "alertText": "· URL格式有误"
                },
                "url1": {
                    "regex": /^(https?):\/\/.*$/,
                    "alertText": "· URL格式有误"
                },
                "url2": {
                    "regex": /^(http?|https):\/\/.*$/,
                    "alertText": "· URL格式有误"
                },
                "onlyNumber": {
                	"regex": /^[0-9]+$/,
					"alertText":"· 必须为数字"
                },
                "onlyNumberSp": {
                    "regex": /^[0-9\ ]+$/,
					"alertText":"· 请输入数字"
                },
                "onlyLetterSp": {
                    "regex": /^[a-zA-Z\ \']+$/,
					"alertText":"· 请输入英文字符"
                },
                "onlyHexadecimal": {
                    "regex": /^[0-9A-F]+$/,
                    "alertText": "· 请输入数字或英文大写字母A-F"
                },
                "onlyLetterNumber": {
                    "regex": /^[0-9a-zA-Z]+$/,
                    "alertText": "· 请输入数字或字母"
                },
                "onlyLetterNumberUnderline": {
                    "regex": /^[0-9a-zA-Z\_]+$/,
                    "alertText": "· 请输入数字、字母、下划线"
                },
                "lotteryLimit":{
                	"regex":/^[1-9][0-9]{0,5}$/,
                	"alertText":"· 请输入1-999999的数字"
                },
                "integratenumberLimit":{
                	"regex":/^[1-9][0-9]{0,4}$/,
                	"alertText":"· 请输入1-99999的数字"
                },
                "deliveramountmaxLimit":{
                	"regex":/^[1-9][0-9]{0,3}$/,
                	"alertText":"· 请输入【1-9999】的正整数"
                },
                "deliverytimeLimit":{
                	"regex":/^[1-9][0-9]{0,2}$/,
                	"alertText":"· 传递的时间必须为正整数【1-999】"
                },
                "unitmarkpositive":{
                	"regex":/^[1-9]\d*$/,
                	"alertText":"· 消费限额为正整数"
                },
                "chapterCount":{
                	"regex":/^[1-9][0-9]{0,8}$/,
                	"alertText":"· 请输入1-999999999的数字"
                },
                "onlyImportIntegerNumber": {
                    "regex": /^[1-9]\d{0,5}|0$/,
                    "alertText": "· 上限值仅能为0~999999的整数！"
                },
                "onlyImportIntegerNumber2": {
                    "regex": /^[1-9]\d{0,4}$/,
                    "alertText": "· 上限值仅能为1~10000的整数！"
                },
                "onlyImportIntegerNumber3": {
                    "regex": /^[1-9]{1}[0-9]*|0$/,
                    "alertText": "· 请输入整数"
                },
                "channelidLimit": {
                    "regex": /^([a-zA-Z]|[0-9]){8}$/,
                    "alertText": "· 请输入8位数字或者字母"
                },
                "spCidOnlyNumber":{
                	"regex":/^[1-9]([0-9]{0,})$/,
                	"alertText":". 请输入正整数"
                },
                // --- CUSTOM RULES -- Those are specific to the demos, they can be removed or changed to your likings
                "ajaxUserCall": {
                    "url": "ajaxValidateFieldUser",
                    // you may want to pass extra data on the ajax call
                    "extraData": "name=eric",
                    "alertText": "· This user is already taken",
                    "alertTextLoad": "· Validating, please wait"
                },
                "ajaxNameCall": {
                    // remote json service location
                    "url": "ajaxValidateFieldName",
                    // error
                    "alertText": "· This name is already taken",
                    // if you provide an "alertTextOk", it will show as a green prompt when the field validates
                    "alertTextOk": "· This name is available",
                    // speaks by itself
                    "alertTextLoad": "· Validating, please wait"
                },
                "validate2fields": {
                    "alertText": "· Please input HELLO"
                },
                "version" : {
	            	'regex': /^[1-9]\d{0,3}(\.{1}\d{1,4})$/g,
	            	'alertText': '· 版本号格式：1-4位主版本号.1-4位子版本号'
	            },
	            "valMaxPrice" : {
	            	'regex': /^(^(?!(99999999(\.[0-9]{1,2})))(0|([1-9][0-9]{0,7}))(\.[0-9]{1,2})?|99999999.0|99999999.00)$/,
	            	'alertText': '· 请输入0-99999999的数字,精确到小数点后两位'
	            },
	            "borrowtimes" : {
	            	'regex': /^([1-9][0-9]*)$/,
	            	'alertText' : '· 借阅卷请输入1~999999999的整数'
	            },
	            "SeparatedOnlyInEnglish":{
	            	'regex':/^([0-9]+[,]?)$/g,
	            	'alertText':'· 请输入0-99999999的数字,精确到小数点后两位'
	            },	           
	            "serName" : {
	            	'regex' : /^([\u4e00-\u9fa5]|[a-zA-Z0-9])+$/,
	            	'alertText' : '不能输入特殊字符，请重新输入！'
	            },
	            "OnlyFiveNumber":{
	            	'regex': /^[1-5]\d{0}$/,
	            	'alertText':'只能输入1-5'
	            },
	            "twoDecimalPlaces" : {
	            	'regex': /^-?\d+\.?\d{0,2}$/,
	            	'alertText': '· 精确到小数点后两位'
	            }, 
	            "pbTaskPrice" : {
	            	'regex':  /^(^(?!(99999(\.[0-9]{1,2})))(0|([1-9][0-9]{0,6}))(\.[0-9]{1,2})?|9999999.0|9999999.00)$/,
	            	'alertText': '· 请输入0-9999999数字,精确到小数点后两位!'
	            },
	            "portNumber" :{
	            	'regex': /^[0-9]{1,40}$/,
	            	'alertText':'106端口号需为整数'
	            },
	            "communicationKey":{
	            	'regex' : /^([\x00-\xff])*$/,
	                'alertText' :'接口通讯密钥不能带有中文字符'
	            },
	            "enterpriseServerIP":{
	            	'regex' : /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/,
	                'alertText' :'请输入正确的IP地址'
	            },
	            "phoneNumber":{
	            	"regex":/^1\d{10}$/,
	            	"alertText":"手机号码必须为1开头的11位数字"
	            },
	            "monthNum2":{
	            	"regex":/^[1-9][0-9]{0,1}|^[1-9]$/,
	            	"alertText":"请输入1~99的整数"
	            }
            };
        }
    };
    $.validationEngineLanguage.newLang();
})(jQuery);


    
