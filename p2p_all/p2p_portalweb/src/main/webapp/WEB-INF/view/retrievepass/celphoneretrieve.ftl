<@cl.html >
        <section class="area">
            <div class="container">
                <div class="regin change-way-phonebox">
                    <h2 class="regin-hd"><strong class="regin-tit">Retrieve Your Password By Phone</strong></h2>
                    <div class="regin-bd">
                    	<@ctl.error />
                        <form action="phoneupdate.htm" class="fpw-phone">
                          <input type="hidden" name="webUser.id" value="${webUser.id}"/>
                            <div class="form-group">
                                <span class="frm-label">Phone Number:</span>
                                <span class="frm-controls">
                                    <span class="phone-num">${webUser.celphone}</span>
                                    <input id="cellphone" type="hidden" value="${webUser.celphone}"/>
                                    <!--<input class="input-text" type="text"  />-->
                                </span>
                            </div>
                            <div class="form-group vfc-code">
                                <span class="frm-label">Captcha:</span>
                                <span class="frm-controls">
        							<@ctl.sendPhoneVerifyCode phoneNumberDivId="cellphone" sendType="2" verifyCodeFlag=phoneVerifyCode  class="btn phone-code"/>
                                </span>
                            </div>
                            <div class="form-group">
                                <span class="frm-label">New Password:</span>
                                <span class="frm-controls">
                                    <input name="password" id="password" class="input-text" type="password" data-rule-rangelength="6,18" data-rule-required="true" data-field_name="New Password" />
                                </span>
                            </div>
                            <div class="form-group">
                                <span class="frm-label">Confirm Password:</span>
                                <span class="frm-controls">
                                    <input name="webUser.password" data-rule-equalTo="#password" class="input-text" type="password" data-rule-rangelength="6,18" data-rule-required="true" data-field_name="Confirm Password" />
                                </span>
                            </div>
                            <div class="form-group">
                                <span class="frm-label">&nbsp;</span>
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
 </@cl.html>