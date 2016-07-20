<#macro verifyCode>
    <span  class="code_change">
        <a href="#" id="changeImg" title="" class="">refresh</a>
    </span>
    <span  class="code_img">
        <img id="img_verify" src="${taglibs.allctx}/verifyCode.htm" alt="" width="60" height="35"/>
    </span>
    <script type="text/javascript">
      $("#changeImg").click(function(){
        var timenow = new Date().getTime();
        $("#img_verify").attr("src", "${taglibs.allctx}" + "/verifyCode.htm?d="+timenow);
      });
    </script>
</#macro>