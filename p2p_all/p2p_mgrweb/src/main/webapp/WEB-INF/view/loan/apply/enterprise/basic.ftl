	<div id="base_info" class="tab-bd-con active">
	    <div class="form_wrp">
	        <table class="table" cellpadding="0" cellspacing="0">
	            <tbody class="tbody">
	                <tr>
	                    <td class="size1of2" >
	                        <label class="frm_label">Enterprise name:</label>
	                        <div class="frm_controls"> 
	                            <#if export?? &&export =="1">
	                             	<span class="frm_val">${enterpriseInfoDto.enterpriseName}</span>
	                             	<input type="hidden" name="enterpriseInfoDto.enterpriseName" value="${enterpriseInfoDto.enterpriseName}"/>
                             	<#elseif updateDistinguish?? &&updateDistinguish =="1" >
	                             	<span class="frm_val">${enterpriseInfoDto.enterpriseName}</span>
	                             	<input type="hidden" name="enterpriseInfoDto.enterpriseName" value="${enterpriseInfoDto.enterpriseName}"/>
	                            <#else>
		                            <@ctl.text id="enterpriseName" name="enterpriseInfoDto.enterpriseName"  required=true  validate={"maxlength":"64"} value="${enterpriseInfoDto.enterpriseName}" fieldName="Enterprise name"/>
	                            </#if>
	                        </div>
	                    </td>
	                    <td class="size1of2">
	                        <label class="frm_label">Business license:</label>
	                        <div class="frm_controls"> 
	                            <#if export?? &&export =="1">
	                             	<span class="frm_val">${enterpriseInfoDto.businessIicense}</span>
	                            	<input type="hidden" name="enterpriseInfoDto.businessIicense" value="${enterpriseInfoDto.businessIicense}"/>
                        		<#elseif updateDistinguish?? &&updateDistinguish =="1" >
                            		<span class="frm_val">${enterpriseInfoDto.businessIicense}</span>
	                            	<input type="hidden" name="enterpriseInfoDto.businessIicense" value="${enterpriseInfoDto.businessIicense}"/>
	                            <#else>
		                            <@ctl.text name="enterpriseInfoDto.businessIicense"  required=true  validate={"maxlength":"15"} value="${enterpriseInfoDto.businessIicense}" fieldName="Business license"/>
	                            </#if>
	                        </div>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="table_right_border size1of2">
	                        <label class="frm_label">Setup year:</label>
	                        <div class="frm_controls"> 
	                            <@ctl.text name="enterpriseInfoDto.createdYear"  required=true  validate={"length":"4","positiveInteger":"true"} counter="year" value="${enterpriseInfoDto.createdYear}" fieldName="Setup year"/>
	                        </div>
	                    </td>
	                    <td class=" table_right_border size1of2">
	                        <label class="frm_label">Contact name:</label>
	                        <div class="frm_controls"> 
	                            <@ctl.text name="enterpriseInfoDto.legalRepresentative"  required=true  validate={"maxlength":"32"} value="${enterpriseInfoDto.legalRepresentative}" fieldName="Contact name"/>
	                        </div>
	                    </td>
	                </tr>
	                <tr>
	                    <td class=" size1of2">
	                        <label class="frm_label">Contact phone:</label>
	                        <div class="frm_controls"> 
	                            <@ctl.text name="enterpriseInfoDto.contactPhone"  required=true validate={"cellphone":"true"}  value="${(enterpriseInfoDto.contactPhone)!}" fieldName="Contact phone"/>
	                        </div>
	                    </td>
	                    <td class=" table_right_border size1of2">
	                        <label class="frm_label">Contact email:</label>
	                        <div class="frm_controls"> 
	                            <@ctl.text name="enterpriseInfoDto.contactMail" validate={"maxlength":"64","email":"true"} value="${enterpriseInfoDto.contactMail}" fieldName="Contact email"/>
	                        </div>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="table_right_border size1of2">
	                        <label class="frm_label">Enterprise industry:</label>
	                        <div class="frm_controls"> 
	                            <@ctl.dropdownlist name="enterpriseInfoDto.industry"  hasChoice="select"  groupCode="IndustryEnum"  dataType="Enum"   class="selectpicker" spanClass="frm_select_picker" value="${(enterpriseInfoDto.industry)!}" />
	                            
	                        </div>
	                    </td>
	                    <td class="table_right_border size1of2">
	                        <label class="frm_label">Enterprise scale:</label>
	                        <div class="frm_controls"> 
	                            <@ctl.dropdownlist name="enterpriseInfoDto.enterpriseScale"  hasChoice="select"  groupCode="enterpriseScale" dataType="BizCode"  class="selectpicker" spanClass="frm_select_picker" value="${(enterpriseInfoDto.enterpriseScale)!}" />
	                        </div>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="size1of2">
	                        <label class="frm_label">Registered capital:</label>
	                        <div class="frm_controls"> 
	                            <@ctl.text name="enterpriseInfoDto.registeredCapital"    validate={"maxlength":"13","twoDecimal":"true"} counter="dollar" value="${enterpriseInfoDto.registeredCapital}" />
	                        </div>
	                    </td>
	                    <td class="table_right_border size1of2">
	                        <label class="frm_label">Annual earnings:</label>
	                        <div class="frm_controls"> 
	                            <@ctl.text name="enterpriseInfoDto.annualEarnings"    validate={"maxlength":"13","twoDecimal":"true"} counter="dollar" value="${enterpriseInfoDto.annualEarnings}" />
	                        </div>
	                    </td>
	                </tr>
	                <tr>
	                    <td class=" size1of2" colspan="2">
	                        <label class="frm_label">Enterprise address:</label>
	                        <div class="frm_controls"> 
	                            <@ctl.text name="enterpriseInfoDto.officeAddr" class="frm_input street" spanClass="medium_l street"   validate={"maxlength":"100"} value="${enterpriseInfoDto.officeAddr}" />
	                        </div>
	                    </td>
	                </tr>
	                <tr class="last">
	                    <td class="border_bottom_none size1of1" colspan="2">
	                        <label class="frm_label">Enterprise brief:</label>
	                        <div class="frm_controls"> 
	                            <@ctl.textarea  name="enterpriseInfoDto.brief" validate={"maxlength":"1000"}  value="${(enterpriseInfoDto.brief)!}" class="frm_textarea"  />
	                        </div>
	                    </td>
	                    <input type="hidden" name="export" value="${export}"/>
	                </tr>
	            </tbody>
	        </table>
	    </div>
	</div>