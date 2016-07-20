<#--
HTML文件框架
	title:标题（列头）。直接显示字符串。默认""。
	css:本页自定义css（多个时以“|”分割）
	js:本页自定义JS（多个时以“|”分割）
-->
<#macro html title="" css="" js="" action="">
<!doctype html>
<html>
	<head>
	<#--<link rel="icon" href="${sysSettingValue('mgr_icon')?default('javascript:void(0);')}" mce_href="${sysSettingValue('mgr_icon')}" type="image/x-icon">
		<#if title!="">
			<title>${title}</title>
		<#else>
			<title>${sysSettingValue('site_name')}</title>
		</#if>
		-->
		<#include "${taglibs.ftlctx}/common/layout/meta.ftl">
		<#include "${taglibs.ftlctx}/common/layout/taglibs.ftl">
		<#include "${taglibs.ftlctx}/common/layout/commonCSS.ftl">
		<#if css!=""><#list css?split("|") as s><link href="${taglibs.ctx}/style/css/${s}" rel="stylesheet" type="text/css" /></#list></#if>
		<#include "${taglibs.ftlctx}/common/layout/commonJS.ftl">
		<#if js!="">
		<#list js?split("|") as s>
		<script src="${taglibs.ctx}/style/js/${s}" type="text/javascript"></script>
		</#list>
		</#if>
		
	</head>
    <body class="zh_CN">
    	<#include "${taglibs.ftlctx}/common/layout/top.ftl"/>
        <div id="body" class="body">
            <div id="js_container_box" class="container_box cell_layout side_l">
           		<#include "${taglibs.ftlctx}/common/layout/menu_new.ftl"/>
				<#nested>
            </div>
        </div>
        <#include "${taglibs.ftlctx}/common/layout/foot.ftl"/>
        <@ctl.error />
   </body>
</html>
</#macro>

<#--
HTML文件框架
	title:标题（列头）。直接显示字符串。默认""。
	css:本页自定义css（多个时以“|”分割）
	js:本页自定义JS（多个时以“|”分割）
-->
<#macro htmlPop title="" css="" js="">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<#assign ftlctx="/${commonResultPath}">
	<head>
		<title>${title}</title>
		<#include "${taglibs.ftlctx}/common/meta.ftl">
		<#include "${taglibs.ftlctx}/common/commonCSS.ftl">
		<script src="${taglibs.ctx}/style/js/jquery.js"></script>
		<script src="${taglibs.ctx}/style/js/jquery-ui.js"></script>
		<script src="${taglibs.ctx}/style/js/jquery.toggle.buttons.js"></script>
		<script src="${taglibs.ctx}/style/js/jquery.uniform.js"></script>
		<script src="${taglibs.ctx}/style/js/bootstrap.js"></script>
		<#if js!="">
		<#list js?split("|") as s>
		<script src="${taglibs.ctx}/style/js/${s}" type="text/javascript"></script>
		</#list>
		</#if>
		<style>
			body{background:none;}
		</style>
	</head>
	<body>
		<div class="container-fluid theme-327cd0" style="background:#f9f9f9;">
        	<div id="main">
           		<@h.error msg=errorMsg/>
				<#nested>
			</div>
		</div>           
	</body>
</html>
</#macro>

<#--
表格列标签:展示数据列。
	title:标题（列头）。直接显示字符串。默认""。
	code:标题（列头）。显示国际化信息。默认""。
	width:列宽。默认""。
	align:对齐方式。
	class:css样式class
	style:css样式style
-->
<#macro nav id="" name="" title="" code="" width="" align="" class="" style="" onDblClick="">
<div class="curnav mcur_nav">
    <em class="mcur_navt">you are here:</em>
    <i class="icon-home mcur_iconh"></i>
    <a href="${taglibs.allctx}/personal/todolist/index.htm" title="" style="text-decoration: underline;">index</a>
    
    <@nav_list url=requestURI serverName=ctx>
	    <#list fmList as fm >
		    	<span class="line_normal">|</span>
		    	<#if fm_index=1>
			    <a href="${taglibs.allctx}${fm.menuUrl}" title="" style="text-decoration: underline;">${fm.menuName}</a>
		    	<#else>
			    <span style="color:#333">${fm.menuName}<span>
			    </#if>
	    </#list>
    </@nav_list>
