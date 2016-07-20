<!-- 个人客户添加 -->
<form id="cust_update" action="${taglibs.allctx}/customer/personal/save.htm" method="post">
    <div class="form_wrp">
        <table class="table">
            <tbody>
            <tr>
                <td class="table_right_border size1of2">
                    <label class="frm_label">Name:</label>
                    <div class="frm_controls">
                        <span class="frm_val">
                            <span><@ctl.text name="custPersonalInfo.customerName" required=true value="${(custPersonalInfo.customerName)!}" validate={"maxlength":"32"} fieldName="Name"/></span>
                        </span>
                    </div>
                </td>
                <td class=" size1of2">
                    <label class="frm_label">ID number:</label>
                    <div class="frm_controls">
                        <span class="frm_val">
                            <span><@ctl.text name="custPersonalInfo.identity" required=true value="${(custPersonalInfo.identity)!}" validate={"chinaId":"true","remote":"identityCheck.htm"} fieldName="ID number"/></span>
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