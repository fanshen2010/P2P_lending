<#--自定义控件的校验属性部分-->
<#if required?? && required>
data-rule-required="true" <#rt/>
</#if>
<#if validate?? && validate?size gt 0>
<#list validate?keys as key>
data-rule-${key}="${validate[key]}" <#rt/>
</#list>
</#if>