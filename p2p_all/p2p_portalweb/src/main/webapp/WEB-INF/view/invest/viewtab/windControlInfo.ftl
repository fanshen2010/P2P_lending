<div class="tab-bd-con" id="winddiv">
	<#if uploadFiles??&&uploadFiles?size gt 0>
		<div class="proinfo-item">
	        <div class="proinfo-cont finance-certific">
	            <ul class="fc-list">
	            	<#list uploadFiles as upload>
	                	<li>
	                        <a class="fancybox" href="${upload.fileUrlBig}" title="${upload.fileName}" rel="certific">
	                        	<img class="fc-thumb" src="${upload.fileUrlThumb}" alt="" width="128" height="128" />
	                            <p class="fc-dec">${upload.fileName}</p>
	                        </a>
	                    </li>
	                </#list>
	            </ul>
	        </div>
	    </div>
    </#if>
    <#if ((loanDto.loanProjectMsgDto.premise)!"")!="">
	    <div class="proinfo-item">  
	        <h2 class="proinfo-tit">Premise</h2>
	        <div class="proinfo-cont founded-premise">
	            <p>${(loanDto.loanProjectMsgDto.premise)!}</p>
	        </div>
	    </div>
    </#if>
    <#if ((loanDto.loanProjectMsgDto.riskControl)!"")!="">
	    <div class="proinfo-item">  
	        <h2 class="proinfo-tit">Risk Control Mothod</h2>
	        <div class="proinfo-cont risk-controls">
	            <p>${(loanDto.loanProjectMsgDto.riskControl)!}</p>
	        </div>
	    </div>
    </#if>
</div>