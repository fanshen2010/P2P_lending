<div class="uc-table" id="investDetail">
<form action="" method="POST" id="investRecordform">
	<table class="table">
	    <thead>
	        <tr>
	            <th class="tl period-num-td">period</th>
	            <th class="tl project-date">repay Plan</th>
	            <th class="tl project-date">repay Date</th>
	            <th class="tl ">amount（$）</th>
	            <th class="tl ">principal（$）</th>
	            <th class="tl ">interest（$）</th>
	            <th class="tl ">penalty（$）</th>
	            <th class="tl ">status</th>
	        </tr>
	    </thead>
	    <tbody>
			<#list lstInvestDetail as investDetail>
	        <tr>
	            <td>${investDetail.num}/${investDetailcriteria.page.totalRecord}</td>
	            <td><@h.datef value=investDetail.recievePlanDate/></td>
	            <td><@h.datef value=investDetail.recieveRealDate /></td>
	            <td><@h.numf value=investDetail.receivableSum/></td>
	            <td><@h.numf value=investDetail.receivablePrincipal/></td>
	            <td><@h.numf value=investDetail.receivableInterest/></td>
	            <td><@h.numf value=investDetail.punitiveInterest/></td>
	            <td><font <#if investDetail.status=="1">color="green"<#elseif investDetail.status=="2">color="red"</#if>><@bizTag.enumValue enumName="InvestDetailStatusEnum" key=investDetail.status /></font></td>
	        </tr>
	        </#list>
	    </tbody>
	</table>
	<input type="hidden" name="investCode" value="${investCode}"/>
	<div class="pagination pagination-right">
	    <@ctl.pageAjax page=investDetailcriteria.page url="myInvestDetailAjax.htm" elemId="investDetail" hideElemPre="investDetailcriteria" formId="investRecordform"/>
	</div>
	</form>
</div>

 