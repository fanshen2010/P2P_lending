<@cl.html title="">
<div class="col_main">
    <div id="main_hd" class="main_hd">
    	<div class="clearfix">
            <ul class="direction">
                <li><span class="txt">you are here:</span></li>
                <li><a id="" class="" href="javascript::">index</a><em>|</em></li>
                <li><span class="txt">提前还款管理查看</span></li>
            </ul>    
            <h2><@ctl.linkButton class="btn btn_primary" href="${taglibs.allctx}/business/project/index.htm" title="return" text="return"/>view</h2>
        </div> 
     </div>
    <div class="main_bd">
        <div class="tab">
        	<ul class="tab_navs tab-hd  position_tab title_tab" id="originalTab">
                <li class="tab-hd-con tab_nav  active first"><a href="javascript:;">Loan Info</a></li>
                <li class="tab-hd-con tab_nav"><a href="javascript:;">Invest Record</a></li>
                <li class="tab-hd-con tab_nav"><a href="javascript:;">Repay Record</a></li>
                <li class="tab-hd-con tab_nav"><a href="javascript:;">Repay Statistic</a></li>
            </ul>
            <div class="tab-bd">
            	<!-- Loan Info  -->
            	<div class="tab-bd-con active">
            		<#include "${taglibs.ftlctx}/loan/viewtab/loanProjectInfo.ftl" />
            	</div>
            	<!-- Invest Record  -->
                <div class="tab-bd-con hide">
                   <#include "${taglibs.ftlctx}/loan/viewtab/investInformation.ftl" /> 
                </div>
                <!-- Repay Record  -->
                <div class="tab-bd-con hide">
                   <#include "${taglibs.ftlctx}/loan/viewtab/commRepayRecord.ftl" />
                </div>
                <!-- Repay Statistic  -->
            	<div class="tab-bd-con hide">
                	<#include "${taglibs.ftlctx}/loan/viewtab/repayCondition.ftl" />
                </div>
            </div>
        </div>
    </div>
</div>
</@cl.html>