	<div class="form_wrp">
	    <table class="table" cellpadding="0" cellspacing="0">
	        <tbody class="tbody" id="">
	            <tr>
	                <td class=" table_right_border size1of2" >
	                    <label class="frm_label">Loan code:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val">${(loanDto.projectInfoDto.loanCode)!}</span>
	                    </div>
	                </td>
	                <td class=" table_right_border size1of2" >
	                    <label class="frm_label">Loan name:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val">${(loanDto.projectInfoDto.loanName)!}</span>
	                    </div>
	                </td>
	            </tr>
	            <tr>
	                <td class=" table_right_border size1of2">
	                    <label class="frm_label">Customer name:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val">${(loanDto.projectInfoDto.customName)!}</span>
	                    </div>
	                </td>
	                <td class="size1of2">
	                    <label class="frm_label">Loan status:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val mark"><@bizTag.loanStatus statusCode=loanDto.projectInfoDto.loanStatus loanAmount=loanDto.projectInfoDto.loanAmount investedAmount=loanDto.projectInfoDto.currentInvestedShare loanEndTime=loanDto.projectInfoDto.loanEndTime /></span>
	                    </div>
	                </td>
	            </tr>
	            <tr>
	                <td class=" size1of2">
	                    <label class="frm_label">Plan amount:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val"><@h.numf value=(loanDto.projectInfoDto.loanAmount)!/><em class="unit">dollar</em></span>
	                    </div>
	                </td>
	                <td class=" size1of2">
	                    <label class="frm_label">Real amount:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val"><@h.numf value=(loanDto.projectInfoDto.currentInvestedShare)/><em class="unit">dollar</em></span>
	                    </div>
	                </td>
	                
	            </tr>
	            <tr>
	                <td class="table_right_border size1of2">
	                    <label class="frm_label">Loan limit:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val"><@bizTag.loanLimit limit=loanDto.projectInfoDto.loanTimeLimit unit=loanDto.projectInfoDto.loanTimeLimitUnit/></span>
	                    </div>
	                </td>
	                <td class=" size1of2">
	                     <label class="frm_label">Start amount:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val"><@h.numf value=(loanDto.projectInfoDto.loanStartShare)!/><em class="unit">dollar</em></span>
	                    </div>
	                </td>
	            </tr>
	            <tr>
	                <td class="table_right_border size1of2">
	                    <label class="frm_label">Unit price:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val"><@h.numf value=(loanDto.projectInfoDto.loanUnitPrice)/><em class="unit">dollar</em></span>
	                    </div>
	                </td>
	                <td class=" size1of2">
	                    <label class="frm_label">Interest manner:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val"><@bizTag.enumValue enumName="LoanInterestMannerEnum" key="${(loanDto.projectInfoDto.loanInterestManner)!}" /></span>
	                    </div>
	                </td>
	            </tr>
	            <tr>
	                <td class="table_right_border size1of2">
	                    <label class="frm_label">Max invest amount:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val"><@h.numf value=(loanDto.projectInfoDto.loanMaxInvest)/><em class="unit">dollar</em></span>
	                    </div>
	                </td>
	                <td class=" size1of2">
	                    <label class="frm_label">Interest rates:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val">${(loanDto.projectInfoDto.loanInterestRates)!}%</span>
	                    </div>
	                </td>
	            </tr>
	            <tr>
	                <td class="table_right_border size1of2">
	                    <label class="frm_label">Invest end time:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val"><@h.datetimef value=loanDto.projectInfoDto.loanEndTime /></span>
	                    </div>
	                </td>
	                <td class=" size1of2">
	                    <label class="frm_label">Platform fee rates:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val">${(loanDto.projectInfoDto.platformRate)!}%</span>
	                    </div>
	                </td>
	            </tr>
	            <tr>
	                <td class=" size1of1" colspan="2">
	                    <label class="frm_label">Loan description:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val">
	                            ${(loanDto.loanProjectMsgDto.loanUse)!}
	                        </span>
	                    </div>
	                </td>
	            </tr>
	            <tr>
	                <td class=" size1of1" colspan="2">
	                    <label class="frm_label">Premise:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val">
	                             ${(loanDto.loanProjectMsgDto.premise)!}
	                        </span>
	                    </div>
	                </td>
	            </tr>
	            <tr>
	                <td class=" size1of1" colspan="2">
	                    <label class="frm_label">RepaySource:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val">
	                            ${(loanDto.loanProjectMsgDto.repaySource)!}
	                        </span>
	                    </div>
	                </td>
	            </tr>
	            <tr>
	                <td class=" size1of1" colspan="2">
	                    <label class="frm_label">RiskControl:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val">
	                            ${(loanDto.loanProjectMsgDto.riskControl)!}
	                        </span>
	                    </div>
	                </td>
	            </tr>
	            <tr>
	                <td class=" size1of1" colspan="2">
	                    <label class="frm_label">Platform suggest:</label>
	                    <div class="frm_controls">
	                        <span class="frm_val">
	                            ${(loanDto.loanProjectMsgDto.platform)!}
	                        </span>
	                    </div>
	                </td>
	            </tr>
	        </tbody>
	    </table>
	</div>
