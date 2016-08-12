//分页
$(function () { 
	//初始化数据
    var totalRecordNum = $("#records").val();    							      //总记录条数
	var pageSize = $("#pageSize").val();										      //每页记录条数
	var totalPageNumber = Math.ceil(totalRecordNum/pageSize); 	 //总页数
	var pageCurrent = parseInt($("#pageCurrent").val());					 //当前页 
	toPage(totalPageNumber,pageCurrent);
	//下一页按钮
	nextPage(totalPageNumber)

	//上一页按钮
	prevPage(totalPageNumber)

	//根据指定页数跳转
	$(document).on("click",".manager_true",function(){
		toPage(totalPageNumber,parseInt($("#pagenum").val()),"changePage");
	});
	//单击切换分页
	$(document).on("click",".pageManager",function(){
	    $(this).parent().each(function () {//移除其余非点中状态
	         $('.manager_number a').removeClass("manager_click2");
	     });
	     $(this).addClass("manager_click2");//给所点中的增加样式
	     
	     pageCurrent =parseInt($(this).text());

		toPage(totalPageNumber,pageCurrent,"changePage");
	});	
})

/**
 * 下一页功能
 * @param totalPageNumber	总页数
 */
function nextPage(totalPageNumber){
	$(document).on("click",".manager_next",function(){
		console.info("aa");
		currentPageNumber = $(".manager_click2").text();
		var nextPage = parseInt(currentPageNumber)+1;
		var nextPageContext = $(".manager_click2").next("a").text();
		if(nextPageContext=="…"){
			$(".manager_number").empty();
			$(".manager_number").append("<a href='#' class='manager_up' ></a>");		
			$(".manager_number").append("<a href='#' class='pageManager' id='row1'>1</a>");
			$(".manager_number").append("<a href='#'  flag='before'>…</a>");
//			//如果总页数-当前页<5；显示5条数据,否则显示3条数据
			if(totalPageNumber-nextPage<5){
				for(var j=4-(totalPageNumber-nextPage);j>0;j--){
					$(".manager_number").append("<a href='#' class='pageManager' id="+(nextPage-j)+">"+parseInt(nextPage-j)+"</a>");			
				}
				for(var i=nextPage;i<nextPage+4;i++){
					if(totalPageNumber<=i){
						break;
					}else if(i==nextPage){
						$(".manager_number").append("<a href='#' class='pageManager manager_click2' id='row"+i+"'>"+i+"</a>");		
					}else{
						$(".manager_number").append("<a href='#' class='pageManager' id='row"+i+"'>"+i+"</a>");		
					}
				}
			}else{
				for(var i=nextPage;i<nextPage+3;i++){
					if(i==nextPage){
						$(".manager_number").append("<a href='#' class='pageManager manager_click2' id='row"+i+"'>"+i+"</a>");		
					}else{
						$(".manager_number").append("<a href='#' class='pageManager' id='row"+i+"'>"+i+"</a>");		
					}
				}			
				$(".manager_number").append("<a href='#'  flag='after'>…</a>");
			}
			//给数字为nextPage   加上样式
			$(".manager_number").append("<a href='#' class='pageManager' id='row"+totalPageNumber+"'>"+totalPageNumber+"</a>");			
			$(".manager_number").append("<a href='#' class='manager_next' ></a>");
			JumpPage(nextPage);
		}else if(totalPageNumber==parseInt(currentPageNumber)){
			
		}else{
			$(".manager_click2").next("a").addClass("manager_click2");
			$(".manager_click2").prev("a").removeClass("manager_click2");			
			JumpPage(nextPage);
		}
	});	
}
/**
 * 上一页
 * @param totalPageNumber	总页数
 */
function prevPage(totalPageNumber){
	$(document).on("click",".manager_up",function(){
		currentPageNumber = $(".manager_click2").text();
		var prevPage = parseInt(currentPageNumber)-1;
		var prevPageContext = $(".manager_click2").prev("a").text();
		if(prevPageContext=="…"){
			$(".manager_number").empty();
			$(".manager_number").append("<a href='#' class='manager_up' ></a>");	
			$(".manager_number").append("<a href='#' class='pageManager' id='row1'>1</a>");
			//如果当前页-6小于=0，则显示6条数据，否则显示3条数据
			if(currentPageNumber-6<=0){
				for(var i=2;i<6;i++){
					if(i==currentPageNumber-1){
						$(".manager_number").append("<a href='#' class='pageManager manager_click2' id = 'row"+i+"'>"+i+"</a>");								
					}else{
						$(".manager_number").append("<a href='#' class='pageManager' id = 'row"+i+"'>"+i+"</a>");							
					}
				}
			}else{
				$(".manager_number").append("<a href='#'  flag='before'>…</a>");		
				for(var i=currentPageNumber-3;i<currentPageNumber;i++){
					if(i==currentPageNumber-1){
						$(".manager_number").append("<a href='#' class='pageManager manager_click2' id = 'row"+i+"'>"+i+"</a>");								
					}else{
						$(".manager_number").append("<a href='#' class='pageManager' id = 'row"+i+"'>"+i+"</a>");							
					}
				}				
			}
			//给数字为nextPage   加上样式
			$(".manager_number").append("<a href='#' flag='after'>…</a>");		
			$(".manager_number").append("<a href='#' class='pageManager' id='row"+totalPageNumber+"'>"+totalPageNumber+"</a>");			
			$(".manager_number").append("<a href='#' class='manager_next' ></a>");			
			JumpPage(prevPage);
		}else if(0==prevPage){
			
		}else{
			$(".manager_click2").prev("a").addClass("manager_click2");
			$(".manager_click2").next("a").removeClass("manager_click2");			
			JumpPage(prevPage);
		}
	});	
}
/**
 * 	 根据指定页数跳转
 * @param totalPageNumber		总页数
 * @param toPage		前往页
 * @param executeSubmit		是否提交表单
 */
