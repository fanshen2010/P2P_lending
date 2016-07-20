<@cl.html  pateType="personal" bodyCss="user-center safe-center">
    <div class="uc-main">
        <h2 class="uc-main-hd"><strong>Message</strong></h2>
        <div class="uc-main-bd">
        <form action="${taglibs.allctx}/account/message/index.htm" id="searchForm" method="POST" >
            <div class="message-center-area">
                <ul class="message-list">
                <#list lstMessageLog as message>
                    <li class="message-li">
                        <div class="message-datetime"><p class="message-datetime-date"><@h.dateformat value=message.sendTime format='yyyy-MM-dd HH:mm:ss'/></p></div>
                        <div class="message-cont">
                            <h3 class="message-cont-hd">
                            	<#--
                                	<strong class="message-tit">${message.status}</strong>
                                -->
                                <strong class="message-tit">${message.subject}</strong>
                            </h3>
                            <div class="message-cont-bd">
                                <p class="message-cont-info">${message.content}</p>
                            </div>
                        </div>
                    </li>
                 </#list>   
                </ul>
             <@ctl.page page=criteria.page />
       	 </div>
        </form>
        </div>
    </div>
</body>
</@cl.html>