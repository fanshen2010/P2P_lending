<div id="organize_type_branch" class="mt20 hide">
 <form action="${taglibs.allctx}/system/organize/save.htm" method="POST" id="searchForm">
    <div class="form_wrp">
        <table class="table" cellpadding="0" cellspacing="0">
            <tbody class="tbody" id="">
                <tr>
                    <td class="size1of1">
                        <label class="frm_label">Name:</label>
                        <div class="frm_controls">
                          <input type="hidden" id="branch"  name="pfmTenantDepartment.departmentType" value="" />
                          <@ctl.text name="pfmTenantDepartment.departmentName" required=true validate={"maxlength":"100","remote":"checkName.htm"} fieldName="Name"/>  
                        </div>
                    </td>
                </tr>
                <tr class="last">
                    <td class="border_bottom_none size1of1">
                        <label class="frm_label">Status:</label>
                        <div class="frm_controls">
                        	<@ctl.radiobuttonlist groupCode="status" name="pfmTenantDepartment.vilidFlag" dataType="BizCode" value="1" spanClass="frm_radio_box uniformjs"/>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="form_btn">
            <div class="fr">
                <@ctl.submit class="btn btn_primary" text="save"/>
            </div>
        </div>
    </div>
 </form>   
</div>