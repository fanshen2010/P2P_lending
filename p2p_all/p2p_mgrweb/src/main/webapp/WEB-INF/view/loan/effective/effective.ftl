<@cl.html title="">
	<div class="col_main">
	    <div class="main_hd">
	    	<div class="clearfix">
	            <h2>Investing Loan</h2>
	        </div> 
	     </div>
	    <div class="main_bd">
	    	<form action="${taglibs.allctx}/loan/effective/effective.htm" id="searchForm"  method="POST">
	    		<input type="hidden"  id="updateFlag" value="${(updateFlag)!}" />
		    	<div class="search_form">
		            <div class="search_list clearfix">
	                    <div class="sl_li">
	                        <label class="frm_label">LoanCode:</label>
	                        <div class="frm_controls">
	                        	<@ctl.text  id="loanCode" name="criteria.loanCode"    value="${criteria.loanCode!}"  />
	                        </div>
	                    </div>
	                    <div class="sl_li">
	                        <label class="frm_label">LoanName:</label>
	                        <div class="frm_controls">
	                        	<@ctl.text  id="loanName" name="criteria.loanName"    value="${criteria.loanName!}"  />
	                        </div>
	                    </div>
	                    <div class="sl_li">
	                        <label class="frm_label">CustName:</label>
	                        <div class="frm_controls">
	                        	<@ctl.text  id="customName" name="criteria.customName"    value="${criteria.customName!}"  />
	                        </div>
	                    </div>
	                    <div class="sl_li sl_btn">
	                    	<@ctl.submit text="search"  />
	                    </div>
		            </div>
		        </div>
		        <div class="main_list">
		            <div class="form_wrp">
		                <div class="nowrap_table">
		                    <table class="table form_wrp_padding table-striped table-hover" cellpadding="0" cellspacing="0">
		                        <thead class="thead">
		                                <tr>
		                                    <th class="table_cell tl">Loan code</th>
		                                    <th class="table_cell tl">Loan name</th>
		                                    <th class="table_cell tl">Customer name</th>
		                                    <th class="table_cell tl">Loan Type</th>
		                                    <th class="table_cell tl">Invested/Total amount</th>
		                                    <th class="table_cell tl">Loan limit</th>
		                                    <th class="table_cell tl">Interest rates</th>
		                                    <th class="table_cell tl">Invest end time</th>
		                                    <th class="table_cell tl">Status</th>
		                                    <th class="table_cell tr">Operate</th>
		                                </tr>
		                            </thead>
		                        <tbody class="tbody" id="">
		                        	<#if lstLoan?has_content>
		                        	<#list lstLoan as loan>
		                            <tr <#if !loan_has_next>class="last"</#if>>
		                                <td class=" table_cell tl">${loan.loanCode}</td>
		                                <td class=" table_cell tl">${loan.loanName}</td>
		                                <td class=" table_cell tl">${loan.customName}</td>
		                                <td class=" table_cell tl"><@bizTag.loanType type=loan.loanType/></td>
		                                <td class=" table_cell tl"><@h.numf value=loan.currentInvestedShare/>/<@h.numf value=loan.loanAmount/></td>
		                                <td class=" table_cell tl"><@bizTag.loanLimit limit=loan.loanTimeLimit unit=loan.loanTimeLimitUnit/></td>
		                                <td class=" table_cell tl">${loan.loanInterestRates}%</td>
		                                <td class=" table_cell tl"><@h.datef value=loan.loanEndTime /></td>
		                                <td class=" table_cell tl"><@bizTag.loanStatus statusCode=loan.loanStatus loanAmount=loan.loanAmount investedAmount=loan.currentInvestedShare loanEndTime=loan.loanEndTime /></td>
		                                <td class=" table_cell tr">
		                                	<@ctl.operateButton linkType="edit" href="${taglibs.allctx}/loan/effective/dispose.htm?loanCode=${loan.loanCode}" title="Edit" text="Edit"/>
		                                </td>
		                           </tr>
		                            </#list>
		                            <#else>
									  <tr><td colspan="10">No data</td></tr>
									</#if>
		                        </tbody>
		                    </table>
		                </div>
		            </div>
		             <@ctl.page page=criteria.page>
		             </@ctl.page>
		        </div>
	        </form>
	    </div>
	</div>
	<script type="text/javascript">
	$(function(){
		var  updateFlag  = $("#updateFlag").val();
		if(updateFlag == 'delay') {
			info("延时成功！");
		} else if(updateFlag == 'start') {
			info("enable success！");
		} else if(updateFlag == 'forbidden') {
			info("disable success！");
		} else if(updateFlag == 'flow') {
			info("fail success！");
		} else if(updateFlag == 'flowfail') {
			info("fail error");
		} else if(updateFlag == 'ultrafail') {
			info("超募退款失败！请联系管理员");
		} else if(updateFlag == 'ultra') {
			info("超募退款成功！");
		}
	});
	</script>
</@cl.html>           
 