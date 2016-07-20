<#if (loanDto.projectInfoDto.loanPostTime?string("yyyyMMdd HH:mm:ss")?datetime("yyyyMMdd HH:mm:ss"))  gt (sysDate?string("yyyyMMdd HH:mm:ss")?datetime("yyyyMMdd HH:mm:ss"))>
	<div class="regin-row invest-cart">
		<h2 class="invest-cart-hd">
	    	<strong class="invest-tit">loan forecast</strong>
	        <span class="project-agreement">read <a href="${taglibs.allctx}/agreement/contract.htm?loanCode=${loanCode}" target="_blank" title=""> Policy </a></span>
	    </h2>
	    <div class="invest-cart-bd">
	    	<div class="invest-group-li cart-top">
	            <div class="invest-group-label">StartShare:</div>
	            <div class="invest-group-control">$<@h.numf value=loanDto.projectInfoDto.loanStartShare /></div>
	        </div>
	        <div class="invest-group-li cart-begincash">
	            <div class="invest-group-label">PostTime:</div>
	            <div class="invest-group-control"><em><@h.datetimef value=loanDto.projectInfoDto.loanPostTime format="HH:mm:ss" /></em></div>
	        </div>
	        <div class="clear"></div>
	        <div class="invest-group-li cart-increase">
	        	<div class="invest-group-label"></div>
	            <div class="invest-group-control"> <em></em></div>
	        </div>
	        <div class="invest-group-li cart-maxcash">
	            <div class="invest-group-label fr"></div>
	            <div class="invest-group-control fr"> <em></em></div>
	        </div>
	        <div class="clear"></div>
	        <form action="" class="invest-submit">
	            <button class="btn disabled" type="button">....</button>
	        </form>
	        <div class="invest-group-li cart-begintime">
	            <div class="invest-group-label">PostTime:</div>
	            <div class="invest-group-control"><@h.datetimef value=loanDto.projectInfoDto.loanPostTime format="yyyy-MM-dd  HH:mm:ss" /></div>
	        </div>
	    </div>
	</div>
