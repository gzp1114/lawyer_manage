
/**
 * Calendar
 * @param   beginYear           1990
 * @param   endYear             2010
 * @param   language            0(zh_cn)|1(en_us)|2(en_en)|3(zh_tw)
 * @param   patternDelimiter    "-"
 * @param   date2StringPattern  "yyyy-MM-dd"
 * @param   string2DatePattern  "ymd"
 * @version 1.0 build 2006-04-01
 * @version 1.1 build 2006-12-17
 * @author  KimSoft (jinqinghua [at] gmail.com)
 * NOTE!    you can use it free, but keep the copyright please
 * IMPORTANT:you must include this script file inner html body elment 
 */


function Calendar(beginYear, endYear, language, patternDelimiter, date2StringPattern, string2DatePattern) {
	this.beginYear = beginYear || 2010;
	this.endYear   = endYear   || 2030;
	this.language  = language  || 0;
	this.patternDelimiter = patternDelimiter     || "-";
	this.date2StringPattern = date2StringPattern || Calendar.language["date2StringPattern"][this.language].replace(/\-/g, this.patternDelimiter);
	this.string2DatePattern = string2DatePattern || Calendar.language["string2DatePattern"][this.language];
	
	this.dateControl = null;
	this.panel  = this.getElementById("__calendarPanel");
	this.iframe = window.frames["__calendarIframe"];
	this.form   = null;
	
	this.date = new Date();
	this.year = this.date.getFullYear();
	this.month = this.date.getMonth();
	
	this.colors = {"bg_cur_day":"#e8f0ff","bg_over":"#fff","bg_out":"#e8f0ff"}
};

Calendar.language = {
	"year"   : ["\u5e74", "", "", "\u5e74"],
	"months" : [
				["\u4e00\u6708","\u4e8c\u6708","\u4e09\u6708","\u56db\u6708","\u4e94\u6708","\u516d\u6708","\u4e03\u6708","\u516b\u6708","\u4e5d\u6708","\u5341\u6708","\u5341\u4e00\u6708","\u5341\u4e8c\u6708"],
				["JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"],
				["JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"],
				["\u4e00\u6708","\u4e8c\u6708","\u4e09\u6708","\u56db\u6708","\u4e94\u6708","\u516d\u6708","\u4e03\u6708","\u516b\u6708","\u4e5d\u6708","\u5341\u6708","\u5341\u4e00\u6708","\u5341\u4e8c\u6708"]
				],
	"weeks"  : [["\u65e5","\u4e00","\u4e8c","\u4e09","\u56db","\u4e94","\u516d"],
				["Sun","Mon","Tur","Wed","Thu","Fri","Sat"],
				["Sun","Mon","Tur","Wed","Thu","Fri","Sat"],
				["\u65e5","\u4e00","\u4e8c","\u4e09","\u56db","\u4e94","\u516d"]
		],
	"clear"  : ["\u6e05\u7a7a", "Clear", "Clear", "\u6e05\u7a7a"],
	"today"  : ["\u4eca\u5929", "Today", "Today", "\u4eca\u5929"],
    "last"  : ["\u6628\u5929", "Last", "Last", "\u6628\u5929"],
    "before"  : ["\u524d\u5929", "Before", "Before", "\u524d\u5929"],
    "true":["\u786e\u5b9a","True","True","\u786e\u5b9a"],
	"close"  : ["\u5173\u95ed", "Close", "Close", "\u95dc\u9589"],
	"date2StringPattern" : ["yyyy-MM-dd", "yyyy-MM-dd", "yyyy-MM-dd", "yyyy-MM-dd"],
	"string2DatePattern" : ["ymd","ymd", "ymd", "ymd"]
};

