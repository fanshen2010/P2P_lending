<div id="projects_info" class="tab-bd-con hide">
    <div class="form_wrp">
        <table class="table" cellpadding="0" cellspacing="0">
            <tbody class="tbody" >
                <input type="hidden" name="projectInfoDto.id" value="${(projectInfoDto.id)!}"/>
                <tr>
                    <td class="table_right_border  size1of2">
                        <label class="frm_label">Loan name:</label>
                        <div class="frm_controls">
                            <@ctl.text  name="projectInfoDto.loanName"  required=true  validate={"maxlength":"30"} value="${(projectInfoDto.loanName)!}" fieldName="Loan name"/>
                        </div>
                    </td>
                    <td class=" size1of2">
                        <label class="frm_label">Loan amount:</label>
                        <div class="frm_controls">
                            <@ctl.text id="loanAmount" name="projectInfoDto.loanAmount" counter="dollar"  required=true  validate={"maxlength":"13","integerorFloat2":"true"} value="${(projectInfoDto.loanAmount)!}" fieldName="Plan amount"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Loan limit:</label>
                        <div class="frm_controls unit_select">
                            <@ctl.text id="loanTimeLimit" name="projectInfoDto.loanTimeLimit"   validate={"maxlength":"4","positiveInteger":"true","max":"9999"} value="${(projectInfoDto.loanTimeLimit)!}"  required=true fieldName="Loan limit"/>
                            <@ctl.dropdownlist id="loanTimeLimitUnit" name="projectInfoDto.loanTimeLimitUnit"    groupCode="LoanTimeLimitUnitEnum"  dataType="Enum"  class="selectpicker" spanClass="frm_select_picker" value="${(projectInfoDto.loanTimeLimitUnit)!}"/>
                        </div>
                    </td>
                    <td class="size1of2">
                         <label class="frm_label">Start amount:</label>
                        <div class="frm_controls">
                            <@ctl.text id="loanStartShare" name="projectInfoDto.loanStartShare" counter="dollar"  required=true  validate={"positiveInteger":"true","ltTo":"#loanAmount"} value="${(projectInfoDto.loanStartShare)!}" fieldName="Start amount"/>
                        </div>
                    </td>
                    
                </tr>
                <tr>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Unit price:</label>
                        <div class="frm_controls">
                            <@ctl.text  name="projectInfoDto.loanUnitPrice" counter="dollar"  required=true  validate={"positiveInteger":"true","ltTo":"#loanAmount"} value="${(projectInfoDto.loanUnitPrice)!}" fieldName="Unit price"/>
                        </div>
                    </td>
                    <td class="size1of2" id="interestManner">
                    	<@bizTag.interestManner productCode=""  value="${(projectInfoDto.loanInterestManner)!}"/>
                    </td>
                    
                </tr>
                <!-- 保存项目支付信息结束 -->
                <tr>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Max invest amount:</label>
                        <div class="frm_controls">
                            <@ctl.text name="projectInfoDto.loanMaxInvest" counter="dollar"  required=true  validate={"maxlength":"13","twoDecimal":"true","ltTo":"#loanAmount","gtTo":"#loanStartShare"} value="${(projectInfoDto.loanMaxInvest)!}" fieldName="Max invest amount"/>
                        </div>
                    </td>
                    <td class=" size1of2">
                        <label class="frm_label">Interest rates:</label>
                        <div class="frm_controls">
                            <@ctl.text  name="projectInfoDto.loanInterestRates" counter="%"  required=true  validate={"integerorFloat2":"true","range":"0,100"} value="${(projectInfoDto.loanInterestRates)!}" fieldName="Interest rates"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Invest end time:</label>
                        <div class="frm_controls">
                            <@ctl.datetimepicker  name="projectInfoDto.loanEndTime"  min="now" required=true  validate={"dateTimeFormat24":"true"} value="${(projectInfoDto.loanEndTime?string('yyyy-MM-dd hh:mm:ss'))!}" fieldName="Invest end time"/>
                            <#--<p class="frm_input_prompt">建议选择工作日，避免节假日放款延时</p>-->
                        </div>
                    </td>
                    <td class=" size1of2">
                        <label class="frm_label">Platform fee rates:</label>
                        <div class="frm_controls">
                            <@ctl.text  name="projectInfoDto.platformRate" counter="%"  required=true  validate={"integerorFloat2":"true","range":"0,100"} value="${(projectInfoDto.platformRate)!}" fieldName="Platform fee rates"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="table_right_border size1of1" colspan="2">
                        <label class="frm_label">Loan description:</label>
                        <div class="frm_controls">
                            <@ctl.textarea  name="loanProjectMsgDto.loanUse" required=true class="frm_textarea" required="true" value="${(loanProjectMsgDto.loanUse)!}" validate={"maxlength":"1000"} fieldName="Loan description" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">Premise:</label>
                        <div class="frm_controls">
                            <@ctl.textarea  name="loanProjectMsgDto.premise" class="frm_textarea"  value="${(loanProjectMsgDto.premise)!}" validate={"maxlength":"1000"}/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class=" size1of1" colspan="2">
                        <label class="frm_label">RepaySource:</label>
                        <div class="frm_controls">
                            <@ctl.textarea  name="loanProjectMsgDto.repaySource" class="frm_textarea" value="${(loanProjectMsgDto.repaySource)!}" validate={"maxlength":"1000"}/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class=" size1of1" colspan="2">
                        <label class="frm_label">RiskControl:</label>
                        <div class="frm_controls">
                            <@ctl.textarea  name="loanProjectMsgDto.riskControl" class="frm_textarea" value="${(loanProjectMsgDto.riskControl)!}" validate={"maxlength":"1000"}/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class=" size1of1" colspan="2">
                        <label class="frm_label">Platform suggest:</label>
                        <div class="frm_controls">
                            <@ctl.textarea  name="loanProjectMsgDto.platform" class="frm_textarea" value="${(loanProjectMsgDto.platform)!}" validate={"maxlength":"1000"}/>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
		$("#loanTimeLimitUnit").change(function(){
		 	var params = {};
			var loanTimeLimitUnitVal = $(this).find("option:selected").val();
			if(loanTimeLimitUnitVal==1){
				$("#loanTimeLimit").data("ruleMax", "99");
			}else{
				$("#loanTimeLimit").data("ruleMax", "9999");
			}
		});
		
</script>
