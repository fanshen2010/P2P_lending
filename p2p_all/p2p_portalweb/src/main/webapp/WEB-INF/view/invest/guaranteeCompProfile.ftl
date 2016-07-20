<@cl.html>
<section class="area">
	<div class="container">
		<div class="regin bonding-company-box">
	        <div class="regin-bd">
	            <div class="bc-intr">
	                <h2 class="bc-tit">Guarantee Company</h2>
	            <div class="bc-record">
	                <div class="bc-con">
	                    <ul class="bc-record-list">
	                        <li>
	                            <i class="bc-record-icon ing"></i>
	                            <div class="bc-record-data">
	                                <p class="bcr-data-txt">Guarantee Loan${curLoanNum}个</p>
	                                <p class="bcr-data-num">$ <em><@h.numi value=(curLoanSumAmount)!0 /></em></p>
	                            </div>
	                        </li>
	                        <li>
	                            <i class="bc-record-icon end"></i>
	                            <div class="bc-record-data">
	                                <p class="bcr-data-txt">Loan End${endloanNum}个</p>
	                                <p class="bcr-data-num">$ <em><@h.numi value=(endLoanSumAmount)!0 /></em></p>
	                            </div>
	                        </li>
	                        <li>
	                            <i class="bc-record-icon help"></i>
	                            <div class="bc-record-data">
	                                <p class="bcr-data-txt">Loan Repaid 0个（0 period）</p>
	                                <p class="bcr-data-num">$ <em>0</em></p>
	                            </div>
	                        </li>
	                    </ul>
	                </div>
	            </div>
	                <div class="bc-con">
	                    <div class="bc-intr-dec">
	                        <p class="bc-intr-li">
	                            <span class="bc-intr-lab">setup date</span>
	                            <span class="bc-intr-val"><@h.datef value=(pfmTenantDepartmentInfo.createdDate)!"" /></span>
	                        </p>
	                        <p class="bc-intr-li">
	                            <span class="bc-intr-lab">setup capital</span>
	                            <span class="bc-intr-val">${(pfmTenantDepartmentInfo.registeredCapital)!}dollar</span>
	                        </p>
	                        <#--
	                        <p class="bc-intr-li">
	                            <span class="bc-intr-lab">公司所在地</span>
	                            <span class="bc-intr-val"><@ctl.address value="${(pfmTenantDepartmentInfo.address)!}" isView="true" /></span>
	                        </p>
	                        <p class="bc-intr-li">
	                            <span class="bc-intr-lab">合作开始日期</span>
	                            <span class="bc-intr-val"><@h.datef value=(pfmTenantDepartmentInfo.cooperateDate)!"" /></span>
	                        </p>
	                        -->
	                    </div>
	                    <div class="bc-intr-info">
	                        <p>${(pfmTenantDepartmentInfo.introduction)!}</p>
	                    </div>
	                </div>
	            </div>	
	            <div class="bc-aptitude">
	                <h2 class="bc-tit">Certificate</h2>
	                <div class="bc-con">
	                    <ul class="bc-aptitude-list">
	                    	<#list picQualifications as item>
	                    	<li>
	                            <a class="fancybox" href="${item.fileUrlOriginal}" title="${item.fileName}" rel="aptitude">
	                                <img class="bc-aptitude-thumb" src="${item.fileUrlThumb}" width="128" height="128" />
	                                <p class="bc-aptitude-dec">${item.fileName}</p>
	                            </a>
	                        </li>
	                    	</#list>
	                    </ul>
	                </div>
	            </div>
	            <div class="clear"></div>
	            <div class="bc-project">
	                <h2 class="bc-tit">Guarantee Loan</h2>
	                <div class="bc-con">
	                    <ul class="investlist">
                        	<#list lstLoan as loan>
                            	<@bizTag.loanObjectLi loan=loan  sysDate=sysDate/>
                            </#list>
                        </ul>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</section>
</@cl.html>