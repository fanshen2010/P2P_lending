<@cl.html title="后台管理_系统角色管理">
<div class="col_main">
    <div id="main_hd" class="main_hd">
    	<div class="clearfix">
		    <h2><a class="fr btn btn_primary fancybox" href="#menu_add_pop" title="add">add</a>Role Manage</h2>                            
        </div> 
     </div>
    <form method="post" id="searchForm" action="${taglibs.allctx}/system/role/index.htm"> 
    <div class="main_bd">
    	<div class="search_form">
            <div class="search_list clearfix">
                <form action="">
                    <div class="sl_li">
                        <label class="frm_label">Role:</label>
                        <div class="frm_controls">
                            <@ctl.text name="roleName" value="${criteria.roleName!}"/>
                        </div>
                    </div>
                    <div class="sl_li sl_btn">
                        <@ctl.submit class="btn btn_primary" title="search" text="search" />
                    </div>
                </form>
            </div>
        </div>
        <div class="main_list">
            <div class="form_wrp">
                <div class="nowrap_table">
                    <table class="table form_wrp_padding table-striped table-hover" id="tabPartner_info_view" cellpadding="0" cellspacing="0">
                        <thead class="thead">
                            <tr>
                                <th class="table_cell tl">Role</th>
                                <th class="table_cell tl">Status</th>
			                    <th class="table_cell tl">Set</th>
                                <th class="table_cell tr">Operate</th>
                            </tr>
                        </thead>
                        <tbody class="tbody" id="">
                        <#if lstRole?has_content>
                        <#list lstRole as role>
                            <tr>
                                <td class=" table_cell tl">${role.roleName}</td>
                                <td class=" table_cell tl"><@ctl.statusButton isView=true status="${role.roleState}"/></td>
                                <td class=" table_cell tl"><@ctl.linkButton class="operate_icon view" href="${taglibs.allctx}/system/role/permissionSet.htm?roleId=${role.roleId}" title="Set" text="Set" /></td>
                                <td class=" table_cell tr">		
                                	<input type="hidden" id="roleId" name="" value="${role.roleId}"/>											
                                    <@ctl.operateButton href="javascript:;" title="modify" class="operate_icon edit fancybox" text="modify"/>
                                	<@ctl.delButton href="${taglibs.allctx}/system/role/delete.htm?roleId=${role.roleId}" title="delete"  text="delete"/>
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
            <@ctl.page page=criteria.page>
        	</@ctl.page>
        </div>
    </div>
  </form>  
</div>
<!-- 新增角色 -->
<div class="popsize_l fancybox_pop" id="menu_add_pop">
<#include "${taglibs.ftlctx}/system/role/add.ftl" >
</div>
<!-- 修改角色 -->
<div class="popsize_l fancybox_pop" id="menu_edit_pop">

</div>

<script type="text/javascript">
	$("#tabPartner_info_view tbody tr a").click(function(){
	if($(this).text()=="modify"){
			$.ajaxLoadFtl({
	            type: "POST",
	            url: "edit.htm",
	            dataType: "json",
	            data: {roleId:$(this).siblings(":hidden").val()},
	            error: function (data, transport) {
	               alert("Error!!");
	            },
	            success: function (result) {
						$("#menu_edit_pop").html(result.html);
						$.fancybox.open($("#menu_edit_pop"),{"title":"modify"});
                        $("#cancel").click(function() {
                            $.fancybox.close();
                        });
	            }
	        });
			$.fancybox.close();
			}
	});
</script>
</@cl.html>