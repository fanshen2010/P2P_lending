<@cl.html title="后台管理_投资客户管理">
	 <div class="col_main">
	    <div class="main_hd">
	    	<div class="clearfix">
	            <h2>Invest Customer Manage</h2>
	        </div> 
	     </div>
	    <div class="main_bd">
	    	<div class="search_form">
	            <div class="search_list clearfix">
	            	<form id="searchForm" action="${taglibs.allctx}/customer/investors/index.htm" method="post">
	                    <div class="sl_li">
	                        <label class="frm_label">UserName:</label>
	                        <div class="frm_controls">
	                                <@ctl.text  id="" name="criteria.userName"    value="${criteria.userName!}"  />
	                        </div>
	                    </div>
	                    <div class="sl_li">
	                        <label class="frm_label">Phone:</label>
	                        <div class="frm_controls">
	                                <@ctl.text  id="" name="criteria.celphone"    value="${criteria.celphone!}"  />
	                        </div>
	                    </div>
	                    <div class="sl_li">
	                        <label class="frm_label">Status:</label>
	                        <div class="frm_controls">
	                        	<@ctl.dropdownlist name="criteria.status"    hasAll=true hasChoice=false   groupCode="investorsStatus"  dataType="BizCode"  class="selectpicker" spanClass="frm_select_picker" value="${(criteria.status)!}" />
	                        </div>
	                    </div>
	                    <div class="sl_li sl_btn">
	                        <button type="submit" class="btn btn_primary">search</button>
	                    </div>
	                </form>
	            </div>
	        </div>
	        <div class="main_list">
				<div class="from_frm">
					<div class="nowrap_table">
	                <table class="table table-striped table-hover" cellpadding="0" cellspacing="0">
	                    <thead class="thead">
	                        <tr>
	                            <th class="table_cell tl">User name</th>
	                            <th class="td_phone tl">Contact phone</th>
	                            <th class="table_cell tl">Total invest（dollar）</th>
	                            <th class="table_cell tl">Repaying invest（dollar）</th>
	                            <th class="table_cell tl">Auditting invest（dollar）</th>
	                            <th class="table_cell tl">Repaying earning（dollar）</th>
	                            <th class="table_cell tl">Status</th>
	                            <th class="table_cell tr">Operate</th>
	                        </tr>
	                    </thead>
	                    <tbody class="tbody" id="">
	                    	<#if investorsList?has_content>
	                    	<#list investorsList as investors>
		                        <tr>
		                            <td class=" table_cell">${investors.userName}</td>
		                            <td class=" table_cell">${investors.celphone}</td>
		                            <td class=" table_cell "><@h.numf value=investors.totalAmount /></td>
		                            <td class=" table_cell "><@h.numf value=investors.payingAmount /></td>
		                            <td class=" table_cell "><@h.numf value=investors.effectiveAmount /></td>
		                            <td class=" table_cell "><@h.numf value=investors.interest /></td>
		                            <td class=" table_cell "><span class="<#if investors.status=="1">mark_green"<#else>mark_red</#if>" >${getCodeName("investorsStatus",investors.status)}</span></td>
		                            <td class=" table_cell tr">
		                                <@ctl.operateButton href="${taglibs.allctx}/customer/investors/view.htm?id=${investors.userId}" title="view" class="operate_icon view" text="view"/>
	                                </td>
		                        </tr>
	                        </#list>
	                        <#else>
							  <tr><td colspan="8">No data</td></tr>
							</#if>
	                    </tbody>
	                </table>
	                <div class="main_list_opera">
	                    <@ctl.page page=criteria.page />
	                </div>
	            </div>
	            </div>
	        </div>
	    </div>
	</div>
</@cl.html>