Calendar.prototype.draw = function() {
	calendar = this;
	
	var _cs = [];
	_cs[_cs.length] = '<form id="__calendarForm" name="__calendarForm" method="post">';
	_cs[_cs.length] = '<table id="__calendarTable" width="100%" border="0" cellpadding="0" cellspacing="0" align="center">';
    _cs[_cs.length] = ' <tr>';
    _cs[_cs.length] = '  <th colspan="7"><input type="button" class="e" name="beforeTodayButton" id="beforeTodayButton" \/><input type="button" class="e" name="lastTodayButton" id="lastTodayButton" \/><input type="button" class="f" name="selectTodayButton" id="selectTodayButton" \/><\/th>';
    _cs[_cs.length] = ' <\/tr>';
	_cs[_cs.length] = ' <tr>';
	_cs[_cs.length] = '  <th><input class="l" name="goPrevMonthButton" type="button" id="goPrevMonthButton" value="" \/><\/th>';
	_cs[_cs.length] = '  <th colspan="5"><select class="year" name="yearSelect" id="yearSelect"><\/select><select class="month" name="monthSelect" id="monthSelect"><\/select><\/th>';
	_cs[_cs.length] = '  <th><input class="r" name="goNextMonthButton" type="button" id="goNextMonthButton" value="" \/><\/th>';
	_cs[_cs.length] = ' <\/tr>';
	_cs[_cs.length] = ' <tr>';
	for(var i = 0; i < 7; i++) {
		_cs[_cs.length] = '<th class="theader">';
		_cs[_cs.length] = Calendar.language["weeks"][this.language][i];
		_cs[_cs.length] = '<\/th>';	
	}
	_cs[_cs.length] = '<\/tr>';
	for(var i = 0; i < 6; i++){
		_cs[_cs.length] = '<tr align="center">';
		for(var j = 0; j < 7; j++) {
			switch (j) {
				case 0: _cs[_cs.length] = '<td class="sun" style="width: 43px; height: 30px;">&nbsp;<\/td>'; break;
				case 6: _cs[_cs.length] = '<td class="sat" style="width: 43px; height: 30px;">&nbsp;<\/td>'; break;
				default:_cs[_cs.length] = '<td class="normal" style="width: 43px; height: 30px;">&nbsp;<\/td>'; break;
			}
		}
		_cs[_cs.length] = '<\/tr>';
	}
	_cs[_cs.length] = ' <tr class="time_color">';
	_cs[_cs.length] = '  <th colspan="7"><input type="button" class="b" name="clearButton" id="clearButton" \/><input type="button" class="c" name="trueButton" id="trueButton" \/><input type="button" class="d" name="closeButton" id="closeButton" \/><\/th>';
	_cs[_cs.length] = ' <\/tr>';
	_cs[_cs.length] = '<\/table>';
	_cs[_cs.length] = '<\/form>';
	
	this.iframe.document.body.innerHTML = _cs.join("");
	this.form = this.iframe.document.forms["__calendarForm"];

	this.form.clearButton.value = Calendar.language["clear"][this.language];
//    今天
	this.form.selectTodayButton.value = Calendar.language["today"][this.language];
    //    昨天
    this.form.lastTodayButton.value = Calendar.language["last"][this.language];
    //    前天
    this.form.beforeTodayButton.value = Calendar.language["before"][this.language];
//    确定按钮
    this.form.trueButton.value = Calendar.language["true"][this.language];

	this.form.closeButton.value = Calendar.language["close"][this.language];
	
	this.form.goPrevMonthButton.onclick = function () {calendar.goPrevMonth(this);}
	this.form.goNextMonthButton.onclick = function () {calendar.goNextMonth(this);}
	this.form.yearSelect.onchange = function () {calendar.update(this);}
	this.form.monthSelect.onchange = function () {calendar.update(this);}
	
	this.form.clearButton.onclick = function () {calendar.dateControl.value = "";calendar.hide();}
	this.form.closeButton.onclick = function () {calendar.hide();}
    this.form.beforeTodayButton.onmouseover=function(){ this.style.color="#2e62b3";}
    this.form.beforeTodayButton.onmouseout=function(){ this.style.color="#6699ff";}
    this.form.lastTodayButton.onmouseover=function(){ this.style.color="#2e62b3";}
    this.form.lastTodayButton.onmouseout=function(){ this.style.color="#6699ff";}
    this.form.selectTodayButton.onmouseover=function(){this.style.color="#2e62b3"; }
    this.form.selectTodayButton.onmouseout=function(){
        this.style.backgroundColor = "#fff";
        this.style.color="#6699ff";
    }
//    今天
    this.form.selectTodayButton.onclick = function () {
        var today = new Date();
        calendar.date = today;
        calendar.year = today.getFullYear();
        calendar.month = today.getMonth();
        calendar.dateControl.value = today.format(calendar.date2StringPattern);
        this.style.backgroundColor = "#6699ff";
        this.style.color="#fff";
        calendar.hide();
    }
    //    昨天
    this.form.lastTodayButton.onclick = function () {
        var today = new Date();
        today.setDate(today.getDate()-1);
        calendar.date = today;
        calendar.year = today.getFullYear();
        calendar.month = today.getMonth();
        calendar.dateControl.value = today.format(calendar.date2StringPattern);
        calendar.hide();
    }

//   前天
    this.form.beforeTodayButton.onclick = function () {
        var today = new Date();
        today.setDate(today.getDate()-2);
        calendar.date = today;
        calendar.year = today.getFullYear();
        calendar.month = today.getMonth();
        calendar.dateControl.value = today.format(calendar.date2StringPattern);
        calendar.hide();
    }

};

