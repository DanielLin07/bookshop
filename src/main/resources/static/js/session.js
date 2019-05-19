$(function(){
    $(".logout").on("click",function(){
        var flag = confirm("确定退出登陆吗？");
        if (flag == true){
            // 为防止使用DELETE、PUT参数传输失败，先将type设为POST，再在data中进行修改
            $.ajax({
                type:"POST",
                url:"/users/sessions",
                async:false,
                traditional:true,
                data:{_method:'DELETE'},
                success:function (result) {
                    if (result.resultCode == 200){
                        location.href = "/users";
                    }
                }
            });
        }
    });
});
