$(function(){
	/**
	 * 友情链接弹出窗体--友情链接删除
	 */
    $("a.del").bind("click",function(){
		var dataid = $(this).data("id")
		$.fancybox.open($("#delete_btn"),{"title":"delete","afterLoad":function(){
			$("#delLinksId").val(dataid);
		}})
    });
    /**
     * 友情链接信息删除
     */
	$("#fancyboxdel").click(function(){
		var id = $("#delLinksId").val();
		window.location.href = webName+"/system/linksmanagent/linksDelete.htm?id="+id;
	});
	/**
	 * 友情链接修改弹出框
	 */
	$(".edit.fancybox").click(function(){
		var dataid = $(this).data("id");
		$.ajax({
	            type: "POST",
	            url: "linksEdit.htm",
	            dataType: "json",
	            data:{id:dataid},
	            async:false,
	            error: function (data, transport) {
	            	$("#msg_view").html("服务器繁忙");
	            },
	            success: function (result) {
	            	$("#msg_view").html(result.html);
	            	$.fancybox.open($("#msg_view"),{"title":"modify"});
	            }
	        });
	});
	
	/**
	 * 删除弹出窗的取消
	 */
	$(".btn_cancel").click(function(){
		$.fancybox.close();
	});
});