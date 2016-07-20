<#--
	普通button按钮
	<@ctl.button id class title text float type />
-->
<#macro button
	id="" class="btn btn_primary" title="" text="" float="" type="button"
>
<#if !title?? || title=="">
	<#local title=text>
</#if>

<#if float?? && float!="">
	<#local class=class+" "+float>
</#if>
<button id="${id!}" type="${type!}" class="${class!}" title="${title!}">${text!}</button>
</#macro>

<#--
	submit按钮
	<@ctl.submit id class title text float />
-->
<#macro submit
	id="" class="btn btn_primary" title="" text="" float=""
>
<#if !title?? || title=="">
	<#local title=text>
</#if>

<#if float?? && float!="">
	<#local class=class+" "+float>
</#if>
<@ctl.button id=id class=class title=title text=text float=float type="submit" />
</#macro>