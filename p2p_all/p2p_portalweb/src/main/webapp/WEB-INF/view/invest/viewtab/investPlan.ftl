<p class="payment_plan_txt"><#--具体回款日期为每月起息日当天--></p>
<table class="table table-bordered payment_plan_table">
	<thead>
        <tr>
            <th class="pp-periods tl">period</th>
            <th class="pp-principal tl">principal</th>
            <th class="pp-interest tl">interest</th>
            <th class="pp-amount tl">total</th>
        </tr>
    </thead>
    <tbody>
    	<#list installments as installment>
	        <tr>
	            <td>${installment.num}/${installments?size}</td>
	            <td>$<@h.numf value=installment.principal /></td>
	            <td>$<@h.numf value=installment.interest /></td>
	            <td>$<@h.numf value=installment.total /></td>
	        </tr>
        </#list>
    </tbody>
</table>