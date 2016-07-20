<#-- ***************************************************************** modify  BEGIN ************************************************** -->
<#--表单在此--><form action="update.htm" method="POST">
                <input type="hidden" name="advertising.id" value="${advertising.id}">
                <div class="form_wrp">
                    <table class="table">
                        <tbody>
                            <tr id="">
                                <td class="size1of1" colspan="2">
                                    <label class="frm_label">Banner name:</label>
                                    <div class="frm_controls">
                                        <@ctl.text name="advertising.adverName" value="${advertising.adverName}" required="true" fieldName="Banner name" validate={"maxlength":"32","minlength":"1"}/>
                                    </div>
                                </td>
                            </tr>
                            <tr id="">
                                <td class="size1of1" colspan="2">
                                    <label class="frm_label">Banner code:</label>
                                    <div class="frm_controls">
                                        <@ctl.text name="advertising.adverCode" value="${advertising.adverCode}" required="true" fieldName="Banner code" validate={"maxlength":"18","minlength":"6","remote":"checkAdvertisingCode.htm?advertising.id=${advertising.id}"}/>
                                    </div>
                                </td>
                            </tr>
                            <tr id="">
                                <td class="size1of1" colspan="2">
                                    <label class="frm_label">Status:</label>
                                    <div class="frm_controls ">
                                        <@ctl.radiobuttonlist groupCode="status" dataType="BizCode" name="advertising.status" value="${advertising.status}"/>
                                    </div>
                                </td>
                            </tr>
                            <tr class="last">
                                <td class="size1of1" colspan="2">
                                    <label class="frm_label">Remark:</label>
                                    <div class="frm_controls">
                                        <@ctl.textarea name="advertising.remark" value="${advertising.remark}" fieldName="Remark" validate={"maxlength":"1000"}/>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="form_btn">
                        <@ctl.submit title="save" text="save"/>
                        <@ctl.linkButton href="javascript:;" id="cancle" title="cancel" text="cancel"/>
                    </div>
                </div>    
            </form><#--表单在此-->
    <script type="text/javascript">
        $(function(){
            $("#cancle").on("click",function(){
               $.fancybox.close();
            });
        });
    </script>
<#-- ***************************************************************** modify  END ************************************************** -->