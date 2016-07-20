<@cl.html title="后台管理_还款管理">
<div class="col_main">
            <div class="main_hd">
            	<div class="clearfix">
                    <ul class="direction">
                        <li><span class="txt">you are here:</span></li>
                        <li><a id="" class="" href="javascript::">index</a><em>|</em></li>
                        <li><span class="txt">Repayment Manage</span></li>
                    </ul>    
                    <h2><a id="" class="btn btn_primary" href="${taglibs.ctx}/loan/afterLoan/repayManage/repayManage.htm"  >return</a>${titleName}</h2>
                </div> 
             </div>
            <div class="main_bd">
                <div class="form_wrp">    
                    <table class="table noborder" cellpadding="0" cellspacing="0">
                        <tbody class="tbody" id="">
                            <tr>
                                <td class="border_bottom_none size1of1" colspan="2">
                                    <label class="frm_label">repay amount</label>
                                    <div class="frm_controls">
                                        <div class="frm_val principal">
                                        	<strong>${((retRepayDetail.receivableSum)!0) + ((retRepayDetail.punitiveDelayPayment)!0) + ((retRepayDetail.punitiveInterest)!0)}<span>dollar</span></strong>
                                            <span>(principal:${(retRepayDetail.receivablePrincipal)!}dollar , interest:${(retRepayDetail.receivableInterest)!}dollar)</span>
                                        </div>
                                    </div>	
                                </td>
                            </tr>
                            <tr>
                                <td class="border_bottom_none size1of1" colspan="2">
                                    <label class="frm_label">repay period:</label>
                                    <div class="frm_controls">
                                        <div class="frm_val">${(retRepayDetail.num)!}/${(loanDto.projectInfoDto.totalRepayNumber)!}</div>
                                        <input type="hidden" id="curLoanCode" value=${curLoanCode} />
                                    </div>	
                                </td>
                            </tr>
                            <tr>
                                <td class="border_bottom_none size1of1" colspan="2">
                                    <label class="frm_label">repay plan date:</label>
                                    <div class="frm_controls">
                                        <div class="frm_val"><@h.datef value=(retRepayDetail.repayPlanDate)! /></div>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="tab">
                	<ul class="tab_navs tab-hd  position_tab title_tab mb20" id="">
                        <li class="tab-hd-con tab_nav"><a href="javascript:;">Loan Info</a></li>
                        <li class="tab-hd-con tab_nav"><a href="javascript:;">Invest Info</a></li>
                        <li class="tab-hd-con tab_nav"><a href="javascript:;">Repay Statistic</a></li>
                        <li class="tab-hd-con tab_nav active first"><a href="javascript:;">Repay Record</a></li>
                    </ul>
                    <div class="tab-bd">
                    	<div class="tab-bd-con hide">
                			<#include "${taglibs.ftlctx}/loan/viewtab/loanProjectInfo.ftl" >
                		</div>
                        <div class="tab-bd-con hide">
                        	<#include "${taglibs.ftlctx}/loan/viewtab/investInformation.ftl" >
                        </div>
                        <div class="tab-bd-con hide">
                        	<#include "${taglibs.ftlctx}/loan/viewtab/repayCondition.ftl" >
                        </div>
                        <div class="tab-bd-con active">
                        	<#include "${taglibs.ftlctx}/loan/viewtab/commRepayRecord.ftl" >
                        </div>	
                    </div>
                </div>
            </div>
            <#if operatType=="one">
            	<#if retRepayDetail.status=="0" || retRepayDetail.status=="2">
            	 <div id="foot_operate_area">
                    <div id="bottomOperate" class="info_operate info_operate_fixed clearfix" >
                        <div class="fl">
                        	<@ctl.button id="quid_btn" text="Platform repay" />
                        	<#if (loanDto.product.hasGuaranteeFlag)! && loanDto.product.hasGuaranteeFlag=="1">
                        		<@ctl.button id="guarantee_btn" text="Guarantee repay" />
	            			</#if>
                        </div>
                        <div class="fr">
                        	<@ctl.button id="repayment_btn" text="repay" />
                        </div>
                    </div>
                    <div class="foot_operate_fixed"></div>
          		 </div>
          		 </#if>
            </#if>
</div>
<div class="popsize_m fancybox_pop" id="repayment_opt">
	<form action="#" id="repaymentfrm" method="POST">
	<@ctl.token/>
	<input type="hidden" id="loanCode" name="curLoanCode" value="${curLoanCode}" />
	<input type="hidden" id="curNum" name="curNum" value="${(retRepayDetail.num)!}" />
	<input type="hidden" name="payTlementType" value="" />
        <div class="form_wrp confirm_area">
            <table class="table" cellpadding="0" cellspacing="0">
                <tbody class="tbody" id="">
                    <tr class="last">
                        <td class="border_bottom_none size1of1" colspan="2" style="border:0;">
                            <label class="frm_label">Password:</label>
                            <div class="frm_controls">
                            	<@ctl.password id="txtPassWord" name="passWord" required=true  validate={"remote":"${taglibs.ctx}/checkPassword.htm"} fieldName="password"/>
                            </div>	
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
	    <p class="form_btn">
	    	<@ctl.button id="btnOkSubmit" text="confirm" />
	        <@ctl.button id="btnCancel"  class="btn" text="cancel" />
	    </p>
    </form>
</div>
<script type="text/javascript">
	$("#btnCancel").click(function(){
		$.fancybox.close();
	});
	$("#btnOkSubmit").click(function(){
		if ($("#repaymentfrm").valid() == false) {
			return;
		}
		$("input[name='payTlementType']").val($("#payTlementType").val());
		$("#btnOkSubmit").unbind("click");
		$("#btnOkSubmit").removeClass("btn_primary").addClass("btn_disabled").text("processing");
		$.ajax({
	            type: "POST",
	            url: "repayOperating.htm",
	            dataType: "json",
	            data: $("#repaymentfrm").serialize(),
	            error: function (data, transport) {
	            },
	            success: function (json) {
	            	$.fancybox.close();
	            	if(json.result){
	            		setTimeout(function time(){
							info(json.html);
							setTimeout(function(){window.location.href='${taglibs.ctx}/loan/afterLoan/repayManage/repayManage.htm';},2200);
						},1000);
	            	}else{
	            		info(json.html);
	            	}
	            }
	        });
		
	});
	
	$("#repayment_btn").fancybox({
		"href" : "#repayment_opt",
		"title" :"Finanier repay",
		afterLoad:function(){
			$("#repaymentfrm").validate();
			this.inner.prepend("<input type='hidden' id='payTlementType'  name='payTlementType' value='21' />");
		}
	});	
	$("#guarantee_btn").fancybox({
		"href" : "#repayment_opt",
		"title" :"Guarantee repay",
		afterLoad:function(){
			this.inner.prepend("<input type='hidden' id='payTlementType'  name='payTlementType' value='31' />");
		}
	});	
	$("#quid_btn").fancybox({
		"href" : "#repayment_opt",
		"title" :"Platform repay",
		afterLoad:function(){
			this.inner.prepend("<input type='hidden' id='payTlementType'  name='payTlementType' value='41' />");
		}
	});	
		
</script>
</@cl.html>