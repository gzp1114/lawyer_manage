/// <reference path="jquery-1.7.1.js" />

$(function () {
	if($(".cinema_title a")!=undefined&&$(".cinema_title a").length>0){
		var fl = $(".cinema_title a")[0].innerText; //当前页面左上方显示的一级菜单名称
		var sl = $(".cinema_title a")[1].innerText; //当前页面左上方显示的二级菜单名称
		onclick(fl,sl);
	}
});

function onclick(fl,sl){
    var li_length = $("#menu").find("li").length; //li标签个数
    for(var i = 0;i < li_length;i++){ //遍历li标签
    	if(i % 2 == 0){ //一级菜单
    		var firstLevelText = $("#menu").find("li")[i].firstElementChild.innerText; //一级菜单内容
    	}else{ //二级菜单
    		var secondLevel = $("#menu").find("li")[i].getElementsByTagName("a"); //li中的a标签
    		var a_length = $("#menu").find("li")[i].getElementsByTagName("a").length; //li中的a标签个数
    		for(var a = 0;a < a_length;a++){ //遍历a标签
    			var secondLevelText = secondLevel[a].innerText; //二级菜单内容
    			//如果跳转后的当前页面显示的一级菜单和二级菜单与菜单表中数据相同
    			if(firstLevelText == fl && secondLevelText == sl){
    				//如果打开的不是第一个二级菜单，则收起第一个二级菜单，展开当前菜单
    				$($("#menu").find("li")[i-1]).find("span").addClass("manager_down");
					/*$("li")[1].setAttribute("class","manager_list hidden");*/
    				$("#menu").find("li")[i].setAttribute("class","manager_list");
					/*$("li")[0].firstElementChild.setAttribute("class","");*/
					/*$("li")[i-1].firstElementChild.setAttribute("class","manager_down");*/
    				//该页面的a连接标签变色
    				secondLevel[a].setAttribute("class","manager_click");
    				return;
    			}
    		}
    	}
    }
    
}