<#if loanDto.projectInfoDto.loanPostTime?string("yyyy-MM-dd HH:mm:ss")?datetime("yyyy-MM-dd HH:mm:ss") lt sysDate?string("yyyy-MM-dd HH:mm:ss")?datetime("yyyy-MM-dd HH:mm:ss") >
<div class="widget invest-records" id="investRecord" >
    <h3 class="widget-head"><strong>Invest Record</strong></h3>
    <div class="widget-body">
        <table class="table">
            <thead>
                <tr>
                    <th class="invest-records-user tl">investor</th>
                    <th class="invest-records-time tl">investTime</th>
                    <th class="invest-records-cash tl">investAmount</th>
                </tr>
            </thead>
            <tbody>
            	<#list investRecord as invest>
                <tr>
                    <td><@h.subStr value=((invest.investUserName)!"")/></td>
                    <td>${invest.investmentTime?string("yyyy-MM-dd HH:mm")}</td>
                    <td>$<@h.numf value=invest.investmentAmount/></td>
                </tr>
                </#list>
            </tbody>
        </table>
        <form action="" id="investRecordform" method="POST">
        <input type="hidden" name="loanCode" value="${(loanCode)!}" />
        <@ctl.pageAjax page=investCriteria.page url="${taglibs.ctx}/invest/investRecordAjax.htm" css="pagination-right" hideElemPre="investCriteria" elemId="investRecord" formId="investRecordform"/>
        </form>
    </div>
</div>
<#else>
<div class="widget invest-records">
    <h3 class="widget-head"><strong>invest Record</strong></h3>
    <div class="widget-body">
        <h2 style="text-align:center;color:#959595">loan is not open for investment now</h2>
    </div>
</div>
</#if>