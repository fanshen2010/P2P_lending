<@cl.html title="后台管理_用户管理">
<div class="col_main">
    <div id="main_hd" class="main_hd">
    	<div class="clearfix">
            <h2><a id="" class="btn btn_primary" href="${taglibs.allctx}/user/index.htm">return</a>view</h2>
        </div> 
     </div>
    <div class="main_bd">
    	<div class="tab">
   			<ul class="tab_navs tab-hd  position_tab title_tab" id="originalTab">
                <li class="tab-hd-con tab_nav first active"><a href="javascript:;">Customer information</a></li>
                <li class="tab-hd-con tab_nav " ><a href="javascript:;">Invest List</a></li>
                <li class="tab-hd-con tab_nav " ><a href="javascript:;">Loan Info</a></li>
    		</ul>
            <div class="tab-bd">	
            	<div class="tab-bd-con active">
                	<div class="form_wrp">
                        <table class="table" cellpadding="0" cellspacing="0">
                            <tbody class="tbody" id="">
                                <tr>
                                    <td class="table_right_border size1of2">
                                        <label class="frm_label">User name:</label>
                                        <div class="frm_controls">
                                            <span class="frm_val">${(investdetail.basicMsg.login)!}</span>
                                        </div>
                                    </td>
                                    <td class=" size1of2">
                                        <label class="frm_label">Contact phone:</label>
                                        <div class="frm_controls">
                                            <span class="frm_val">${(investdetail.basicMsg.celphone)!}</span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="table_right_border size1of2">
                                        <label class="frm_label">Email:</label>
                                        <div class="frm_controls">
                                            <span class="frm_val">${(investdetail.basicMsg.email)!}</span>
                                        </div>
                                    </td>
                                    <td class=" size1of2">
                                        <label class="frm_label">Register time:</label>
                                        <div class="frm_controls">
                                            <span class="frm_val"><@h.datetimef value=investdetail.basicMsg.createTime /></span>
                                        </div>
                                    </td>
                                </tr>
								<tr>
                                    <td class="table_right_border size1of2">
                                        <label class="frm_label">Account Balance:</label>
                                        <div class="frm_controls">
                                            <span class="frm_val">${(investdetail.basicMsg.ciccAccount)!}</span>
                                        </div>
                                    </td>
                                    <td class=" size1of2">
                                        <label class="frm_label">Status:</label>
                                        <div class="frm_controls">
                                            <span class="frm_val">${(investdetail.basicMsg.active)!}</span>
                                        </div>
                                    </td>
                                </tr>
								<tr class="last">
                                    <td class="table_right_border size1of2" colspan="2">
                                        <label class="frm_label">Latest login time:</label>
                                        <div class="frm_controls">
                                            <span class="frm_val"><@h.datetimef value=investdetail.basicMsg.updateTime /></span>
                                        </div>
                                    </td>
                                </tr>
                           </tbody>
                        </table>
                    </div>
                </div>
                <div class="tab-bd-con hide">
				    <p class="mtb-10 tr"><a href="${taglibs.allctx}/business/investment/index.htm" title="">more</a></p>
                    <div class="item">
                        	<div class="item_hd"><strong>Invest total amount:<@h.numf value=investdetail.investDetailDto.investingAmount+investdetail.investDetailDto.toInvestAmount+investdetail.investDetailDto.investedAmount />dollar</strong></div>
                            <div class="item_bd">
                            	<div class="form_wrp no_border">
                                    <table class="table table_bgfff table-hover form_wrp_padding" cellpadding="0" cellspacing="0">
                                        <tbody class="tbody" id="">
                                            <tr class="">
                                                <td class=" table_cell">Repaying invest:<@h.numf value=investdetail.investDetailDto.investingAmount /> dollar</td>
                                                <td class=" table_cell">Auditting invest:<@h.numf value=investdetail.investDetailDto.toInvestAmount /> dollar</td>
                                                <td class=" table_cell">Repaid invest:<@h.numf value=investdetail.investDetailDto.investedAmount /> dollar</td>
                                            </tr>
                                        </tbody>
                                    </table>
                        		</div>
                            </div>
                        </div>
                    	<div class="item">
                        	<div class="item_hd"><strong>Earning total amount:<@h.numf value=investdetail.investDetailDto.collectEarnings+investdetail.investDetailDto.receivedEarnings />dollar</strong></div>
                            <div class="item_bd">
                            	<div class="form_wrp no_border">
                                    <table class="table table_bgfff table-hover form_wrp_padding" cellpadding="0" cellspacing="0">
                                        <tbody class="tbody" id="">
                                            <tr class="">
                                                <td class=" table_cell">Repaying earning:<@h.numf value=investdetail.investDetailDto.collectEarnings /> dollar</td>
                                                <td class=" table_cell">Repaid earning:<@h.numf value=investdetail.investDetailDto.receivedEarnings /> dollar</td>
                                            </tr>
                                        </tbody>
                                    </table>
                        		</div>
                            </div>
                     </div>            	
                </div>
				<div class="tab-bd-con hide">
				    <p class="mtb-10 tr"><a href="${taglibs.allctx}/business/project/index.htm" title="">more</a></p>
                	<div class="item">
                    	<div class="item_hd"><strong>Repay total amount:<@h.numf value=loandetail.payAmount/>dollar</strong></div>
                        <div class="item_bd">
                        	<div class="form_wrp no_border">
                                <table class="table table_bgfff table-hover form_wrp_padding" cellpadding="0" cellspacing="0">
                                    <tbody class="tbody" id="">
                                    <tr class="">
                                        <td class="size1of2 table_cell">Repaid amount:<@h.numf value=loandetail.hasPayAmount/> dollar</td>
                                        <td class="size1of2 table_cell">Repaying amount:<@h.numf value=loandetail.waitPayAmount/> dollar<span class="mark_red"><#--（逾期待还:<@h.numf value=loandetail.outTimeWaitPayAmount/> dollar）--></span></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                	<div class="item">
                    	<div class="item_hd"><strong>Loan amount:<@h.numf value=loandetail.loanAmount/>dollar</strong></div>
                        <div class="item_bd">
                        	<div class="form_wrp no_border">
                                <table class="table table_bgfff table-hover form_wrp_padding" cellpadding="0" cellspacing="0">
                                    <tbody class="tbody" id="">
                                        <tr class="">
                                            <td class=" table_cell">Loan Record:${(loandetail.loanCount)!}次</td>
                                            <#--<td class=" table_cell">逾期记录:累计<span class="mark_red">${(loandetail.timeOutCount)!}</span>次</td>-->
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
</@cl.html>