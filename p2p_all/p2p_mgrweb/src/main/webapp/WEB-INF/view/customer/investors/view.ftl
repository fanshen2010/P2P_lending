<@cl.html title="后台管理_投资客户管理">
	<div class="col_main">
        <div id="main_hd" class="main_hd">
        	<div class="clearfix">
                <h2><a id="" class="btn btn_primary" href="${taglibs.allctx}/customer/investors/index.htm">return</a>view</h2>
            </div> 
         </div>
        <div class="main_bd">
        	<div class="tab">
       			<ul class="tab_navs tab-hd  position_tab title_tab" id="originalTab">
                    <li class="tab-hd-con tab_nav first active"><a href="javascript:;">Basic Info</a></li>
                    <li class="tab-hd-con tab_nav " ><a href="javascript:;">Invest List</a></li>
                    <li class="tab-hd-con tab_nav " ><a href="javascript:;">Repay List</a></li>
        		</ul>
                <div class="tab-bd">
                	<div class="tab-bd-con active">
                    	<h3 class="h3">User Info</h3>
                    	<div class="form_wrp">
                            <table class="table" cellpadding="0" cellspacing="0">
                                <tbody class="tbody" id="">
                                    <tr>
                                        <td class="table_right_border size1of2">
                                            <label class="frm_label">User name:</label>
                                            <div class="frm_controls">
                                                <span class="frm_val">${(detail.basicMsg.login)!}</span>
                                            </div>
                                        </td>
                                        <td class=" size1of2">
                                            <label class="frm_label">Contact phone:</label>
                                            <div class="frm_controls">
                                                <span class="frm_val">${(detail.basicMsg.celphone)!}</span>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr class="last">
                                        <td class="table_right_border size1of2">
                                            <label class="frm_label">Email:</label>
                                            <div class="frm_controls">
                                                <span class="frm_val">${(detail.basicMsg.email)!}</span>
                                            </div>
                                        </td>
                                        <td class=" size1of2">
                                            <label class="frm_label">Status:</label>
                                            <div class="frm_controls">
                                                <span class="frm_val">${getCodeName("investorsStatus",(detail.basicMsg.active)!)}</span>
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
                    </div>
                    <div class="tab-bd-con hide">
                    	<div class="item">
                        	<div class="item_hd"><strong>Invest total amount:<@h.numf value=detail.investDetailDto.investingAmount+detail.investDetailDto.toInvestAmount+detail.investDetailDto.investedAmount />dollar</strong></div>
                            <div class="item_bd">
                            	<div class="form_wrp no_border">
                                    <table class="table table_bgfff table-hover form_wrp_padding" cellpadding="0" cellspacing="0">
                                        <tbody class="tbody" id="">
                                            <tr class="">
                                                <td class=" table_cell">Repaying invest:<@h.numf value=detail.investDetailDto.investingAmount /> dollar</td>
                                                <td class=" table_cell">Auditting invest:<@h.numf value=detail.investDetailDto.toInvestAmount /> dollar</td>
                                                <td class=" table_cell">Repaid invest:<@h.numf value=detail.investDetailDto.investedAmount /> dollar</td>
                                            </tr>
                                        </tbody>
                                    </table>
                        		</div>
                            </div>
                        </div>
                    	<div class="item">
                        	<div class="item_hd"><strong>Earning total amount:<@h.numf value=detail.investDetailDto.collectEarnings+detail.investDetailDto.receivedEarnings />dollar</strong></div>
                            <div class="item_bd">
                            	<div class="form_wrp no_border">
                                    <table class="table table_bgfff table-hover form_wrp_padding" cellpadding="0" cellspacing="0">
                                        <tbody class="tbody" id="">
                                            <tr class="">
                                                <td class=" table_cell">Repaying earning:<@h.numf value=detail.investDetailDto.collectEarnings /> dollar</td>
                                                <td class=" table_cell">Repaid earning:<@h.numf value=detail.investDetailDto.receivedEarnings /> dollar</td>
                                            </tr>
                                        </tbody>
                                    </table>
                        		</div>
                            </div>
                        </div>
                        <p class="pt20 tr"><a href="${taglibs.allctx}/business/investment/index.htm?loginName=${(detail.basicMsg.login)!}" title="">more</a></p>
                    </div>
                    <div class="tab-bd-con hide">
                    	<div class="search_form">
                            <div class="search_list clearfix">
                                <form action="">
                                	<div class="sl_li">
                                        <label class="frm_label">Repay date:</label>
                                        <div class="frm_controls group">
                                        	<@ctl.dropdownlist  id="selectyear" name="year"  hasAll=true  listData= detail.years class="selectpicker" spanClass="frm_select_picker" value="${(year)!}" />
                                        </div>
                                    </div>
                                    <div class="sl_li sl_btn">
                                        <button type="button" class="btn btn_primary">search</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <#include "${taglibs.ftlctx}/customer/investors/repaymentPlan.ftl"/>
                    </div>
                </div>
        	</div>
    	</div>
    </div>
    <!-- 投资人还款详细 -->
    <div class="popsize_l" id="repayment_pop">
    </div>
    <script type="text/javascript">
    	$(function(){
    		$("body").on("click","a.audit",function(){
    			var yearMonth=$(this).data("yearmonth");
    			var userId=$(this).data("userid");
    			
    			var params={"yearMonth":yearMonth,"id":userId}
    			$.ajaxLoadFtl({
    				type:"POST",
    				url:"${taglibs.allctx}/customer/investors/detail.htm",
    				data:params,
    				datatype:"json",
    				 error: function (data, transport) {
					               $("#repayment_pop").html("系统错误，请联系管理员");
					               $.fancybox.open($("#repayment_pop"),{"title":"Repay Detail"});
					            },
    				success:function(result){
    					$("#repayment_pop").html(result.html);
    					$.fancybox.open($("#repayment_pop"),{"title":"Repay Detail"});
    				}
    			});
    		});
    		$("body").on("click","button.btn_primary",function(){
    			var yearMonth=$("#selectyear").val();
    			var params={"year":yearMonth,"id":"${id}"}
    			$.ajaxLoadFtl({
    				type:"POST",
    				url:"${taglibs.allctx}/customer/investors/repaymentPlan.htm",
    				data:params,
    				datatype:"json",
    				 error: function (data, transport) {
					               $("#repaymentPlan").html("系统错误，请联系管理员");
					            },
    				success:function(result){
    					$("#repaymentPlan").replaceWith(result.html);
    				}
    			});
    		});
    	});
    </script>
</@cl.html>