function toPage(totalPageNumber,toPage,executeSubmit){
	if(toPage<=5){					 //前往页<5
		$(".manager_number").empty();
		$(".manager_number").append("<a href='#' class='manager_up' ></a>");	
		if(totalPageNumber<=7){//总页数 小于7，显示所有
			for(var i=1;i<8&&i<=totalPageNumber;i++){
				if(i==toPage){
					$(".manager_number").append("<a href='#' class='pageManager manager_click2' id = 'row"+i+"'>"+i+"</a>");								
				}else{
					$(".manager_number").append("<a href='#' class='pageManager' id = 'row"+i+"'>"+i+"</a>");		
				}
			}				
		}else{//总页数大于7，显示1-5页数据
			for(var i=1;i<6;i++){
				if(i==toPage){
					$(".manager_number").append("<a href='#' class='pageManager manager_click2' id = 'row"+i+"'>"+i+"</a>");								
				}else{
					$(".manager_number").append("<a href='#' class='pageManager' id = 'row"+i+"'>"+i+"</a>");		
				}
			}					
			$(".manager_number").append("<a href='#'   flag='after'>…</a>");	
			$(".manager_number").append("<a href='#'   class='pageManager' id='row"+totalPageNumber+"'>"+totalPageNumber+"</a>");				
		}
		$(".manager_number").append("<a href='#' class='manager_next' ></a>");			
	}else if(totalPageNumber-toPage<5&&totalPageNumber-toPage>=0){//大数	 //如果总页数-前往页<5		
		$(".manager_number").empty();
		$(".manager_number").append("<a href='#' class='manager_up' ></a>");		
		$(".manager_number").append("<a href='#' class='pageManager' id='row1'>1</a>");
		if(totalPageNumber>7){
			$(".manager_number").append("<a href='#'  flag='before'>…</a>");
		}
		//显示后5条数据
		//总页数-前往页=j ;对j循环
		if(totalPageNumber==7){
			$(".manager_number").append("<a href='#' class='pageManager' id = 'row2'>2</a>");				
		}
		for(var j=4-(totalPageNumber-toPage);j>0;j--){
			$(".manager_number").append("<a href='#' class='pageManager' id = 'row"+(toPage-j)+"'>"+parseInt(toPage-j)+"</a>");			
		}			
		for(var i=toPage;i<=totalPageNumber;i++){
			if(i==toPage){
				$(".manager_number").append("<a href='#' class='pageManager manager_click2' id = 'row"+i+"'>"+i+"</a>");								
			}else{
				$(".manager_number").append("<a href='#' class='pageManager' id = 'row"+i+"'>"+i+"</a>");							
			}				
		}
		$(".manager_number").append("<a href='#' class='manager_next' ></a>");
	}else if(toPage>5&&toPage<totalPageNumber){//中间
		$(".manager_number").empty();
		$(".manager_number").append("<a href='#' class='manager_up' ></a>");
		$(".manager_number").append("<a href='#' class='pageManager' id='row1'>1</a>");
		$(".manager_number").append("<a href='#'   flag='before'>…</a>");	
		for(var i=toPage;i<=totalPageNumber;i++){
			if(i==toPage){
				$(".manager_number").append("<a href='#'  class='pageManager manager_click2' id = 'row"+i+"' >"+i+"</a>");
			}else{
				$(".manager_number").append("<a href='#'  class='pageManager' id = 'row"+i+"'>"+i+"</a>");
			}
			if(totalPageNumber>7&&i>toPage+1){
				$(".manager_number").append("<a href='#'   flag='after'>…</a>");			
				$(".manager_number").append("<a href='#'   class='pageManager'>"+totalPageNumber+"</a>");
				break;
			}
		}
		$(".manager_number").append("<a href='#' class='manager_next' ></a>");
	}else{
//		alert("请输入正确页码!");
		return;
	}
	if(executeSubmit!=undefined){
		JumpPage(toPage)
	}
}
/**
 * 提交数据
 * @param 跳转到指定页码
 */
function JumpPage(jumpPage){
	$("form").find("[name='page']").remove();
	$("form").append("<input type='hidden' name='page' value="+jumpPage+">");
    $("form").submit();
}