Calendar.prototype.bindYear = function() {
	var ys = this.form.yearSelect;
	ys.length = 0;
	for (var i = this.beginYear; i <= this.endYear; i++){
		ys.options[ys.length] = new Option(i + Calendar.language["year"][this.language], i);
	}
};

Calendar.prototype.bindMonth = function() {
	var ms = this.form.monthSelect;
	ms.length = 0;
	for (var i = 0; i < 12; i++){
		ms.options[ms.length] = new Option(Calendar.language["months"][this.language][i], i);
	}
};

Calendar.prototype.goPrevMonth = function(e){
	if (this.year == this.beginYear && this.month == 0){return;}
	this.month--;
	if (this.month == -1) {
		this.year--;
		this.month = 11;
	}
	this.date = new Date(this.year, this.month, 1);
	this.changeSelect();
	this.bindData();
};

Calendar.prototype.goNextMonth = function(e){
	if (this.year == this.endYear && this.month == 11){return;}
	this.month++;
	if (this.month == 12) {
		this.year++;
		this.month = 0;
	}
	this.date = new Date(this.year, this.month, 1);
	this.changeSelect();
	this.bindData();
};

Calendar.prototype.changeSelect = function() {
	var ys = this.form.yearSelect;
	var ms = this.form.monthSelect;
	for (var i= 0; i < ys.length; i++){
		if (ys.options[i].value == this.date.getFullYear()){
			ys[i].selected = true;
			break;
		}
	}
	for (var i= 0; i < ms.length; i++){
		if (ms.options[i].value == this.date.getMonth()){
			ms[i].selected = true;
			break;
		}
	}
};

Calendar.prototype.update = function (e){
	this.year  = e.form.yearSelect.options[e.form.yearSelect.selectedIndex].value;
	this.month = e.form.monthSelect.options[e.form.monthSelect.selectedIndex].value;
	this.date = new Date(this.year, this.month, 1);
	this.changeSelect();
	this.bindData();
};

Calendar.prototype.bindData = function () {
	var calendar = this;
	var dateArray = this.getMonthViewDateArray(this.date.getFullYear(), this.date.getMonth());
	var tds = this.getElementsByTagName("td", this.getElementById("__calendarTable", this.iframe.document));
	for(var i = 0; i < tds.length; i++) {
  		tds[i].style.backgroundColor = calendar.colors["bg_over"];
		tds[i].onclick = null;
		tds[i].onmouseover = null;
		tds[i].onmouseout = null;
		tds[i].innerHTML = dateArray[i] || "&nbsp;";
		if (i > dateArray.length - 1) continue;
		if (dateArray[i]){
			tds[i].onclick = function () {
				if (calendar.dateControl){
					calendar.dateControl.value = new Date(calendar.date.getFullYear(),
														calendar.date.getMonth(),
														this.innerHTML).format(calendar.date2StringPattern);
				}
				calendar.hide();
			}
			tds[i].onmouseover = function () {
                this.style.backgroundColor = calendar.colors["bg_out"];
                this.style.color="#6699ff";
            }
			tds[i].onmouseout  = function () {
                this.style.backgroundColor = calendar.colors["bg_over"];
                this.style.color="#333";
            }
			var today = new Date();
			if (today.getFullYear() == calendar.date.getFullYear()) {
				if (today.getMonth() == calendar.date.getMonth()) {
					if (today.getDate() == dateArray[i]) {
						tds[i].style.backgroundColor = calendar.colors["bg_cur_day"];
                        tds[i].style.color="#6699ff";
                        tds[i].style.fontWeight="bold";
						tds[i].onmouseover = function () { this.style.backgroundColor = calendar.colors["bg_out"];}
						tds[i].onmouseout  = function () {this.style.backgroundColor = calendar.colors["bg_cur_day"];}
					}
				}
			}
		}//end if
	}//end for
};

