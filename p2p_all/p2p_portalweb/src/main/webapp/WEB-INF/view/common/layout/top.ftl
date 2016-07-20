<header class="header">
    <div class="header-top">
        <div class="container">
            <div class="hotline">Services:${getSettingValue('service_tell')}</div>
            <div class="profile-info">
                <#if loginuser?exists>
	                <div class="after-landing">
	                    <span class="al-username">Hi,<a href="${taglibs.allctx}/account/index.htm" title="">${(loginuser.username)!}</a></span>
	                    <a class="al-msg" href="${taglibs.allctx}/account/message/index.htm" title="">Message <span class="count">${getHeaderMessage('0',loginuser.id)}</span></a>
	                    <span class="al-line"></span>
	                    <a class="al-exit" href="${taglibs.allctx}/logout.htm" title="">Log out</a>
	                </div>
                <#else>
	                 <nav class="top-nav">
	                    <a class="log" href="${taglibs.allctx}/login.htm" title="">Log in</a>
	                    <a class="reg" href="${taglibs.allctx}/register.htm" title="">Register</a>
	                 </nav>
                </#if>
            </div>
        </div>
    </div>
    <div class="header-main">
        <div class="container">
        	<#--
            <h1 class="logo"><a class="" href="${taglibs.allctx}/index.htm" title=""><img src="${getSettingValue('por_logo')}" alt="" /></a></h1>
            -->
            <nav class="nav">
                <ul>
                    <li><a class="" href="${taglibs.allctx}/index.htm" title="">Home</a></li>
                    <li><a class="" href="${taglibs.allctx}/invest/investList.htm" title="">Shop</a></li>
                    <#--<li><a class="" href="${taglibs.allctx}/onlineloan/index.htm" title="">我要借款</a></li> //TODOBUG-->
                    <li><a class="" href="${taglibs.allctx}/account/index.htm" title="">My Account</a></li>
                    <li><a class="" href="${taglibs.allctx}/aboutus/index.htm" title="">FAQ</a></li>
                </ul>
            </nav>
        </div>
    </div>
</header>