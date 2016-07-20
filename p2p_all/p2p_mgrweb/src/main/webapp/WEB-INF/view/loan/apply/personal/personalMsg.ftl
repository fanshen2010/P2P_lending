                            <#if custPersonalInfo??>
                            <div class="form_wrp">
                            	<h3 class="h3">Customer information</h3>
                                <table class="table" cellpadding="0" cellspacing="0">
                                    <tbody class="tbody" id="">
                                        <tr>
                                            <td class="size1of1" colspan="2">
                                                <label class="frm_label">Customer name:</label>
                                                <div class="frm_controls"> 
                                                    <span class="frm_val">${custPersonalInfo.customerName}</span>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr class="last">
                                            <td class="table_right_border size1of2">
                                                <label class="frm_label">ID number:</label>
                                                <div class="frm_controls"> 
                                                    <span class="frm_val">${custPersonalInfo.identity}</span>
                                                </div>
                                            </td>
                                            <td class="size1of2">
                                                <label class="frm_label">Contact phone:</label>
                                                <div class="frm_controls"> 
                                                    <span class="frm_val">${custPersonalInfo.cellphone}</span>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            </#if>
                            
                            <#if loanCustomerInfoDto??>
		                         <#if loanCustomerInfoDto.loanCode??>
		                        
		                        <p class="tr ptb20">Customer has loan record, export the latest information?</p>
		                        <form id="resultfrm" action="new_loan.htm" method="POST">
		                        <#--用于导入个人历史数据-->
		                        <input type="hidden"   name="loanCode" value="${(loanCustomerInfoDto.loanCode)!}">
		                        <input type="hidden" name="loanType" value="0">
		                        <div class="tr ">
		                        	<@ctl.radiobuttonlist  name="export" groupCode="ifExport" value="1" dataType="BizCode"/>
		                        </div>
		                        </form>
		                        <div class="tr ptb20">
		                            <@ctl.linkButton float="fr" text="Next"/>
		                        </div>
		                         <#else>
		                        <div class="clearfix">
		                        	<form id="resultfrm" action="new_loan.htm" method="POST">
		                        	<input type="hidden" name="loanType" value="0">
		                        	<input type="hidden" id="customerCode" name="customerCode" value="${(loanCustomerInfoDto.customerCode)!}"/>
		                                <p class="fl">Customer has no loan record, please click<span class="add_finance_next">Next</span>to fill in new information。</p>
		                                <div class="fr">
		                                   <@ctl.linkButton float="fr" text="Next"/>
		                                </div>
		                            </form>
		                        </div>
		                        </#if>
                            <#else>
                            <div class="clearfix">
                            	<form id="resultfrm" action="new_loan.htm" method="POST">
                            	<input type="hidden" name="loanType" value="0">
                            	<input type="hidden" id="customerCode" name="customerCode" value="${(loanCustomerInfoDto.customerCode)!}"/>
	                                <p class="fl">Customer has no loan record, please click<span class="add_finance_next">Next</span>to fill in new information。</p>
	                                <div class="fr">
	                                   <@ctl.linkButton float="fr" text="Next"/>
	                                </div>
                                </form>
                            </div>
                            </#if>
                           <script type="text/javascript">
                            	 $(function(){
                            	$("a.btn").click(function(){$("#resultfrm").submit();});
                            	$(".uniformjs").find("select, input, button, textarea").uniform();
                            });
							</script>
                            