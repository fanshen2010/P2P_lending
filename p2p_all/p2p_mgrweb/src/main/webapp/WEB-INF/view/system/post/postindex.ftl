<@cl.html title="后台管理_职务管理">
                <div class="col_main">
                    <div id="main_hd" class="main_hd">
                        <div class="clearfix">
                            <h2><@ctl.linkButton href="add.htm" title="add"text="add"/>Staff Role Manage</h2>
                        </div> 
                     </div>
                    <div class="main_bd">
  <#-- 查询表单开始 --><form action="index.htm" id="searchForm" method="POST">
                        <div class="search_form">
                            <div class="search_list clearfix">
                                    <div class="sl_li">
                                        <label class="frm_label">Position:</label>
                                        <div class="frm_controls">
                                           <@ctl.text name="postDto.post.postName" value="${(postDto.post.postName)!}"/>
                                        </div>
                                    </div>
                                    <div class="sl_li sl_btn">
                                        <@ctl.submit title="search" text="search"/>
                                    </div>
                            </div>
                        </div>
                        <div class="main_list">
                            <div class="form_wrp">
                                <div class="nowrap_table">
                                    <table class="table form_wrp_padding table-striped table-hover" cellpadding="0" cellspacing="0">
                                        <thead class="thead">
                                            <tr>
                                                <th class="table_cell tl">Position</th>
                                                <th class="table_cell tl">Role</th>
                                                <th class="table_cell tl">Status</th>
                                                <th class="table_cell tl">Staff Set</th>
                                                <th class="table_cell tr">Operate</th>
                                            </tr>
                                        </thead>
                                        <tbody class="tbody" id="">
                                        <#if postDto.posts?has_content>
                                        <#list postDto.posts as post>
                                            <tr>
                                                <td class=" table_cell tl">${(post.postName)}</td>
                                                <td class=" table_cell tl business-role-td">
                                                    <#list postDto.roleList[post_index] as role>
                                                       <span>${(role.roleName)}</span>
                                                    </#list>                                                                                                     
                                                </td>
                                                <td class=" table_cell tl">
                                                    <@ctl.statusButton status="${(post.validFlag)}" isView="true" href="javascript:;"/>
                                                </td>
                                                <td class=" table_cell tl">
                                                    <@ctl.operateButton  href="member.htm?postDto.post.postCd=${(post.postCd)}" title="Staff Set" linkType="edit" text="Staff Set" isFancybox="false" />
                                                </td>
                                                <td class=" table_cell tr">
                                                    <@ctl.operateButton  href="edit.htm?postDto.post.postCd=${(post.postCd)}" title="set" linkType="edit" text="set" isFancybox="false" />
                                                    <@ctl.delButton href="delete.htm?postDto.post.postCd=${(post.postCd)}&postDto.post.tenantCd=${(post.tenantCd)}" title="delete" text="delete"/>
                                                </td>
                                            </tr>
                                            </#list>
                                            <#else>
											  <tr><td colspan="5">No data</td></tr>
											</#if>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <@ctl.page page=postDto.criteria.page/>
                        </div>
                    </div>
                    </form><#-- 查询表单结束 -->
                </div>
 </@cl.html>