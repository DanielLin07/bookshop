$(function(){

	//菜单特效
	var index = 0
	$("#book-menu-toggle").on("click",function(){
		if (index ===0 ) {
			$("#book-menu").css("left","1px");
			$(this).css("left","178px");
			index =1;
		}else{
			$("#book-menu").css("left","-180px");
			$(this).css("left","1px");
			index=0;
		}
	});

	//按菜单展示某个板块，并将更多删除，展示翻页
	$(".book-class li").on("click",function(){

		//展示对应板块，删除更多，展示翻页
		var showClass = $(this).find("a").attr("href");
		var $showClassDiv = $("#book-container").find(showClass);
		// console.log(showClass);
		$showClassDiv.show().siblings("div").hide();
		$showClassDiv.children(".more").hide();
		$showClassDiv.children(".page-btn").show();
		


		//加粗当前点击的li
		$(this).css("font-weight","600").siblings("li").css("font-weight","500");
	});

	//点击更多展示对应板块，并将更多删除，展示翻页
	$("a.more").on("click",function(){
		var showClass = $(this).parent().attr("id");
		var $showClassDiv = $("#book-container").find("#"+showClass);
		// console.log($showClassDiv);
		$showClassDiv.show().siblings("div").hide();
		$showClassDiv.children(".more").hide();
		$showClassDiv.children(".page-btn").show();
	});




})