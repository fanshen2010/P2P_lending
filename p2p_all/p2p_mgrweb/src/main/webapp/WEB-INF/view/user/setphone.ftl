 <!-- 电话号码设置 -->
<form id="cust_update" action="${taglibs.allctx}/user/update.htm" method="post">
<div class="form_wrp">
    <table class="table">
        <tbody>
           <tr id="">
               <td class="size1of1" colspan="2">
                    <label class="frm_label">绑定手机号:</label>
                    <div class="frm_controls">
                        <@ctl.text name="user.bindPhone" required="true" fieldName="绑定手机号" validate={"maxlength":"20","minlength":"1"}/>
                        <@ctl.button type="submit" title="发送验证码" text="发送验证码"/>  
                        </div>
                    </td>
            </tr>          
            <tr id="">
                <td class="size1of1" colspan="2">
                    <label class="frm_label">手机验证码:</label>
                    <div class="frm_controls">
                        <@ctl.text name="user.phoneCode" required="true" fieldName="手机验证码" validate={"maxlength":"18","minlength":"6","remote":"checkUserphoneCode.htm"}/>
                    </div>
                </td>
            </tr>              
        </tbody>
    </table>
    <div class="form_btn">
        <@ctl.submit title="save" text="save"/>
        <@ctl.linkButton href="javascript:;" id="cancel" title="cancel" text="cancel"/>
    </div>
</div>    
</form>