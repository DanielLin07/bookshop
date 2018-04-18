$(function(){

    //获取性别
    var sexInput = $("#sexInput").val();
    var sexText = document.getElementById("sexText");
    var realSex;
    if(sexInput == "m"){
        realSex = "男";
    }else {
        realSex = "女";
    }
    $("#sexText").html(realSex);


    //根据性别显示图片
    if (realSex === "女") {
        $("#sex-pic").attr("src","img/girl.png");
    }else if (realSex === "男") {
        $("#sex-pic").attr("src","img/boy.png");
    }else{
        $("#sex-pic").attr("src","img/sex-unknow.png");
    }


    //切换选项卡，并根据对应选型卡显示按钮
    $(".card-btn").find("span").on("click",function(){
        var title = $(this).attr("title");
        var name = $(this).attr("id");
        var showName = name.split("-")[0];
        $(this).addClass("active").siblings("span").removeClass("active");
        $(this).parent().find("#"+showName+"-upload").show().siblings("a").hide();
        $("#container").find("#"+title).show().siblings("div").hide();
    });


	//选择对应书本进行选择删除操作
	var bookIdArr=[],bookId;
	$("span.delete-btn").on("click",function(){

		bookId = $(this).siblings("form").find(".book-id").attr("value");
		//判断数组里是否有bookId,无则返回-1
		if($.inArray(bookId ,bookIdArr) == - 1){ 
			$(this).css("background-image","url(img/delete1.png)");
			bookIdArr.push(bookId);
			console.log(bookIdArr);
		}else{
			$(this).css("background-image","url(img/delete2.png)");
			bookIdArr = $.grep(bookIdArr,function(val){
				return val != bookId;
			});
			console.log(bookIdArr);			
		}
	});


	//点击删除图标，确认是否删除
	var flag  = false;
	$("#delete-book").on("click",function(){
		flag = confirm("确定删除所选择的二手书吗？");
        if (flag == true){
            console.log(bookIdArr);
            $.ajax({
                type:"POST",
                url:"deleteBook.do",
                async:false,
                traditional:true,
                data:{"bookIds":bookIdArr},
                success:function (msg) {
                    flag = msg;
                    alert("删除成功！");
                    location.href = "myBookshelf.do";
                }
            });
        }
	});

});
