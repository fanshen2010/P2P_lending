 <!-- Email set -->
<form id="cust_update" action="${taglibs.allctx}/user/update.htm" method="post">
<div class="form_wrp">
    <table class="table">
        <tbody>
           <tr id="">
               <td class="size1of1" colspan="2">
                    <label class="frm_label">绑定邮箱:</label>
                    <div class="frm_controls">
                        <@ctl.text name="user.bindEmail" required="true" fieldName="绑定邮箱" validate={"maxlength":"20","minlength":"1"}/> 
                        <@ctl.button type="submit" title="发送验证码" text="发送验证码"/>                
                    </div>                   
               </td>
            </tr>    
            <tr id="">
                <td class="size1of1" colspan="2">
                    <label class="frm_label">邮箱验证码:</label>
                    <div class="frm_controls">
                        <@ctl.text name="user.emailCode" required="true" fieldName="邮箱证码" validate={"maxlength":"18","minlength":"6","remote":"checkUseremailCode.htm"}/>
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