<#elseif (loanDto.projectInfoDto.loanEndTime?string("yyyyMMdd HH:mm:ss")?date("yyyyMMdd HH:mm:ss"))  gt (sysDate?string("yyyyMMdd HH:mm:ss")?date("yyyyMMdd HH:mm:ss"))>
	<#if loanDto.projectInfoDto.currentSurplusShare gt 0>
		<div class="regin-row invest-cart">
	    	<h2 class="invest-cart-hd">
	        	<strong class="invest-tit">investAmount</strong>
	            <span class="project-agreement"><a href="${taglibs.allctx}/agreement/contract.htm?loanCode=${loanCode}" target="_blank" title=""> Policy</a></span>
	        </h2>
	        <div class="invest-cart-bd">
	        	<div class="invest-group-li cart-top">
	                <div class="invest-group-label">InvestAmount available:</div>
	                <div class="invest-group-control">$ <em><@h.numf value=loanDto.projectInfoDto.currentSurplusShare /></em></div>
	            </div>
	                <div class="invest-group-li cart-cash">
	                    <div class="invest-group-label">investAmount:</div>
	                    <div class="invest-group-control invest-amount">
	                        <a class="amount-btn increase" href="javascript:;" title=""></a>
	                        <input class="input-text" id="investAmount" name="investAmount" type="text" onkeydown="onlyNum();" ondragenter = "return false"     placeholder="${(loanDto.projectInfoDto.loanStartShare)!} dollar to start，${(loanDto.projectInfoDto.loanUnitPrice)!} dollar as unit price"  />
	                        <a class="amount-btn decrease" href="javascript:;" title=""></a>
	                    </div>
	                </div>
	            	<div class="invest-group-li dn" id="err">investAmount must be more than $200</div>
	                <form action="" class="invest-submit">
	                <button class="btn btn-primary dialog-link" id="trueorder_view" type="button">Invest</button>
	                </form>
	            <div class="invest-group-li cart-cuttime">
	                <div class="invest-group-label">valid time:</div>
	                <div class="invest-group-control cuttime-val"></div>
	                <script>$(".cuttime-val").countdown({timeCountDownTo:"${loanDto.projectInfoDto.loanEndTime?string("yyyy-MM-dd HH:mm:ss")}"});</script>
	            </div>
	        </div>
	    </div>
	<#else>
		<#if loanDto.projectInfoDto.loanStatus=="11" || loanDto.projectInfoDto.loanStatus=="12" >
			<div class="regin-row invest-cart">
	        	<h2 class="invest-cart-hd">
	            	<#-- 已完成 -->
	            	<strong class="invest-tit">repaid</strong>
	                <span class="project-agreement">read <a href="${taglibs.allctx}/agreement/contract.htm?loanCode=${loanCode}" title="">Policy</a></span>
	            </h2>
	            <div class="invest-cart-bd">
	            	<#-- 已完成 -->
	            	<div class="cart-status">repaid</div>
	                <div class="invest-group-li cart-periods">
	                    <div class="invest-group-label">current/total:</div>
	                    <div class="invest-group-control"><em>${(loanDto.projectInfoDto.totalRepayNumber)!}</em>/${(loanDto.projectInfoDto.receivableRepayNumber)!}</div>
	                </div>
	                <div class="invest-group-li cart-data">
	                    <div class="invest-group-label">value date:</div>
	                    <div class="invest-group-control"><@h.datef value=loanDto.projectInfoDto.carryInterestFrom /></div>
	                </div>
	            </div>
	        </div>
		<#elseif loanDto.projectInfoDto.loanStatus=="10" >
			<div class="regin-row invest-cart">
	        	<h2 class="invest-cart-hd">
	            	<strong class="invest-tit">repaying</strong>
	                <span class="project-agreement">read<a href="${taglibs.allctx}/agreement/contract.htm?loanCode=${loanCode}" title="">Policy</a></span>
	            </h2>
	            <div class="invest-cart-bd">
	                <#-- 还款中  --> 
	            	<div class="cart-status">repaying</div>
	                <div class="invest-group-li cart-periods">
	                    <div class="invest-group-label">current/total:</div>
	                    <div class="invest-group-control"><em>${(loanDto.projectInfoDto.receivableRepayNumber)!}</em>/${(loanDto.projectInfoDto.totalRepayNumber)!}</div>
	                </div>
	                <div class="invest-group-li cart-data">
	                    <div class="invest-group-label">value date:</div>
	                    <div class="invest-group-control"><@h.datef value=loanDto.projectInfoDto.carryInterestFrom /></div>
	                </div>
	            </div>
	        </div>
		<#elseif  loanDto.projectInfoDto.loanStatus=="04">
			<div class="regin-row invest-cart">
	        	<h2 class="invest-cart-hd">
	            	<strong class="invest-tit">auditing</strong>
	                <span class="project-agreement">read<a href="${taglibs.allctx}/agreement/contract.htm?loanCode=${loanCode}" title="">Policy</a></span>
	            </h2>
	            <div class="invest-cart-bd">
	                    <#-- 已结束-->
	                	<div class="cart-status">full scale</div>
	                    <div class="invest-group-li cart-person">
	                        <div class="invest-group-label">invest number :</div>
	                        <div class="invest-group-control"><em>${(investCriteria.page.totalRecord)!}</em></div>
	                    </div>
	                    <div class="invest-group-li cart-enddata">
	                        <div class="invest-group-label">invest deadline:</div>
	                        <div class="invest-group-control">
		                    <#list investRecord as i>
			                    <#if i_index==0>
			                        <@h.datetimef value=i.investmentTime />
		                            <#break>
		                        </#if>
		                    </#list>
		                    </div>
	                    </div>
	            </div>
	        </div>
        <#elseif  loanDto.projectInfoDto.loanStatus=="07" >
	        <div class="regin-row invest-cart">
	        	<h2 class="invest-cart-hd">
	            	<strong class="invest-tit">auditing</strong>
	                <span class="project-agreement">read<a href="${taglibs.allctx}/agreement/contract.htm?loanCode=${loanCode}" title="">Policy</a></span>
	            </h2>
	            <div class="invest-cart-bd">
	                    <#-- 已结束-->
	                	<div class="cart-status">auditing</div>
	                    <div class="invest-group-li cart-person">
	                        <div class="invest-group-label">invest number:</div>
	                        <div class="invest-group-control"><em>${(investCriteria.page.totalRecord)!}</em></div>
	                    </div>
	                    <div class="invest-group-li cart-enddata">
	                        <div class="invest-group-label">invest deadline:</div>
	                        <div class="invest-group-control">
		                    <#list investRecord as i>
			                    <#if i_index==0>
			                        <@h.datetimef value=i.investmentTime />
		                            <#break>
		                        </#if>
		                    </#list>
		                    </div>
	                    </div>
	            </div>
	        </div>
		</#if>
	</#if>
