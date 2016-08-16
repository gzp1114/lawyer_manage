;(function($) {
    $.fn.downSelect=function(options){
        var defaults={
            width:200,
            height:100
        }
        // 将defaults 和 options 参数合并到{}
        var opts = $.extend({},defaults,options);

//        创建select内容
        return this.each(function(){
           var obj= $(this);
           var parent=$("<div>").addClass("downSelect_cinema_select mr_10 mt_10");
           var tmp='<div class="downSelect_cinema_type">\n\
                        <input type="text" readonly="" value="" name="'+obj.attr("data-name")+'" class="cinema_up">\n\
                    </div>\n\
                    <div class="downSelect_cinema_drop hidden">\n\
                        <div class="downSelect_cinema_menu">';
                            obj.find("option").each(function(){
                                tmp+='<span class="">'+$(this).html()+'</span>';
                            });
                tmp+='</div></div>';
                obj.after(parent.append(tmp));
                parent.append(obj);

//            获取select后面div的值给input
//            var input_val=parent.next().html();
//            parent.find(".cinema_type input").val(defaults.name);

//            鼠标滑过和滑出select
            parent.on({
                mouseover:function(){
                    $(this).find(".downSelect_cinema_type").css({"border-color":"#6699ff","background-color":"#fff"});


                },
                mouseout:function(){
                    if($(this).hasClass("seat_select")){
                        $(this).find(".downSelect_cinema_type").css({"border-color":"#dfe7f0"});
                    }else{
                        $(this).find(".downSelect_cinema_type").css({"border-color":"#dfe7f0","background-color":"#f6f8fb"});
                    }
                }
            })


//            点击input显示内容
            parent.on({
                click:function(e){
                    e.stopPropagation();
                    $(this).find("input").blur();
                    $(this).find("input").addClass("downSelect_cinema_up");
                    $(this).siblings().addClass("hidden");
                    $(this).next().removeClass("hidden");

//        如果是所属省份select框内容
                    if($(this).find("input").val()=="所属省份"){
                        if(!parent.find(".cinema_point").html()){
                            var cinema_str=$("<div></div>").addClass("cinema_inter movie_inter cinema_feed clearfix cinema_point");
                            parent.find(".downSelect_cinema_drop").prepend($(cinema_str));
                          /*  var cinema_input= $('<input name="" value="" placeholder="请输入关键字" />');
                            parent.find(".cinema_point").append($(cinema_input));*/
                        }


                        $.get("/json/tmp.php",function(data){
                            var cityHtml = "";
                            var cityWord = "";
                            for(var i in data){
                                cityHtml += "<option>"+data[i]+"</option>";
                                cityWord += "<span class=''>"+data[i]+"</span>";
                                parent.find("select").html(cityHtml);
                                parent.find(".downSelect_cinema_menu").html(cityWord);
                            }
                            $(cinema_input).on("keyup",function(){
                                var cityVal = $(this).val();
                                var cityContent="";
                                var citySelect="";
                                for(var arr in data) {
                                    var reCat = new RegExp(cityVal);
                                    var res = reCat.test(data[arr]);
                                    if(res == true){
                                        parent.find(".downSelect_cinema_menu span").css("display","none");
                                        cityContent += "<span class=''>"+data[arr]+"</span>";
                                        citySelect += "<option>"+data[arr]+"</option>";
                                        parent.find("select").html(citySelect);
                                        parent.find(".downSelect_cinema_menu").html(cityContent);
                                    console.log(cityContent)
                                    }

                                }

                            })

                        })

                    }


                },
                mouseout:function(){
                    $(this).css({"border-color":"#dfe7f0"});
                },
                focusin:function(){
                    $(this).find("input").blur();
                }
            },".downSelect_cinema_type")


//            选择select把值赋给input
            parent.on({
                mouseover:function(){
                    $(this).siblings().removeClass("cinema_checked");
                    $(this).addClass("cinema_checked");
                },
                click:function(){
                    $(this).siblings().css({"color":"#637283"});
                    $(this).css({"color":"#6699ff"});
                    var cinema_index=$(this).index();
                    obj.find("option").attr("selected",false);
                    $(this).parents(".downSelect_cinema_select").find("option").eq(cinema_index).attr("selected",true);
                    var cinema_val=$(this).parents(".downSelect_cinema_select").find("option").eq(cinema_index).html();
                    $(this).parents(".downSelect_cinema_drop").prev().find("input").val(cinema_val);
                    $(this).parents(".downSelect_cinema_drop").prev().find("input").removeClass("downSelect_cinema_up");
                    $(this).parents(".downSelect_cinema_drop").prev().css({"border-color":"#dfe7f0"});
                    $(this).parents(".downSelect_cinema_drop").prev().find("input").css({"color":"#637283"});
                    $(this).parents(".downSelect_cinema_drop").addClass("hidden");
                    parent.off("mouseout");
                }
            },".downSelect_cinema_menu span")
        })
    }

})(jQuery);

