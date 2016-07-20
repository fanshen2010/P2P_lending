<div id="main_hd" class="main_hd">
	<div class="clearfix">
		<h2>Staff Message Manage</h2>
	</div> 
</div>
<div class="main_bd">
	<div class="tab">
		<ul class="tab_navs tab-hd  position_tab title_tab" id="originalTab">
			<li class="tab-hd-con tab_nav first active"><a href="javascript:;" id="user_tab">Front User Message</a></li>
			<li class="tab-hd-con tab_nav "><a href="javascript:;" id="business_tab">Back User Message</a></li>
		</ul>
		<div class="tab-bd">
			<div class="tab-bd-con">
				<form method="post" id="searchForm" action="index.htm">
					<div class="search_form">
						<div class="search_list clearfix">
							<div class="sl_li">
								<label class="frm_label">Title:</label>
								<div class="frm_controls">
									<@ctl.text name="userCriteria.msgTitle" id="msgTitle" value="${(userCriteria.msgTitle)!}"/>
								</div>
							</div>
							<div class="sl_li">
								<label class="frm_label">Status:</label>
								<div class="frm_controls">
									<@ctl.dropdownlist name="userCriteria.validFlag" id="validFlag" hasAll=true dataType="BizCode" groupCode="status" value="${(userCriteria.validFlag)!}" class="selectpicker" spanClass="frm_select_picker"/>
								</div>
							</div>
							<div class="sl_li sl_btn">
								<a class="btn btn_primary" href="javascript:;" title="search" id="user_search">search</a>
							</div>
						</div>
					</div>
					<div class="main_list">
						<div class="form_wrp">
							<div class="nowrap_table">
								<table class="table form_wrp_padding table-stripd table-hover" cellpadding="0" cellspacing="0">
									<thead class="thead">
										<tr>
											<th class="table_cell tl">Title</th>
											<th class="table_cell tl">Content</th>
											<th class="table_cell tl">Status</th>
											<th class="table_cell tr">Operate</th>
										</tr>
									</thead>
									<tbody class="tbody" id="">
										<#if lstUserSms?has_content>
										<#list lstUserSms as userSms>
											<tr>
												<td class=" table_cell tl">${userSms.msgTitle!}</td>
												<td class=" table_cell tl">${userSms.msgTeml!}</td>
												<td class=" table_cell tl"><@ctl.statusButton status="${(userSms.validFlag)}" isView="true" href="javascript:;"/></td>
												<td class=" table_cell tr">
													<@ctl.statusButton status="${(userSms.validFlag)}" id="user_status" href="updateStatus.htm?receiverId=${userSms.id!}"/>
												</td>
											</tr>
										</#list>
										<#else>
										  <tr><td colspan="4">No data</td></tr>
										</#if>
									</tbody>
								</table>
							</div>
						</div>
						<@ctl.pageAjax page=userCriteria.page url="${taglibs.ctx}/system/messageManagement/userPage.htm"  elemId="tab_show"/>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
