<@cl.html title="后台管理_业务人员管理">
<div class="col_main">
    <div class="main_hd" id="main_hd">
    	<div class="clearfix">
            <h2>Account setting</h2>
        </div> 
     </div>
    <div class="main_bd">
    	<div class="form_wrp">
            <table class="table" cellpadding="0" cellspacing="0">
                <tbody class="tbody" id="">
                    <tr>
                        <td class="size1of2">
                            <label class="frm_label">User name:</label>
                            <div class="frm_controls">
                                <span class="frm_val">
                                    <span>${(pfmUserDto.userName)!}</span>
                                    <input type="hidden" id="hidUserId" value="${pfmUserDto.id}" />
                                    <input type="hidden" id="hidUserName" value="${pfmUserDto.userName}" />
                                </span>
                            </div>
                        </td>
                        <td class="table_right_border size1of2">
                            <label class="frm_label">Name:</label>
                            <div class="frm_controls">
                                <span class="frm_val">
                                    <span>${(pfmUserDto.realName)!}</span>
                                </span>
                            </div>
                        </td>
                    </tr>                        
                    <tr>
                        <td class="table_right_border size1of2">
                            <label class="frm_label">Department:</label>
                            <div class="frm_controls">
                                <span class="frm_val">
                                    <span>${(pfmUserDto.departName)!}</span>
                                </span>
                            </div>
                        </td>
                        <td class="size1of2">
                            <label class="frm_label">Position:</label>
                            <div class="frm_controls">
                                <span class="frm_val">
                                    <span>${(pfmUserDto.postName)!}</span>
                                </span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_right_border size1of2">
                        	<form id="formPhone" method="POST">
                            <label class="frm_label">Contact phone:</label>
                            <div class="frm_controls">
                                <span class="frm_val">
                                    <span>${(pfmUserDto.contactPhone)!}</span>
                                </span>
                                <a class="phone_addlink" id="phone_add" href="javascript:;" title="">modify</a>
                                <span class="phone_addbox">
                                    <@ctl.text id="txt_phone" name="txt_phone_name" validate={"telephoneOrCellphone":"true"} required="true" />
                                    <a class="btn btn_primary" href="javascript:;" title="">save</a>
                                    <a class="btn btn_default" href="javascript:;" title="">cancel</a>
                                </span>
                            </div>
                            </form>
                        </td>
                        <td class="size1of2">
                        	<form id="formEmail" method="POST">
                            <label class="frm_label">Email:</label>
                            <div class="frm_controls">
                                <span class="frm_val">
                                    <span>${(pfmUserDto.emailAddress)!}</span>
                                </span>
                                <a class="email_editlink" id="email_edit" href="javascript:;" title=""></a>
                                <span class="email_editbox">
                                    <@ctl.text id="txt_email" name="txt_email_name" validate={"email":"true","maxlength":"64"} />
                                    <a class="btn btn_primary" href="javascript:;" title="">save</a>
                                    <a class="btn btn_default" href="javascript:;" title="">cancel</a>
                                </span>
                            </div>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_right_border size1of2">
                            <label class="frm_label">Password:</label>
                            <div class="frm_controls">
            					<a class="btn btn_primary fancybox" href="#loginpwd" title="modify"><i class="icon"></i>modify</a>
                            </div>
                        </td>
                        <td class="size1of2">
                            <label class="frm_label">Balance:</label>
                            <div class="frm_controls">
                                <span class="frm_val">
                                    <span>$ ${(balance)!}</span>
                                </span>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- 修改登录密码  -->
<div class="fancybox_pop popsize_m" id="loginpwd">
	<div class="form_wrp">
		<form id="passWordEditForm" method="POST">
    	<table class="table">
            <tbody>
            	<tr>
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">OldPassWord:</label>
                        <div class="frm_controls">
                            <@ctl.password id="oldPassWord" name="pfmUser.password" required="true" validate={"rangelength":"[6,18]","remote":"passWordCheck.htm?pfmUser.userName=${pfmUserDto.userName}"} />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">NewPassWord:</label>
                        <div class="frm_controls">
                            <@ctl.password id="newPassWordFir" name="newPassWordFirName" required="true" validate={"rangelength":"[6,18]"} />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">ConfirmPassWord:</label>
                        <div class="frm_controls">
                            <@ctl.password id="newPassWordSec" name="newPassWordSecName" required="true" validate={"rangelength":"[6,18]","equalTo":"#newPassWordFir"} />
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="form_btn">
        	<@ctl.button id="btnOkSubmit" class="btn btn_primary" text="save" />
            <@ctl.button id="btnCancel" class="btn" text="cancel" />
        </div>
        </form>
    </div>
