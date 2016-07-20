<@cl.html title="">
<div class="col_main">
    <div class="main_hd">
    	<div class="clearfix">
            <h2>Invest search</h2>
        </div> 
     </div>
   <form action="${taglibs.allctx}/business/investment/index.htm" id="searchForm" method="POST" >
    <div class="main_bd">
    	<div class="search_form">
            <div class="search_list clearfix">
                	<div class="sl_li">
                        <label class="frm_label">InvestCode:</label>
                        <div class="frm_controls">
                            <@ctl.text  name="investCode" value="${(investCode)!}" />
                        </div>
                    </div>
                    <div class="sl_li">
                        <label class="frm_label">UserName:</label>
                        <div class="frm_controls">
                            <@ctl.text  name="loginName" value="${(loginName)!}" />
                        </div>
                    </div>
                    <div class="sl_li">
                        <label class="frm_label">LoanName:</label>
                        <div class="frm_controls">
                            <@ctl.text  name="loanName" value="${(loanName)!}" />
                        </div>
                    </div>
                    <div class="sl_li">
                        <label class="frm_label">Status:</label>
                        <div class="frm_controls">
                            <@ctl.dropdownlist name="investStatus" hasAll=true groupCode="InvestStatusEnmu"  dataType="Enum" value="${(investStatus)!}" />
                        </div>
                    </div>
                    <div class="sl_li">
                        <label class="frm_label">InvestTime:</label>
                        <div class="frm_controls group">
                            <@ctl.datepicker name="investMinTime" value=investMinTime />
                            <span class="">~</span>
                            <@ctl.datepicker name="investMaxTime" value=investMaxTime />
                        </div>
                    </div>
                    <div class="sl_li sl_btn tr">
                        <@ctl.button type="submit" class="btn btn_primary" text="search"/>
                    </div>
            </div>
        </div>
        <div class="main_list">
            <div class="form_wrp">
                <div class="nowrap_table">
                    <table class="table form_wrp_padding table-striped table-hover" cellpadding="0" cellspacing="0">
                        <thead class="thead">
                            <tr>
                                <th class="table_cell tl">Invest code</th>
                                <th class="table_cell tl">User name</th>
                                <th class="table_cell tl">Invest amount</th>
                                <th class="table_cell tl">Loan name</th>
                                <th class="table_cell tl">Invest time</th>
                                <th class="table_cell tl">Status</th>
                                <th class="table_cell tr">Operate</th>
                            </tr>
                        </thead>
                        <tbody class="tbody" id="">
                        <#if lstInvest?has_content>
                        <#list lstInvest as invest>
                            <tr>
                                <td class="table_cell tl">${invest.investCode}</td>
                                <td class="table_cell tl">${invest.investUserName}</td>
                                <td class="table_cell tl"><@h.numf value=(invest.investmentAmount)!0 /></td>
                                <td class="table_cell tl">${invest.loanName}</td>
                                <td class="table_cell tl"><@h.datetimef value=invest.investmentTime /></td>
                                <td class="table_cell tl"><@bizTag.enumValue enumName="InvestStatusEnmu" key=invest.status /></td>
                                <td class="table_cell tr">
                                    <@ctl.operateButton href="${taglibs.allctx}/business/investment/view.htm?loanCode=${invest.loanCode}&&investId=${invest.id}" title="view" class="operate_icon view" text="view"/>
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
            <@ctl.page page=criteria.page>
            </@ctl.page>
        </div>
    </div>
  </form>
</div>
</@cl.html>