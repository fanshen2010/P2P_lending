<@cl.html title="Personal Loan Customer Manage" js="bussiness/personal.js">
<div class="col_main">
    <div class="main_hd">
        <div class="clearfix">
            <h2><a class="fr btn btn_primary fancybox add" href="#cust_add_pop" title="New Customer">add</a>Personal Loan Customer Manage</h2>
        </div>
    </div>
    <div class="main_bd">
        <form action="${taglibs.allctx}/customer/personal/index.htm" id="searchForm"  method="POST">
            <div class="search_form">
                <div class="search_list clearfix">
                    <div class="sl_li">
                        <label class="frm_label">CustName:</label>
                        <div class="frm_controls">
                            <@ctl.text   name="name"  value="${name!}"  />
                        </div>
                    </div>
                    <div class="sl_li">
                        <label class="frm_label">Phone:</label>
                        <div class="frm_controls">
                            <@ctl.text   name="phone"  value="${phone!}"  />
                        </div>
                    </div>
                    <div class="sl_li sl_btn">
                        <@ctl.submit text="search"  />
                    </div>
                </div>
            </div>
            <div class="main_list">
                <div class="form_wrp">
                    <div class="nowrap_table">
                        <table class="table form_wrp_padding table-striped table-hover" cellpadding="0" cellspacing="0">
                            <thead class="thead">
                            <tr>
                                <th class="table_cell tl">Cust name</th>
                                <th class="td_idnum tl">ID number</th>
                                <th class="table_cell tl">Contact phone</th>
                                <th class="table_cell tl">Loan amount（dollar）</th>
                                <th class="table_cell tl">Repaying amount（dollar）</th>
                                <th class="table_cell tr">Operate</th>
                            </tr>
                            </thead>
                            <tbody class="tbody" id="">
                            	<#if customerDtoList?has_content>
                                <#list customerDtoList as customerDto>
                                    <@ctl.tr rowindex=customerDto_index>
                                    <td class=" table_cell tl">${(customerDto.personalDto.customerName)!}</td>
                                    <td class=" table_cell tl">${(customerDto.personalDto.identity)!}</td>
                                    <td class=" table_cell tl">${(customerDto.personalDto.cellphone)!}</td>
                                    <td class=" table_cell tl"><@h.numf value=customerDto.statisticalDto.loanAmount/></td>
                                    <td class=" table_cell tl"><@h.numf value=customerDto.statisticalDto.waitPayAmount/></td>
                                    <td class=" table_cell tr">
                                        <@ctl.operateButton linkType="view" href="${taglibs.allctx}/customer/personal/view.htm?id=${customerDto.personalDto.id}" title="view" text="view"/>
                                        <@ctl.linkButton class="operate_icon edit" href="javascript:;" data={'id':'${customerDto.personalDto.id}'} title="set" text="set" />
                                    </td>
                                    </@ctl.tr>
                                </#list>
                                <#else>
								  <tr><td colspan="8">No data</td></tr>
								</#if>
                            </tbody>
                        </table>
                    </div>
                </div>
                <@ctl.page page=criteria.page>
                </@ctl.page>
            </div>
        </form>
    </div>
</div>
<!--个人客户添加弹出层-->
<div class="popsize_l cust_add_pop" id="cust_add_pop">
</div>
<!--个人设置修改弹出层-->
<div class="popsize_l cust_edit_pop" id="cust_edit_pop">

</div>
<script type="text/javascript">
    // 个人客户添加
    $(".add").click(function(){
        $.ajaxLoadFtl({
            url: "${taglibs.allctx}/customer/personal/add.htm",
            data: {},
            error: function (data, transport) {
                $("#cust_add_pop").html("服务器繁忙");
            },
            success: function (map) {
                $("#cust_add_pop").html(map.result);
                $.fancybox.open($("#cust_add_pop"),{"title":"New Customer","afterLoad" : function(){
                    this.inner.addClass("heightauto");
                }});
                $(".btn_cancel").click(function() {
                    $.fancybox.close();
                });
            }
        });
    });

    // 个人客户修改
    $(".edit").click(function(){
        $.ajaxLoadFtl({
            url: "${taglibs.allctx}/customer/personal/edit.htm",
            data: {"id":$(this).data("id")},
            error: function (data, transport) {
                $("#cust_edit_pop").html("服务器繁忙");
            },
            success: function (map) {
                $("#cust_edit_pop").html(map.result);
                $.fancybox.open($("#cust_edit_pop"),{"title":"set","afterLoad" : function(){
                    this.inner.addClass("heightauto");
                }});
                $(".btn_cancel").click(function() {
                    $.fancybox.close();
                });
            }
        });
    });
</script>
</@cl.html>           
 