<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${getSettingValue('site_name')}</title>
	<#include "${taglibs.ftlctx}/common/layout/meta.ftl">
	<#include "${taglibs.ftlctx}/common/layout/taglibs.ftl">
	<#include "${taglibs.ftlctx}/common/layout/commonCSS.ftl">
	<#include "${taglibs.ftlctx}/common/layout/commonJS.ftl">
</head>
<body class="theme-yilicai login">
	<div class="container">
	<#if agreeType?length == 0 || agreeType="" ||agreeType=="agreement">
    	<div class="agreement">
    	    <#if category?? && category?length!=0>
            <h1 class="tc">${category.title}</h1>
            <div>${category.content}</div>
            <#else>
            <h1 class="tc">协议加载错误</h1>
            </#if>           
        </div>
    <#elseif agreeType=="contract">    	
    	<div class="agreement agreement-download">
    		<#if contractContent?? && contractContent?length!=0>
    		<div class="ad-link"><#if condition=="true">
    			<#if investCode?? && investCode?length!=0>
	    		<a href="printPdf.htm?loanCode=${loanCode}&investCode=${investCode}" class="fr btn btn-primary">下载</a>
	    		<#else>
	    		<a href="printPdf.htm?loanCode=${loanCode}" class="fr btn btn-primary">下载</a>
	    		</#if>
	    	</div>
	    	</#if>
            <div>${contractContent}</div>
            <#else>
            <h1 class="tc">合同加载错误</h1>
            </#if>           
        </div>    	
    </#if>
    </div>
</body>
</html>