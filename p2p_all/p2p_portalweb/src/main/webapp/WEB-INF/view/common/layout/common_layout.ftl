<#--
HTML文件框架
	title:标题（列头）。直接显示字符串。默认""。
	css:本页自定义css（多个时以“|”分割）
	js:本页自定义JS（多个时以“|”分割）
	pateType:页面类型，根据不同参数调用不同模块，默认为自定义模块（personal:个人中心模块）
-->
<#macro html title="" css="" js="" action="" bodyCss="" pateType="">
<!doctype html>
<html>
	<head>
	<link rel="icon" href="mgr_icon.png" mce_href="mgr_icon.png" type="image/x-icon">
		<#if title!="">
			<title>${title}</title>
		<#else>
			<title>${getSettingValue('site_name')}</title>
		</#if>
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
		<script>
			var _hmt = _hmt || [];
			(function() {
			  var hm = document.createElement("script");
			  hm.src = "//hm.baidu.com/hm.js?${getSettingValue('baidu_key')}";
			  var s = document.getElementsByTagName("script")[0]; 
			  s.parentNode.insertBefore(hm, s);
			})();
		</script>
		<#assign actionUrl=request.requestURI>
		<script type="text/javascript">
			$(function(){
				<#if actionUrl?index_of("/")==actionUrl?last_index_of("/")>
					$("nav.nav ul li a[href='${actionUrl}']").addClass("active");
	    		<#else>
	    			<#assign actionUrl=actionUrl.substring(actionUrl?index_of("/"),actionUrl?index_of("/",1))>
	    			$("nav.nav ul li a[href^='${actionUrl}']").addClass("active");
	    		</#if>
			});
		</script>
	</head>
    <body class="theme-yilicai ${bodyCss}">
    	<#include "${taglibs.ftlctx}/common/layout/top.ftl"/>
    	<#if pateType="personal">
    		<section class="area">
				<div class="container">
			    	<div class="usercenter">
			            <#include "${taglibs.ftlctx}/common/layout/menu_personal.ftl"/>
			            <#nested>
			        </div>
			    </div>
			</section>
    	<#else>
    		<#nested>
    	</#if>
    	<#--  <#include "${taglibs.ftlctx}/common/layout/foot.ftl"/>//TODOBUG-->
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
    <a href="${taglibs.allctx}/personal/todolist/index.htm" title="" style="text-decoration: underline;">Home</a>
    
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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<#assign ftlctx="/${commonResultPath}">
	<head>
	<link rel="icon" href="" type="image/x-icon">
		<title>${title}</title>
		<link href="${taglibs.ctx}/style/css/jquery.fancybox.css" rel="stylesheet">
		<#include "${taglibs.ftlctx}/common/layout/meta.ftl">
		<#--<#include "${taglibs.ftlctx}/common/commonCSS.ftl">-->
		<#--<#if css!=""><#list css?split("|") as s><link href="${taglibs.ctx}/${s}" rel="stylesheet" type="text/css" /></#list></#if>-->
		<#include "${taglibs.ftlctx}/common/layout/commonJS.ftl">
		<#if js!="">
		<#list js?split("|") as s>
		<script src="${taglibs.ctx}/style/js/${s}" type="text/javascript"></script>
		</#list>
		</#if>
		<style type="text/css">
			*,html,body,form,input{margin: 0;padding: 0;}
			body,input{font: 12px "微软雅黑",Arial,Helvetica,sans-serif;color:#555;}
			.login{ width:100%; height:100%; background: #eaeaea;}
			.login_ui{ width:310px; margin:100px auto 0; background:#fff; border:1px solid #ddd; border-radius:5px; }
			.login_tit{background-color: #327cd0; border-radius: 5px 5px 0 0; color: #fff; font-size: 16px; padding: 25px; line-height:24px; text-align: center; text-transform: uppercase;}
			.login_ul{ background: #fff; padding: 25px 25px 0; position: relative;}
			.arrow{ position:absolute; top:-6px; left:150px; width:0; height:0; border-bottom:6px solid #fff; border-left:6px solid transparent;  border-right:6px solid transparent; }
			.login_txt{ display:block; margin:0 0 10px; font-size:14px;}
			.input_text{ border: 1px solid #dcdcdc; padding: 10px; width: 238px; border-radius: 3px 3px 0 0; font-size: 14px; color:#191919; }
			.input_text_third{ border: 1px solid #dcdcdc; padding: 10px; width: 100px; border-radius: 3px 3px 0 0; font-size: 14px; color:#191919; }
			.input_text:focus {outline: none;}
			.input_text.second{ border-radius: 0 0 3px 3px ; border-top:0; *margin-top:-1px; }
			.login_btn{  margin-top:20px; background: #fff; border-radius: 0 0 5px 5px; padding: 0 25px 10px; overflow:hidden;}
			.input_btn{border-radius: 3px; color: #fff; font-weight: bold; padding: 12px 20px; font-size: 14px; cursor:pointer; border:0;}
			.sub_btn{ float: left; width:70px; height:40px; padding:0; line-height:40px; text-align:center; background: #327cd0;  }
			.sub_btn.pull-right{float: right;}
			.sub_btn:hover{ background:#2864a8;}
			.error-msg{ display:block; clear:both; padding:0 25px 20px; color:#f00;}
			.psd-img{ margin-top:20px;  }
			.psd-img img{ display:inline-block; margin-left:37px; height:40px; vertical-align:top; text-align:right;}
			.psd-img .input_text{ width:100px; border-radius:3px;  }
		</style>		
	</head>
	<body class="login">
	    <@h.error msg=errorMsg/>
		<#nested>          
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