<@cl.html pateType="personal" bodyCss="user-center uc-index">
	<div class="uc-main">
            <h2 class="uc-main-hd"><strong>Earnings</strong></h2>
            <div class="uc-main-bd">
                <div class="uc-mainbox">
                	<#--
                	<#if ciccAccountFlag==true>
                    <div class="uci-account-info clearfix">
                        <div class="my-cfca">
                        	<p class="cfca-prompt"><a target="_blank" href="https://payment.china-clearing.com/PaymentUser/index.htm" title=""></a> 已关联中金支付</p>
                        </div>
                    </div>
                    <div class="uci-cfca-entrance"><a target="_blank" href="https://payment.china-clearing.com/PaymentUser/index.htm" class="" title="">第三方资金托管—中金支付</a></div>
                    <#else>
                     <div class="uci-account-info clearfix">
                        <div class="my-cfca">
                            <p class="cfca-prompt">尚未关联中金支付账户<a target="_blank" href="${taglibs.allctx}/account/ciccDoRegister.htm" title="">立即关联</a></p>
                        </div>
                    </div>
                    <div class="uci-cfca-entrance"><a target="_blank" href="https://payment.china-clearing.com/PaymentUser/index.htm" class="" title="">第三方资金托管—中金支付</a></div>
                    </#if>
                    -->
                    <div class="uci-recent-income clearfix">
                        <div class="my-info">
                            <div class="uci-li amount-date">
                                <span class="uci-lab">Balance:</span>
                                <span class="uci-val">$ <@h.numf value=(balance)!0 /></span>
                            </div>
                            &nbsp;
                            <a id="add-balance-view" class="btn btn-primary" href="javascript:;" title="">add</a>
                            <a id="withdraw-balance-view" class="btn btn-primary" href="javascript:;" title="">withdraw</a>
                        </div>
                    </div>
                    
                    <div class="uci-recent-income clearfix">
                        <h2 class="h2">Next Earnings</h2>
                        <div class="my-info">
                            <div class="uci-li amount-date">
                                <span class="uci-lab">Date:</span>
                                <span class="uci-val">
                                <#if investIncomeDto.nextPaymentDate??>
                                <@h.datef value=investIncomeDto.nextPaymentDate />
                                <#else>
                                	No data
                                </#if>
                                </span>
                            </div>
                            <div class="uci-li amount-cash">
                                <span class="uci-lab">Amount:</span>
                                <span class="uci-val"><em>$ <@h.numf value=(investIncomeDto.nextPaymentAmount)!0 /></em></span>
                            </div>
                        </div>
                    </div>
                    
                    <div class="uci-recent-income clearfix">
                        <h2 class="h2">Total Earnings</h2>
                        <div class="my-info">
                            <div class="uci-li amount-date">
                                <span class="uci-lab">Investment:</span>
                                <span class="uci-val">$ <@h.numf value=(investIncomeDto.totalInvestmentAmount)!0 />
                                </span>
                            </div>
                            <div class="uci-li amount-cash">
                                <span class="uci-lab">Earnings:</span>
                                <span class="uci-val"><em>$ <@h.numf value=(investIncomeDto.totalInvestEarnings)!0 /></em></span>
                            </div>
                        </div>
                    </div>                    
                    
                    
                    <#--
                    <div class="uci-invest-income clearfix" >
                        <h2 class="h2">投资收益总览</h2>
                        <div class="uci-invest-info total-invest">
                            <div class="uci-li head">
                                <span class="uci-lab">累计投资金额:</span>
                                <span class="uci-val"><em><@h.numf value=(investIncomeDto.totalInvestmentAmount)!0 /></em>dollar</span>
                            </div>
                            <div class="uci-li">
                                <span class="uci-lab">待生效的投资:</span>
                                <span class="uci-val"><em><@h.numf value=(investIncomeDto.toInvestAmount)!0 /></em>dollar</span>
                                <a class="uci-prompt" href="${taglibs.allctx}/account/myinvest/investList.htm" title="">(共${(investIncomeDto.toInvestCount)!0}笔)</a>
                            </div>
                            <div class="uci-li">
                                <span class="uci-lab">回款中的投资:</span>
                                <span class="uci-val"><em><@h.numf value=(investIncomeDto.investingAmount)!0 /></em>dollar</span>
                                <a class="uci-prompt" href="${taglibs.allctx}/account/myinvest/investList.htm" title="">(共${(investIncomeDto.investingCount)!0}笔)</a>
                            </div>
                            <div class="uci-li">
                                <span class="uci-lab">已回款的投资:</span>
                                <span class="uci-val"><em><@h.numf value=(investIncomeDto.investedAmount)!0 /></em>dollar</span>
                            </div>
                        </div>
                        <div class="uci-income-info total-yields">
                            <div class="uci-li head">
                                <span class="uci-lab">累计收益:</span>
                                <span class="uci-val"><em><@h.numf value=(investIncomeDto.totalInvestEarnings)!0 /></em>dollar</span>
                            </div>
                            <div class="uci-li">
                                <span class="uci-lab">Repaying earning:</span>
                                <span class="uci-val"><em><@h.numf value=(investIncomeDto.collectEarnings)!0 /></em>dollar</span>
                            </div>
                            <div class="uci-li">
                                <span class="uci-lab">Repaid earning:</span>
                                <span class="uci-val"><em><@h.numf value=(investIncomeDto.receivedEarnings)!0 /></em>dollar</span>
                            </div>
                        </div>
                    </div>
                    -->
                    <div class="uc-earnings">
                    	<h2 class="h2">Statistic Earnings</h2>
                    	<#if lineState??>
                        <div class="uc-earnings-chart" id="earnings_chart"></div>
                        <#else>
                        <div class="uc-earnings-chart" id="earnings_chart"></div>                       
                        <div class="earnings-chart-undata">No data！</div>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
        
