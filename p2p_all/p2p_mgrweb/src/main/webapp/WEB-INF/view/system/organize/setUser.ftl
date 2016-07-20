<@cl.html title="">
<div class="col_main">
	<div id="main_hd" class="main_hd"> 
		<div class="clearfix">
	        <h2><@ctl.linkButton class="fr btn btn_primary" href="${taglibs.allctx}/system/organize/index.htm" title="return" text="return"/>Staff Set</h2>
	    </div>
	</div>
<div class="main_bd">
	<div class="tab">
        <ul class="tab_navs tab-hd  position_tab title_tab" id="originalTab">
            <li id="next_show_tab" class="tab-hd-con tab_nav hide" ><a href="javascript:;">Staff</a></li>
        </ul>             
        <form action="${taglibs.allctx}/system/organize/setUser.htm" method="post" id="search_form">     
        	<input type="hidden" id="departmentCd" name="departCd" value="${departCd}" />                                         
            	<div class="lr_area role_area clearfix">
                	<div class="left item">
                        <div class="item_hd">
                        	<strong class="item_hd_title">Staff List</strong>
                        </div>
                        <div class="item_bd">
                            <div id="display_list" class="form_wrp">
                                <div class="">
                                    <table class="table form_wrp_padding table-striped table-hover" cellpadding="0" cellspacing="0">
                                        <thead class="thead">
                                            <tr>
                                                <th class="table_cell tl">User name</th>
                                                <th class="table_cell tl">Name</th>
                                                <th class="table_cell tl">Position</th>
                                                <th class="table_cell tr">Operate</th>
                                            </tr>
                                        </thead>
                                        <tbody class="tbody" id="">
                                         <#list lstPfmUser as user>
                                            <tr>
                                                <td class=" table_cell tl">${user.userName}</td>
                                                <td class=" table_cell tl">${user.realName}</td>
                                                <td class=" table_cell tl">${user.postName}</td>
                                                <td class=" table_cell tr">
                                                    <@ctl.operateButton href="${taglibs.allctx}/system/organize/remove.htm?userId=${user.id}&departmentCd=${departCd}" title="移除" class="operate_icon" text="移除"/>
                                                </td>
                                            </tr>
                                         </#list>   
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="right item">
                        <div class="item_hd">
                        	<strong class="item_hd_title">add staff</strong>
                        </div>
                        <div class="item_bd">
                        	<p>
                            	<span class="frm_input_box search with_del append role_area_search">
                                     <a id="search_btn" href="javascript:" class="frm_input_append"><i class="icon16_common search_gray">search</i>&nbsp;</a>
                                    <input id="keyInput" type="text" placeholder="业务人员姓名" name="realName" value="${realName}" class="frm_input">
                                </span>
                            </p>
                            <div class="role_name_area" id="user">
                            <#if lstName?? && lstName?size gt 0>
                                <ul class="role_name_list">
                                <#list lstName as users>
                                    <li>
                                        <span class="uniformjs" >
                                           <span class=" check_content uniformjs">
                                                <label class="frm_checkbox_label selected">
                                                    <input type="checkbox" id="checkbox1" class="frm_checkbox" name="ck" value="${users.id}" />
                                                </label>
                                            </span>
                                            <span class="name">${users.realName}</span>
                                        </span>
                                    </li>
                                </#list>
                                </ul>
                                 <@ctl.submit id="add_role" class="btn btn_primary" text="add"/>
                             <#else>
                             	<ul class="role_name_list">
                             	<li><span class="name">No data</span></li>
                             	</ul>
                             </#if>
                            </div>
                        </div>
                    </div>
                </div>
               </form>                                 
        </div>
    </div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
	
  	$("#search_btn").click(function(){
  	   $("#search_form").submit();	
  	  // $("#user").show();
  	});
  
  	$("#add_role").click(function(){
  	   $("#search_form").attr("action","${taglibs.allctx}/system/organize/set_add.htm")
  	   $("#search_form").submit();	
  	});
  	
});
</script>
</@cl.html>