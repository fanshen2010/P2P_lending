<!-- 个人客户设置 -->
<form id="cust_update" action="${taglibs.allctx}/customer/personal/update.htm" method="post">
    <input type="hidden" name="custPersonalInfo.id"  value="${(custPersonalInfo.id)!}">
    <div class="form_wrp">
        <table class="table">
            <tbody>
            <tr>
                <td class="table_right_border size1of2">
                    <label class="frm_label">Name:</label>
                    <div class="frm_controls">
                        <span class="frm_val">
                            <span>${(custPersonalInfo.customerName)!}</span>
                            <input type="hidden" name="custPersonalInfo.customerName" value="${(custPersonalInfo.customerName)!}"/>
                            <input type="hidden" name="custPersonalInfo.customerCode" value="${(custPersonalInfo.customerCode)!}"/>
                        </span>
                    </div>
                </td>
                <td class=" size1of2">
                    <label class="frm_label">ID number:</label>
                    <div class="frm_controls">
                        <span class="frm_val">
                            <span>${(custPersonalInfo.identity)!}</span>
                            <input type="hidden" name="custPersonalInfo.identity" value="${(custPersonalInfo.identity)!}"/>
                        </span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="table_right_border size1of2">
                    <label class="frm_label">Contact phone:</label>
                    <div class="frm_controls">
                    <@ctl.text name="custPersonalInfo.cellphone" required=true value="${(custPersonalInfo.cellphone)!}" validate={"telephoneOrCellphone":"true"} fieldName="Contact phone"/>
                    </div>
                </td>
                <td class=" size1of2">
                    <label class="frm_label">Email:</label>
                    <div class="frm_controls">
                    <@ctl.text name="custPersonalInfo.email"  value="${(custPersonalInfo.email)!}" validate={"maxlength":"64","email":"true"}/>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="form_btn">
        <@ctl.button type="submit" title="save" text="save"/>
        <@ctl.linkButton class="btn btn_cancel" href="javascript:;" title="cancel" text="cancel" />
        </div>
    </div>
</form>