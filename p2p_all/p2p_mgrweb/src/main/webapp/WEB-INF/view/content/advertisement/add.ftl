	<form id="addForm" action="save.htm" method="POST" enctype="multipart/form-data">
	    <input type="hidden" name="dto.advertisement.adverCode" value="${(dto.advertisement.adverCode)!}">
		<div class="form_wrp">
		    <table class="table" cellpadding="0" cellspacing="0">
		        <tbody class="tbody" id="">
		            <tr >
		                <td class="size1of1" colspan="2">
		                    <label class="frm_label">Banner name:</label>
		                    <div class="frm_controls">
		                        <span class="frm_val" id="adverName">${dto.advertising.adverName}</span> 
		                    </div>  
		                </td>
		            </tr>
		            <tr class="">
		                <td class="size1of1" colspan="2">
		                    <label class="frm_label">Title:</label>
		                    <div class="frm_controls">
		                        <@ctl.text name="dto.advertisement.title" value="" required="true" validate={"maxlength":"32","minlength":"1"} fieldName="Title"/>
		                    </div>
		                </td>
		            </tr>
		            <tr class="">
		                <td class="size1of1" colspan="2">
		                    <label class="frm_label">Pic:</label>
		                    <div class="frm_controls">
		                        <@ctl.fileUpload required=true isfancybox=true formId="addForm" validate={"imageAccept":"[.bmp,.png,.jpeg, .jpg,.gif]"} fieldName="Pic"/>
		                    </div>
		                </td>
		            </tr>
		            <tr class="">
		                <td class="size1of1" colspan="2">
		                    <label class="frm_label">Link:</label>
		                    <div class="frm_controls">
		                        <@ctl.text value="" name="dto.advertisement.connectUrl" validate={"url":"true","maxlength":"255"} spanClass="medium_xxl"/>
		                    </div>
		                </td>
		            </tr>
		            <tr class="">
		                <td class="size1of1" colspan="2">
		                    <label class="frm_label">Open way:</label>
		                    <div class="frm_controls">
		                        <@ctl.radiobuttonlist groupCode="windows" dataType="BizCode" name="dto.advertisement.opens" value="0"/>
		                    </div>
		                </td>
		            </tr>
		            <tr class="">
		                <td class="size1of2">
		                    <label class="frm_label">Start time:</label>
		                    <div class="frm_controls">
		                        <@ctl.datetimepicker id="startAt" name="dto.advertisement.startAt" validate={"dateTimeFormat24":"true"}/>
		                    </div>
		                </td>
		                <td class="size1of2">
		                    <label class="frm_label">End time:</label>
		                    <div class="frm_controls">
		                        <@ctl.datetimepicker name="dto.advertisement.endAt" validate={"dateTimeFormat24":"true","gtTo":"#startAt"} fieldName="later than start time"/>
		                    </div>
		                </td>
		            </tr>
		            <tr class="">
		                <td class="size1of1" colspan="2">
		                    <label class="frm_label">Order:</label>
		                    <div class="frm_controls">
		                        <@ctl.text name="dto.advertisement.orderNum" value="1" required="true" validate={"min":"1","max":"999"} fieldName="Order"/>
		                    </div>
		                </td>
		            </tr>
		            <tr class="">
		                <td class="size1of1" colspan="2">
		                    <label class="frm_label">Status:</label>
		                    <div class="frm_controls">
		                        <@ctl.radiobuttonlist groupCode="status" dataType="BizCode" name="dto.advertisement.status" value="1"/>
		                    </div>
		                </td>
		            </tr>
		            <tr class="last">
		                <td class="size1of1" colspan="2">
		                    <label class="frm_label">Remark:</label>
		                    <div class="frm_controls">
		                        <@ctl.textarea name="dto.advertisement.remark" value="" validate={"maxlength":"1000"}/>
		                    </div>
		                </td>
		            </tr>
		        </tbody>
		    </table>
		    <div class="form_btn">
		        <@ctl.submit title="save" text="save"/>
		        <a href="javascript:;" id="cancel" title="cancel" class="btn">cancel</a>
		    </div>
		</div>
	</form>