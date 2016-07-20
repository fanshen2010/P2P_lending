<@cl.html >
        <section class="area">
            <div class="container">
                <div class="regin change-pwd-success">
                    <h2 class="regin-hd"><strong class="regin-tit">Retrieve Password Success</strong></h2>
                    <div class="regin-bd">
                        <p class="pwd-success-tit">Retrieve Password Success！</p>
                        <p class="pwd-success-prompt"><span id="count">5</span> seconds return Home，Please Login again<a href="${taglibs.ctx}/login.htm" title="" class="">Login</a></p>
                    </div>
                </div>
            </div>
        </section>
        <script type="text/javascript">
            $(document).ready(function(){
               var count = 5;
               setInterval(function(){
                  if(count == 0 ){
                      window.location="${taglibs.allctx}/index.htm";
                  }
                  $("#count").html(count--);
               },1000);
            });
        </script>
 </@cl.html>