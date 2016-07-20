     	<div class="handle_area">
            <div class="table_wrp">
                <table class="table" cellpadding="0" cellspacing="0" id="tabRepayDetail">
                	<thead class="thead">
                        <tr>
                            <th class="table_cell tl">Investor</th>
                            <th class="table_cell tl">Repay plan amount（dollar）</th>
                            <th class="table_cell tl">Repay real amount（dollar）</th>
                        </tr>
                    </thead>
                    <tbody class="tbody" id="">
                    <#list investDetails as detail>
                        <tr>
                            <td class=" table_cell">${(detail.investUserName)!}</td>
                            <td class=" table_cell"><@h.numf value=detail.receivableSum /></td>
                            <td class=" table_cell"><@h.numf value=detail.recievedSum /></td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
