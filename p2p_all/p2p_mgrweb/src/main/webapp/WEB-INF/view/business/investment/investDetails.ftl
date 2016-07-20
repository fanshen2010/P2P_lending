<div class="form_wrp">
    <table class="table" cellpadding="0" cellspacing="0">
        <tbody class="tbody" id="">
            <tr>
                <td class="size1of2 table_right_border" colspan="2">
                    <label class="frm_label">Invest code:</label>
                    <div class="frm_controls">
                        <span class="frm_val">
                            <span>${invest.investCode}</span>
                        </span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="size1of2 table_right_border">
                    <label class="frm_label">User name:</label>
                    <div class="frm_controls">
                        <span class="frm_val">
                            <span>${invest.investUserName}</span>
                        </span>
                    </div>
                </td>
                <td class=" size1of2">
                    <label class="frm_label">Invest time:</label>
                    <div class="frm_controls">
                        <span class="frm_val">
                            <span><@h.datef value=invest.investmentTime /></span>
                        </span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="size1of2 table_right_border">
                    <label class="frm_label" title="Interest rates">Interest rates:</label>
                    <div class="frm_controls">
                        <span class="frm_val">
                            <span>${invest.investInterstRate}%</span>
                        </span>
                    </div>
                </td>
                <td class=" size1of2">
                    <label class="frm_label" >Invest amount:</label>
                    <div class="frm_controls">
                        <span class="frm_val">
                            <span>${invest.investmentAmount}dollar</span>
                        </span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="size1of2 table_right_border">
                    <label class="frm_label">Interest date:</label>
                    <div class="frm_controls">
                        <span class="frm_val">
                            <span><@h.datef value=invest.interestDate /></span>
                        </span>
                    </div>
                </td>
                <td class=" size1of2">
                    <label class="frm_label">end date:</label>
                    <div class="frm_controls">
                        <span class="frm_val">
                            <span><@h.datef value=invest.endDate /></span>
                        </span>
                    </div>
                </td>
            </tr>
            <tr class="last">
                <td class="size1of2 table_right_border">
                	<label class="frm_label">Interest:</label>
                    <div class="frm_controls">
                        <span class="frm_val">
                            <span>${invest.investInterst}dollar</span>
                        </span>
                    </div>
                </td>
                <td class=" size1of2">
                    <label class="frm_label">Invest status:</label>
                    <div class="frm_controls">
                        <span class="frm_val mark">
                            <span><@bizTag.enumValue enumName="InvestStatusEnmu" key=invest.status /></span>
                            <!-- 状态有:已转出、转让中 -->
                        </span>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
</div>
