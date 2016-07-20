<div id="main_hd" class="main_hd">
	<div class="clearfix">
		<h2>Message search</h2>
	</div> 
</div>
<div class="main_bd">
	<div class="tab">
		<ul class="tab_navs tab-hd  position_tab title_tab" id="originalTab">
			<li class="tab-hd-con tab_nav first "><a href="javascript:;" id="user_tab">Front User Message</a></li>
			<li class="tab-hd-con tab_nav active "><a href="javascript:;" id="business_tab">Back User Message</a></li>
		</ul>
		<div class="tab-bd" id="tab_show">
			<div class="tab-bd-con" id="businessPage">
				<form method="post" id="searchForm" action="index.htm">
					<div class="search_form">
						<div class="search_list clearfix">
							<div class="sl_li">
								<label class="frm_label">Title:</label>
								<div class="frm_controls">
									<@ctl.text name="businessCriteria.subject" value="${(businessCriteria.subject)!}"/>
								</div>
							</div>
							<div class="sl_li sl_btn">
								<@ctl.linkButton id="business_search" title="search" text="search"/>
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
                                            <th class="table_cell tl">Send time</th>
                                        </tr>
									</thead>
									<tbody class="tbody" id="">
									<#if businessLogs?has_content>
									<#list businessLogs as messageLog>
										<tr>
                                            <td class=" table_cell tl">${messageLog.subject}</td>
                                            <td class=" table_cell tl">${messageLog.content}</td>
                                            <td class=" table_cell tl"><@h.datef value=messageLog.sendTime/></td>
                                        </tr>
									</#list>
									<#else>
									  <tr><td colspan="3">No data</td></tr>
									</#if>	
									</tbody>
								</table>
							</div>
						</div>
						<@ctl.pageAjax page=businessCriteria.page url="businessPage.htm"  elemId="tab_show"/>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