</div>
</#macro>

<#--
页面头部标题栏标签
	title:头部标题
	addUrl:添加页面的URL
	returnUrl:返回页面的URL
	editUrl:编辑页面URL
	addTitle:添加页面的标题，默认为“添加”
	returnTitle:返回页面的标题，默认为“返回”
	editTitle:编辑页面标题，默认为“编辑”
-->
<#macro pageTitle title="" addUrl="" returnUrl="" editUrl="" addTitle="add" returnTitle="return" editTitle="modify">
<div class="item-button item-heading">
    <h3>${title}</h3>
    <#if collect||mat>
    	<#if isUnified == 0>		<#--当前为统一账户平台不显示余额-->
    	<#if loanDetail.loan.repayWay.dictCode[0]=="1">
	  	<h4>账户余额:<@h.numf value=acountAmount />dollar</h4>
	  	</#if>
	  	</#if>
	</#if>
    <div class="pull-right item-hbtn">
    	<#nested>
    <#if returnUrl!="">
        <a href="${returnUrl}" title="" class="btn btn-primary btn-icon circle_plus">
        	<i class="icon-backward  icon-white icon_mt03"></i>
          		  ${returnTitle}
        </a>
    </#if>
    <#if addUrl!="">
        <a href="${addUrl}" title="" class="btn btn-primary btn-icon circle_plus">
        	<i class="icon-plus icon-white icon_mt03"></i>
          		  ${addTitle}
        </a>
    </#if>
    <#if editUrl!="">
        <a href="${editUrl}" title="" class="btn btn-primary btn-icon circle_plus">
        	<i class="icon-edit icon-white icon_mt03"></i>
          		${editTitle}
        </a>
    </#if>
   </div> 
</div>
</#macro>


<#--
HTML文件框架
	title:标题（列头）。直接显示字符串。默认""。
	css:本页自定义css（多个时以“|”分割）
	js:本页自定义JS（多个时以“|”分割）
-->
<#macro login title="" css="" js="">
<!doctype html>
<html>
	<#assign ftlctx="/${commonResultPath}">
	<head>
		<#include "${taglibs.ftlctx}/common/layout/meta.ftl">
		<#include "${taglibs.ftlctx}/common/layout/taglibs.ftl">
		<link onerror="wx_loaderror(this)" rel="stylesheet" type="text/css" href="${taglibs.ctx}/style/css/layout_head.css">
        <link onerror="wx_loaderror(this)" rel="stylesheet" type="text/css" href="${taglibs.ctx}/style/css/base.css">
        <link onerror="wx_loaderror(this)" rel="stylesheet" type="text/css" href="${taglibs.ctx}/style/css/lib.css">
        <link onerror="wx_loaderror(this)" rel="stylesheet" type="text/css" href="${taglibs.ctx}/style/css/content.css">
		<#if css!=""><#list css?split("|") as s><link href="${taglibs.ctx}/style/css/${s}" rel="stylesheet" type="text/css" /></#list></#if>
		<script type="text/javascript" src="${taglibs.ctx}/style/js/jquery.js"></script>
		<#if js!="">
		<#list js?split("|") as s>
		<script src="${taglibs.ctx}/style/js/${s}" type="text/javascript"></script>
		</#list>
		</#if>
		
	</head>
    <body class="zh_CN";">
    	<div class="head" id="header">
			<div class="head_box">
				<div class="inner wrp">
					<h1 class="logo"><a href="home.html" title="汇融投融资平台"></a></h1>
               	</div>
             </div>
        </div>
        <@ctl.error />
		<#nested>    
        <div class="foot_singlepage" id="footer">
            <ul class="links ft">
                <li class="links_item"><p class="copyright"> Copyright | All rights reserved</p> </li>
            </ul>
		</div>
   </body>
</html>
</#macro>

<#--
功能标题标签
-->
<#macro searchForm>
<div class="search_form">
	<#nested>
</div>
</#macro>
<#macro selectPop>
	<#include "${taglibs.ftlctx}/common/control/selectPop.ftl"/>
</#macro>
<#macro pop>
	<#include "${taglibs.ftlctx}/common/control/pop.ftl"/>
</#macro>