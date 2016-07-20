$(function(){
	// 一级菜单点击展开伸缩 菜单添加
	$(".level_first .menu_toggle").click(function(){
		var parents = $(this).parents(".level_first");
		var num = parents.attr("id");
		var data = parents.data("status");
		//alert(data);
		if(data=="open"){
			$("[id^=" +num+ "_level]").fadeOut();
			parents.data("status","close");	
			$(this).removeClass("open");	
		}else if(data=="close"){
			$("[id^=" +num+ "_level].level_second").each(function(){
				var secondLevel = $(this).attr("id")
				$(this).fadeIn();
				if($(this).data("status") == "open"){
					$("[id^=" + secondLevel + "_level]").fadeIn();
				}	
			});	
			parents.data("status","open");		
			$(this).addClass("open");	
		}
	});
	// 二级菜单点击展开伸缩 菜单添加
	$(".level_second .menu_toggle").click(function(){
		var parents = $(this).parents(".level_second");
		var num = parents.attr("id");
		var data = parents.data("status");
		//alert(data);
		if(data=="open"){
			$("[id^=" +num+ "_level]").fadeOut();
			parents.data("status","close");	
			$(this).removeClass("open");		
		}else if(data=="close"){
			$("[id^=" +num+ "_level]").fadeIn();
			parents.data("status","open");			
			$(this).addClass("open");	
		}
	});
	
	
	$("#second_menu_show").hide();

	//二级菜单和三级菜单添加
	$(".menu_add").click(function(){
		var params={"menuId":$(this).data("id")};
		$.ajaxLoadFtl({
            url: "menuAdd.htm",
            data: params,
            error: function (data, transport) {
            
               $("#menu_add_pop").html("服务器繁忙");
            },
            success: function (result) {
                $("#menu_add_pop").html(result.html);
        		$.fancybox.open($("#menu_add_pop"),{"title":"menu add","afterLoad" : function(){
					this.inner.addClass("heightauto");
			}});
            }
        });
	});
	
	//一级菜单添加
	$("#menu_add").click(function(){
		var params={"menuId":null};
		$.ajaxLoadFtl({
            url: "menuAdd.htm",
            data: params,
            error: function (data, transport) {
               $("#menu_add_pop").html("服务器繁忙");
            },
            success: function (result) {
                $("#menu_add_pop").html(result.html);
                $("#menu_add_pop #second_menu_show").hide();
        		$.fancybox.open($("#menu_add_pop"),{"title":"menu add","afterLoad" : function(){
					this.inner.addClass("heightauto");
        		}});
            }
        });
	});
	
	//删除提示框的显示
    $("a.del").bind("click",function(){
		var params={"menuId":$(this).data("id")};
		$.ajax({
            type: "POST",
            url: "menuDeleteCheck.htm",
            dataType: "json",
            data: params,
            error: function (data, transport) {
            
               $("#menu_add_pop").html("服务器繁忙");
            },
            success: function (result) {
        		$.fancybox.open($("#delete_btn"),{"title":"delete","afterLoad":function(){
        			$("#delMenuId").val(result.menuId);
        			if(result.size>0){
        				$("#delete_btn").find(".ptb20").text("comfirm to delete parent and child category together？");
        			}else{
        				$("#delete_btn").find(".ptb20").text("comfirm to delete？");
        			}
        		}});	
            }
        });
    });
    
    //删除方法的实现
	$("#delMenu").click(function(){
		var menuId = $("#delMenuId").val();
		window.location.href = webName+"/system/menu/delete.htm?menuId="+menuId;
	});
	
	
	// 删除弹出窗的取消
	$(".btn_cancel").click(function(){
		$.fancybox.close();
	});
	
	// 菜单修改
	$(".edit").click(function(){
		var params={"menuId":$(this).data("id")};
		$.ajaxLoadFtl({
            url: "menuEdit.htm",
            data: params,
            error: function (data, transport) {
            
               $("#menu_add_pop").html("服务器繁忙");
            },
            success: function (result) {
                $("#menu_edit_pop").html(result.html);
        		$.fancybox.open($("#menu_edit_pop"),{"title":"menu modify","afterLoad" : function(){
					this.inner.addClass("heightauto");
			}});
            }
        });
	});
    
});