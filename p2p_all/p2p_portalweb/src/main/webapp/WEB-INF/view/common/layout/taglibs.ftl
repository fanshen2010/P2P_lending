<#if request ??>
<#assign ctx = request.contextPath/>
<#assign requestURI = request.requestURI/>
<#--ctx=${ctx}-->
<#--requestURI=${request.requestURL}-->
<#--
<#if request.contextPath == "/">
	<#assign allctx = "http://" + request.serverName  + ":" + request.serverPort?c />
</#if>

<#if request.contextPath != "/">
	<#assign allctx = "http://" + request.serverName  + ":" + request.serverPort?c + request.contextPath/>
</#if>
-->
<#--allctx=${allctx}-->
<#assign ftlctx = "/${commonResultPath}"/>
<#assign allctx = request.contextPath/>

<#assign s=JspTaglibs["/META-INF/struts-tags.tld"]>

</#if>
