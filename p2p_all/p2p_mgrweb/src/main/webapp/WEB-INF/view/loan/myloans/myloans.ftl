<@cl.html title="后台管理_我的融资申请">
                <div class="col_main">
                    <div class="main_hd">
                        <div class="clearfix">
                            <h2>Loan Searching</h2>
                        </div> 
                     </div>
                    <div class="main_bd">
                    	<form id="searchForm" action="${taglibs.allctx}/loan/myloans/myloans.htm" method="POST">
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
                                    <@ctl.submit  text="search"/>
                                </div>
                            </div>
                        </div>
                        <div class="main_list">
                                <dl class="filter">
                                <dd><a id="0" class="" href="javascript:;"　title="" >All<em class="num">(${allCount})</em></a></dd>
                                <dd><a id="3" class="" href="javascript:;" title="" >Investing<em class="num">(${effectiveCount})</em></a></dd>
                                <dd><a id="4" class="" href="javascript:;" title="" >Repaying<em class="num">(${replayingCount})</em></a></dd>
                                <dd><a id="5" class="" href="javascript:;" title="" >Finished<em class="num">(${overCount})</em></a></dd>
                            </dl>
                            <input type="hidden" id="status" name="status" value="${status}"/>
                            <div>
                                <div  class="form_wrp">
                                    <div class="nowrap_table">
                                        <table class="table form_wrp_padding table-striped table-hover" cellpadding="0" cellspacing="0">
                                            <thead class="thead">
                                                <tr>
                                                    <th class="table_cell tl">Loan code</th>
                                                    <th class="table_cell tl">Loan name</th>
                                                    <th class="table_cell tl">Customer name</th>
                                                    <th class="table_cell tl">Loan amount（dollar）</th>
                                                    <th class="table_cell tl">Status</th>
                                                    <th class="table_cell tr">Operate</th>
                                                </tr>
                                            </thead>
                                            
                                            
                                            <tbody class="tbody" id="">
                                                <#if lstLoan?has_content>
                                                <#list lstLoan as loan>
                                                <@ctl.tr rowindex=loan_index>
                                                    <td class=" table_cell tl">${(loan.loanCode)}</td>
                                                    <td class=" table_cell tl">${(loan.loanName)}</td>
                                                    <td class=" table_cell tl">${(loan.customName)}</td>
                                                    <td class=" table_cell tl">${(loan.loanAmount)}</td>
                                                    <td class=" table_cell tl">
                                                       <#-- 关于审核中的流程状态，为定值，请注意修改 -->
                                                       <@bizTag.loanStatus statusCode=loan.loanStatus loanAmount="${(loan.loanAmount)}" loanEndTime=loan.loanEndTime investedAmount="${(loan.currentInvestedShare)}" bizType="1" loanId="${(loan.id)}"/>
                                                    </td>
                                                    <td class=" table_cell tr">
                                                       <#if loan.loanStatus = "03">
                                                       		<@ctl.operateButton linkType="edit" href="${taglibs.allctx}/loan/apply/new_loan.htm?loanCode=${loan.loanCode}&loanType=${loan.loanType}&export=1&updateDistinguish=1" title="modify" text="modify"/>
                                                       </#if>
                                                       <@ctl.operateButton linkType="view" href="view.htm?loan.loanCode=${(loan.loanCode)}" title="view" text="view"/>
                                                       <#if loan.loanStatus = "03">
                                                       		<@ctl.operateButton id="del_${(loan_index)}" linkType="del" href="delete.htm?loanCode=${(loan.loanCode)}" title="delete" text="delete"/>
                                                       </#if>
                                                    </td>
                                                </@ctl.tr>
                                                </#list>
                                                <#else>
                                                   <tr><td colspan="6">No data</td></tr>
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
                </div>
           <script type="text/javascript">
            $(function(){
            	var linkId = '0';
            	if ($("#status").val()=='03') {
            		linkId = '1';
            	} else if ($("#status").val()=='01') {
            		linkId = '2';
            	} else if ($("#status").val()=='04,05,07') {
            		linkId = '3';
            	} else if ($("#status").val()=='10') {
            		linkId = '4';
            	} else if ($("#status").val()=='09,11,12') {
            		linkId = '5';
            	}
            	$("#" + linkId).addClass("active").parent().siblings().children().removeClass("active");
            	
            });
            $(".filter dd a").click(function(){
            	var linkId = this.id;
            	if (linkId == "0") {
            		$("#status").val("00");
            	} else if  (linkId == "1") {
            		$("#status").val("03");
            	} else if  (linkId == "2") {
            		$("#status").val("01");
            	} else if  (linkId == "3") {
            		$("#status").val("04,05,07");
            	} else if  (linkId == "4") {
            		$("#status").val("10");
            	} else if  (linkId == "5") {
            		$("#status").val("09,11,12");
            	}
            	$(this).addClass("active").parent().siblings().children().removeClass("active");
            	$("#searchForm").submit();
            });
            $("#del").click(function(){
            	confirm("确认要启用本项目吗？")
            });
        </script>
 </@cl.html>