</div>
<script type="text/javascript">
				//页面加载
				$(document).ready(function(){ 
					if($("#email_edit").prev(".frm_val").find("span").text()==""){
						$("#email_edit").show().text("add");
					} else {
						$("#email_edit").show().text("modify");
					}
				}) 
				// Contact phone 赋值
				$("#phone_add").click(function(){
					var phoneVal = $(this).prev(".frm_val").find("span").text();
					$(this).next(".phone_addbox").find(".frm_input_box").find("input").val(phoneVal);
					$(this).prev(".frm_val").hide();
					$(this).hide();
					$(this).next(".phone_addbox").show();
				});	
				// 联系电话修改
				$(".phone_addbox .btn_primary").click(function(){
					var phoneVal = $(this).siblings(".frm_input_box").find("input").val();
					if($("#formPhone").valid()){
						$.ajax({
					            type: "POST",
					            url: "personalPhoneEditSubmit.htm",
					            dataType: "json",
					            data: {"pfmUser.contactPhone":phoneVal,"pfmUser.id":$("#hidUserId").val()},
					            error: function (data, transport) {
										alert("save error");			               
					            },
					            success: function (result) {
					            	if(result.retMes=="1"){
					            		$(".phone_addbox .btn_primary").parent().siblings(".frm_val").find("span").text(phoneVal);
										$(".phone_addbox .btn_primary").parent().hide();
										$(".phone_addbox .btn_primary").parent().siblings(".phone_addlink").show();
										$(".phone_addbox .btn_primary").parent().siblings(".frm_val").show();
					            	}else{
					            		alert("save error");
					            	}
					            }
					        });
					    }
				});
				//联系电话取消
				$(".phone_addbox .btn_default").click(function(){
					$(this).parent().hide();
					$(this).parent().siblings(".phone_addlink").show();
					$(this).parent().siblings(".frm_val").show();
				});
				// 电子邮箱 赋值
				$("#email_edit").click(function(){
					var email = $(this).prev(".frm_val").find("span").text();
					$(this).next(".email_editbox").find(".frm_input_box").find("input").val(email);
					$(this).prev(".frm_val").hide();
					$(this).hide();
					$(this).next(".email_editbox").show();
				});	
				// 电子邮箱 modify
				$(".email_editbox .btn_primary").click(function(){
					var emailVal = $(this).siblings(".frm_input_box").find("input").val();
					if($("#formEmail").valid()){
						$.ajax({
					            type: "POST",
					            url: "personalEmailEditSubmit.htm",
					            dataType: "json",
					            data: {"pfmUser.emailAddress":emailVal,"pfmUser.id":$("#hidUserId").val()},
					            error: function (data, transport) {
										alert("save error");			               
					            },
					            success: function (result) {
					            	if(result.retMes=="1"){
					            		if(emailVal==""){
					            			$("#email_edit").show().text("add");
					            		}else{
					            			$("#email_edit").show().text("modify");
					            		}
					            		$(".email_editbox .btn_primary").parent().siblings(".frm_val").find("span").text(emailVal);
										$(".email_editbox .btn_primary").parent().hide();
										$(".email_editbox .btn_primary").parent().siblings(".email_editlink").show();
										$(".email_editbox .btn_primary").parent().siblings(".frm_val").show();
					            	}else{
					            		alert("save error");
					            	}
					            }
					        });
					    }
				});
				//Email cancel
				$(".email_editbox .btn_default").click(function(){
					$(this).parent().hide();
					$(this).parent().siblings(".email_editlink").show();
					$(this).parent().siblings(".frm_val").show();
				});
				
				// 修改密码 关闭fancybox
				$("#btnCancel").click(function(){
					$.fancybox.close();	
				});
				
				//密码修改保存方法
				$("#btnOkSubmit").click(function(){
					if($("#passWordEditForm").valid()){
						$.ajax({
				            type: "POST",
				            url: "personalPassEditSubmit.htm",
				            dataType: "json",
				            data: {"pfmUser.userName":$("#hidUserName").val(),"pfmUser.password":$("#oldPassWord").val(),"pfmUserPassFir":$("#newPassWordFir").val(),"pfmUserPassSec":$("#newPassWordSec").val(),"pfmUser.id":$("#hidUserId").val()},
				            error: function (data, transport) {
									alert("save error");			               
				            },
				            success: function (result) {
				            	if(result.retMes=="1"){
				            		alert("save success");
				            		$.fancybox.close();
				            	} else if(result.retMes=="2"){
				            		alert("old password error")
				            	} else if(result.retMes=="3"){
				            		alert("confirm password error");
				            	} else{
				            		alert("save error");
				            	}
				            }
				        });
					}
				});
</script>
</@cl.html>