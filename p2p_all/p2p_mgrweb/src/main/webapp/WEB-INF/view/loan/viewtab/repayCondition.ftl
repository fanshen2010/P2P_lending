<div class="form_wrp">
    <table class="table table-striped table-hover form_wrp_padding" cellpadding="0" cellspacing="0">
        <thead class="thead">
            <tr>
                <th class="table_cell tl">Loan</th>
                <th class="table_cell tl">Plan</th>
                <th class="table_cell tl">Repaid</th>
                <th class="table_cell tl">Repaying</th>
            </tr>
        </thead>
        <tbody class="tbody" id="">
            <tr>
                <td class="">principal（dollar）</td>
                <td class=""><@h.numf value=(loanDto.projectInfoDto.receivablePrincipal)!0 /></td>
                <td class=""><@h.numf value=(loanDto.projectInfoDto.receivedPrincipal)!0 /></td>
                <td class=""><@h.numf value=((loanDto.projectInfoDto.receivablePrincipal)!0)-((loanDto.projectInfoDto.receivedPrincipal)!0) /></td>
            </tr>
            <tr>
                <td class="">interest（dollar）</td>
                <td class=""><@h.numf value=(loanDto.projectInfoDto.receivableInterest)!0 /></td>
                <td class=""><@h.numf value=(loanDto.projectInfoDto.receivedInterest)!0 /></td>
                <td class=""><@h.numf value=((loanDto.projectInfoDto.receivableInterest)!0)-((loanDto.projectInfoDto.receivedInterest)!0) /></td>
            </tr>
            <tr class="last">
                <td class="">Repaying total amount（dollar）</td>
                <td class=""><@h.numf value=(loanDto.projectInfoDto.receivableSum)!0 /></td>
                <td class=""><@h.numf value=(loanDto.projectInfoDto.receivedSum)!0 />
                	<#-- 代偿数据TODO
                	<span>（Platform repay:50元；Guarantee repay:20元）</span>
                	-->
                </td>
                <td class=""><@h.numf value=((loanDto.projectInfoDto.receivableSum)!0)-((loanDto.projectInfoDto.receivedSum)!0) /></td>
            </tr>
        </tbody>
    </table>
</div>