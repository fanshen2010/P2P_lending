<@cl.html  js="bussiness/invest.js" bodyCss="invest-channel">
	<section class="area">
		<form action="${taglibs.requestURI}" id="searchForm" method="POST"/>
        	<div class="container">
            	<div class="tab invest-channel-list">
            		<#--
            		<div class="tab-hd">
                        <a href="${taglibs.ctx}/invest/investList.htm" title="" class="tab-hd-con active">投资项目列表</a>
                        <a href="${taglibs.ctx}/invest/transferList.htm" title="" class="tab-hd-con ">债权转让</a>
                    </div>
                    -->
                    <div class="tab-bd">
                        <div class="tab-bd-con active">
                            <div class="filter invest-filter clearfix" id="filter">
                                <div class="filter-hd clearfix">
                                    <span class="tit">searching criteria</span>
                                    <a class="reset" href="${taglibs.requestURI}">reset</a>
                                </div>
                                <dl class="filter-list clearfix">
                                    <dt>Loan Limit:</dt>
                                    <dd>
                                        <a class="investList" data-id="loanTimeLimit" data-father="loanTimeLimit" data-key="none" href="javascript:;" title="">All</a>
                                        <#list loanTimeLimitList?keys as key>
											<a class="investList" data-id="loanTimeLimit" data-father="loanTimeLimit" data-key="${key}" href="javascript:;" title="">${loanTimeLimitList[key]}</a>
										</#list>
										<input type="hidden" name="loanTimeLimit" id="loanTimeLimit" value="${(loanTimeLimit)!}"/>
                                    </dd>
                                </dl>
                                <dl class="filter-list clearfix">
                                    <dt>Interest Manner:</dt>
                                    <dd>
                                        <a class="investList" data-id="loanInterestManner" data-father="loanInterestManner" data-key="none" href="javascript:;" title="">All</a>
                                    	<#list rstList?keys as key>
											<a class="investList" data-id="loanInterestManner" data-father="loanInterestManner" data-key="${key}" href="javascript:;" title="">${rstList[key]}</a>
										</#list>
										<input type="hidden" name="loanInterestManner" id="loanInterestManner" value="${(loanInterestManner)!}"/>
                                    </dd>
                                </dl>
                            </div>
                            <div class="invest-list">
                                <div class="widget" id="">
                                    <div class="widget-head">
                                        <h2 class="h2"></h2>
                                        <a class="more" href="javascript:;">more &lt;</a>
                                    </div>
                                    <div class="widget-background">
                                    	<#if criteria.page.currentPage =="1">
                                    	<ul class="investlist stay-tuned">
                                    	<@loan_notice_list pageSize="1">
                                    		<#list loans as loan>
	                                            <li>
	                                                <div class="project-name clearfix">
		                                                <#--是否有担保标识-->
												    	<#if loan.guaranteeComCode??>
												    		<i class="project-type guarantee"></i>
											    		<#else>
											    			<i class="project-type other"></i>
												    	</#if>
	                                                    <a href="${taglibs.ctx}/invest/investDetail.htm?loanCode=${loan.loanCode}" title="">${loan.loanName}</a>
	                                                </div>
	                                                <div class="project-progress clearfix">
	                                                    <div class="progress-all">
	                                                        <p class="progress-finish" style="width:60%"></p>
	                                                    </div>
	                                                    <div class="progress-num">
	                                                        60%
	                                                    </div>
	                                                </div>
	                                                <div class="project-rate clearfix">
	                                                    <p class="project-rate-t">Interest rates</p>
	                                                    <p class="project-rate-n">
	                                                        <strong><@h.numf value=loan.loanInterestRates /></strong>
	                                                        <span>%</span>
	                                                    </p>
	                                                </div>
	                                                <div class="project-term clearfix">
	                                                    <p class="project-total-t">Time Limit</p>
	                                                    <p class="project-term-n">
	                                                        <strong>${loan.loanTimeLimit}</strong>
	                                                        <span><@bizTag.enumValue enumName="LoanTimeLimitUnitEnum" key=loan.loanTimeLimitUnit /></span>
	                                                    </p>
	                                                </div>
	                                                <div class="project-way clearfix">
	                                                    <p class="project-way-t">Interest Manner:</p>
	                                                    <p class="project-way-n">
	                                                        <span><@bizTag.loanInterestManner loanInterestManner=loan.loanInterestManner /></span>
	                                                    </p>
	                                                </div>
	                                                <div class="project-total clearfix">
	                                                    <p class="project-total-t">Loan Amount:</p>
	                                                    <p class="project-total-n">
	                                                        <span>$</span>
	                                                        <strong><@h.numf value=loan.loanAmount /></strong>
	                                                    </p>
	                                                </div>
	                                                <div class="cuttime">
	                                                    <span class="cuttime-txt">Time to post</span>
	                                                    <span class="cuttime-val"></span>
	                                                    <script>$(".cuttime-val").countdown({timeCountDownTo:"${loan.loanPostTime?string("yyyy-MM-dd HH:mm:ss")}"});</script>
	                                                </div>
	                                                <div class="project-operate">
	                                                    <a href="investDetail.htm" title="" class="btn btn-primary">Invest</a>
	                                                </div>
	                                            </li>
                                    	</#list>
                                    	</@loan_notice_list>
                                        </ul>
                                        </#if>
                                        <ul class="investlist">
                                        	<#list lstLoan as loan>
                                            	<@bizTag.loanObjectLi loan=loan  sysDate=sysDate/>
                                            </#list>
                                        </ul>
                                        
                                        <@ctl.page page=criteria.page />
                                    </div>
                                </div>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </div>
                </div>
            </div>
            </form>
        </section>
        <script type="text/javascript">
        $(function(){
        	var loanTimeLimit=$("#loanTimeLimit").val();
        	var loanInterestManner=$("#loanInterestManner").val();
        	$("a.investList").each(function(){
        		var elemVal=$(this).data("key");
        		var elemf=$(this).data("father");
        		if(elemVal==loanTimeLimit  && elemf =="loanTimeLimit"){
        			$(this).addClass("active");
        		}
        		if(elemVal==loanInterestManner  && elemf =="loanInterestManner"){
        			$(this).addClass("active");
        		}
        	});
        	
		});
		
        </script>
</@cl.html>           
 