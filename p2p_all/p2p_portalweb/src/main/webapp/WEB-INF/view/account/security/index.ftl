<@cl.html pateType="personal" bodyCss="user-center safe-center">				
		<div class="uc-main">
                <div class="uc-main-hd"><strong>Security</strong></div>
                <div class="uc-main-bd">
                    <div class="safe-center-area">
                        <div class="sc-li sc-username active clearfix">
                            <p class="sc-info">
                                <i class="icon"></i>
                                <span class="sc-lab">UserName</span>
                                <span class="sc-val">${(webUser.login)!}</span>
                            </p>
                        </div>
                        <div class="sc-li sc-password active clearfix">
                            <p class="sc-info">
                                <i class="icon"></i>
                                <span class="sc-lab">Password</span>
                                <span class="sc-val">*************</span>
                            </p>
                            <p class="sc-operate">
                            	<a id="alter-loginpwd-view" class="dialog-link" href="javascript:;" title="">set</a>
                            </p>
                        </div>
                        <div class="sc-li sc-phone active clearfix">
                            <p class="sc-info">
                                <i class="icon"></i>
                                <span class="sc-lab">Cellphone</span>
                                <span class="sc-val">
                                <#if webUser.celphone?length gt 10>
                                	${(webUser.celphone?substring(0,3) + '****' + webUser.celphone?substring(7))}
                                <#else>
                                	${(webUser.celphone)!}
                                </#if>
                                </span>
                            </p>
                            <p class="sc-operate">
                                <a id="alter-phonenum-view" class="dialog-link" href="javascript:;" title="">set</a>
                            </p>
                        </div>
                        <div class="sc-li sc-mail <#if webUser.email??&& webUser.email?length!=0>active</#if> clearfix">
                            <p class="sc-info">
                                <i class="icon"></i>
                                <span class="sc-lab">Email</span>
                                <span class="sc-val">
                             	<#if webUser.email?? && webUser.email?length!=0>
                                	<#if webUser.email?length gt 2>
                                	${(webUser.email?substring(0,2) + '...' + webUser.email?substring(webUser.email?index_of('@') - 1))}
                            		<#else>
                            		${(webUser.email)!}
                            		</#if>
                            	<#else>
                            		No Email
                            	</#if>
                            	</span>
                            </p>
							<p class="sc-operate">
                            	<a id="alter-mail-view" class="dialog-link" href="javascript:;" title="">set</a>
                            </p>
                        </div>
                        <#--
                        <div class="sc-li sc-third-account <#if ciccAccountFlag==true>active</#if> clearfix">
                            <p class="sc-info">
                                <i class="icon"></i>
                                <span class="sc-lab">中金支付帐户</span>
                                <#if ciccAccountFlag==true>
                                <span class="sc-val">已关联</span>
                                <#else>
                                <span class="sc-val">未关联</span>
                                </#if>
                            </p>
                            <p class="sc-operate">
                            	<#if ciccAccountFlag==false>
                            	<a target="_blank" href="${taglibs.allctx}/account/ciccDoRegister.htm" title="">set</a>
                            	</#if>
                            </p>
                        </div>
                        //TODOBUG-->
                    </div>
                </div>
         </div>
         
<div class="dn" id="alter-loginpwd"></div>
<div class="dn" id="alter-phonenum"></div>
<div class="dn" id="alter-mail"></div>
<div class="dn" id="bind-mail-dialog"></div>

<script type="text/javascript">
$(function(){

	// 修改密码
	$("#alter-loginpwd-view").click(function(){
		$.ajax({
            type: "POST",
            url: "changePasswordDialog.htm",
            async:false,
            dataType: "json",
            error: function (data, transport) {			            
            },
            success: function (result) {
            	//修改密码(第一页)
            	$("#alter-loginpwd").html(result.html);
				$.fancybox.open($("#alter-loginpwd"),{"title":"Password Set"});
            }
        });		
	});
	
	
	// 修改电话
	$("#alter-phonenum-view").click(function(){
		$.ajax({
            type: "POST",
            url: "changeCellphoneDialog.htm",
            async:false,
            dataType: "json",
            error: function (data, transport) {			            
            },
            success: function (result) {
            	//修改电话(第一页)
            	$("#alter-phonenum").html(result.html);
				$.fancybox.open($("#alter-phonenum"),{"title":"Cellphone Set"});
            }
        });		
	});
	
	// 修改邮箱
	$("#alter-mail-view").click(function(){
		$.ajax({
            type: "POST",
            url: "changeMailDialog.htm",
            async:false,
            dataType: "json",
            error: function (data, transport) {			            
            },
            success: function (result) {
            	//修改密码(第一页)
            	if(result.updateMailStep=="new"){
            		$("#bind-mail-dialog").html(result.html);
					$.fancybox.open($("#bind-mail-dialog"),{"title":"Email set"});
            	}else if(result.updateMailStep=="1"){
            		$("#alter-mail").html(result.html);
					$.fancybox.open($("#alter-mail"),{"title":"Email set"});
            	}
            }
        });		
	});
	
})
 </script>
 </@cl.html>