Calendar.prototype.getMonthViewDateArray = function (y, m) {
	var dateArray = new Array(42);
	var dayOfFirstDate = new Date(y, m, 1).getDay();
	var dateCountOfMonth = new Date(y, m + 1, 0).getDate();
	for (var i = 0; i < dateCountOfMonth; i++) {
		dateArray[i + dayOfFirstDate] = i + 1;
	}
	return dateArray;
};

Calendar.prototype.show = function (dateControl, popuControl) {
	if (this.panel.style.visibility == "visible") {
		this.panel.style.visibility = "hidden";
	}
	if (!dateControl){
		throw new Error("arguments[0] is necessary!")
	}
	this.dateControl = dateControl;
	popuControl = popuControl || dateControl;

	this.draw();
	this.bindYear();
	this.bindMonth();
	if (dateControl.value.length > 0){
		this.date  = new Date(dateControl.value.toDate(this.patternDelimiter, this.string2DatePattern));
		this.year  = this.date.getFullYear();
		this.month = this.date.getMonth();
	}
	this.changeSelect();
	this.bindData();

	var xy = this.getAbsPoint(popuControl);
	this.panel.style.left = xy.x + "px";
	this.panel.style.top = (xy.y + dateControl.offsetHeight) + "px";
	this.panel.style.visibility = "visible";
};

Calendar.prototype.hide = function() {
	this.panel.style.visibility = "hidden";
};

Calendar.prototype.getElementById = function(id, object){
	object = object || document;
	return document.getElementById ? object.getElementById(id) : document.all(id);
};

Calendar.prototype.getElementsByTagName = function(tagName, object){
	object = object || document;
	return document.getElementsByTagName ? object.getElementsByTagName(tagName) : document.all.tags(tagName);
};

Calendar.prototype.getAbsPoint = function (e){
	var x = e.offsetLeft;
	var y = e.offsetTop;
	while(e = e.offsetParent){
		x += e.offsetLeft;
		y += e.offsetTop;
	}
	return {"x": x, "y": y};
};

/**
 * @param   d the delimiter
 * @param   p the pattern of your date
 * @author  meizz
 * @author  kimsoft add w+ pattern
 */
