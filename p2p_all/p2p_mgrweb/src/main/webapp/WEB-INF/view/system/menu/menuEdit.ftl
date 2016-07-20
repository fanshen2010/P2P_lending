<!-- 菜单修改 -->
<form id="menu_update" action="${taglibs.allctx}/system/menu/update.htm" method="post" >
<div class="form_wrp">
    <table class="table">
        <tbody>
        	<input type="hidden" name="pfmMenu.menuId" value="${(pfmMenu.menuId)!}" />
        	<tr id="second_menu_show">
                <td class="size1of1" colspan="2">
                    <label class="frm_label">Parent menu:</label>
                    <div class="frm_controls">
                        <span class="frm_val">
                            <span>${(parentPfmMenu.menuName)!}</span>
                        </span>
                    </div>
                </td>
            </tr>
            <tr id="second_menu_level">
                <td class="size1of1" colspan="2">
                    <label class="frm_label">Menu level:</label>
                    <div class="frm_controls">
                        <span class="frm_val">
                            <span>${(pfmMenu.menuLevel)!}</span>
                        </span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="size1of1" colspan="2">
                    <label class="frm_label">Menu name:</label>
                    <div class="frm_controls">
                    	<@ctl.text name="pfmMenu.menuName" required=true value="${(pfmMenu.menuName)!}" validate={"maxlength":"20"} fieldName="Menu name"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="size1of1" colspan="2">
                    <label class="frm_label">Order:</label>
                    <div class="frm_controls">
                    	<@ctl.text name="pfmMenu.orderNum" required=true value="${(pfmMenu.orderNum)!}" validate={"maxlength":"3","number":"true"} fieldName="Order"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="size1of1" colspan="2">
                    <label class="frm_label">Menu url:</label>
                    <div class="frm_controls">
                    	<@ctl.text name="pfmMenu.menuUrl" required=true value="${(pfmMenu.menuUrl)!}" validate={"maxlength":"40"} fieldName="Menu url"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="size1of1" colspan="2">
                    <label class="frm_label">Menu Pic:</label>
                    <div class="frm_controls">
                    	<@ctl.text name="pfmMenu.menuIcon" required=true value="${(pfmMenu.menuIcon)!}" validate={"maxlength":"20"} fieldName="Menu Pic"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="size1of1" colspan="2">
                    <label class="frm_label">Menu Status:</label>
                    <div class="frm_controls uniformjs">
                        <@ctl.radiobuttonlist id="active" dataType="BizCode" groupCode="status" name="pfmMenu.status" value="${(pfmMenu.status)!}" />
                        <span class="required-star">*</span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="size1of1" colspan="2">
                    <label class="frm_label">Open way:</label>
                    <div class="frm_controls">
                        <@ctl.dropdownlist name="pfmMenu.menuTarget" hasChoice="select" listData={"0":"new window","1":"self window"} value="${(pfmMenu.menuTarget)!}"  class="selectpicker" spanClass="frm_select_picker" required=true fieldName="Open way"/>
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
