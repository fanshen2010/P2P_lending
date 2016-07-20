<#--图片验证码-->
<#macro verifyCode>
    <span class="code-img">
    	<img id="img_verify" src="${taglibs.allctx}/verifyCode.htm" width="60" height="40" />
    </span>
    <script type="text/javascript">
      $("#img_verify").click(function(){
        var timenow = new Date().getTime();
        $("#img_verify").attr("src", "${taglibs.allctx}" + "/verifyCode.htm?d="+timenow);
      });
    </script>
</#macro>

<#--手机验证码-->
<#macro sendPhoneVerifyCode phoneNumberDivId sendType countClean=false verifyCodeFlag="" class="btn disable"> 
	<input class="input-text" type="text" name="phoneVerifyCode" data-rule-required="true" data-field_name="Captcha"/>
    <button class="${class}" type="button" id="btnSendCode" disabled="disable"
            onclick="sendVerifyCode('${phoneNumberDivId}','${sendType}');">send
    </button>    
    <script type="text/javascript">
    $(function(){
    	<#if verifyCodeFlag!=null && verifyCodeFlag!="">
	    	<#if countClean==false>
			if(!isNaN(GetCookieByName("countName")) && GetCookieByName("countName")!="0"){
				$("#btnSendCode").attr("disabled", "disabled");
		    	$("#btnSendCode").addClass("disable");
		    	$("#btnSendCode").text("send(" + curCount + ")");
		    }
		    else{
				$("#btnSendCode").removeAttr("disabled");// 启用按钮
		    	$("#btnSendCode").removeClass("disable");
		    	$("#btnSendCode").text("send");
		    }
	    	 <#else>
	    	 curCount = 0;
	    	 </#if>
	   	<#else>
	   	curCount = 0;
	    $("#btnSendCode").removeAttr("disabled");// 启用按钮
    	$("#btnSendCode").removeClass("disable");
    	$("#btnSendCode").text("send");
	    </#if>
	})
    
    var sendVerifyCode = function(obj,sendType) {
		var v=$("#"+obj);
		var patten = new RegExp(/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9]|17[0-9])\d{8}$/);
		if (patten.test(v.val())){
		    curCount = count;
		    var params={
		    		phoneNumber:v.val(),
		    		sendType:sendType
		    };
		    //向后台发送处理数据
		    $.ajax({
		        type: "POST", //用POST方式传输
		        dataType: "JSON", //数据格式:JSON
		        url: "${taglibs.allctx}/sendPhoneVerifyCode.htm", //目标地址
		        beforeSend: function () {
		        },
		        complete: function() {
		        },
		        data: params,
		        error: function (request) {
		            location.href = "${taglibs.allctx}/account/index.htm";
		        },
		        success: function (json){
		        	if(json.msg=="success"){
		        		alert("Send Success ,captcha:"+json.phoneVerifyCode);
		        		 //设置button效果，开始计时
		        		$("#btnSendCode").attr("disabled", "disabled");
		    		    $("#btnSendCode").addClass("disable");
		    		    $("#btnSendCode").text("send(" + curCount + ")");
		    		    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
		        	}else{
		        		alert("Send Error , captcha"+json.phoneVerifyCode);
		        	}
		        }
		    });
		}else{
			v.blur();
		}	
	}
    </script>
</#macro>