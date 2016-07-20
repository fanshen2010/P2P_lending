<@cl.html title="">
<div class="col_main">
    <div class="main_hd">
    	<div class="clearfix">
            <h2>Loan search</h2>
        </div> 
     </div>
       <form action="${taglibs.allctx}/business/project/index.htm" method="post" id="searchForm">
    <div class="main_bd">
    	<div class="search_form">
            <div class="search_list clearfix">
                	<form action="">
                        <div class="sl_li">
                            <label class="frm_label">Loan code:</label>
                            <div class="frm_controls">
                                <@ctl.text name="loanNo" value="${loanNo}" />
                            </div>
                        </div>
                        <div class="sl_li">
                            <label class="frm_label">Loan name:</label>
                            <div class="frm_controls">
                                <@ctl.text name="loanName" value="${loanName}" />
                            </div>
                        </div>
                        <div class="sl_li">
                            <label class="frm_label">Customer name:</label>
                            <div class="frm_controls">
                                <@ctl.text name="customerName" value="${customerName}" />
                            </div>
                        </div>                                    
                        <div class="sl_li">
                            <label class="frm_label">Status:</label>
                            <div class="frm_controls">
                                <@ctl.dropdownlist name="loanStatus" hasAll=true groupCode="LoanStatusEnum" value="${loanStatus}"  dataType="Enum" />
                            </div>
                        </div>
                        <div class="sl_li">
                            <label class="frm_label">Loan amount:</label>
                            <div class="frm_controls group">
                                <@ctl.text name="loanMinAmount" value="${loanMinAmount}" counter="dollar"/>
                                <span class="">~</span>
                                <@ctl.text name="loanMaxAmount" value="${loanMaxAmount}" counter="dollar"/>
                            </div>
                        </div>
                        <div class="sl_li">
                            <label class="frm_label" title="Interest rates">Interest rates:</label>
                            <div class="frm_controls group">
                                <@ctl.text name="loanMinRate" value="${loanMinRate}" counter="%"/>
                                <span class="">~</span>
                                <@ctl.text name="loanMaxRate" value="${loanMaxRate}" counter="%"/>
                            </div>
                        </div>
                        <div class="sl_li sl_btn">
                             <@ctl.button type="submit" class="btn btn_primary" text="search"/>
                        </div>
                    </form>
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
                                <th class="table_cell tl">Plan amount</th>
                                <th class="table_cell tl">Real amount</th>
                                <th class="table_cell tl">Interest rates</th>
                                <th class="table_cell tl">Loan limit</th>
                                <th class="table_cell tl">Status</th>
                                <th class="table_cell tr">Operate</th>
                            </tr>
                        </thead>
                        <tbody class="tbody" id="">
                        <#if lstloan?has_content>
                        <#list lstloan as loan>
                            <tr>
                                <td class="table_cell tl">${loan.loanCode}</td>
                                <td class="table_cell tl">${loan.loanName}</td>
                                <td class="table_cell tl">${loan.customName}</td>
                                <td class="table_cell tl"><@h.numf value=(loan.loanAmount)!0 />dollar</td>
                                <td class="table_cell tl"><@h.numf value=(loan.actualAmount)!0 />dollar</td>
                                <td class="table_cell tl">${loan.loanInterestRates}%</td>
                                <td class="table_cell tl"><@bizTag.loanLimit limit="${loan.loanTimeLimit}" unit="1" /></td>
                                <td class="table_cell tl"><@bizTag.loanStatus statusCode=loan.loanStatus loanAmount=loan.loanAmount investedAmount=loan.currentInvestedShare loanEndTime=loan.loanEndTime /></td>
                                <td class="table_cell tr">
                                <@ctl.operateButton href="${taglibs.allctx}/business/project/view.htm?loanCode=${loan.loanCode}" title="view" class="operate_icon view" text="view"/>
                                </td>
                            </tr>
                        </#list>
                        <#else>
						  <tr><td colspan="8">No data</td></tr>
						</#if>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="pagination_wrp">
                <@ctl.page page=criteria.page>
            	</@ctl.page>
            </div>
        </div>
    </div>
   </form> 
</div>
</@cl.html>