<div id="organize_type_guarantee" class="mt20 hide">
<form action="${taglibs.allctx}/system/organize/save.htm" method="POST" id="searchForm" enctype="multipart/form-data">
	<div class="form_wrp">
        <table class="table" cellpadding="0" cellspacing="0">
            <tbody class="tbody" id="">
                <tr>
                    <td class="table_right_border size1of2" >
                        <label class="frm_label">Name:</label>
                        <div class="frm_controls">
                           <@ctl.text name="pfmTenantDepartment.departmentName" required=true validate={"maxlength":"100","remote":"departmentNameCheck.htm"} fieldName="Name"/>
                        </div>
                    </td>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Type:</label>
                        <div class="frm_controls">
                        <input type="hidden" id="guarantee"  name="pfmTenantDepartment.departmentType" value="" />
                            <span class="frm_val">Guarantee</span>
                        </div>
                    </td>
                </tr>
                
	            <tr>
	                <td class="table_right_border size1of2">
	                    <label class="frm_label">Setup time:</label>
	                    <div class="frm_controls group">
	                        <@ctl.datepicker id="createdDate" validate={"ltTo":"#cooperateDate"} name="pfmTenantDepartmentInfo.createdDate" />
	                    </div>
	                </td>
	                <td class="table_right_border size1of2">
	                    <label class="frm_label">Coop time:</label>
	                    <div class="frm_controls group">
	                        <@ctl.datepicker id="cooperateDate" name="pfmTenantDepartmentInfo.cooperateDate" validate={"gtTo":"#createdDate"} />
	                    </div>
	                </td>
	            </tr>
	            
	            <tr>
	                <td class="table_right_border size1of2" colspan="2">
	                    <label class="frm_label">Registered capital:</label>
	                    <div class="frm_controls">
	                       <@ctl.text name="pfmTenantDepartmentInfo.registeredCapital" required=true counter="dollar" validate={"maxlength":"13","integerorFloat2":"true"} fieldName="Registered capital"/>
	                    </div>
	                </td>
	            </tr>
	            
	            <tr>
	                <td class=" size1of2" colspan="2">
	                    <label class="frm_label">Address:</label>
	                    <div class="frm_controls frm_address">
	                    	<@ctl.text name="pfmTenantDepartmentInfo.address" required=true validate={"maxlength":"50"}  fieldName="Address"/>
	                    </div> 
	                </td>
	            </tr>
	            <tr>
	                <td class=" size1of1" colspan="2">
	                    <label class="frm_label">Brief:</label>
	                    <div class="frm_controls">
	                        <@ctl.textarea  name="pfmTenantDepartmentInfo.introduction"  rows="1" cols="2" validate={"maxlength":"1000"}/>
	                    </div>
	                </td>
	            </tr>
	            <tr>
	                <td class="border_bottom_none size1of2" colspan="2">
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
                <@ctl.submit id="guaranteeSubmit" class="btn btn_primary" text="save"/>
            </div>
        </div>
    </div>
</form>    
</div>