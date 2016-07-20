<@cl.html>
        <section class="area">
        	<div class="container">
            	<div class="regin change-way-box">
                	<h2 class="regin-hd"><strong class="regin-tit">Choose The Way To Retrieve Your Password</strong></h2>
                    <div class="regin-bd">
                    	<div class="change-waylist">
                    	<#assign hasMail = (webUser.email?? && webUser.email != "") >
                            <a href="${hasMail?string("sendMail.htm?webUser.id=${webUser.id}", "javascript:;")}" title="" class="way-mail ${hasMail?string("", "unbind")}">
                                <i class="icon"></i>
                                <p>${hasMail?string("Retrieve Password By Email", "No Verified Email,Please Choose Other Way")}</p>
                            </a>
                            <a href="cellphoneway.htm?webUser.id=${(webUser.id)!}" title="" class="way-phone">
                                <i class="icon"></i>
                                <p>Retrieve Password By Phone</p>
                            </a>
                            <p class="change-way-prompt">If you can not retrieve your password, please contact with our services:${getSettingValue('service_tell')}</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
 </@cl.html>