<#else>
	<#if loanDto.projectInfoDto.currentSurplusShare gt 0>
			<div class="regin-row invest-cart">
		    	<h2 class="invest-cart-hd">
		        	<strong class="invest-tit">auditing</strong>
		            <span class="project-agreement">read<a href="${taglibs.allctx}/agreement/contract.htm?loanCode=${loanCode}" title="">Policy</a></span>
		        </h2>
		        <div class="invest-cart-bd">
		                <#-- auditing-->
		            	<div class="cart-status">repaid</div>
		                <div class="invest-group-li cart-person">
		                    <div class="invest-group-label">invest num:</div>
		                    <div class="invest-group-control"><em>${(investCriteria.page.totalRecord)!}</em></div>
		                </div>
		                <div class="invest-group-li cart-enddata">
		                    <div class="invest-group-label">invest deadline:</div>
		                    <div class="invest-group-control">
		                    <#list investRecord as i>
			                    <#if i_index==0>
			                        <@h.datetimef value=i.investmentTime />
		                            <#break>
		                        </#if>
		                    </#list>
		                    </div>
		                </div>
		        </div>
		    </div>
	<#else>
		<#if loanDto.projectInfoDto.loanStatus=="11" || loanDto.projectInfoDto.loanStatus=="12" >
			<div class="regin-row invest-cart">
	        	<h2 class="invest-cart-hd">
	            	<#-- 已完成 -->
	            	<strong class="invest-tit">repaid</strong>
	                <span class="project-agreement">read<a href="${taglibs.allctx}/agreement/contract.htm?loanCode=${loanCode}" title="">Policy</a></span>
	            </h2>
	            <div class="invest-cart-bd">
	            	<#-- 已完成 -->
	            	<div class="cart-status">repaid</div>
	                <div class="invest-group-li cart-periods">
	                    <div class="invest-group-label">current/total:</div>
	                    <div class="invest-group-control"><em>${(loanDto.projectInfoDto.totalRepayNumber)!}</em>/${(loanDto.projectInfoDto.receivableRepayNumber)!}</div>
	                </div>
	                <div class="invest-group-li cart-data">
	                    <div class="invest-group-label">value date:</div>
	                    <div class="invest-group-control"><@h.datef value=loanDto.projectInfoDto.carryInterestFrom /></div>
	                </div>
	            </div>
	        </div>
		<#elseif loanDto.projectInfoDto.loanStatus=="10" >
			<div class="regin-row invest-cart">
	        	<h2 class="invest-cart-hd">
	            	<strong class="invest-tit">repaying</strong>
	                <span class="project-agreement">read<a href="${taglibs.allctx}/agreement/contract.htm?loanCode=${loanCode}" title="">Policy</a></span>
	            </h2>
	            <div class="invest-cart-bd">
	                <#-- 还款中  --> 
	            	<div class="cart-status">repaying</div>
	                <div class="invest-group-li cart-periods">
	                    <div class="invest-group-label">current/tota:</div>
	                    <div class="invest-group-control"><em>${(loanDto.projectInfoDto.receivableRepayNumber)!}</em>/${(loanDto.projectInfoDto.totalRepayNumber)!}</div>
	                </div>
	                <div class="invest-group-li cart-data">
	                    <div class="invest-group-label">value date:</div>
	                    <div class="invest-group-control"><@h.datef value=loanDto.projectInfoDto.carryInterestFrom /></div>
	                </div>
	            </div>
	        </div>
		<#elseif  loanDto.projectInfoDto.loanStatus=="04" || loanDto.projectInfoDto.loanStatus=="07" >
			<div class="regin-row invest-cart">
	        	<h2 class="invest-cart-hd">
	            	<strong class="invest-tit">auditing</strong>
	                <span class="project-agreement">read<a href="${taglibs.allctx}/agreement/contract.htm?loanCode=${loanCode}" title="">Policy</a></span>
	            </h2>
	            <div class="invest-cart-bd">
	                    <#-- 已结束-->
	                	<div class="cart-status">repaid</div>
	                    <div class="invest-group-li cart-person">
	                        <div class="invest-group-label">invest num:</div>
	                        <div class="invest-group-control"><em>${(investCriteria.page.totalRecord)!}</em></div>
	                    </div>
	                    <div class="invest-group-li cart-enddata">
	                        <div class="invest-group-label">invest deadline:</div>
	                        <div class="invest-group-control">
		                    <#list investRecord as i>
			                    <#if i_index==0>
			                        <@h.datetimef value=i.investmentTime />
		                            <#break>
		                        </#if>
		                    </#list>
		                    </div>
	                    </div>
	            </div>
	        </div>
		</#if>
	</#if>
</#if>
