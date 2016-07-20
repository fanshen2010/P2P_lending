<@cl.html title="后台管理_还款管理">
<div class="col_main">
    <div class="main_hd">
    	<div class="clearfix">
            <h2>Repayment Manage</h2>
        </div> 
     </div>
    <div class="main_bd">
    	<form id="searchForm" action="${taglibs.ctx}/loan/afterLoan/repayManage/repayManage.htm" method="POST">
    	<div class="search_form">
            <div class="search_list clearfix">
                    <div class="sl_li">
                        <label class="frm_label">LoanCode:</label>
                        <div class="frm_controls">
                            <@ctl.text name="loanCode" value="${loanCode}" />
                        </div>
                    </div>
                    <div class="sl_li">
                        <label class="frm_label">LoanName:</label>
                        <div class="frm_controls">
                        	<@ctl.text name="loanProject" value="${loanProject}" />
                        </div>
                    </div>
                    <div class="sl_li">
                        <label class="frm_label">CustName:</label>
                        <div class="frm_controls">
                        	<@ctl.text name="customName" value="${customName}" />
                        </div>
                    </div>
                    <div class="sl_li">
                        <label class="frm_label">RepayDate:</label>
                        <div class="frm_controls group" style="width:auto">
                        	<@ctl.datepicker name="repayDateMin" value=repayDateMin />
                            <span class="">~</span>
                            <@ctl.datepicker name="repayDateMax" value=repayDateMax />
                        </div>
                    </div>
                    <div class="sl_li sl_btn">
                    	<@ctl.submit text="search" />
                    </div>
                    <input type="hidden" name="receivableRepayStatus" value="${receivableRepayStatus}"/>
            </div>
        </div>
        <div class="main_list">
            <div class="nowrap_table">
            <table class="table table-striped table-hover" cellpadding="0" cellspacing="0">
                <thead class="thead">
                    <tr>
                        <th class="table_cell tl">Loan code</th>
                        <th class="table_cell tl">Loan name</th>
                        <th class="table_cell tl">Customer name</th>
                        <th class="table_cell tl">Period</th>
                        <th class="table_cell tl">Loan amount（dollar）</th>
                        <th class="table_cell tl">Repay plan date</th>
                        <th class="table_cell tl">Loan status</th>
                        <th class="table_cell tr">Operate</th>
                    </tr>
                </thead>
                <tbody class="tbody" id="">
                
                	<#if loanBuckleRepayList?has_content>
                 	<#list loanBuckleRepayList as item>
                        <tr <#if ((item.receivableRepayStatus)!"")=="7">class="error"</#if>>
                            <td class=" table_cell">${item.loanCode}</td>
                            <td class=" table_cell">${item.loanName}</td>
                            <td class=" table_cell">${item.customName}</td>
                            <#if item.repayType=="2">
                            	<td class=" table_cell ">${item.receivableRepayNumber}/${item.totalRepayNumber}~${item.totalRepayNumber}/${item.totalRepayNumber}</td>
                            <#else>
                             	<td class=" table_cell ">${item.receivableRepayNumber}/${item.totalRepayNumber}</td>
                            </#if>
                            <td class=" table_cell "><@h.numf value=item.actualAmount!0 /></td>
                            <td class=" table_cell "><@h.datef value=item.receivableNextDate /></td>
                            <td class=" table_cell "><#if ((item.receivableRepayStatus)!"")!="0"><@bizTag.enumValue enumName="RepaymentStatusEnum" key=item.receivableRepayStatus  /></#if></td>
                            <td class=" table_cell tr">
                            	<#if item.receivableRepayStatus=="2" || item.receivableRepayStatus=="0">
                            		<@ctl.operateButton href="repayDetail.htm?curLoanCode=${item.loanCode}&operatType=one&repayType=${item.repayType}" title="Repay" text="Repay" />
                            	<#else>
                            		<@ctl.operateButton href="javascript:;"  class="operepayte_icon repaypayment disable" title="Repay" text="Repay" />
                            	</#if>
                            	<@ctl.operateButton href="repayDetail.htm?curLoanCode=${item.loanCode}&operatType=two&repayType=${item.repayType}" title="view" text="view" />
                            </td>
                        </tr>
                    </#list>
                    <#else>
					  <tr><td colspan="9">No data</td></tr>
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
</@cl.html>