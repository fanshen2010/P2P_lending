<form id="formSecondAdd" method="POST">
	<div class="form_wrp">
	    <table class="table">
	        <tbody>
	        	<tr id="second_menu_show">
	                <td class="size1of1" colspan="2">
	                    <label class="frm_label">Parent category:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val">
	                            <span>${parentName}</span>
	                            <input type="hidden" name="category.parentCategory" value="${parentId}" />
	                        </span>
	                    </div>
	                </td>
	            </tr>
	            <tr id="">
	                <td class="size1of1" colspan="2">
	                    <label class="frm_label">Name:</label>
	                    <div class="frm_controls">
	                        <@ctl.text name="category.title"required=true validate={"maxlength":"16"} placeholder="max length is 16" />
	                    </div>
	                </td>
	            </tr>
	            <tr id="">
	                <td class="size1of1" colspan="2">
	                    <label class="frm_label">Seo keywords:</label>
	                    <div class="frm_controls">
	                        <@ctl.text name="category.seoKeywords" validate={"maxlength":"32"} placeholder="max length is 32" />
	                    </div>
	                </td>
	            </tr>
	            <tr>
	                <td class="size1of1" colspan="2">
	                    <label class="frm_label">Seo description:</label>
	                    <div class="frm_controls">
	                       <@ctl.text name="category.seoDescription" validate={"maxlength":"64"} placeholder="max length is 64" />
	                    </div>
	                </td>
	            </tr>
	            <tr>
	                <td class="size1of1" colspan="2">
	                    <label class="frm_label">Order:</label>
	                    <div class="frm_controls">
	                        <@ctl.text name="category.orderNum" required=true validate={"positiveInteger":"true","max","999"} placeholder="Please fill in 0~1000 integer" />
	                    </div>
	                </td>
	            </tr>
	            <tr>
	                <td class="size1of1" colspan="2">
	                    <label class="frm_label">Category code:</label>
	                    <div class="frm_controls">
	                       <@ctl.text name="category.categoryCode"  required=true validate={"maxlength","20"} placeholder="the max length is 20" required=true />
	                    </div>
	                </td>
	            </tr>
	            <tr class="without_link">
	                <td class="size1of1" colspan="2">
	                    <label class="frm_label">Category type:</label>
	                    <div class="frm_controls">
                           <@ctl.dropdownlist id="model_style" name="category.categoryType" required=true hasChoice=true groupCode="pageType" dataType="BizCode" />
	                    </div>
	                </td>
	            </tr>
	            <tr class="without_link">
	                <td class="size1of1" colspan="2">
	                    <label class="frm_label">Target way:</label>
	                    <div class="frm_controls uniformjs">
	                    	<@ctl.dropdownlist name="category.target" required=true hasChoice=true groupCode="windows" dataType="BizCode" />
	                    </div>
	                </td>
	            </tr>
	            <tr class="without_link hide" id="page_listnum">
	                <td class="size1of1" colspan="2">
	                    <label class="frm_label">Per page:</label>
	                    <div class="frm_controls">
	                        <@ctl.text name="category.perPage" required=true validate={"positiveInteger":"true","max","100"} placeholder="Please fill in 0~100 integer" />
	                    </div>
	                </td>
	            </tr>
	            <tr class="last">
	                <td class="size1of1" colspan="2">
	                    <label class="frm_label">Status:</label>
	                    <div class="frm_controls ">
	                    	<@ctl.radiobuttonlist spanClass="frm_radio_box uniformjs supplement_info_radio" groupCode="status" dataType="BizCode" name="category.status" value="1" />
	                    </div>
	                </td>
	            </tr>
	        </tbody>
	    </table>
	    <div class="form_btn">
	  	  	<@ctl.button id="okSubmitSecondAdd" text="save" />
	  	  	<@ctl.button id="btnCancelSecond" text="cancel" />
	    </div>
	</div> 
</form> 
<script type="text/javascript">
	$("#btnCancelSecond").click(function(){
		$.fancybox.close();
	});
	
	$("#okSubmitSecondAdd").click(function(){
	
			if($("#formSecondAdd").valid()){
				$.ajax({
		            type: "POST",
		            url: "okSubmitSecondAdd.htm",
		            dataType: "json",
		            data: $("form#formSecondAdd").serialize(),
		            error: function (data, transport) {
		               alert("error");
		            },
		            success: function (result) {
		            	if(result.retMes=="1") {
		            		alert("success");	
		            		location.replace(location);
		            	} else {
		            		alert("error");
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
	</script>  