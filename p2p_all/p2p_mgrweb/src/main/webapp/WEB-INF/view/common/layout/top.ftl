<div class="head" id="header">
			<div class="head_box">
				<div class="inner wrp">
					<h1 class="logo"><a href="${taglibs.ctx}/index.htm" title="微信公众平台"><img src="${getSettingValue('mgr_logo')}" alt=""></a></h1>
					<div class="account">
                    	<div class="account_meta account_info account_meta_primary">
                            <a href="javascript:;" class="nickname">hello！${(loginuser.realName)!}</a>
                        </div>
                        <div id="accountArea" class="account_meta account_inbox account_meta_primary">
                            <a href="${taglibs.ctx}/noticecenter/index.htm" class="account_inbox_switch">
                                <i class="icon_inbox icon_inbox_notice">Message</i>&nbsp;<@bizTag.message userId="${(loginuser.id)!}" />                
                            </a>
                            <a href="${taglibs.ctx}/system/businessStaff/personal.htm" class="account_inbox_switch">
                                <i class="icon_inbox icon_inbox_setup">set</i>
                            </a>
                        </div>
                        <div class="account_meta account_logout account_meta_primary">
                            <a id="logout" href="${taglibs.ctx}/logout.htm" class="back">Logout</a>
                    	</div>
                    </div>
               	</div>
             </div>
        </div>