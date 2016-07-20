<form id="formSecondEdit" method="POST">
	<div class="form_wrp">
	    <table class="table">
	        <tbody>
	        	<tr id="second_menu_show">
	                <td class="size1of1" colspan="2">
	                    <label class="frm_label">Parent category:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val">
	                            <span>${category.parentCategory}</span>
	                        </span>
	                    </div>
	                </td>
	            </tr>
	            <tr id="">
	                <td class="size1of1" colspan="2">
	                    <label class="frm_label">Name:</label>
	                    <div class="frm_controls">
	                        <@ctl.text name="category.title" value="${category.title}" required=true validate={"maxlength":"16"} placeholder="max length is 16" />
	                        <input type="hidden" name="category.id" value="${category.id}" />
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
	                        <@ctl.text name="category.orderNum" value="${category.orderNum}" required=true validate={"positiveInteger":"true","max","999"} placeholder="Please fill in 0~1000 integer" />
	                    </div>
	                </td>
	            </tr>
	            <tr>
	                <td class="size1of1" colspan="2">
	                    <label class="frm_label">Category code:</label>
	                    <div class="frm_controls">
	                       <@ctl.text name="category.categoryCode" value="${category.categoryCode}" required=true required=true validate={"maxlength","20"} placeholder="the max length is 20" />
	                    </div>
	                </td>
	            </tr>
	            <tr class="without_link">
	                <td class="size1of1" colspan="2">
	                    <label class="frm_label">Category type:</label>
	                    <div class="frm_controls">
	                        <@ctl.dropdownlist id="model_style" name="category.categoryType" value="${category.categoryType}" hasChoice=true groupCode="pageType" dataType="BizCode" />
	                        <span class="required-star">*</span>
	                    </div>
	                </td>
	            </tr>
	            <tr class="without_link">
	                <td class="size1of1" colspan="2">
	                    <label class="frm_label">Target way:</label>
	                    <div class="frm_controls uniformjs">
	                        <@ctl.dropdownlist name="category.target" value="${category.target}" hasChoice=true groupCode="windows" dataType="BizCode" />
	                        <span class="required-star">*</span>
	                    </div>
	                </td>
	            </tr>
	            <tr class="without_link" id="page_listnum">
	                <td class="size1of1" colspan="2">
	                    <label class="frm_label">Per page:</label>
	                    <div class="frm_controls">
	                        <@ctl.text name="category.perPage" value="${category.perPage}" required=true validate={"positiveInteger":"true","max","100"} placeholder="Please fill in 0~100 integer" />
	                    </div>
	                </td>
	            </tr>
	            <tr class="last">
	                <td class="size1of1" colspan="2">
	                    <label class="frm_label">Status:</label>
	                    <div class="frm_controls ">
	                    	<@ctl.radiobuttonlist spanClass="frm_radio_box uniformjs supplement_info_radio" groupCode="status" dataType="BizCode" name="category.status" value="${category.status}" />
	                    </div>
	                </td>
	            </tr>
	        </tbody>
	    </table>
	    <div class="form_btn">
	   		<@ctl.button id="okSubmitSecondEdit" text="save" />
	  	  	<@ctl.button id="btnCancel" text="cancel" />
	    </div>
	</div> 
</form> 
<script type="text/javascript">
	$("#btnCancel").click(function(){
			$.fancybox.close();
		});
		
	$("#okSubmitSecondEdit").click(function(){
			if($("#formSecondEdit").valid()){
				$.ajax({
		            type: "POST",
		            url: "okSubmitSecondEdit.htm",
		            dataType: "json",
		            data: $("form#formSecondEdit").serialize(),
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
	
	$(function(){
		$("#model_style").change(function(){
				var selecttext = $(this).find("option:selected").val();
				if(selecttext=="1"){
					$("#page_listnum").show();	
				}else if(selecttext=="0"){
					$("#page_listnum").hide();
				}
			});
	});
	$(document).ready(function(){ 
	　　$("#model_style").change();
	});
	</script>  