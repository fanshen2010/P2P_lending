<div class="proinfo-item">      
    <h2 class="proinfo-tit">Loan History</h2>
    <div class="proinfo-cont finance-record">
        <div class="fr-total">
            <div class="fr-total-li">
                <div class="fr-total-libox">
                    <p class="frt-li">
                        <span class="frt-li-lab">repaid ontime:</span>
                        <span class="frt-li-val"><em><@h.numi value=loaneeLoanRedordDto.normalPay /></em> times</span>
                    </p>
                    <p class="frt-li">
                        <span class="frt-li-lab">repaid overdue:</span>
                        <span class="frt-li-val"><em><@h.numi value=loaneeLoanRedordDto.overduePay /></em> times</span>
                    </p>
                    <p class="frt-li">
                        <span class="frt-li-lab">total borrow :</span>
                        <span class="frt-li-val">$<em><@h.numf value=loaneeLoanRedordDto.totalBorrow /></em></span>
                    </p>
                    <p class="frt-li">
                        <span class="frt-li-lab">repaying:</span>
                        <span class="frt-li-val">$<em><@h.numf value=loaneeLoanRedordDto.stillAmount /></em></span>
                    </p>
                </div>
            </div>
        </div>
        <div class="fr-list">
        	<table class="table">
            	<thead>
                	<tr>
                    	<th class="tl finance-id">loanCode</th>
                        <th class="tl finance-product">loanName</th>
                        <th class="tl finance-cash">amount（dollar）</th>
                        <th class="tl finance-status">status</th>
                        <th class="tl finance-data">applyTime</th>
                    </tr>
                </thead>
                <tbody>
               	 	<#list loaneeLoanRedordDto.loans as loan>
	                	<tr>
	                    	<td class="finance-id">${loan.loanCode}</td>
	                        <td class="finance-product"><div class="text_overflow">${loan.loanName}</div></td>
	                        <td class="finance-cash"><@h.numf value=loan.loanAmount /></td>
	                        <td class="finance-status"><@bizTag.loanStatus statusCode=loan.loanStatus/></td>
	                        <td class="finance-data"><@h.datef value=loan.createTime /></td>
	                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>