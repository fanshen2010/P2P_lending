<#--
	linkButton标签，以<a></a>的方式实现的按钮
	<@ctl.linkButton id class href title text isFancybox float />
-->
<#macro linkButton
	id="" class="btn btn_primary" href="javascript:;" title="" text="" isFancybox=false float="" data={}
>
<#if !title?? || title=="">
	<#local title=text>
</#if>

<#if isFancybox?? && isFancybox>
	<#local class=class+" fancybox">
</#if>

<#if float?? && float!="">
	<#local class=class+" "+float>
</#if>
<a id="${id!}" class="${class!} " href="${href!}" title="${title!}" <#rt/>
<#if data?? && data?size gt 0>
<#list data?keys as key>
	data-${key}="${data[key]}" <#rt/>
</#list>
</#if>
>${text!}</a>
</#macro>

<#--
	operateButton标签，列表页上的操作按钮
	<@ctl.operateButton id class linkType href title text isFancybox />
-->
<#macro operateButton
	id="" class="operate_icon" linkType="view" href="javascript:;" title="" text="" isFancybox=false data={}	
>
<#if linkType?? && linkType!="">
	<#local class=class+" "+linkType>
</#if>
<@ctl.linkButton id=id class=class href=href title=title text=text isFancybox=isFancybox data=data />
</#macro>

<#--
	删除按钮
-->
<#macro delButton
	id="" class="operate_icon del" href="javascript:;" title="" text="" data={}	
>
<@ctl.linkButton id=id class=class href=href title=title text=text isFancybox=false data=data />
</#macro>

<#--
	删除按钮
-->
<#macro statusButton
	status="1" isView=false id="" href="javascript:;" name="status"
>
<#if status?? && status == "1">
<#--当前状态是启用，显示禁用按钮-->
	<#if isView?? && isView>
	<span class="mark_green dev_status">enabled</span>
	<#else>
	<@ctl.linkButton id=id class="operate_icon dev_btn_status disabled" href=href text="disable" isFancybox=false data={"${name}": "0"} />
	</#if>
<#else>
<#--当前状态是禁用，显示启用按钮-->
	<#if isView?? && isView>
	<span class="mark_red dev_status">disabled</span>
	<#else>
	<@ctl.linkButton id=id class="operate_icon dev_btn_status enabled" href=href text="enable" isFancybox=false data={"${name}": "1"} />
	</#if>
</#if>

</#macro>