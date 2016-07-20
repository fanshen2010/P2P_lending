<!-- 菜单修改 一级菜单 -->
<form id="formFirstEdit" method="POST">
<div class="form_wrp">
    <table class="table">
        <tbody>
            <tr id="">
                <td class="size1of1" colspan="2">
                    <label class="frm_label">Name:</label>
                    <div class="frm_controls">
                        <@ctl.text name="category.title" value="${category.title}" required=true validate={"maxlength":"16"} placeholder="max length is 16" />
                        <input type="hidden" name="category.id" value="${category.id}"  />
                    </div>
                </td>
            </tr>
            <tr id="">
                <td class="size1of1" colspan="2">
                    <label class="frm_label">Seo keywords:</label>
                    <div class="frm_controls">
                        <@ctl.text name="category.seoKeywords" value="${category.seoKeywords}" validate={"maxlength":"32"} placeholder="max length is 32" />
                    </div>
                </td>
            </tr>
            <tr>
                <td class="size1of1" colspan="2">
                    <label class="frm_label">Seo description:</label>
                    <div class="frm_controls">
                        <@ctl.text name="category.seoDescription" value="${category.seoDescription}" validate={"maxlength":"64"} placeholder="max length is 64" />
                    </div>
                </td>
            </tr>
            <tr>
                <td class="size1of1" colspan="2">
                    <label class="frm_label">Order:</label>
                    <div class="frm_controls">
                        <span class="frm_val">${category.orderNum}</span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="size1of1" colspan="2">
                    <label class="frm_label">Category code:</label>
                    <div class="frm_controls">
                        <span class="frm_val">${category.categoryCode}</span>
                    </div>
                </td>
            </tr>
            <tr class="without_link">
                <td class="size1of1" colspan="2">
                    <label class="frm_label">Category type:</label>
                    <div class="frm_controls">
                        <span class="frm_val">${category.categoryType}</span>
                    </div>
                </td>
            </tr>
            <tr class="without_link">
                <td class="size1of1" colspan="2">
                    <label class="frm_label">Target way:</label>
                    <div class="frm_controls uniformjs">
                        <span class="frm_val">${category.target}</span>
                    </div>
                </td>
            </tr>
            <tr class="last">
                <td class="size1of1" colspan="2">
                    <label class="frm_label">Status:</label>
                    <div class="frm_controls ">
                    	<span class="frm_val"><@ctl.statusButton status="${category.status}" isView=true /></span>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
    <div class="form_btn">
    	<@ctl.button id="btnOkSubmitFirstEdit" text="save" />
	  	<@ctl.button id="btnCancel" text="cancel" />
    </div>
</div>
</form>
<script type="text/javascript">
	$("#btnCancel").click(function(){
		$.fancybox.close();
	});

	$("#btnOkSubmitFirstEdit").click(function(){
			if($("#formFirstEdit").valid()){
				$.ajax({
		            type: "POST",
		            url: "okSubmitFirstEdit.htm",
		            dataType: "json",
		            data: $("form#formFirstEdit").serialize(),
		            error: function (data, transport) {
		               alert("修改失败");
		            },
		            success: function (result) {
		            	if(result.retMes=="1") {
		            		alert("修改成功");	
		            		location.replace(location);
		            	} else {
		            		alert("修改失败");
		            	}
		            }
		        });
				$.fancybox.close();
			}
		});
</script>