$(function(){
  //  $(".cinema_val").downSelect();
    $(".cinema_frame").each(function(i){
        var name="";
        switch (i){
            case 0:
                name="所属区域";
                break;
            case 1:
                name="所属省份";
                break;
            case 2:
                name="所属城市";
                break;
            case 3:
                name="影院类型";
                break;
            case 4:
                name="影院状态";
                break;
        }
        $(this).parents(".downSelect_cinema_select").find(".downSelect_cinema_type input").val(name)
    })

$(function(){
    $(".photo-show").each(function(){
        $(this).hover(function(){
            $(this).siblings('.bigger').show();
        },function(){
            $(this).siblings('.bigger').hide();
        })
    })
})


//    影厅类型select
    $(".cinema_drop .cinema_menu span").on({
        mouseover:function(){
            $(".cinema_drop .cinema_menu span").removeClass("cinema_checked");
            $(obj).addClass("cinema_checked");
        }
    })


//    左侧
    $(".manager_tip").on({
        mouseover:function(){
            $(".manager_tip").css({"background-color":"#fff"});
            $(this).css({"background-color":"#f5f8fa"});
        },
        mouseout:function(){
            $(this).css({"background-color":"#fff"});
        },
        click:function(){
            $(".manager_list").addClass("hidden");
            $(this).next().removeClass("hidden");
            $(".manager_tip span").removeClass("manager_down");
            $(this).find("span").addClass("manager_down");
        }

    })




    $(".manager_list a").on({
        mouseover:function(){
            $(".manager_list a").not(".manager_click").removeClass().addClass("manager_default");
            $(this).not(".manager_click").removeClass().addClass("manager_hover");
        },
        click:function(){
            $(".manager_list a").removeClass("manager_click");
            $(".manager_list a").not(".manager_click").removeClass().addClass("manager_default");
            $(this).removeClass().addClass("manager_click");
        }

    })



//    查询按钮状态
    $(".cinema_inquiry, .cinema_update, .movie_export, .movie_return, .price_rule, .price_new, .price_detail, .rule_apply, .advance_button").on({
        mouseover:function(){
            $(this).css({"background-color":"#3380f6"});
        },
        mouseout:function(){
            $(this).css({"background-color":"#5795f5"});
        },
        mousedown:function(){
            $(this).css({"background-color":"#2e62b3","color":"#bebebe"});
        },
        mouseup:function(){
            $(this).css({"background-color":"#5795f5","color":"#fff"});
        }
    })


//    重置按钮状态
    $(".cinema_reset").on({
        mouseover:function(){
            $(this).css({"background-color":"#eaf2fe"});
        },
        mouseout:function(){
            $(this).css({"background-color":"#fff"});
        },
        mousedown:function(){
            $(this).css({"background-color":"#2e62b3","border-color":"#2e62b3","color":"#bdbdbd"});
        },
        mouseup:function(){
            $(this).css({"background-color":"#fff","border-color":"#6699ff","color":"#6699ff"});
        }
    })

//    编辑、上线按钮
    $(".movie_new, .rule_modify").on({
        mouseover:function(){
            $(this).css({"background-color":"#39b56d"});
        },
        mouseout:function(){
            $(this).css({"background-color":"#4bd083"});
        },
        mousedown:function(){
            $(this).css({"background-color":"#218e4f","color":"#bebebe"});
        },
        mouseup:function(){
            $(this).css({"background-color":"#4bd083","color":"#fff"});
        }
    })



//    智能输入框
    $(".cinema_smart").on({
        mouseover:function(){
            $(this).find("input").css({"border-color":"#6699ff"});
        },
        mouseout:function(){
            $(this).find("input").css({"border-color":"#dfe7f0"});
        },
        keydown:function(){
            $(this).find("input").css({"color":"#637283","border-color":"#6699ff"});
        },
        keyup:function(){
            if($(this).find("input").val()=="双井"){
                $(this).find(".cinema_result").removeClass("hidden");
            }
        }
    })


    $(".cinema_result p").on({
        mouseover:function(){
            $(".cinema_result p").css({"background-color":"#fff"});
            $(this).css({"background-color":"#ebf3fe"});
        },
        click:function(){
            $(".cinema_result p").css({"color":"#637283"});
            $(".cinema_result p span").css({"color":"#ff9999"});
            $(this).css({"color":"#6699ff"});
            $(this).find("span").css({"color":"#6699ff"});
            var cinema_html=$(this).html();
            cinema_html=cinema_html.replace(/<span[^>]*>([^<]*)<\/span>/ig,'');
            $(this).parent().addClass("hidden");
            $(this).parents(".cinema_smart").find(".cinema_inter input").val(cinema_html);
        }
    })







    //    普通输入框
    $(".cinema_feed").on({
        mouseover:function(){
            $(this).find("input").css("border-color","#6699ff");
        },
        mouseout:function(){
            $(this).find("input").css("border-color","#dfe7f0");
        },
        keydown:function(){
            $(this).find("input").css({"color":"#637283","border-color":"#6699ff"});
        },
        focusout:function(){
            $(this).find("input").css("border-color","#dfe7f0");
        }
    })

    $(".movie_area").on({
        mouseover:function(){
            $(this).css("border-color","#6699ff");
        },
        mouseout:function(){
            $(this).css("border-color","#dfe7f0");
        },
        keydown:function(){
            $(this).css({"color":"#637283","border-color":"#6699ff"});
        },
        focusout:function(){
            $(this)
                .css("border-color","#dfe7f0");
        }
    })





//    编辑影厅
    $(".cinema_edit").on({
        click:function(){
            $(this).parents("ul").css("display","none");
            $(this).parents("ul").next().css("display","block");
        }
    })

//    取消
    $(".cinema_cancel").on({
        click:function(){
            $(this).parents(".cinema_message").css("display","none");
            $(this).parents(".cinema_message").prev().css("display","block");
        }
    })

//    保存
    $(".cinema_save").on({
        click:function(){
            $(".cinema_success").fadeIn("slow");
        }
    })

//    判断表格基偶不同背景颜色
    $(".cinema_news,.movie_information,.history_tip,.finish_information, .column_list,.column_list1,.column_list2,.tuikuan_list,.dingdan_movie,.dingdan_movie1, .item_list, .price_list, .rule_list, .detail_item, .check_information, .advance_list").each(function(){
        $(".cinema_news:odd, .movie_information:odd, .history_tip:odd, .finish_information:odd, .column_list:odd,.tuikuan_list:odd, .column_list1:odd, .column_list2:odd,.dingdan_movie:odd, .dingdan_movie:odd,.item_list:odd, .price_list:odd, .rule_list:odd, .detail_item:odd, .check_information:odd, .advance_list:odd").css("background-color","#f3f7ff");
    });

    $(".cinema_news,.movie_information,.history_tip,.finish_information, .column_list,.column_list1,.column_list2,.tuikuan_list,.dingdan_movie,.dingdan_movie1, .item_list, .price_list, .rule_list, .detail_item, .check_information, .advance_list").each(function(){
    $(".cinema_news:odd, .movie_information:odd, .history_tip:odd, .finish_information:odd, .column_list:odd, .column_list1:odd,.tuikuan_list:odd, .column_list2:odd, .dingdan_movie1:odd,.dingdan_movie:odd,.item_list:odd, .price_list:odd, .rule_list:odd, .detail_item:odd, .check_information:odd, .advance_list:odd").addClass("oddcss");
});

//    分页
    $(".manager_number a").on({
        mouseover:function(){
            $(this).css("border-color","#6699ff");
            $(".manager_number a").css("border-color","#dfe7f0");
            $(".manager_number a").removeClass("manager_over");
            $(".manager_number a").prev().removeClass("manager_state");
            if($(this).index()!==1){
                $(this).prev().addClass("manager_state");
                $(this).addClass("manager_over");
            }else{
                $(this).addClass("manager_over");
            }
        },
        mouseout:function(){
            $(".manager_number a").removeClass("manager_over");
            $(".manager_number a").prev().removeClass("manager_state");
        },
        click:function(){
            $(".manager_number a").removeClass("manager_click");
            $(".manager_number a").prev().removeClass("manager_state");
            if($(this).index()!==1){
                $(this).prev().addClass("manager_state");
                $(this).addClass("manager_click");
            }else{
                $(this).addClass("manager_click");
            }
            if($(".manager_number span").hasClass("manager_click")){
                $(".manager_number span").removeClass("manager_click");
            }
        }
    })



//    影院列表
    $(".movie_checkbox").on({
        click:function(){
            if($(this).attr("class")=="movie_checkbox"){
                $(this).addClass("movie_check");
            }else{
                $(this).removeClass("movie_check");
            }
        }
    })
    //新建卖品券页面
    $(".xinjian_bg").on({
        click:function(){
            if($(this).attr("class")=="xinjian_bg"){
                $(this).addClass("movie_check1");
            }else{
                $(this).removeClass("movie_check1");
            }
        }
    })


//    规则列表复选框
    $(".rule_checkbox").on({
        click:function(){
            if($(this).attr("class")=="rule_checkbox"){
                $(this).addClass("movie_check");
            }else{
                $(this).removeClass("movie_check");
            }
        }
    })


//    规则详情
    $(".detail_check").on({
        click:function(){
            if($(this).attr("class")=="detail_check"){
                $(this).addClass("movie_check");
            }else{
                $(this).removeClass("movie_check");
            }
        }
    })

//    地图选点 图片详情
    $(".movie_subject img,.movie_operate span,.movie_control input, .seat_cancel, .rule_cancel").on({
        click:function(){
            $(".movie_layer, .seat_custom, .set_detail,.alert_warnning, .movie_shade, .movie_point, .set_main, .rule_point, .rule_movie, .detail_lead, .detail_plot, .advance_tip").addClass("hidden");
        }
    })

    $(".movie_able p, .film_information, .rule_apply, .detail_in, .advance_modify,.bohui, .verify_new ,.cinema_quit").on("click",function(){
        $(".movie_point, .movie_shade, .set_detail, .rule_movie, .detail_lead, .advance_tip").removeClass("hidden");
    })
    //表格里的对号错号
    $(".tuikuan_dui,.zuhu_dui,.tuikuan_cuo,.tuikuan_open,.list_close,.list_mess,.list_delet,.list_open,.maipinquan_edit,.maipinquan_delet").on("click",function(){
        $(".alert_warnning, .movie_shade").removeClass("hidden");
    })
    $(".set_content").on({
        click:function(){
            $(".movie_shade, .set_main").removeClass("hidden");
        }
    })
    $(".xinjian").on({
        click:function(){
            $(".movie_shade,.advance_tip, .set_main").removeClass("hidden");
        }
    })

//    修改价格策略
    $(".detail_revise").on({
        click:function(){
            $(".movie_shade, .detail_plot").removeClass("hidden");
        }
    })



//  多行文本输入框
    $(".set_tip").on({
        mouseover:function(){
            $(".set_tip").css({"background-color":"#e8f0f7","border-color":"#dfe7f0"});
            $(this).css({"background-color":"#d6ebff","border-color":"#6699ff"});
        },
        click:function(){
            $(this).css({"background-color":"#add6ff","border-color":"#6699ff"});
        }
    })



//    提示信息
    $(".set_information").on({
        mouseover:function(){
            $(this).addClass("gallery_current");
            $(this).parents(".movie_title").next().find(".gallery_tip").removeClass("hidden");

        },
        mouseout:function(){
            $(this).removeClass("gallery_current");
            $(this).parents(".movie_title").next().find(".gallery_tip").addClass("hidden");
        }
    })



    $(".gallery_list li").on({
        click:function(){
            $(".gallery_list li").removeClass("gallery_once");
            $(this).addClass("gallery_once");
            var gallery_index=$(this).index();
            $(".news_content").addClass("hidden");
            $(".news_content").eq(gallery_index).removeClass("hidden");
//            点击操作历史上线和下线状态切换
            if($(this).index()==3){
                $(".gallery_title p").addClass("gallery_top").html("已上线");
                $(".gallery_online").css("display","none");
                $(".gallery_down").css("display","block");
            }else{
                $(".gallery_title p").removeClass("gallery_top").html("已下线");
                $(".gallery_down").css("display","none");
                $(".gallery_online").css("display","block");
            }

        }
    })

    $(function () {
        //  即将上映、正在上映切换
        $("#activity_content li").bind("click", function () {
            $("#activity_content li").removeClass("title_current");
            $(this).addClass("title_current");
            $(".list_content").addClass("hidden");
            $($(".list_content").get($(this).index())).removeClass("hidden");
            $(".xinjian_radio").removeClass("movie_check1");
            $($(".xinjian_radio").get($(this).index())).addClass("movie_check1");

        });
        $("#activity_content1 li").bind("click", function () {
            $("#activity_content1 li").removeClass("title_current");
            $(this).addClass("title_current");
            $(".list_content1").addClass("hidden");
            $($(".list_content1").get($(this).index())).removeClass("hidden");
        });
		$("#activity_content2 li").bind("click", function () {
            $("#activity_content2 li").removeClass("title_current");
            $(this).addClass("title_current");
            $(".list_content2").addClass("hidden");
            $($(".list_content2").get($(this).index())).removeClass("hidden");

        });
		$("#activity_content3 li").bind("click", function () {
            $("#activity_content3 li").removeClass("title_current");
            $(this).addClass("title_current");
            $(".list_content3").addClass("hidden");
            $($(".list_content3").get($(this).index())).removeClass("hidden");

        });
		
        $("#quanxian_city li a").bind("click", function () {
            $("#quanxian_city li a").removeClass("duihao");
            $(this).addClass("duihao");
        })
        $("#quanxian_city_yingyuan li a").bind("click", function () {
            $("#quanxian_city_yingyuan li a").removeClass("duihao");
            $(this).addClass("duihao");
        })


        $("#quanxian_city_selected li a").bind("click", function () {
            $("#quanxian_city_selected li a").removeClass("chahao");
            $(this).addClass("chaihao");
        })
        $("#quanxian_city_selected_yingyuan li a").bind("click", function () {
            $("#quanxian_city_selected_yingyuan li a").removeClass("chahao");
            $(this).addClass("chaihao");
        })
    });
    //全选对号
    $("#all_check").bind("click", function () {
        $("#quanxian_city li a").addClass("duihao");
    })
    $("#all_check_yingyuan").bind("click", function () {
        $("#quanxian_city_yingyuan li a").addClass("duihao");
    })
    //全选错号
    $("#all_chacheck1").bind("click", function () {
        $("#quanxian_city_selected li a").addClass("duihao");
    })
    $("#all_chacheck1_yingyuan").bind("click", function () {
        $("#quanxian_city_selected_yingyuan li a").addClass("chahao");
    })
    //左侧对号清空
    $("#dele").bind("click", function () {
        $("#quanxian_city li a").removeClass("duihao");
    })
    $("#dele_yingyuan").bind("click", function () {
        $("#quanxian_city_yingyuan li a").removeClass("duihao");
    })
    //右侧清空
    $("#dele1_yingyuan").bind("click", function () {
        $("#quanxian_city_selected_yingyuan li a").empty();
    })
    $("#dele1_qingkong").bind("click", function () {
        $("#quanxian_city_selected li a").empty();
    })



//    影院地址
    $(".news_picture img").on({
        mouseover:function(){
            $(this).attr("src","../images/news_over.jpg");
        },

        click:function(){
            $(this).attr("src","../images/news_click.jpg");
            $(".movie_shade").removeClass("hidden");
            $(".set_detail").removeClass("hidden");
        },
        mouseout:function(){
            $(this).attr("src","../images/news_icon.jpg");
        }
    })


//    座位图
    $(".seat_picture li span").on({
    /*    mouseover:function(){
            var seat_name=$(this).attr("call");
            if($(this).attr("class")==seat_name){
                $(this).removeClass().addClass(seat_name+"_hover");
            }
        },
        mouseout:function(){
            var seat_name=$(this).attr("call");
            if($(this).attr("class")==seat_name+"_hover"){
                $(this).removeClass().addClass(seat_name);
            }
        },*/
        click:function(){
            var seat_name=$(this).attr("call");
            if($(this).attr("class")==seat_name||$(this).attr("class")==seat_name+"_hover"){
                $(this).removeClass().addClass(seat_name+"_click");
            }else if($(this).attr("class")==seat_name+"_click"){
                var seat_name=$(this).attr("call");
                $(this).removeClass().addClass(seat_name);
            }

        }
    })




//    影片图库浮层
    $(".set_film li").on({
        mouseover:function(){
            $(this).find(".set_content").css({"border-color":"#6699ff"});
            $(this).find(".film_layer").removeClass("hidden");
            $(this).find(".film_icon").removeClass("hidden");
        },
        mouseout:function(){
            $(this).find(".set_content").css({"border-color":"#dfe7f0"});
            $(this).find(".film_layer").addClass("hidden");
            $(this).find(".film_icon").addClass("hidden");
        }
    })


    $(".film_icon img").on({
        mouseover:function(){
            var film_name=$(this).attr("class");
            $(this).attr("src","../images/"+film_name+"_hover.png");
        },
        mouseout:function(){
            var film_name=$(this).attr("class");
            $(this).attr("src","../images/"+film_name+".png");
        },
        click:function(){
            var film_name=$(this).attr("class");
            $(this).attr("src","../images/"+film_name+"_click.png");
        }
    })

//    点击删除
    $(".film_delete").on({
        click:function(){
            $(this).parents("li").find(".set_content img").css("display","none");
            var film_val=$("<span></span>").addClass("set_point").html("点击添加");
            $(this).parents("li").find(".set_content").prepend($(film_val));
        }
    })



    //    选择城市select
    $(".build_select").each(function(){
        var array=new Array();
        var build_val="";
        build_val=$(this).find("select option");
        build_val.each(function(){
            array.push($(this).html());
        })
        var build_area = '<div class="build_menu">';
        for(var h=0; h<array.length; h++){
            build_area += '<span>'+array[h]+'</span>';
        }
        build_area += '</div>';
        $(this).append(build_area);
        if(!$(this).prev(".build_search").html()){
            $(this).find(".build_menu").css({"height":"221px","margin-top":"10px"});
        }
        $(this).parents(".rule_movie").find(".build_menu:eq(0)").css({"height":"180px"});
        $(this).parents(".rule_movie").find(".build_menu:eq(1)").css({"height":"180px"});
    })



//    选择城市
    $(".build_menu span").on({
        mouseover:function(){
            if($(this).hasClass("build_click")){
                $(this).not(".build_click").addClass("build_hover");
            }else{
                $(".build_menu span").removeClass("build_hover");
                $(this).addClass("build_hover");
            }
        },
        mouseout:function(){

            $(this).removeClass("build_hover");
        },
        click:function(){
            $(this).addClass("build_click");
            var build_index=$(this).index();
            if(!$(this).parents(".build_select").prev(".build_search").html()){
                $(this).addClass("rule_error");
            }
            $(".build_select option").attr("selected",false);
            $(this).parents(".build_select").find("option").eq(build_index).attr("selected",true);
            if($(this).hasClass("rule_error")){
               $(".rule_error").click(function(){
                   $(this).remove();
               })
            }
        }
    })



//    全选
    $(".build_all").on({
        click:function(){
            $(this).parents(".build_provinces").find(".build_menu span").addClass("build_click");
            if(!$(this).parents(".build_title").next(".build_search").html()){
                $(this).parents(".build_provinces").find(".build_menu span").addClass("rule_error");
            }
        }
    })

//    清空
    $(".build_empty").on({
        click:function(){
            $(this).parents(".build_provinces").find(".build_menu span").removeClass("build_click");
            if(!$(this).parents(".build_title").next(".build_search").html()){
                $(this).parents(".build_provinces").find(".build_menu span").removeClass("rule_error");
            }
        }
    })


//   搜索框
    $(".build_button").on({
        mouseover:function(){
            $(this).css({"background-image":"url('../images/build_hover.jpg')"});
        },
        click:function(){
            $(this).css({"background-image":"url('../images/build_click.jpg')"});
        }
    })


//    座位图自定义颜色
    colorData = [
        {
            "id":"seat_radio1",                //模块id
            "label":"座位区域1",               //显示内容
            'backgroundColor':"#61aefd",    //颜色
            "borderColor":"#1f7ffd"         //描边颜色
        },
        {
            'id': "seat_radio2",
            'label':"座位区域2",
            'backgroundColor': "#a0f6e2",
            'borderColor': "#1cd1a6"
        },
        {
            'id': "seat_radio3",
            'label':"座位区域3",
            'backgroundColor': "#feb83e",
            'borderColor': "#f28c04"
        },
        {
            'id': "seat_radio4",
            'label':"座位区域4",
            'backgroundColor': "#ff999a",
            'borderColor': "#ff6666"
        },
        {
            'id': "seat_radio5",
            'label':"座位区域5",
            'backgroundColor': "#c7b7fd",
            'borderColor': "#9c72ea"
        },
        {
            'id': "seat_radio6",
            'label':"座位区域6",
            'backgroundColor': "#ffea58",
            'borderColor': "#f2d303"
        },
        {
            'id': "seat_radio7",
            'label':"座位区域7",
            'backgroundColor': "#c1e947",
            'borderColor': "#99cc00"
        },
        {
            'id': "seat_radio8",
            'label':"座位区域8",
            'backgroundColor': "#91f3ff",
            'borderColor': "#30dff4"
        },
        {
            'id': "seat_radio9",
            'label':"座位区域9",
            'backgroundColor': "#ffa365",
            'borderColor': "#ff6633"
        },
        {
            'id': "seat_radio10",
            'label':"座位区域10",
            'backgroundColor': "#f8e5fe",
            'borderColor': "#f1baf3"
        }

    ];

    var str = "";
    for(var j=0;j<colorData.length;j++){
        str += '<li>'+
                '<input type="radio" class="hidden" id="'+colorData[j].id+'" value="" name="radio" checked="checked">'+
                '<label for="'+colorData[j].id+'" >'+
                '<p>'+colorData[j].label+'</p>'+
                '<span style="background-color:'+colorData[j].backgroundColor+';border-color:'+colorData[j].borderColor+'"></span>'+
                '</label>'+
                '</li>';
    }
    $("<ul></ul>").addClass("seat_show clearfix").html(str).insertAfter(".seat_point");


    //    选中radio
    $(".seat_show li label").on({
        click:function(){
            $(".seat_show li label").removeClass("seat_current");
            $(this).addClass("seat_current");
            $(this).prev().attr("checked",true);
        }
    })


    $(".build_radio label, .build_date label, .detail_radio label, .detail_date label, .detail_limit label").on({
        click:function(){
            $(".build_radio label, .build_date label, .detail_radio label, .detail_date label, .detail_limit label").removeClass("seat_current");
            $(this).addClass("seat_current");
            $(this).prev().attr("checked",true);
        }
    })



    $(".seat_define").on({
        click:function(){
            $(".movie_shade").removeClass("hidden");
            $(".seat_custom").removeClass("hidden");
        }
    })


//    规则列表点击删除按钮
    $(".rule_delete").on({
        click:function(){
            $(".rule_point, .movie_shade").removeClass("hidden");
        }

    })


//    提示信息展开
    $(".rule_down").on({
        click:function(){
            if($(this).attr("class")=="rule_down"){
                $(this).addClass("rule_up");
                $(this).parents(".rule_default").next().removeClass("hidden");
            }else{
                $(this).removeClass("rule_up");
                $(this).parents(".rule_default").next().addClass("hidden");
            }
        }
    })



//    发布按钮
    $(".advance_issue").on({
        mouseover:function(){
            $(this).css({"background-color":"#dbf6e6"});
        },
        mouseout:function(){
            $(this).css({"background-color":"#fff"});
        },
        mousedown:function(){
            $(this).css({"background-color":"#218e4f","color":"#bebebe"});
        },
        mouseup:function(){
            $(this).css({"background-color":"#fff","color":"#4bd083"});
        }
    })
//蓝绿色按钮
    $(".kaquan_green").on({
        mouseover:function(){
            $(this).css({"background-color":"#90dbdb"});
        },
        mouseout:function(){
            $(this).css({"background-color":"#90dbdb"});
        },
        mousedown:function(){
            $(this).css({"background-color":"#009999"});
        },
        mouseup:function(){
            $(this).css({"background-color":"#009999"});
        }
    })

    //    删除按钮
    $(".advance_delete, .rule_delete").on({
        mouseover:function(){
            $(this).css({"background-color":"#ffebeb"});
        },
        mouseout:function(){
            $(this).css({"background-color":"#fff"});
        },
        mousedown:function(){
            $(this).css({"background-color":"#cc6666","color":"#bebebe"});
        },
        mouseup:function(){
            $(this).css({"background-color":"#fff","color":"#ff9999"});
        }
    })
    //退券
    $(".movie_export_green").on({
        mouseover:function(){
            $(this).css({"background-color":"#ffcccc"});
        },
        mouseout:function(){
            $(this).css({"background-color":"#ffcccc"});
        },
        mousedown:function(){
            $(this).css({"background-color":"#cc6666"});
        },
        mouseup:function(){
            $(this).css({"background-color":"#cc6666"});
        }
    })
//恢复使用
    $(".kaquan_huifu").on({
        mouseover:function(){
            $(this).css({"background-color":"#56799b"});
        },
        mouseout:function(){
            $(this).css({"background-color":"#56799b"});
        },
        mousedown:function(){
            $(this).css({"background-color":"#22303e"});
        },
        mouseup:function(){
            $(this).css({"background-color":"#22303e"});
        }
    })
    $(".advance_delete").on({
        click:function(){
            $(".movie_shade, .movie_layer").removeClass("hidden");
        }
    })
//蓝色按钮
    $(".cinema_use").on({
        mouseover:function(){
            $(this).css({"background-color":"#a3c2ff"});
        },
        mouseout:function(){
            $(this).css({"background-color":"#a3c2ff"});
        },
        mousedown:function(){
            $(this).css({"background-color":"#3366ff"});
        },
        mouseup:function(){
            $(this).css({"background-color":"#3366ff"});
        }
    })
    $(".advance_delete").on({
        click:function(){
            $(".movie_shade, .movie_layer").removeClass("hidden");
        }
    })

//    排期审核
    $(".verify_detail").on({
        mouseover:function(){
            $(".verify_detail").css({"background-color":"#fff"});
            $(this).css({"background-color":"#f3f7ff"});
        },
        mousedown:function(){
            $(this).css({"background-color":"#f3f7ff"});
            $(".verify_detail").removeClass("verify_tip");
            $(this).addClass("verify_tip");
            $(".verify_detail .verify_point").addClass("hidden");
            $(this).find(".verify_point").removeClass("hidden");
        },
        mouseup:function(){
            $(this).css({"background-color":"#fff"});
        }
    })


//    规则详情页面增加按钮
    $(".detail_add").on({
        click:function(){
            var detail_val=$(this).prev().find("input").val();
            var detail_str="<p>"+"<span>"+"</span>"+detail_val+"</p>"
            $(detail_str).appendTo($(".detail_append"));
        }
    })


//    增加内容点击删除
    $(".detail_append p span").on({
        click:function(){
            $(this).parent("p").remove();
        }
    })

})

window.onload=function(){

//    影院id
    var cinema_number=$(".cinema_value").html();
    if(cinema_number){
        cinema_number = cinema_number.replace(/(\d{4})/g,'$1 ').replace(/\s*$/,'');
        var cinema_length="";
        $(".cinema_value").html(cinema_number);
    }

    var shade_new=$(document).height();
    $(".movie_shade").height(shade_new-61);


//    影院全称

    $(".cinema_word").each(function(){
        cinema_length=$(this).html().length;
        if(cinema_length>20){
            $(this).removeClass("cinema_50").addClass("cinema_line");
        }
    })

//    计算右侧选择条件宽度
    var top_width=$(".manager_right").width();
    var top_button=$(".cinema_button").width();
    $(".cinema_condition").width(top_width-top_button-40);


}

window.onresize=function(){
    //    计算右侧选择条件宽度
    var top_width=$(".manager_right").width();
    var top_button=$(".cinema_button").width();
    $(".cinema_condition").width(top_width-top_button-40);
}


