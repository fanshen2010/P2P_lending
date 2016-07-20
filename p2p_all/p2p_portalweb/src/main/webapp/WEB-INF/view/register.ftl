<@cl.html>
        <section class="area">
            <div class="container">
                <div class="register-area ">
                    <div class="register-content tab">
                        <div class="tab-bd clearfix">
                            <div class="register-form person tab-bd-con active">
                                <h2 class="reg-tit"><span class="reg-loglink"><a href="login.htm" title="">Log in</a></span><span class="tit">Register</span></h2>
                                <@ctl.error />
                                <form action="saveinfo.htm" id="regform_personal" method="POST">
                                    <div class="form-group username">
                                        <label class="frm-label">UserName:</label>
                                        <span class="frm-controls">
                                            <input type="text" class="input-text" name="webUser.login" data-rule-required="true" data-rule-rangelength="6,18" data-rule-remote="checkUserLogin.htm" data-field_name="UserName" placeholder="UserName" data-remote_msg="UserName has been used" value="${(webUser.login)!}"/ >
                                        </span>
                                    </div>
                                    <div class="form-group phone">
                                        <label class="frm-label">Cellphone:</label>
                                        <span class="frm-controls">
                                            <input type="text" class="input-text" id="celphone" name="webUser.celphone" data-rule-required="true" data-rule-cellphone="true" data-rule-remote="checkUserPhone.htm" data-field_name="Cellphone" placeholder="Cellphone" data-remote_msg="Cellphone has been used" value="${(webUser.celphone)!}"/>
                                        </span>
                                    </div>
                                    <div class="form-group verifycode">
                                        <span class="frm-label">Captcha:</span>
                                        <span class="frm-controls">
                							<@ctl.sendPhoneVerifyCode phoneNumberDivId="celphone" sendType="1" verifyCodeFlag=phoneVerifyCode/>
                                            
                                        </span>
                                    </div>
                                    <div class="register-line"></div>
                                    <div class="form-group password">
                                        <label class="frm-label">Password:</label>
                                        <span class="frm-controls">
                                            <input type="password" name="password" id="password" data-rule-required="true" data-rule-rangelength="6,18" class="input-text" data-field_name="Password" placeholder="Password"  />
                                        </span>
                                    </div>
                                    <div class="form-group re-password">
                                        <label class="frm-label">Confirm Password:</label>
                                        <span class="frm-controls">
                                            <input type="password" name="webUser.password" data-rule-required="true" data-rule-rangelength="6,18" data-rule-equalTo="#password"  class="input-text" data-field_name="Password" placeholder="Confirm Password"/>
                                        </span>
                                    </div>
                                    <div class="form-group-other ps">
                                        <button type="submit" class="btn btn-primary">Register</button>
                                        <div class="agree">
                                            <input type="checkbox" class="checkbox" name="agreen" data-rule-required="true" data-field_name="please read this Policy"/>
                                            <span>I agree to </span>
                                            <a id="loan_view"  class="col-68a7aa dialog-link" href="${taglibs.allctx}/agreement/index.htm?catalog=register_2nd" target="_blank" title="">the Terms Of Use and Privacy Policy</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
 </@cl.html>