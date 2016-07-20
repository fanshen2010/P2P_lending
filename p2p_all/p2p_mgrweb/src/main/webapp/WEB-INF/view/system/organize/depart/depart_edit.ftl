<@cl.html title="">
<div class="col_main">
    <div id="main_hd" class="main_hd"> 
    	<div class="clearfix">
            <h2><@ctl.linkButton href="index.htm" title="return" text="return"/>set</h2>
        </div>
    </div>
    <div class="main_bd">
    	<div class="tab">
            <div class="tab-bd">
            <form action="${taglibs.allctx}/system/organize/update.htm" method="post" id="search_form">
                <div id="" class="tab-bd-con active">
                    <div class="form_wrp">
                        <table class="table" cellpadding="0" cellspacing="0">
                            <tbody class="tbody" id="">
                            <input type="hidden" name="pfmTenantDepartment.departmentCd" value="${pfmTenantDepartment.departmentCd}"/>
                            <input type="hidden" name="pfmTenantDepartment.tenantCd" value="${pfmTenantDepartment.tenantCd}"/>
                                <tr>
                                    <td class="size1of1">
                                        <label class="frm_label">Name:</label>
                                        <div class="frm_controls">
                                            <@ctl.text name="pfmTenantDepartment.departmentName" value="${pfmTenantDepartment.departmentName}" required=true validate={"maxlength":"100","remote":"checkName.htm?pfmTenantDepartment.departmentCd=${pfmTenantDepartment.departmentCd}"} fieldName="Name"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="size1of1">
                                        <label class="frm_label">Type:</label>
                                        <div class="frm_controls">
                                        	<input type="hidden" name="departmentType" value="${pfmTenantDepartment.departmentType}"/>
                                            <span class="frm_val">${getCodeName("departStatus",pfmTenantDepartment.departmentType)}</span>
                                        </div>
                                    </td>
                                </tr>
                                <tr class="last">
                                    <td class="border_bottom_none size1of1">
                                        <label class="frm_label">Status:</label>
                                        <div class="frm_controls">
                                            <@ctl.radiobuttonlist groupCode="status" name="pfmTenantDepartment.vilidFlag" value="${pfmTenantDepartment.vilidFlag}" dataType="BizCode" spanClass="frm_radio_box uniformjs"/>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <div class="form_btn">
                            <div class="fr">
                                <@ctl.submit id="save_btn" class="btn btn_primary" text="save"/>
                            </div>
                        </div>
                    </div>
                </div>
               </form>
            </div>
        </div>
    </div>
</div>
</@cl.html>