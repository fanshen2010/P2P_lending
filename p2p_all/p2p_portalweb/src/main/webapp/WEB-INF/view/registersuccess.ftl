<@cl.html>
        <section class="area">
            <div class="container">
                <div class="prompt-page reg-success">
                    <p class="pp-notice">
                        <strong>Register Success ！</strong>
                    </p>
                    <div class="line-dashed"></div>
                    <p class="pp-txt"><span id="count">5</span>second to<a href="${taglibs.ctx}/account/index.htm" title="">My Account</a>，or click<a href="${taglibs.ctx}/invest/investList.htm" title="">Shop</a></p>
                    <#--
                    <ul class="pp-link clearfix">
                        <li>或者逛逛:</li>
                        <li><a class="first" href="javascript:;" title="">投资频道</a></li>
                        <li><a href="javascript:;" title="">帮助中心</a></li>
                        <li><a href="javascript:;" title="">安全保障</a></li>
                    </ul>
                    //TODOBUG-->
                </div>
            </div>    
        </section>
		<script type="text/javascript">
            $(document).ready(function(){
               var count = 5;
               setInterval(function(){
                  if(count == 0 ){
                      window.location="${taglibs.allctx}/account/index.htm";
                  }
                  $("#count").html(count--);
               },1000);
            });
        </script>
 </@cl.html>