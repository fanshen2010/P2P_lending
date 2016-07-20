<@cl.html title="">
<div class="col_main">
    <div class="main_hd">
        <div class="clearfix">
            <h2><@ctl.linkButton class="btn btn_primary" href="${taglibs.allctx}/customer/personal/index.htm" title="return" text="return"/>
                view</h2>
        </div>
    </div>
    <div class="main_bd">
        <div class="tab">
            <ul class="tab_navs tab-hd  position_tab title_tab" id="originalTab">
                <li class="tab-hd-con tab_nav first active"><a href="javascript:;">Customer Info</a></li>
                <li class="tab-hd-con tab_nav"><a href="javascript:;">Loan Info</a></li>
            </ul>
            <#--Customer information-->
            <div class="tab-bd">
                <div class="form_wrp tab-bd-con active">
                    <table class="table" cellpadding="0" cellspacing="0">
                        <tbody class="tbody" id="">
                        <tr>
                            <td class="table_right_border size1of2">
                                <label class="frm_label">Name:</label>
                                <div class="frm_controls">
                                    <span class="frm_val">${(customerDto.personalDto.customerName)!}</span>
                                </div>
                            </td>
                            <td class="table_right_border size1of2">
                                <label class="frm_label">ID number:</label>
                                <div class="frm_controls">
                                    <span class="frm_val">${(customerDto.personalDto.identity)!}</span>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class=" size1of2">
                                <label class="frm_label">Contact phone:</label>
                                <div class="frm_controls">
                                    <span class="frm_val">${(customerDto.personalDto.cellphone)!}</span>
                                </div>
                            </td>
                            <td class="size1of1" colspan="2">
                                <label class="frm_label">Email:</label>
                                <div class="frm_controls">
                                    <span class="frm_val">${(customerDto.personalDto.email)!}</span>
                                </div>
                            </td>
                        </tr>
                        <tr class="last">
                            <td class="table_right_border size1of2" colspan="2">
                                <label class="frm_label">Balance:</label>
                                <div class="frm_controls">
                                    <span class="frm_val">$ ${(balance)!}</span>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            <#--融资概况-->
                <div class="form_wrp tab-bd-con hide">
                    <p class="mtb-10 tr"><a href="javascript:;" onclick=otherView("${(customerDto.personalDto.customerName)!}") title="">more</a></p>
                    <div class="item">
                        <div class="item_hd"><strong>Repay total amount:<@h.numf value=customerDto.statisticalDto.payAmount/>dollar</strong></div>
                        <div class="item_bd">
                            <div class="form_wrp no_border">
                                <table class="table table_bgfff table-hover form_wrp_padding" cellpadding="0" cellspacing="0">
                                    <tbody class="tbody" id="">
                                    <tr class="">
                                        <td class="size1of2 table_cell">Repaid amount:<@h.numf value=customerDto.statisticalDto.hasPayAmount/> dollar</td>
                                        <td class="size1of2 table_cell">Repaying amount:<@h.numf value=customerDto.statisticalDto.waitPayAmount/> dollar<span class="mark_red"><#--（逾期待还:<@h.numf value=customerDto.statisticalDto.outTimeWaitPayAmount/> dollar）--></span></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="item_hd"><strong>Loan amount:<@h.numf value=customerDto.statisticalDto.loanAmount/>dollar</strong></div>
                        <div class="item_bd">
                            <div class="form_wrp no_border">
                                <table class="table table_bgfff table-hover form_wrp_padding" cellpadding="0" cellspacing="0">
                                    <tbody class="tbody" id="">
                                    <tr class="">
                                        <td class="size1of2 table_cell">Loan Record:${(customerDto.statisticalDto.loanCount)!} times</td>
                                        <#--<td class="size1of2 table_cell">逾期记录:累计<span class="mark_red">${(customerDto.statisticalDto.timeOutCount)!}</span>次</td>-->
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	//more 页面跳转
	function otherView(customerName){
		//设置编码
		customerName=encodeURI(customerName);
		window.location.href="${taglibs.allctx}/business/project/index.htm?customerName="+customerName;
	}
</script>
</@cl.html>