<@cl.html  pateType="personal" bodyCss="user-center my-invest">
	<div class="uc-main">
        <div class="uc-main-hd"><strong>Investment</strong></div>
        <div class="uc-main-bd">
            <div class="tab uc-tab">
                <ul class="tab-hd uc-tab-hd">
                    <li class="<#if flag=="pending"> active</#if>" id="pending"><a class="" href="${taglibs.ctx}/account/myinvest/investList.htm?flag=pending" title=""><span>auditing</span><em>${(map.pendingCount)!}</em></a></li>
                    <li class="<#if flag=="receivables"> active</#if>"  id="receivables"><a class="" href="${taglibs.ctx}/account/myinvest/investList.htm?flag=receivables" title=""><span>repaying</span><em>${(map.receivablesCount)!}</em></a></li>
                    <li class="<#if flag=="complete"> active</#if> last"  id="complete"><a class="" href="${taglibs.ctx}/account/myinvest/investList.htm?flag=complete" title=""><span>repaid</span><em>${(map.completeCount)!}</em></a></li>
                </ul>
                <div class="tab-bd uc-tab-bd">
                	<div id="myinvesttab">
                    	<div class="tab-bd-con active"  id="myinvest-tab03">
							<form action="${taglibs.ctx}/account/myinvest/investList.htm?flag=complete" method="POST" id="searchForm">
							    <div class="uc-table">
							        <table class="table">
							            <thead>
							                <tr>
							                    <th class="tl my-project-name">loan</th>
							                    <th class="tl invest-money">investAmount</th>
							                    <th class="tl received-principal">principal</th>
							                    <th class="tl received-interest">interest</th>
							                    <th class="tl table-project-date">endDate</th>
							                    <th class="tl end-style">endStatus</th>
							                    <th class="tl table-project-detail">detail</th>
							                </tr>
							            </thead>
							            <tbody>
							            	<#if invests?size lte 0>
								                <tr>
								                    <td>no data</td>
								                    <td></td>
								                    <td></td>
								                    <td></td>
								                    <td></td>
								                    <td></td>
								                    <td></td>
								                </tr>
							                <#else>
								                <#list invests as invest>
									                <tr>
									                    <td><a class="project-name-link"  href="${taglibs.allctx}/invest/investDetail.htm?loanCode=${invest.loanCode}" title="">${invest.loanName!}</a></td>
									                    <td><@h.numf value=invest.investmentAmount/></td>
									                    <td><@h.numf value=invest.receivedPrincipal/></td>
									                    <td><@h.numf value=invest.receivedInterest/></td>
									                    <td><@h.datef value=invest.carryInterestTo/></td>
									                    <td>repaid<#--${invest.settlementType!}--></td>
									                    <td><a id="beover-list-view" href="details.htm?investId=${invest.id}" >view</a></td>
									                </tr>
								                </#list>
							                </#if>
							            </tbody>
							        </table>
							            <@ctl.page page=criteria.page css="pagination-right"/>
							    </div>
							    </form>
							</div>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>
</@cl.html>