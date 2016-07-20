<@cl.html>
<section class="area">
	<div class="container">
    	<div class="login-area">
        	<div class="login-img"></div>
            <div class="login-card">
                <h2 class="login-tit"><span class="tit">Log in</span></h2>
                <div class="login-form">
                   <@ctl.error />
                    <form action="logincheck.htm" name="" id="loginform" method="post">
                        <div class="form-group username">
                            <label class="frm-label">UserName:</label>
                            <span class="frm-controls">
                                <input type="text" name ="j_username" class="input-text username" data-rule-required="true" value="${j_username}" data-field_name="UserName" placeholder="UserName" />
                            </span>
                        </div>
                        <div class="form-group password">
                            <label class="frm-label">Password:</label>
                            <span class="frm-controls">
                                <input type="password" name ="j_password" class="input-text password" data-rule-required="true" data-field_name="Password" placeholder="Password" />
                            </span>
                        </div>
                        <div class="form-group verifycode">
                            <label class="frm-label">Captcha:</label>
                            <span class="frm-controls">
                                <input type="text" id="verifyCode" name="verifyCode" class="input-text middle" />
                            	<@ctl.verifyCode />
                            </span>
                        </div>
                        <div class="frm-prompt frm-prompt-ftg">
                            <span class="remenber-account">
                                <input type="checkbox" id="_spring_security_remember_me" name="_spring_security_remember_me" class="checkbox" />
                                <label for="remember_checkbox">Remember Me</label>
                            </span>
                            <span class="fgt-psd"><a href="${taglibs.allctx}/retrievepass/index.htm" title="" class="forgot-pwd"> Forgot your password?</a></span>
                        </div>
                        <div class="login-submit">
                            <button type="submit" class="btn btn-primary">Log in</button>
                        </div>
                        <div class="login-line"></div>
                        <div class="frm-prompt last">
                            <p class="go-reg"><span>No account?<a href="register.htm" title="">Register</a></span></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
</@cl.html>