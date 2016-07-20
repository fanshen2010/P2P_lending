$(function(){
	/**
	 * 角色添加button
	 */
	$("#save_btn").click(function(){
		$("#pageFormSubmit").submit();
	});
	
	/**
	 * 系统角色弹出窗体--角色删除
	 */
    $("a.del").bind("click",function(){
		var dataid = $(this).data("id")
		$.fancybox.open($("#delete_btn"),{"title":"delete","afterLoad":function(){
			$("#delRoleId").val(dataid);
		}})
    });
    /**
	 * 系统角色弹出窗体--角色状态
	 */
    $("a.edit").bind("click",function(){
		var dataid = $(this).data("id")
		var dataState = $(this).data("state")
		$.fancybox.open($("#state_btn"),{"afterLoad":function(){
			$("#stateRoleId").val(dataid);
			$("#state").val(dataState);
		}})
    });
    /**
     * 系统角色确定删除
     */
	$("#fancyboxdel").click(function(){
		var roleId = $("#delRoleId").val();
		window.location.href = webName+"/system/rolemanagent/roleDelete.htm?roleId="+roleId;
	});
	/**
     * 系统角色-禁用启用
     */
	$("#fancyboxstate").click(function(){
		var roleId = $("#stateRoleId").val();
		var state = $("#state").val();
		window.location.href = webName+"/system/rolemanagent/roleState.htm?roleId="+roleId+"&state="+state;
	});
	/**
	 * 删除弹出窗的取消
	 */
	$(".btn_cancel").click(function(){
		$.fancybox.close();
	});
});
/**
 * 角色添加取消button
 */		
$("#back_btn").click(function(){
});