<div class="dn" id="add-Balance"></div>
<div class="dn" id="withdraw-Balance"></div>

<script type="text/javascript">
$(function(){

	$("#add-balance-view").click(function(){
		$.ajax({
            type: "POST",
            url: "addBalanceDialog.htm",
            async:false,
            dataType: "json",
            error: function (data, transport) {			            
            },
            success: function (result) {
            	//修改密码(第一页)
            	$("#add-Balance").html(result.html);
				$.fancybox.open($("#add-Balance"),{"title":"Add Balance"});
            }
        });		
	});
	
	
	$("#withdraw-balance-view").click(function(){
		$.ajax({
            type: "POST",
            url: "withdrawBalanceDialog.htm",
            async:false,
            dataType: "json",
            error: function (data, transport) {			            
            },
            success: function (result) {
            	//修改密码(第一页)
            	$("#withdraw-Balance").html(result.html);
				$.fancybox.open($("#withdraw-Balance"),{"title":"Withdraw Balance"});
            }
        });		
	});

	var dataOfOriginal;//原始数据
	var dataOfProduct = {};//以产品类型为维度的统计数据
	//var months = ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"];
	var months = ${(months)!};
	var dataOfLine_investPlan = ${(investPlan)!};//点线图数据
	var dataOfLine_investActual = ${(investActual)!};//点线图数据
	drawLine();//绘制点线图
	/*
	* 绘制点线图
	*/
	function drawLine(){
		$('#earnings_chart').highcharts({
			chart: {
				height: 250,
				type: 'line',
	            style: {
	                fontFamily: "Dosis, sans-serif"
	            }
			},
			title: {
				text: '',
	            style: {
	                fontSize: '16px'
	            },
				x: -20 //center
			},
			xAxis: {
				categories: months,
			},
			yAxis: {
				title: {
					text: ''
				},
				min: 0,
				labels: {
                    formatter: function() {
                    	if(this.value>=10000){
                    		return this.value / 10000 +'万';
                    	}else{
                    		return this.value;
                    	}
                    }
                },
				plotLines: [{
					value: 0,
					width: 1,
					color: '#808080'
				}]
			},
			tooltip: {
				crosshairs: true,
				shared: true,
				valueSuffix: ' dollar'
			},
			legend: {
	            enabled: false,
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'top',
				borderWidth: 0
			},
			series:[
			/*
				{
					name: 'Plan Earning',
					data: [null, null, null, null, null, null, null, null, 23.3, 18.3, 13.9, 9.6],
					dashStyle: 'dash',
					color: '#f60'				
				},
				{
					name: 'Actual Earning',
					data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, null, null, null],
					dashStyle: 'Solid',
					color: '#06f'
				}
			*/
				{
					name: 'Plan Earning',
					data: dataOfLine_investPlan,
					dashStyle: 'dash',
					color: '#f60'				
				},
				{
					name: 'Actual Earning',
					data: dataOfLine_investActual,
					dashStyle: 'Solid',
					color: '#06f'
				}
				
			]
		});	
	}
	
});
</script>
           
  </@cl.html>