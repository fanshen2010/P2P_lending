<!-- 个人客户添加 -->
<form id="cust_update" action="${taglibs.allctx}/customer/enterprise/save.htm" method="post">
    <div class="form_wrp">
        <table class="table">
            <tbody>
            <tr>
                <td class="table_right_border size1of2">
                    <label class="frm_label">Enterprise name:</label>

                    <div class="frm_controls">
                    <@ctl.text name="custEnterpriseInfo.customerName" required=true value="${(custEnterpriseInfo.customerName)!}" validate={"maxlength":"64","remote":"customerNameCheck.htm"} fieldName="Enterprise name"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="table_right_border size1of2">
                    <label class="frm_label">Business license:</label>

                    <div class="frm_controls">
                    <@ctl.text name="custEnterpriseInfo.licenseNumbers" required=true value="${(custEnterpriseInfo.licenseNumbers)!}" validate={"maxlength":"15"} fieldName="Business license"/>
                    </div>
                </td>
                <td class=" size1of2">
                    <label class="frm_label">Contact name:</label>

                    <div class="frm_controls">
                    <@ctl.text name="custEnterpriseInfo.contactName" required=true value="${(custEnterpriseInfo.contactName)!}" validate={"maxlength":"32"} fieldName="Contact name"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="table_right_border size1of2">
                    <label class="frm_label">Contact phone:</label>

                    <div class="frm_controls">
                    <@ctl.text name="custEnterpriseInfo.cellphone" required=true value="${(custEnterpriseInfo.cellphone)!}" validate={"telephoneOrCellphone":"true"} fieldName="Contact phone"/>
                    </div>
                </td>
                <td class=" size1of2">
                    <label class="frm_label">Email:</label>

                    <div class="frm_controls">
                    <@ctl.text name="custEnterpriseInfo.email"  value="${(custEnterpriseInfo.email)!}" validate={"maxlength":"64","email":"true"}/>
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