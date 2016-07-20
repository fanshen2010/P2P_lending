<@cl.html js="account.js" pateType="personal" bodyCss="user-center my-invest">
<div class="uc-main">
	<div class="uc-main-hd">
		<strong>
			<a href="javascript:history.back();" title="">&lt; return</a>
		</strong>
	</div>
    <div class="uc-main-bd">
    	<div class="my-invest-info">
        	<div class="invest-info-list">
            	<div class="form-group">
                    <span class="frm-label">investCode:</span>
                    <span class="frm-controls">${invest.investCode}</span>
                </div>
                <div class="form-group odd">
                    <span class="frm-label">loanName:</span>
                    <span class="frm-controls">${invest.loanName}</span>
                </div>
                <div class="form-group">
                    <span class="frm-label">investAmount:</span>
                    <span class="frm-controls"><@h.numf value=invest.investmentAmount/></span>
                </div>
                <div class="form-group odd">
                    <span class="frm-label">investTime:</span>
                    <span class="frm-controls"><@h.dateformat value=invest.investmentTime format='yyyy-MM-dd HH:mm:ss'/></span>
                </div>
                <div class="form-group">
                    <span class="frm-label">investInterst:</span>
                    <span class="frm-controls"><@h.numf value=Invest.investInterst/></span>
                </div>
                <div class="form-group odd">
                    <span class="frm-label">InterstRate:</span>
                    <span class="frm-controls">${invest.investInterstRate}%</span>
                </div>
                <div class="form-group">
                    <span class="frm-label">InvestStatus:</span>
                    <span class="frm-controls"><@bizTag.enumValue enumName="InvestStatusEnmu" key=invest.status /></span>
                </div>
                <div class="form-group odd">
                    <span class="frm-label">interestDate:</span>
                    <span class="frm-controls"><@h.dateformat value=invest.interestDate format='yyyy-MM-dd'/></span>
                </div>
                <div class="form-group">
                    <span class="frm-label">timeLimit:</span>
                    <span class="frm-controls"><@bizTag.loanLimit limit=invest.investLimit unit=invest.investTimeLimitUnit /></span>
                </div>
                <input type="hidden" id="investCode" value="${invest.investCode}" />
            </div>
            <div class="tit clearfix">
                <h2 class="h2">repay plan</h2>
                <div class="agreement-link" >
                	<#--
                	<a class="link" href="${taglibs.allctx}/agreement/contract.htm?loanCode=${invest.loanCode}&investCode=${invest.investCode}" target="_blank" title="">查看项目协议</a>
                	-->
                	<#--
                    <div class="agreement-pop popover bottom ">
                        <div class="arrow"></div>
                        <div class="popover-content">
                            <p class="mark-notice-cont">
                                <a class="link"　href="协议_资金出借协议.html" target="_blank" title="" >查看项目协议</a>
                                <a class="link"　href="协议_资金出借协议.html" target="_blank" title="" >查看项目协议</a>
                            </p>
                        </div>
                    </div>
                    -->
                </div>
            </div>
            <div id="investDetail">
            </div>
    </div>
</div>
<script type="text/javascript">
        $(function(){
        	var params = {};
        	params["investCode"] = $("#investCode").val();
        	params["currentPage"] = '1';
    		$.ajax({
                type: "POST",
                dataType: "JSON",
                data:params,
                url:"myInvestDetailAjax.htm",
                success: function (json) {
                	$("#investDetail").html(json.html);
                }
            });
        });
    </script>
</@cl.html>