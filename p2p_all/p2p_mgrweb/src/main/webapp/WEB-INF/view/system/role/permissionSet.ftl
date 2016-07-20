<@cl.html title="后台管理_系统角色权限设置">
<div class="col_main">
  <div id="main_hd" class="main_hd">
      <div class="clearfix">
          <h2><@ctl.linkButton href="index.htm" title="return" text="return"/>Role Set</h2>
      </div> 
  </div>
  <form id="pageFormSubmit" action="${taglibs.ctx}/system/role/dataPermissionSet.htm" method="POST">
  		<input type="hidden" name="roleId" value="${roleId}" /> 
    	<div class="main_bd">
     		<div class="system_role_manage">
     			<@ctl.checkboxlist listData=menuMap  name="menuId" />
     		</div>
     		<div class="form_btn">
	            <div class="fr">
	            	 <@ctl.submit class="btn btn_primary" text="save"/>
	            </div>
	        </div>
   		</div>
 </form>
</div>
</@cl.html>