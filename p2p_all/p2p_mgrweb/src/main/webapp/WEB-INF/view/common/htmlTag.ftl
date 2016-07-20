<#--共通工具标签，禁止添加业务相关标签-->
<#-- 
	输出校验错消息
 -->
<#macro error msg="">

    <#if msg?? && msg?size gt 0>
        <div class="form-prompt-txt error" style="width: 250px">
            <#list msg as ms>
                <p class="fpt-li">
                    <#assign msArray=ms.split(":")/>
                    <span class="fpt-label">${msArray[0]}</span>
                    <span class="fpt-val">${msArray[1]}</span>
                </p>
            </#list>
        </div>
    </#if>
</#macro>

<#macro stringLength value  len >
    <#if value?length lt len>
    ${value}<#rt>
    <#else>
    ${value[0..len-1]}...<#rt>
    </#if>
</#macro>

<#--日期格式化  yyyy-MM-dd hh:mm:ss  -->
<#macro datetimef value="">
    <#if value?is_date>
    	${value?string("yyyy-MM-dd HH:mm:ss")}<#rt>
    </#if>
</#macro>

<#--日期格式化  yyyy-MM-dd  -->
<#macro datef value="" format="yyyy-MM-dd" default="">
    <#if value?? && value?is_date>
    	${value?string(format)}<#rt>
    <#else>
    	${default}
    </#if>
</#macro>

<#-- 数字格式化  带有两位小数-->
<#macro numf value="">
    <#if value=="">
    ${0?string(",##0.00")}<#rt>
    <#else>
    ${value?string("#,##0.00")}<#rt>
    </#if>
</#macro>

<#macro urlEncode url="">
    <#lt>${encodeURL("${url}")}<#rt>
</#macro>
<#macro hiddenEncode value="">
    <#lt>${dataEncrypt("${value}")}<#rt>
</#macro>