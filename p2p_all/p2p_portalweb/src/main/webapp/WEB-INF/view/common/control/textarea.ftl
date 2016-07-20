<#--
普通文本域
<@ctl.textarea id name class title tabindex value rows cols required validate />
-->

<#macro textarea
	id="" name="" class="frm_textarea" title="" tabindex="" value="" rows="" cols=""
	required=false validate={} idEditor=false fieldName="" >
	<#if idEditor?? && idEditor>
		<textarea id="${id!}" name="${name!}" title="${title!}" tabindex="${tabindex!}" class="${class!}" rows="${rows!}" cols="${cols}"<#rt/>
		<#include "${taglibs.ftlctx}/common/control/validate_attributes.ftl"><#rt>
		<#if fieldName?? && fieldName!=""> data-field_name="${fieldName}" </#if><#rt>
		>${value!}</textarea>
    <#else>
    	<span class="frm_textarea_box">
			<textarea id="${id!}" name="${name!}" title="${title!}" tabindex="${tabindex!}" class="${class!}" rows="${rows!}" cols="${cols}"<#rt/>
			<#include "${taglibs.ftlctx}/common/control/validate_attributes.ftl"><#rt>
			<#if fieldName?? && fieldName!=""> data-field_name="${fieldName}" </#if><#rt>
			>${value!}</textarea>
	    </span>
    </#if>
    <#if required?? && required>
    <span class="required-star">*</span>
    </#if>
</#macro>
