<@cl.login title="用户登录" js="">
 <script type="text/javascript">
	$(function(){
		$(window).on("load resize", function(){
			if($(window).height() >= $(document.body).height()){
				$("#footer").css({"position":"fixed","bottom":0});	
			}else{
				$("#footer").css({"position":"static","bottom":0});	
			}
		});
	});
	</script>
<div class="login_wrap">            
    	<div class="login_area">
            <div class="login_img"><img src="${taglibs.ctx}/style/images/logbanner.jpg" /></div>
            <div class="login_card">
                <h2 class="login_tit"><span class="tit">Login</span></h2>
                <div class="login_form">
                    <form action="logincheck.htm" name="" id="loginform" method="post">
                        <div class="frm_li username">
                            <label class="frm_label">UserName:</label>
                            <span class="frm_controls">
                                <input type="text" name ="j_username" class="frm_input username" value="${j_username}" placeholder="UserName" />
                            </span>
                        </div>
                        <div class="frm_li password">
                            <label class="frm_label">Password:</label>
                            <span class="frm_controls">
                                <input type="password" name ="j_password" class="frm_input password" placeholder="Password" />
                            </span>
                        </div>
                        <div class="clearfix">
                            <div class="frm_li  verifycode fl">
                                <label class="frm_label">VerifyCode:</label>
                                <span class="frm_controls">
                                    <input type="text" id="verifyCode" name="verifyCode" class="frm_input middle" datatype="*" nullmsg="verifyCode！" />
                                </span>
                            </div>
                            <@ctl.verifyCode />
                        </div>
                        <div class="frm_prompt frm_prompt_ftg">
                            <span class="remenber_account">
                                <input type="checkbox" class="checkbox" id="remember_checkbox" /><label for="remember_checkbox">remember me</label>
                            </span>
                        </div>
                        <div class="login_submit">
                            <button type="submit" class="btn btn_primary">Login</button>
                        </div>
                        <div class="login_line"></div>
                        <div class="frm_prompt last">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
 </@cl.login>