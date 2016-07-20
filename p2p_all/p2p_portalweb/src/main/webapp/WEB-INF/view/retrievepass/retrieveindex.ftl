<@cl.html >
        <section class="area">
            <div class="container">
                <div class="regin forget-pwd">
                    <h2 class="regin-hd"><strong class="regin-tit">Retrieve Your Password</strong></h2>
                    <div class="regin-bd">
                        <form action="chooseways.htm" class="forget-pwd-frm">
                            <div class="form-group">
                                <span class="frm-label">Username:</span>
                                <span class="frm-controls">
                                    <input class="input-text" name="webUser.login" type="text" data-rule-required="true" data-field_name="Username" data-rule-remote="checkUserLogin.htm" data-field_name="Username" data-remote_msg="Username does not exist"/>
                                </span>
                            </div>
                            <div class="form-group vfc-code">
                                <span class="frm-label">Captcha:</span>
                                <span class="frm-controls">
                                    <#-- name="verifyCode" 为固定值 -->
                                    <input class="input-text" type="text" name="verifyCode" data-rule-required="true" data-field_name="Captcha"/>
                                    <a id="changeImg" class="code-change" href="javascript:;" title="">refresh</a>
                                    <span  class="code-img">
                                        <@ctl.verifyCode />
                                    </span>
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
        <script type="text/javascript">
            $("#changeImg").click(function(){
				var timenow = new Date().getTime();
				$("#img_verify").attr("src", "${taglibs.allctx}" + "/verifyCode.htm?d="+timenow);
			});
        </script>
 </@cl.html>