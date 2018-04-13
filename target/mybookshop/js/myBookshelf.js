$(function(){
   
   //获取性别
	var sexInput = $("#sexInput").val();
	var sexText = document.getElementById("sexText");
	var realsex;
    if(sexInput == "m"){
    	realsex = "男";
    }else {
    	realsex = "女";
    }
    $("#sexText").html(realsex);   


	//根据性别显示图片
	if (realsex === "女") {
		$("#sex-pic").attr("src","img/girl.png");
	}else if (realsex === "男") {
		$("#sex-pic").attr("src","img/boy.png");
	}else{
		$("#sex-pic").attr("src","img/sex-unknow.png");
	};


	//切换选项卡，并根据对应选型卡显示按钮
	$(".card-btn").find("span").on("click",function(){
		var title = $(this).attr("title");
		var name = $(this).attr("id");
		var showName = name.split("-")[0];
		$(this).addClass("active").siblings("span").removeClass("active");
		$(this).parent().find("#"+showName+"-upload").show().siblings("a").hide();
		$("#container").find("#"+title).show().siblings("div").hide();
	});
});
