<@cl.html  bodyCss="project-detail">
    <section class="area">
            <div class="container">
                <div class="breadcrumb">
                    <a href="${taglibs.ctx}/index.htm" title="">Home</a>
                    <span class="breadcrumb-arrow">&gt;</span>
                    <a href="${taglibs.ctx}/invest/investList.htm" title="">Shop</a>
                    <span class="breadcrumb-arrow">&gt;</span>
                    <span class="breadcrumb-cur">${(loanDto.projectInfoDto.loanName)!}Investment Detail</span>
                </div>
                <div class="regin invest-detail">
                	<div class="regin-row invest-info">
                    	<h2 class="invest-info-hd">
                        	<strong class="invest-tit">${(loanDto.projectInfoDto.loanName)!}</strong>
                        </h2>
                        <div class="invest-info-bd">
                        	<div class="invest-group trunk">
                            	<div class="invest-group-li rate">
                                	<div class="invest-group-label">Interest rates:</div>
                            		<div class="invest-group-control"><em>${(loanDto.projectInfoDto.loanInterestRates)!}</em>%</div>
                                </div>
                                <div class="invest-group-li term">
                                	<div class="invest-group-label">Time Limit:</div>
                            		<div class="invest-group-control"><@bizTag.loanLimit limit=loanDto.projectInfoDto.loanTimeLimit unit=loanDto.projectInfoDto.loanTimeLimitUnit /></div>
                                </div>
                            </div>
                            <div class="invest-group prop">
                                <div class="invest-group-li">
                                	<div class="invest-group-label">Interest Manner:</div>
                            		<div class="invest-group-control"><@bizTag.loanInterestManner loanInterestManner=loanDto.projectInfoDto.loanInterestManner /></div>
                                </div>
                                <div class="invest-group-li">
                                	<div class="invest-group-label">value date:</div>
                            		<div class="invest-group-control">T(due date) + 1</div>
                                </div>
                                <div class="invest-group-li">
                                	<div class="invest-group-label">progress:</div>
                                	<#assign investProgress= ((loanDto.projectInfoDto.currentInvestedShare!0)/(loanDto.projectInfoDto.loanAmount)*100) />
                            		<div class="invest-group-control">
                                    	<div class="progress">
                                        	<div class="progressbar" style=" width:<#if investProgress gt 100>100<#else><@h.numi value= investProgress/></#if>%;"></div>
                                        </div>
                                        <span class="progress-num"><#if investProgress gt 100>100<#else><@h.numi value= investProgress/></#if>%</span>
                                    </div>
                                </div>
                            	<div class="invest-group-li">
                                	<div class="invest-group-label">Amount:</div>
                            		<div class="invest-group-control">$ <em><@h.numf value=loanDto.projectInfoDto.loanAmount /></em></div>
                                </div>
                                <input  type="hidden"  id="loanCode" name="loanCode" value="${(loanDto.projectInfoDto.loanCode)!}" />
                                <#--担保信息-->
                                <#include "${taglibs.ftlctx}/invest/viewtab/guarantee.ftl" >
                            </div>
                    	</div>
                	</div>
                     <#include "${taglibs.ftlctx}/invest/viewtab/investAmount.ftl" >
                </div>
                <div class="regin tab invest-detailsub">
                	<ul class="tab-hd">
                    	<li class="tab-hd-con active"><a href="javascript:;" title="">loan information</a></li>
                    	<#if !(uploadFiles??&&uploadFiles?size gt 0) && ((loanDto.loanProjectMsgDto.premise)!"")=="" &&((loanDto.loanProjectMsgDto.riskControl)!"")=="" >
                    	<#else>
                        	<li class="tab-hd-con" id="windli"><a href="javascript:;" title="">risk information</a></li>
                        </#if>
                    </ul>
                    <div class="tab-bd">
                    	<div class="tab-bd-con active">
                    		<#if ((loanDto.loanProjectMsgDto.loanUse)!"")!="">
	                        	<div class="proinfo-item">
	                                <h2 class="proinfo-tit">Loan description</h2>
	                                <div class="proinfo-cont project-description">
	                                    <p>${(loanDto.loanProjectMsgDto.loanUse)!}</p>
	                                </div>
	                            </div>
                            </#if>
                            <#--融资方信息-->
                            <#include "${taglibs.ftlctx}/invest/viewtab/loaneeInformation.ftl" >
                            <#--Loan Record-->
                            <#include "${taglibs.ftlctx}/invest/viewtab/loaneeLoanRecord.ftl" >
                        </div>
                        <#include "${taglibs.ftlctx}/invest/viewtab/windControlInfo.ftl" >
                    </div>
                </div>
                <#-- Invest Record-->
                <#include "${taglibs.ftlctx}/invest/viewtab/investRecord.ftl" >
            </div>
	</section>
	<#include "${taglibs.ftlctx}/invest/viewtab/javascript.ftl" >
</@cl.html>