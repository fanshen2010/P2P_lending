<@cl.html title="" js="bussiness/menuManagement.js">
<div class="col_main">
	<div class="main_hd" id="main_hd">
		<div class="clearfix">
			<h2><@ctl.linkButton class="fr btn btn_primary fancybox" id = "menu_add" href="#menu_add_pop" title="add" text="add" />Menu Manage</h2>
		</div> 
 	</div>
	<div class="main_bd">
		<div class="main_list">
		<form action="updateOrder.htm" method="POST">
			<div class="nowrap_table">
				<table class="table">
					<thead class="thead">
						<tr>
							<th class="table_cell tl">Order</th>
							<th class="table_cell tl">Menu name</th>
							<th class="table_cell tl">Menu url</th>
							<th class="table_cell tl">Menu level</th>
							<th class="table_cell tl">Status</th>
							<th class="table_cell tr">Operate</th>
						</tr>
					</thead>
					<tbody class="tbody" id="">
					<#assign leve1=001>
					<#assign i=0>
					<#list menuLists as menu1>
						<tr class="level_first" id="level${leve1}" data-status="open">
							<td class="table_cell">
								<div class="orderby_input">
									<input type="hidden" name="orderNums[${i}].menuId" value="${(menu1.menuId)!}">
                                    <@ctl.text class="frm_input dev_onlynum" name="orderNums[${i}].orderNum" value="${(menu1.orderNum)!}"/>
								</div>
							</td>
							<td class=" table_cell">
								<div class="menu_tit">
									<span class="menu_toggle open"></span>
									<span class="menu_txt">${menu1.menuName}</span>
									<span class="menu_add" data-id="${menu1.menuId}"></span>
								</div>
							</td>
							<td class=" table_cell">${menu1.menuUrl}</td>
							<td class=" table_cell"><@bizTag.level value=menu1.menuLevel/></td>
							<td class=" table_cell ">
								<@ctl.statusButton status="${(menu1.status)}" isView="true" href="javascript:;"/>
							</td>
							<td class=" table_cell tr">
								<@ctl.linkButton class="operate_icon edit" href="#menu_edit_pop" data={"id":"${menu1.menuId}"} title="modify" text="modify" />
								<@ctl.linkButton class="operate_icon del fancybox" href="#delete_btn" data={"id":"${menu1.menuId}"} title="delete" text="delete" />
							</td>
						</tr>
						<#assign leve2=01>
						<#list menu1.menuList as menu2>
							<#assign i=i+1>
							<tr class="level_second" id="level${leve1}_level${leve2}" data-status="open">
								<td class="table_cell">
									<div class="orderby_input">
										<input type="hidden" name="orderNums[${i}].menuId" value="${(menu2.menuId)!}">
                                    	<@ctl.text class="frm_input dev_onlynum" name="orderNums[${i}].orderNum" value="${(menu2.orderNum)!}"/>
									</div>
								</td>
								<td class=" table_cell">
									<div class="menu_tit">
										<span class="menu_toggle open"></span>
										<span class="menu_txt">${menu2.menuName}</span>
										<span class="menu_add" data-id="${menu2.menuId}"></span>
									</div>
								</td>
								<td class=" table_cell">${menu2.menuUrl}</td>
								<td class=" table_cell"><@bizTag.level value=menu2.menuLevel/></td>
								<td class=" table_cell ">
									<@ctl.statusButton status="${(menu2.status)}" isView="true" href="javascript:;"/>
								</td>
								<td class=" table_cell tr">
									<@ctl.linkButton class="operate_icon edit" href="#menu_edit_pop" data={"id":"${menu2.menuId}"} title="modify" text="modify" />
									<@ctl.linkButton class="operate_icon del fancybox" href="#delete_btn" data={"id":"${menu2.menuId}"} title="delete" text="delete" />
								</td>
							</tr>
							<#assign leve3=01>
							<#list menu2.menuList as menu3>
								<tr class="level_three" id="level${leve1}_level${leve2}_level${leve3}">
									<td class="table_cell">&nbsp;</td>
									<td class=" table_cell"><div class="menu_tit"><span class="menu_txt">${menu3.menuName}</span></div></td>
									<td class=" table_cell">${menu3.menuUrl}</td>
									<td class=" table_cell"><@bizTag.level value=menu3.menuLevel/></td>
									<td class=" table_cell ">
										<@ctl.statusButton status="${(menu3.status)}" isView="true" href="javascript:;"/>
									</td>
									<td class=" table_cell tr">
										<@ctl.linkButton class="operate_icon edit" href="#menu_edit_pop" data={"id":"${menu3.menuId}"} title="modify" text="modify" />
										<@ctl.linkButton class="operate_icon del" href="#delete_btn" data={"id":"${menu3.menuId}"} title="delete" text="delete" />
									</td>
								</tr>
								<#assign leve3=leve3+1>
							</#list>
							<#assign leve2=leve2+1>
						</#list>
						<#assign leve1=leve1+1>
						<#assign i=i+1>
					</#list>
					<input type="hidden" name="menuCount" value="${(i)!}" />
					</tbody>
				</table>
			</div>
			<div class="main_list_opera">
				<div class="form_btn">
					<@ctl.submit title="save order" class="btn" text="save order"/>
				</div>
			</div>
		</form>
		</div>
	</div>
</div>

<!--系统菜单新增弹出层-->
<div class="confirm_pop_s fancybox_pop" id="delete_btn">
	<input type="hidden" name="id" id="delMenuId" >
	<p class="tc ptb20">Comfirm to delete？</p>
	<p class="form_btn">
		<@ctl.linkButton id="delMenu" class="btn btn_primary" href="javascript:;" title="confirm" text="confirm" />
		<@ctl.linkButton class="btn btn_cancel" href="javascript:;" title="cancel" text="cancel" />
	</p>
</div>
<!--系统菜单添加弹出层--> 
<div class="fancybox_pop menu_add_pop" id="menu_add_pop">
</div>
<!--系统菜单修改弹出层-->
<div class="fancybox_pop menu_edit_pop" id="menu_edit_pop">
</div>
<script type="text/javascript">
	$(".dev_onlynum").onlynum();
</script>
</@cl.html>