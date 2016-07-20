<#--还款方式tag-->
<#macro interestManner productCode=""  value=""
>
<#if productCode!="">
	<#--<#local interestManners=getInterestManners(productCode)>-->
    <label class="frm_label">Interest manner:</label>
    <div class="frm_controls">
    	<@ctl.dropdownlist  name="projectInfoDto.loanInterestManner"  hasChoice="select" groupCode="LoanInterestMannerEnum"  class="selectpicker"  value=value spanClass="frm_select_picker"  required=true dataType="Enum" fieldName="Interest manner"/>
    	<#--<@ctl.dropdownlist  name="projectInfoDto.loanInterestManner"  hasChoice="select"   listData= interestManners class="selectpicker"  value=value spanClass="frm_select_picker"  required=true fieldName="Interest manner"/>-->
    </div>
<#else>
		<label class="frm_label">Interest manner:</label>
        <div class="frm_controls">
        	<@ctl.dropdownlist  name="projectInfoDto.loanInterestManner"  hasChoice="select" groupCode="LoanInterestMannerEnum"  class="selectpicker"  value=value spanClass="frm_select_picker"  required=true dataType="Enum" fieldName="Interest manner"/>
        	<#--<@ctl.dropdownlist  name="projectInfoDto.loanInterestManner"  hasChoice="select"   class="selectpicker" spanClass="frm_select_picker" value=value required=true fieldName="Interest manner"/>-->
        </div>
</#if>

</#macro>