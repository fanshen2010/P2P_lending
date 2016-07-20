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
            <form action="${taglibs.allctx}/system/organize/update.htm" method="post" id="search_form" enctype="multipart/form-data">
                <div id="" class="tab-bd-con active">
                    <div class="form_wrp">
                    	<table class="table" cellpadding="0" cellspacing="0">
                            <tbody class="tbody" id="">
                            <input type="hidden" name="pfmTenantDepartment.departmentCd" value="${pfmTenantDepartment.departmentCd}"/>
                            <input type="hidden" name="pfmTenantDepartment.tenantCd" value="${pfmTenantDepartment.tenantCd}"/>
                            <input type="hidden" name="pfmTenantDepartmentInfo.departmentCd" value="${pfmTenantDepartmentInfo.departmentCd}"/>
                            <input type="hidden" name="pfmTenantDepartmentInfo.tenantCd" value="${pfmTenantDepartmentInfo.tenantCd}"/>
                                <tr>
                                    <td class="table_right_border size1of2">
                                        <label class="frm_label">Name:</label>
                                        <div class="frm_controls">
                                             <@ctl.text name="pfmTenantDepartment.departmentName" value="${pfmTenantDepartment.departmentName}" required=true validate={"maxlength":"100","remote":"departmentNameCheck.htm?pfmTenantDepartment.departmentCd=${pfmTenantDepartment.departmentCd}"} fieldName="Name"/>
                                        </div>
                                    </td>
                                    <td class="table_right_bordersize1of2">
                                        <label class="frm_label">Type:</label>
                                        <div class="frm_controls">
                                        <input type="hidden" name="departmentType" value="${pfmTenantDepartment.departmentType}"/>
                                            <span class="frm_val">${getCodeName("departStatus",pfmTenantDepartment.departmentType)}</span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="table_right_border size1of2">
                                        <label class="frm_label">Setup time:</label>
                                        <div class="frm_controls group">
                                        <@ctl.datepicker name="pfmTenantDepartmentInfo.createdDate" value="${(pfmTenantDepartmentInfo.createdDate?string('yyyy-MM-dd'))!}"  />
                                        </div>
                                    </td>
                                    <td class="table_right_border size1of2">
                                        <label class="frm_label">Coop time:</label>
                                        <div class="frm_controls group">
                                        <@ctl.datepicker name="pfmTenantDepartmentInfo.cooperateDate" value="${(pfmTenantDepartmentInfo.cooperateDate?string('yyyy-MM-dd'))!}"  />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="table_right_border size1of2" colspan="2">
                                        <label class="frm_label">Registered capital:</label>
                                        <div class="frm_controls">
                                            <@ctl.text name="pfmTenantDepartmentInfo.registeredCapital" required=true validate={"maxlength":"13","integerorFloat2":"true"} value="${pfmTenantDepartmentInfo.registeredCapital}" counter="dollar" fieldName="Registered capital"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class=" size1of2" colspan="2">
                                        <label class="frm_label">Address:</label>
                                        <div class="frm_controls frm_address">
                                        	<@ctl.text name="pfmTenantDepartmentInfo.address" required=true validate={"maxlength":"50"} value="${pfmTenantDepartmentInfo.address}" fieldName="Address"/>
                                        </div> 
                                    </td>
                                </tr>
                                <tr>
                                    <td class=" size1of1" colspan="2">
                                        <label class="frm_label">Brief:</Briefl>
                                        <div class="frm_controls">
                                            <@ctl.textarea  name="pfmTenantDepartmentInfo.introduction"  rows="1" cols="2" validate={"maxlength":"1000"} required=false value="${pfmTenantDepartmentInfo.introduction}" />
                                        </div>
                                    </td>
                                </tr>
                                <tr class="last">
                                    <td class="border_bottom_none size1of2" colspan="2">
                                        <label class="frm_label">Status:</label>
                                        <div class="frm_controls">
                                            <@ctl.radiobuttonlist groupCode="status" name="pfmTenantDepartment.vilidFlag" dataType="BizCode" value="${pfmTenantDepartment.vilidFlag}" spanClass="frm_radio_box uniformjs"/>
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