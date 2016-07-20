<div class="uc-side">
    <div class="uc-info">
        <a href="${taglibs.allctx}/account/index.htm" title=""><i class="icon"></i></a>
        <a class="uc-info-username" href="${taglibs.allctx}/account/index.htm" title="">${(loginuser.username)!}</a>
    </div>
    <div class="uc-menu">
    	<h3 class="uc-menu-t my-info-t">My Account</h3>
        <ul class="my-info">
            <li id="account"><a class="uc-myaccount" href="${taglibs.allctx}/account/index.htm" title=""><span>Earnings</span></a></li>
            <li id="security"><a class="uc-mysafe" href="${taglibs.allctx}/account/security/index.htm" title=""><span>Security</span></a></li>
            <#--
            <li id="paymentLog"><a class="uc-myrecords" href="${taglibs.ctx}/account/paymentLog/paymentLog.htm" title=""><span>交易记录</span></a></li>
            //TODOBUG-->
            <li id="message"><a class="uc-mymsg" href="${taglibs.ctx}/account/message/index.htm" title=""><span>Message</span></a></li>
        </ul>
        <h3 class="uc-menu-t my-invest-t">My Invest</h3>
        <ul class="my-invest">
        	<#--
            <li id="paymentPlan"><a class="uc-mypayments" href="${taglibs.ctx}/account/paymentPlan/index.htm" title=""><span>回款计划</span></a></li>
            //TODOBUG-->
            <li id="myinvest"><a class="uc-myinvest" href="${taglibs.ctx}/account/myinvest/investList.htm" title=""><span>Investment</span></a></li>
        </ul>
        <#--
        <h3 class="uc-menu-t my-finance-t">我的融资</h3>
        <ul class="my-finance">
            <li class=""><a class="uc-myfinance" href="个人中心_我的融资.html" title=""><span>我的融资</span></a></li>
        </ul>
        -->
    </div>
    <div class="uc-manager">
         <!--注意 开发人员做好功能后注释必须删掉
         三种状态 :
             1.用户已经注册经理人，并且后台验证通过显示 经理人注册的信息 “<a href="javascript:;" title="">经理人信息</a>”
             2.用户已经注册经理人，后台验证不通过或者管理员给注册经理人禁用了，此处显示 “<span class="manager-disable">经理人信息</span>”
             3.用户未注册经理人，提供链接让其去注册经理人 “<a href="javascript:;" title="">注册经理人</a>”-->
        <a href="注册经理人.html" title="">注册经理人</a>
    </div>
</div>

<#assign actionUrl=request.requestURI>
<script type="text/javascript">
	$(function(){
		<#if position!="">
    		$("#${position}").addClass("active");
		</#if>
		$("[href='${actionUrl}']").parent().addClass("active");
	});
</script>