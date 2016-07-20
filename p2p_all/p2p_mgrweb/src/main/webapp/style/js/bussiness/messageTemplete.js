$(function(){
	// 点击用户短信管理显示数据
	$("body").on("click","#user_tab", function(){
		$.ajaxLoadFtl({
            url: "userSearch.htm",
            dataType:"json",
            error: function (data, transport) {
               $("#tab_show").html("服务器繁忙");
            },
            success: function (result) {
                $("#tab_show").html(result.html);
            }
        });
	});
	
	// 点击业务人员短信管理显示数据
	$("body").on("click","#business_tab", function(){
		$.ajaxLoadFtl({
            url: "businessSearch.htm",
            dataType:"json",
            error: function (data, transport) {
               $("#tab_show").html("服务器繁忙");
            },
            success: function (result) {
                $("#tab_show").html(result.html);
            }
        });
	});
	
	// 用户短信管理查询
	$("body").on("click","#user_search", function(){
		var formData=$("form").serialize();
		$.ajaxLoadFtl({
            url: "userSearch.htm",
            data: formData,
            error: function (data, transport) {
            
               $("#tab_show").html("服务器繁忙");
            },
            success: function (result) {
                $("#tab_show").html(result.html);
            }
        });
	});

	// 业务人员短信管理查询
	$("body").on("click","#business_search", function(){
		var formData=$("form").serialize();
		$.ajaxLoadFtl({
            url: "businessSearch.htm",
            dataType:"json",
            data: formData,
            error: function (data, transport) {
            
               $("#tab_show").html("服务器繁忙");
            },
            success: function (result) {
                $("#tab_show").html(result.html);
            }
        });
	});
	
	 //设置页面
	$("body").on("click","a.view.fancybox", function(){
		var params={"receiverId":$(this).data("id"),"realName":$("#keyInput").attr("value")};
		$.ajaxLoadFtl({
            url: "businessView.htm",
            dataType:"json",
            data: params,
            error: function (data, transport) {
            
               $("#tab_show").html("服务器繁忙");
            },
            success: function (result) {
                $("#tab_show").html(result.html);
            }
        });
	});
	
	// 添加接受人员
	$("body").on("click",".reci_oper_arrow", function(r){
		var obj = $(this).parents("li").clone();
		$("#recipient_list_right ul").append(obj);
		$("#recipient_list_right ul li").each(function(e){
			$(this).find("a").removeClass("reci_oper_arrow").addClass("reci_oper_del");
			$(this).attr("id","clone_"+e);
			$(this).find("input[type='hidden']").attr("name","receiveuserList");
		});
		$(this).parents("li").remove();
	});
	
	//删除接受人员
	$("body").on("click",".reci_oper_del", function(r){
		var obj = $(this).parents("li").clone();
		$("#recipient_list_left ul").append(obj);
		$("#recipient_list_left ul li").each(function(e){
			$(this).find("a").removeClass("reci_oper_del").addClass("reci_oper_arrow");
			$(this).attr("id","clone_"+e);
			$(this).find("input[type='hidden']").attr("name","pfmuserid");
		});
		$(this).parents("li").remove();
	});
	
	$("body").on("click","#recipient_list_right ul li", function(){
		$(this).toggleClass("active");
	});
	
	// 查询业务人员
	$("body").on("click","#search_btn", function(){
		$("#realName").val($("#keyInput").val());
		var formData=$("form").serialize();
		$.ajaxLoadFtl({
            url: "businessView.htm",
            dataType:"json",
            data: formData,
            error: function (data, transport) {
            
               $("#tab_show").html("服务器繁忙");
            },
            success: function (result) {
                $("#tab_show").html(result.html);
            }
        });
	});
	$("body").on("keypress","#keyInput", function(e){
        var keyCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;  
        if (keyCode == 13) {  
        	$("#realName").val($("#keyInput").val());
    		var formData=$("form").serialize();
    		$.ajaxLoadFtl({
                url: "businessView.htm",
                dataType:"json",
                data: formData,
                error: function (data, transport) {
                
                   $("#tab_show").html("服务器繁忙");
                },
                success: function (result) {
                    $("#tab_show").html(result.html);
                }
            }); 
    		 return false;  
        } else {  
            return true;  
        }  
    });
	
});