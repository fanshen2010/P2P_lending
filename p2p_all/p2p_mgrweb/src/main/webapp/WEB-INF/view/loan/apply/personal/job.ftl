<div id="job_info" class="tab-bd-con hide">
    <div class="form_wrp">
        <table class="table" cellpadding="0" cellspacing="0">
            <tbody class="tbody" >
                <tr>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Company name:</label>
                        <div class="frm_controls">
                            <@ctl.text  name="loanPersonalJobDto.companyName"  required=true  validate={"maxlength":"64"} value="${(loanPersonalJobDto.companyName)!}" fieldName="Company name"/>
                        </div>
                    </td>
                    <td class=" size1of2">
                         <label class="frm_label">Department:</label>
                        <div class="frm_controls">
                            <@ctl.text  name="loanPersonalJobDto.department"  required=true  validate={"maxlength":"32"} value="${(loanPersonalJobDto.department)!}" fieldName="Department"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="table_right_border size1of2">
                         <label class="frm_label">Position:</label>
                        <div class="frm_controls">
                            <@ctl.text  name="loanPersonalJobDto.workPosition"  required=true  validate={"maxlength":"32"} value="${(loanPersonalJobDto.workPosition)!}" fieldName="Position"/>
                        </div>
                    </td>
                    <td class="size1of2">
                        <label class="frm_label">Salary:</label>
                        <div class="frm_controls">
                            <@ctl.text  name="loanPersonalJobDto.monthlyIncome"  counter="dollar" required=true  validate={"maxlength":"13","integerorFloat2":"true"} value="${(loanPersonalJobDto.monthlyIncome)!}" fieldName="Salary"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Company type:</label>
                        <div class="frm_controls">
                        	<@ctl.dropdownlist  name="loanPersonalJobDto.companyCategory"  hasChoice="select"  groupCode="CompanyTypeEnum" dataType="Enum" value="${(loanPersonalJobDto.companyCategory)!}"  class="selectpicker" spanClass="frm_select_picker" />
                        </div>
                    </td>
                    <td class=" size1of2">
                        <label class="frm_label">Company industry:</label>
                        <div class="frm_controls">
                        	<@ctl.dropdownlist  name="loanPersonalJobDto.companyIndustry"  hasChoice="select"  groupCode="CompanyTradesEnum" dataType="Enum" value="${(loanPersonalJobDto.companyIndustry)!}"  class="selectpicker" spanClass="frm_select_picker" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Company scale:</label>
                        <div class="frm_controls">
                        	<@ctl.dropdownlist  name="loanPersonalJobDto.companySize"  hasChoice="select"  groupCode="CompanySizeEnum" dataType="Enum" value="${(loanPersonalJobDto.companySize)!}"  class="selectpicker" spanClass="frm_select_picker" />
                        </div>
                    </td>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Company phone:</label>
                        <div class="frm_controls">
                            <@ctl.text  name="loanPersonalJobDto.companyTel"   validate={"telephoneOrCellphone":"true"} value="${(loanPersonalJobDto.companyTel)!}" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class=" size1of2" colspan="2">
                        <label class="frm_label">Company address:</label>
                        <div class="frm_controls frm_address">
                        <@ctl.text  name="loanPersonalJobDto.address" class="frm_input street" spanClass="medium_l street"  validate={"maxlength":"100"} value="${(loanPersonalJobDto.address)!}" />
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>