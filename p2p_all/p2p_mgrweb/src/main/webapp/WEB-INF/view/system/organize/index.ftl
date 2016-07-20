<@cl.html title="">
<div class="col_main">
    <div id="main_hd" class="main_hd">
    	<div class="clearfix">
            <h2><@ctl.linkButton class="fr btn btn_primary" href="${taglibs.allctx}/system/organize/new_index.htm" title="add" text="add"/>Staff Organization Manage</h2>
        </div> 
     </div>
<form action="${taglibs.allctx}/system/organize/index.htm" method="POST" id="searchForm">     
    <div class="main_bd">
    	<div class="search_form">
            <div class="search_list clearfix">
                <form action="">
                    <div class="sl_li">
                        <label class="frm_label">Department:</label>
                        <div class="frm_controls">
                            <@ctl.text name="departmentName" value="${departmentName}" />
                        </div>
                    </div>
                    <div class="sl_li">
                        <label class="frm_label">Type:</label>
                        <div class="frm_controls">
                            <@ctl.dropdownlist name="departmentType" hasAll=true  groupCode="DepartmentTypeEnum"  dataType="Enum" value="${departmentType}" />
                        </div>
                    </div>
                    <div class="sl_li sl_btn">
                        <@ctl.submit class="btn btn_primary" text="search"/>
                    </div>
                </form>
            </div>
        </div>
        <div class="main_list">
            <div class="form_wrp">
                <div class="nowrap_table">
                    <table class="table form_wrp_padding table-striped table-hover" cellpadding="0" cellspacing="0">
                        <thead class="thead">
                            <tr>
                                <th class="table_cell tl">Department</th>
                                <th class="table_cell tl">Type</th>												
                                <th class="table_cell tl">Status</th>
			                    <th class="table_cell tl">Staff Set</th>
                                <th class="table_cell tr">Operate</th>
                            </tr>
                        </thead>
                        <tbody class="tbody" id="">
                        <#if lstResult?has_content>
                        <#list lstResult as department >
                            <tr>
                                <td class=" table_cell tl">${department.departmentName}</td>
                                <td class=" table_cell tl">${getCodeName("departStatus",department.departmentType)}</td>
			                    <td class=" table_cell tl"><@ctl.statusButton isView=true status="${department.vilidFlag}"/></td>
			                    <td class=" table_cell tl">
				                    <@ctl.operateButton href="${taglibs.allctx}/system/organize/setUser.htm?departCd=${department.departmentCd}" title="Staff Set" class="operate_icon edit" text="Staff Set"/>
			                    </td>                                                
                                <td class=" table_cell tr">												
                                    <@ctl.operateButton href="${taglibs.allctx}/system/organize/edit.htm?departCd=${department.departmentCd}&departmentType=${department.departmentType}" title="set" class="operate_icon edit" text="set"/>
                                    <@ctl.delButton href="${taglibs.allctx}/system/organize/delete.htm?departCd=${department.departmentCd}&tenantCd=${department.tenantCd}&departmentType=${department.departmentType}" title="delete"  text="delete"/>
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
            <@ctl.page page=criteria.page>
            </@ctl.page>
        </div>
    </div>
</form>  
</div>
</@cl.html>