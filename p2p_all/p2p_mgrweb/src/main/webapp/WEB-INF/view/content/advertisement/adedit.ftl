<#-- ***************************************************************** modify  BEGIN ************************************************** -->
         <form id="editForm" action="update.htm" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="dto.advertisement.id" value="${dto.advertisement.id}">
            <div class="form_wrp">
                <table class="table" cellpadding="0" cellspacing="0">
                    <tbody class="tbody" id="">
                        <tr >
                            <td class="size1of1" colspan="2">
                                <label class="frm_label">Banner name:</label>
                                <div class="frm_controls">
                                    <span class="frm_val">${dto.advertising.adverName}</span> 
                                </div>  
                            </td>
                        </tr>
                        <tr class="">
                            <td class="size1of1" colspan="2">
                                <label class="frm_label">Title:</label>
                                <div class="frm_controls">
                                    <@ctl.text name="dto.advertisement.title" value="${dto.advertisement.title}" required="true" validate={"maxlength":"32"} fieldName="Title"/>
                                </div>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="size1of1" colspan="2">
                                <label class="frm_label">Pic:</label>
                                <div class="frm_controls">
                                   <@ctl.fileUpload fileUploaded=dto.uploadFile isfancybox=true formId="editForm" required=true validate={"imageAccept":"[.bmp,.png,.jpeg, .jpg,.gif]"}/>
                                </div>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="size1of1" colspan="2">
                                <label class="frm_label">Link:</label>
                                <div class="frm_controls">
                                    <@ctl.text value="${dto.advertisement.connectUrl}" spanClass="medium_xxxl" name="dto.advertisement.connectUrl" validate={"url":"true","maxlength":"255"} spanClass="medium_xxl"/>
                                </div>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="size1of1" colspan="2">
                                <label class="frm_label">Open way:</label>
                                <div class="frm_controls">
                                    <@ctl.radiobuttonlist groupCode="windows" value="${dto.advertisement.opens}" name="dto.advertisement.opens" dataType="BizCode"/>
                                </div>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="size1of2">
                                <label class="frm_label">Start time:</label>
                                <div class="frm_controls">
                                    <@ctl.datetimepicker id="startAt1" value=dto.advertisement.startAt name="dto.advertisement.startAt" validate={"dateTimeFormat24":"true"}/>
                                </div>
                            </td>
                            <td class="size1of2">
                                <label class="frm_label">End time:</label>
                                <div class="frm_controls">
                                    <@ctl.datetimepicker value=dto.advertisement.endAt name="dto.advertisement.endAt" validate={"dateTimeFormat24":"true","gtTo":"#startAt1"} fieldName="later than start time"/>
                                </div>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="size1of1" colspan="2">
                                <label class="frm_label">Order:</label>
                                <div class="frm_controls">
                                    <@ctl.text name="dto.advertisement.orderNum" value="${(dto.advertisement.orderNum)}" required="true" validate={"min":"1","max":"999"} fieldName="Order"/>
                                </div>
                            </td>
                        </tr>
                        <tr class="">
                            <td class="size1of1" colspan="2">
                                <label class="frm_label">Status:</label>
                                <div class="frm_controls">
                                    <@ctl.radiobuttonlist groupCode="status" value="${(dto.advertisement.status)}" name="dto.advertisement.status" dataType="BizCode"/>
                                </div>
                            </td>
                        </tr>
                        <tr class="last">
                            <td class="size1of1" colspan="2">
                                <label class="frm_label">Remark:</label>
                                <div class="frm_controls">
                                    <@ctl.textarea value="${(dto.advertisement.remark)}" name="dto.advertisement.remark" validate={"maxlength":"1000"}/>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div class="form_btn">
                    <@ctl.submit title="save" text="save"/>
                    <@ctl.linkButton href="javascript:;" class="btn" id="cancle" title="cancel" text="cancel"/>
                </div>
           </div>
        </form>
        <script type="text/javascript">
            $(function(){
                $("#cancle").on("click",function(){
                   $.fancybox.close();
                });
            });
        </script>
<#-- ***************************************************************** modify  END ************************************************** -->