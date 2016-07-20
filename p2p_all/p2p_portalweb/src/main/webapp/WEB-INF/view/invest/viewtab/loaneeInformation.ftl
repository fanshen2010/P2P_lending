<div class="proinfo-item">    
    <h2 class="proinfo-tit">Financier Information</h2>
    <#if loanDto.projectInfoDto.loanType=="1">
	    <div class="proinfo-cont finance-info">
	        <div class="fi-list">
	            <div class="fi-li col">
	                <span class="fi-li-lab">Enterprise name:</span>
	                <span class="fi-li-val">${(loanDto.enterpriseInfoDto.enterpriseName)!}</span>
	            </div>
	            <div class="fi-li col">
	                <span class="fi-li-lab">Set up capital:</span>
	                <span class="fi-li-val">${(loanDto.enterpriseInfoDto.registeredCapital)!}<#if loanDto.enterpriseInfoDto.registeredCapital??> dollar</#if></span>
	            </div>
	            <div class="fi-li col">
	                <span class="fi-li-lab">Set up time:</span>
	                <span class="fi-li-val">${(loanDto.enterpriseInfoDto.createdYear)!}year</span>
	            </div>
	            <div class="fi-li col">
	                <span class="fi-li-lab">Enterprise scale:</span>
	                <span class="fi-li-val"><@bizTag.enumValue enumName="EnterpriseScaleEnum" key="${(loanDto.enterpriseInfoDto.enterpriseScale)!}" /></span>
	            </div>
	            <#--
	            <div class="fi-li col">
	                <span class="fi-li-lab">企业所在地:</span>
	                <span class="fi-li-val">${(loanDto.enterpriseInfoDto.officeAddr)!}</span>
	            </div>
	            -->
	        </div>
	    </div>
    <#elseif loanDto.projectInfoDto.loanType=="0">
	    <#--融资项目为个人融资时显示-->
	    <div class="proinfo-cont finance-info">
	        <div class="fi-list">
	            <div class="fi-li col">
	                <span class="fi-li-lab">Name:</span>
	                <span class="fi-li-val">${(loanDto.personalBasicDto.name)!}</span>
	            </div>
	            <div class="fi-li col">
	                <span class="fi-li-lab">Gender:</span>
	                <span class="fi-li-val"><@bizTag.enumValue enumName="SexEnum" key="${(loanDto.personalBasicDto.gender)!}" /></span>
	            </div>
	            <div class="fi-li col">
	                <span class="fi-li-lab">Age:</span>
	                <span class="fi-li-val"><@bizTag.countAge identity= (loanDto.personalBasicDto.identity)! /></span>
	            </div>
	            <div class="fi-li col">
	                <span class="fi-li-lab">Occupation:</span>
	                <span class="fi-li-val">${(loanDto.personalBasicDto.occupation)!}</span>
	            </div>
	            <div class="fi-li col">
	                <span class="fi-li-lab">Income:</span>
	                <span class="fi-li-val">$ ${(loanDto.personalJobDto.monthlyIncome)!}</span>
	            </div>
	        </div>
	    </div>
    </#if>
</div>