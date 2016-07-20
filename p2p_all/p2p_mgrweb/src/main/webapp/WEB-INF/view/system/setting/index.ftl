<@cl.html title="">
<div class="col_main">
    <div class="main_hd" id="main_hd">
    	<div class="clearfix">
            <h2>系统参数配置</h2>
        </div> 
     </div>
    <div class="main_bd">
    	<div class="tab">
        	<ul class="tab_navs tab-hd  position_tab title_tab" id="originalTab">
                <li class="tab-hd-con tab_nav active first"><a href="javascript:;">站点基本设置</a></li>
                <li class="tab-hd-con tab_nav"><a href="javascript:;">业务参数设置</a></li>
            </ul>
            <div class="tab-bd">
            	<!-- 站点基本设置 -->
            	<#include "${taglibs.ftlctx}/system/setting/basic.ftl" />
                <!-- 业务参数设置 -->
                <#include "${taglibs.ftlctx}/system/setting/business.ftl" />
            </div>
        </div> 
    </div>
</div>
</@cl.html>