Date.prototype.format = function(style) {
	var o = {
		"M+" : this.getMonth() + 1, //month
		"d+" : this.getDate(),      //day
		"h+" : this.getHours(),     //hour
		"m+" : this.getMinutes(),   //minute
		"s+" : this.getSeconds(),   //second
		"w+" : "\u65e5\u4e00\u4e8c\u4e09\u56db\u4e94\u516d".charAt(this.getDay()),   //week
		"q+" : Math.floor((this.getMonth() + 3) / 3),  //quarter
		"S"  : this.getMilliseconds() //millisecond
	}
	if (/(y+)/.test(style)) {
		style = style.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for(var k in o){
		if (new RegExp("("+ k +")").test(style)){
			style = style.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return style;
};

/**
 * @param d the delimiter
 * @param p the pattern of your date
 * @rebuilder kimsoft
 * @version build 2006.12.15
 */
String.prototype.toDate = function(delimiter, pattern) {
	delimiter = delimiter || "-";
	pattern = pattern || "ymd";
	var a = this.split(delimiter);
	var y = parseInt(a[pattern.indexOf("y")], 10);
	//remember to change this next century ;)
	if(y.toString().length <= 2) y += 2000;
	if(isNaN(y)) y = new Date().getFullYear();
	var m = parseInt(a[pattern.indexOf("m")], 10) - 1;
	var d = parseInt(a[pattern.indexOf("d")], 10);
	if(isNaN(d)) d = 1;
	return new Date(y, m, d);
};

document.writeln('<div id="__calendarPanel" style="position:absolute;visibility:hidden;z-index:9999;background-color:#FFFFFF;border:1px solid #666666;width:200px;height:216px;">');
document.writeln('<iframe name="__calendarIframe" id="__calendarIframe" width="100%" height="100%" scrolling="no" frameborder="0" style="margin:0px;"><\/iframe>');
var __ci = window.frames['__calendarIframe'];
__ci.document.writeln('<!DOCTYPE html PUBLIC "-\/\/W3C\/\/DTD XHTML 1.0 Transitional\/\/EN" "http:\/\/www.w3.org\/TR\/xhtml1\/DTD\/xhtml1-transitional.dtd">');
__ci.document.writeln('<html xmlns="http:\/\/www.w3.org\/1999\/xhtml">');
__ci.document.writeln('<head>');
__ci.document.writeln('<meta http-equiv="Content-Type" content="text\/html; charset=utf-8" \/>');
__ci.document.writeln('<title>Web Calendar(UTF-8) Written By KimSoft<\/title>');
__ci.document.writeln('<style type="text\/css">');
__ci.document.writeln('<!--');
__ci.document.writeln('body {font-size:12px;margin:0px;text-align:center;}');
__ci.document.writeln('form {margin:0px;}');
__ci.document.writeln('select {font-size:12px; height:30px; border:1px solid #dfe7f0; margin-bottom:10px;}');
__ci.document.writeln('table {border:0px solid #CCCCCC;background-color:#FFFFFF}');
__ci.document.writeln('th {font-size:12px;font-weight:normal;background-color:#FFFFFF;}');
__ci.document.writeln('th.theader {font-weight:normal;background-color:#f2f5f9;color:#94a7c3;width:24px; height:29px; margin-bottom:10px;}');
__ci.document.writeln('select.year {width:88px; margin-right:5px;}');
__ci.document.writeln('select.month {width:88px;}');
__ci.document.writeln('td {font-size:12px;text-align:center;}');
__ci.document.writeln('td.sat {color:#0000FF;background-color:#EFEFEF;}');
__ci.document.writeln('td.sun {color:#FF0000;background-color:#EFEFEF;}');
__ci.document.writeln('td.normal {background-color:#EFEFEF;}');
__ci.document.writeln('input.l {border: 0;background: url("../images/check_left.png") no-repeat left center; width:7px;height:32px;}');
__ci.document.writeln('input.r {border: 0;background: url("../images/check_right.png") no-repeat left center;width:7px;height:32px;}');
__ci.document.writeln('input.b {border: 1px solid #ff9999;background-color:#fff;width:68px;height:30px; color:#ff9999; margin-right:10px;}');
__ci.document.writeln('input.c {border: 0;background-color:#5795f5;width:70px;height:32px; color:#fff;margin-right:10px;}');
__ci.document.writeln('input.d {border: 1px solid #6699ff;background-color:#fff;width:68px;height:30px; color:#6699ff;}');
__ci.document.writeln('input.e {padding:0 12px; color:#6699ff;background: url("../images/check_line.png") no-repeat right center; line-height:20px; border:0; margin:20px 0;}');
__ci.document.writeln('input.f {padding:0 12px; color:#6699ff; line-height:20px; border:0;margin:20px 0;background-color:#fff;}');
__ci.document.writeln('tr.time_color th{background-color:#f5f8fa; padding:20px 0;}');
__ci.document.writeln('-->');
__ci.document.writeln('<\/style>');
__ci.document.writeln('<\/head>');
__ci.document.writeln('<body>');
__ci.document.writeln('<\/body>');
__ci.document.writeln('<\/html>');
__ci.document.close();
document.writeln('<\/div>');
var calendar = new Calendar();
//-->