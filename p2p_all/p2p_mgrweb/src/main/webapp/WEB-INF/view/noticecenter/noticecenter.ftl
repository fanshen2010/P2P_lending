<@cl.html title="通知中心" css="page_notification.css">
                <div class="col_main">
                    <div class="main_hd">
                        <h2>Notice Center</h2>
                    </div>
                    <div class="main_bd">
                        <div id="notification" class="notificationCenterPage">
                        <#list messageLogs as messageLog>
                            <#assign isReaded = (messageLog.status?? && messageLog.status == "0") >
                            <dl class="notify_item ${isReaded?string("", "readed")}">
                                <dt id="${messageLog.id}">
                                    <a class="notify_title_wrapper" href="javascript:;">
                                        <span class="notify_status">
                                            <span class="notify_time">
                                                <#if (messageLog.sendTime)?if_exists>
                                                    <@h.datef value=messageLog.sendTime format="yyyy-MM-dd"/>
                                                </#if>
                                            </span>
                                            <i class="arrow"></i>
                                        </span>
                                        <span class="notify_title">
                                            <i class="dot">●</i>${messageLog.subject}
                                        </span>
                                    </a>
                                </dt>
                                <dd class="none">${messageLog.content}</dd>
                            </dl>
                        </#list>
                        </div>
                        <form action="index.htm" id="searchForm" method="POST">
                           <@ctl.page page=criteria.page/>
                        </form>
                    </div>
                </div>
                <script type="text/javascript">
                    $(function(){
                        $(".notify_item dt").click(function(){
                            $(this).parent("dl").siblings().find("dd").addClass("none");
                            $(this).parent("dl").siblings().removeClass("select show");
                            $(this).parent("dl").toggleClass("select show");
                            $(this).next("dd").toggleClass("none");
                        if(!$(this).parent("dl").hasClass("readed")){
                            var _parent = $(this).parent("dl");  // 定义当前被点击的元素的父对象，由于success: function() 不识别$(this)
                            if(!$(this).parent("dl").hasClass('readed')){
                                 $.ajaxLoadFtl({     // 利用ajax，更改消息的状态为已读
                                      type: 'POST',
                                      url: 'status.htm',      //  对应的action
                                      dataType:"json",
                                      data: {
                                          'messageLog.id': $(this).attr("id"),   
                                      },
                                      success: function(response){
                                         $(_parent).addClass("readed");
                                         if(response.record <= 0){
                                         $("i.icon_dot_notices").remove();
                                         }
                                      },
                                      error: function (data, transport) {
                                      },
                                });
                            }
                            }
                        }); 
                    });
                </script>
</@cl.html>