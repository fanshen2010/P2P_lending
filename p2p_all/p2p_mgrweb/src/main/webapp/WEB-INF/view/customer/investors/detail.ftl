<div class="handle_area">
    <div class="table_wrp">
        <table class="table" cellpadding="0" cellspacing="0">
            <thead class="thead">
                <tr>
                    <th class="table_cell tl">Repay plan date</th>
                    <th class="table_cell tl">Repay real date</th>
                    <th class="table_cell tl">Repay plan amount</th>
                    <th class="table_cell tl">Repay real amount</th>
                    <th class="table_cell tl">Status</th>
                    <th class="table_cell tl">Loan name</th>
                </tr>
            </thead>
            <tbody class="tbody" id="">
           	 	<#list investDetails as detail>
	                <tr class="<#if detail.status=="1">success</#if>">
	                	<td class=" table_cell"><@h.datef value=detail.recievePlanDate /></td>
	                    <td class=" table_cell"><@h.datef value=detail.recieveRealDate /></td>
	                    <td class=" table_cell"><@h.numf value=detail.receivableSum />dollar</td>
	                    <td class=" table_cell"><@h.numf value=detail.recievedSum />dollar</td>
	                    <td class=" table_cell"><@bizTag.enumValue enumName="InvestDetailStatusEnum" key=detail.status /></td>
	                    <td>${detail.loanName}</td>
	                </tr>
                </#list>
            </tbody>
        </table>
    </div>
</div>