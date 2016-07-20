<@cl.html title="">
<div class="col_main">
	<form action="">
    <div class="main_hd">
    	<div class="clearfix">
            <ul class="direction">
                <li><span class="txt">you are here:</span></li>
                <li><a id="" class="" href="javascript::">index</a><em>|</em></li>
                <li><span class="txt">转出的债权查看</span></li>
            </ul>    
            <h2><@ctl.linkButton class="btn btn_primary" href="${taglibs.allctx}/business/investment/index.htm" title="return" text="return"/>view</h2>
        </div> 
     </div>
    <div class="main_bd">
        <div class="tab">
        	<ul class="tab_navs tab-hd  position_tab title_tab mb20" id="">
                <li class="tab-hd-con tab_nav active first"><a href="javascript:;">Invest Detail</a></li>
                <li class="tab-hd-con tab_nav"><a href="javascript:;">Loan Info</a></li>
                <#if invest.status!="01" && invest.status!="02" && invest.status!="05" && invest.status!="06"  && invest.status!="14"><li class="tab-hd-con tab_nav"><a href="javascript:;">Repay List</a></li></#if>
                <!--<#if invest.status=="10" || invest.status=="11"||invest.status="12">
                <li class="tab-hd-con tab_nav"><a href="javascript:;">债权转让</a></li>
                </#if>-->
            </ul>
            <div class="tab-bd">
            	<!--Invest Detail -->
            	<div class="tab-bd-con active">
                	<#include "${taglibs.ftlctx}/business/investment/investDetails.ftl" />
                </div>
                <!--Loan Info --> 
                <div class="tab-bd-con hide">
                	<#include "${taglibs.ftlctx}/loan/viewtab/loanProjectInfo.ftl" />
                </div>
                <#if invest.status!="01" && invest.status!="02" && invest.status!="05" && invest.status!="06" && invest.status!="14">
                <!--Repay Record -->
                <div class="tab-bd-con hide">
                	<#include "${taglibs.ftlctx}/loan/viewtab/commRepayRecord.ftl" />
                </div>
                </#if>
                <!--提前还款 -->
                <div class="form_wrp">    
                       
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="loanCode" value=${loanCode} />
    </form>
</div>
</@cl.html>