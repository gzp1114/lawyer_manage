/// <reference path="jquery-1.7.1.js" />

$(function () {
    $(".select_box .select_showbox").click(function () {
        var input = $(this);
        var ul = input.next("ul");
        //woaowoca
        var $select  = input.parent().parent().find("select"); //根据dom把select找到去遍历

        if (ul.is(":hidden")) {//判断是否隐藏
            if (ul.height() > 150) {//根据你要显示的li的个数确定
                ul.css({ height: "150px", "overflow-y": "scroll" });
            };
            ul.fadeIn("100");
            ul.hover(function () { }, function () { ul.fadeOut("100"); });
            ul.children("li").click(function () {
                input.val($(this).text());
                ul.fadeOut("100");
         //li赋值到select
                var val = $(this).text();
                $select.find("option").removeAttr("selected")
                $select.find("option").each(function(){
                    if(val==$(this).text()){
                        $(this).attr("selected","selected");
                    }
                })
                //li赋值到select
            }).hover(function () { $(this).addClass("hover"); }, function () {
                $(this).removeClass("hover");
            });
        }
        else {
            ul.fadeOut("fast");
        }
    });
});