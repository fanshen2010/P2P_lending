<@cl.html title="后台管理_业务人员管理">
<div class="col_main">
            <div id="main_hd" class="main_hd">
            	<div class="clearfix">
                    <h2><@ctl.linkButton id="staffInfoAdd" href="javascript:;" title="add" text="add" isFancybox=true />Staff Manage</h2>
                </div> 
             </div>
            <div class="main_bd">
           	<form id="searchForm" action="${taglibs.ctx}/system/businessStaff/businessStaff.htm" method="POST">
            	<div class="search_form">
                    <div class="search_list clearfix">
                            <div class="sl_li">
                                <label class="frm_label">Department:</label>
                                <div class="frm_controls">
                                    <@ctl.dropdownlist listData=departMap name="departCd"  hasAll=true  value="${departCd}" />
                                </div>
                            </div>
                            <div class="sl_li">
                                <label class="frm_label">Position:</label>
                                <div class="frm_controls">
                                    <@ctl.dropdownlist listData=postMap name="postCd"  hasAll=true  value="${postCd}" />
                                </div>
                            </div>
                            <div class="sl_li">
                                <label class="frm_label">UserName:</label>
                                <div class="frm_controls">
                                    <@ctl.text name="userName" value="${userName}" />
                                </div>
                            </div>
                            <div class="sl_li">
                                <label class="frm_label">Name:</label>
                                <div class="frm_controls">
                                    <@ctl.text name="realName" value="${realName}" />
                                </div>
                            </div>
                            <div class="sl_li">
                                <label class="frm_label">Statue:</label>
                                <div class="frm_controls">
                                	<@ctl.dropdownlist name="active" hasAll=true groupCode="status" value="${active}" dataType="BizCode" />
                                </div>
                            </div>
                            <div class="sl_li sl_btn">
                            	<@ctl.submit text="search" />
                            </div>
                    </div>
                </div>
                <div class="main_list">
                    <div class="form_wrp">
                        <div class="nowrap_table">
                            <table id="tabPartner_info_view" class="table form_wrp_padding table-striped table-hover" cellpadding="0" cellspacing="0">
                                <thead class="thead">
                                    <tr>
                                        <th class="table_cell tl">User name</th>
                                        <th class="table_cell tl">Name</th>
										<th class="table_cell tl">Contact phone</th>
                                        <th class="table_cell tl">Department</th>
                                        <th class="table_cell tl">Position</th>
                                        <th class="table_cell tl">Status</th>
                                        <th class="table_cell tr">Operate</th>
                                    </tr>
                                </thead>
                                <tbody class="tbody" id="">
                                	<#if pfmUserDtoList?has_content>
                                	<#list pfmUserDtoList as item>
                                    <tr>
                                        <td class=" table_cell tl">${item.userName}</td>
                                        <td class=" table_cell tl">${item.realName}</td>
										<td class=" table_cell tl">${item.contactPhone}</td>
                                        <td class=" table_cell tl">${item.departName}</td>
                                        <td class=" table_cell tl">${item.postName}</td>
                                        <td class=" table_cell tl"><@ctl.statusButton status="${item.active}" isView=true /></td>
                                        <td class=" table_cell tr">
                                        	<input type="hidden" id="hidId" name="" value="${item.id}" />
                                        	<@ctl.operateButton title="set" text="set" isFancybox=true />
                                        	<@ctl.delButton href="okSubmitDelete.htm?curId=${item.id}" title="delete" text="delete" />
                                        </td>
                                    </tr>
                                    </#list>
                                    <#else>
									  <tr><td colspan="7">No data</td></tr>
									</#if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <@ctl.page page=criteria.page>
           			 </@ctl.page>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- 新增业务人员 -->
<div class="popsize_l fancybox_pop " id="partner_info_add">
    
</div>
<!-- 修改业务人员 -->
<div class="popsize_l fancybox_pop " id="partner_info">
	
</div>
<!--- operate_pop -->
<div id="operate_pop" class="oper_suces_pop">
	<div class="oper_suces_bd" id="divOperate"></div>
</div>
 <script type="text/javascript">
	$("#tabPartner_info_view tbody tr a").click(function(){
		if($(this).text()=="set"){
			$.ajaxLoadFtl({
	            url: "staffEdit.htm",
	            data: {curId:$(this).siblings(":hidden").val()},
	            error: function (data, transport) {
	               alert("Error!!");
	            },
	            success: function (result) {
						$("#partner_info").html(result.html);
						$(".selectpicker").selectpicker();
						$.fancybox.open($("#partner_info"),{"title":"set"});
	            }
	        });
		}
		$.fancybox.close();
	});
	$("#staffInfoAdd").click(function(){
		$.ajaxLoadFtl({
	            url: "staffAdd.htm",
	            data: {},
	            error: function (data, transport) {
	               alert("Error!!");
	            },
	            success: function (result) {
						$("#partner_info_add").html(result.html);
						$(".selectpicker").selectpicker();
						$.fancybox.open($("#partner_info_add"),{"title":"New Staff"});
	            }
	        });
	});
</script>
</@cl.html>