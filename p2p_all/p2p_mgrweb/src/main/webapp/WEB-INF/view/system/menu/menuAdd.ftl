<!-- 菜单添加 -->
<form id="menu_add" action="${taglibs.allctx}/system/menu/save.htm" method="post" >
	<div class="form_wrp">
        <table class="table">
            <tbody>
            	<tr id="second_menu_show">
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">Parent menu:</label>
                        <div class="frm_controls">
                            <span class="frm_val">
                                <span>${(menuParentName)!}</span>
                                <input type="hidden" name="pfmMenu.menuParentId" value="${(menuParentId)!}" />
                            </span>
                        </div>
                    </td>
                </tr>
                <tr id="second_menu_level">
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">Menu level:</label>
                        <div class="frm_controls">
                            <span class="frm_val">
                                <span>${(menuLevel)!}</span>
                                <input type="hidden" name="pfmMenu.menuLevel" value="${(menuLevel)!}" />
                            </span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">Menu name:</label>
                        <div class="frm_controls">
                        	<@ctl.text  name="pfmMenu.menuName" required=true validate={"maxlength":"20"} fieldName="Menu name"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">Order:</label>
                        <div class="frm_controls">
                        	<@ctl.text  name="pfmMenu.orderNum" required=true validate={"maxlength":"3","number":"true"} fieldName="Order"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">Menu url:</label>
                        <div class="frm_controls">
                        	<@ctl.text  name="pfmMenu.menuUrl" required=true validate={"maxlength":"60"} fieldName="Menu url"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">Menu Pic:</label>
                        <div class="frm_controls">
                        	<@ctl.text  name="pfmMenu.menuIcon" required=true validate={"maxlength":"20"} fieldName="Menu Pic"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">Menu Status:</label>
                        <div class="frm_controls uniformjs">
                        	<@ctl.radiobuttonlist id="active" dataType="BizCode" groupCode="status" name="pfmMenu.status" value="1" />
                            <span class="required-star">*</span>
                        </div>
                        
                    </td>
                </tr>
                <tr>
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">Open way:</label>
                        <div class="frm_controls">
                            <@ctl.dropdownlist name="pfmMenu.menuTarget" hasChoice="select" listData={"0":"new window","1":"self window"}  class="selectpicker" spanClass="frm_select_picker" required=true fieldName="Open way"/>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="form_btn">
        	<@ctl.button type="submit" title="save" text="save"/>
        </div>
    </div>
</form>
