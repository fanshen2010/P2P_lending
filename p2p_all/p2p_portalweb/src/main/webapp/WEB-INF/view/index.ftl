<@cl.html  bodyCss="index">
<#--<#include "${taglibs.ftlctx}/common/layout/banner.ftl"/>-->
<section class="banner">
	<div class="banner-box">
        <ul class="slider">
        <@advertisement_list adverCode="banner" pageSize="4">
        	 <#list AdDtos as addto>
            	<li class="slider-list">
	                <div class="banner-bg"  style="height:340px; background: rgb(0, 103, 204) url(${(addto.uploadFile.fileUrlOriginal)!}) center center no-repeat;"><a href="${addto.advertisement.connectUrl}" target="_blank"class="" style="display:block; height:350px;"></a></div>
	            </li>
            </#list>
        </@advertisement_list>
        </ul>
        <#-- // 未登录状态//登陆后隐藏-->
        <div class="banner-bar">
            <div class="banner-barbox">
            	<h2 class="banner-title">${getSettingValue('site_name')}Interest rates</h2>
                <p class="banner-rate">Up To<strong><em>15.00</em>%</strong></p>
                <p class="banner-rate-t">
                	<em>4 times</em> Current deposit income
                </p>
                <a class="banner-reg" href="${taglibs.ctx}/register.htm" title="">Register</a>
                <p class="banner-log"><a class="" href="${taglibs.ctx}/login.htm" title="">Log in</a>
            </div>
        </div>
    </div>
</section>
<section class="area">
    <div class="container">
        <div class="main">
            <div class="widget invest-index" id="invest_index">
                <div class="widget-head">
                    <h2 class="h2">Invest List</h2>
                    <a class="more" href="${taglibs.ctx}/invest/investList.htm">more</a>
                </div>
                <div class="widget-body">
                    <ul class="investlist">
                    <@loan_list pageSize="4" >
                   		<#--等待共通-->
                    	<#list loans as loan>
	                        <@bizTag.loanObjectLi loan=loan  sysDate=sysDate/>
                        </#list>
                    </@loan_list>
                    </ul>
                </div>
            </div>
        </div>
        <div class="side">
        <#--媒体报道-->
        	<@category_list categoryCode="news_2nd" pageSize="5" updateTimeOrder="0">
	            <div class="widget media" id="media">
	                <div class="widget-head">
	                    <h2 class="h2">${category.title}</h2>
	                    <a class="more" href="${taglibs.allctx}/aboutus/index.htm?aboutUsDto.category.id=${category.id}">more</a>
	                </div>
	                <div class="widget-body">
	                    <ul class="list">
	                        <#list articles as mediaReport>
	                        	<li><a class="" href="${taglibs.allctx}/aboutus/view.htm?aboutUsDto.article.id=${mediaReport.id}&aboutUsDto.category.id=${mediaReport.category}" title="">${mediaReport.title}</a></li>
	                        </#list>
	                    </ul>
	                </div>
	            </div>
            </@category_list>
            <#--平台公告-->
            <@category_list categoryCode="notice_2nd" pageSize="5" updateTimeOrder="1">
            <div class="widget notice" id="notice">
                <div class="widget-head">
                    <h2 class="h2">${category.title}</h2>
                    <a class="more" href="${taglibs.allctx}/aboutus/index.htm?aboutUsDto.category.id=${category.id}">more</a>
                </div>
                <div class="widget-body">
                    <ul class="list">
                        <#list articles as announcement>
                        	<li><a class="" href="${taglibs.allctx}/aboutus/view.htm?aboutUsDto.article.id=${announcement.id}&aboutUsDto.category.id=${announcement.category}" title="">${announcement.title}</a></li>
                        </#list>
                    </ul>
                </div>
            </div>
            </@category_list>
        </div>
    </div>
</section>
<#--<#include "${taglibs.ftlctx}/common/layout/friendly_links.ftl"/>   //TODOBUG-->
<script type="text/javascript">
    $(function(){
        $(".slider").bxSlider({mode: 'fade',controls: true,infiniteLoop: true,auto: true, autoHover: true});
    });
</script>
</@cl.html>