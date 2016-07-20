<#--
项目投资列表li
<@bizTag.loanObjectLi loan />
-->
<#macro loanObjectLi
	loan sysDate
>
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
	       <#if loan.loanStatus=="04">
	        	<#if (loan.loanEndTime?string("yyyyMMdd HH:mm:ss")?date("yyyyMMdd HH:mm:ss"))  gt (sysDate?string("yyyyMMdd HH:mm:ss")?date("yyyyMMdd HH:mm:ss"))>
	        		<#if loan.currentSurplusShare gt 0 >
	        			 <div class="progress-all">
			                <p class="progress-finish" style="width:<@h.numi value=((loan.currentInvestedShare!0)/(loan.loanAmount)*100)/>%"></p>
			            </div>
			            <div class="progress-num">
			              <@h.numi value=((loan.currentInvestedShare!0)/(loan.loanAmount)*100)/>%
			            </div>
	        		<#else>
	        			 <div class="progress-all">
			                <p class="progress-finish all" style="width:100%"></p>
			            </div>
			            <div class="progress-num">
			              100%
			            </div>
	        		</#if>
	        	<#else>
	        		 <div class="progress-all">
		                <p class="progress-finish all" style="width:100%"></p>
		            </div>
		            <div class="progress-num">
		              100%
		            </div>
	        	</#if>
	        <#else>
	        	 <div class="progress-all">
	                <p class="progress-finish all" style="width:100%"></p>
	            </div>
	            <div class="progress-num">
	              100%
	            </div>
	        </#if>
	    </div>
	    <div class="project-rate clearfix">
	        <p class="project-rate-t">Interest rates</p>
	        <p class="project-rate-n">
	            <strong><@h.numf value=loan.loanInterestRates/></strong>
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
	        <p class="project-total-t">Amount:</p>
	        <p class="project-total-n">
	            <span>$</span>
	            <strong><@h.numf value=loan.loanAmount /></strong>
	        </p>
	    </div>
	    <div class="project-operate">
	        <#if loan.loanStatus=="04">
	        	<#if (loan.loanEndTime?string("yyyyMMdd HH:mm:ss")?date("yyyyMMdd HH:mm:ss"))  gt (sysDate?string("yyyyMMdd HH:mm:ss")?date("yyyyMMdd HH:mm:ss"))>
	        		<#if loan.currentSurplusShare gt 0 >
	        			<a href="${taglibs.ctx}/invest/investDetail.htm?loanCode=${loan.loanCode}" title="" class="btn btn-primary">Invest</a>
	        		<#else>
	        			<a href="javascript:;" title="" class="btn disabled">Full scale</a>
	        		</#if>
	        	<#else>
	        		<a href="javascript:;" title="" class="btn disabled">Repaid</a>
	        	</#if>
	        <#else>
	        	<a href="javascript:;" title="" class="btn disabled"><@bizTag.loanStatus statusCode=loan.loanStatus/></a>
	        </#if>
	    </div>
	</li>
</#macro>