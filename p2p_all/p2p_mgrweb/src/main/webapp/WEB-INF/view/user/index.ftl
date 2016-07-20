<@cl.html title="后台管理_用户管理">
<div class="col_main">
    <div class="main_hd" id="main_hd">
    	<div class="clearfix">
            <h2>Front User Manage</h2>
        </div> 
     </div>
    <div class="main_bd">
      <form action="${taglibs.allctx}/user/index.htm" id="searchForm"  method="POST">
    	<div class="search_form">
        	<div class="search_list clearfix">
                <div class="sl_li">
                    <label class="frm_label">User name:</label>
                    <div class="frm_controls">              
                        <@ctl.text  name="criteria.login" value="${criteria.login!}" />
                    </div>                  
                </div>
                <div class="sl_li">
                    <label class="frm_label">Contact phone:</label>
                    <div class="frm_controls">
                        <@ctl.text  name="criteria.celphone" value="${criteria.celphone!}" />
                    </div>
                </div>
                <div class="sl_li">
                    <label class="frm_label">Email:</label>
                    <div class="frm_controls">
                        <@ctl.text  name="criteria.email" value="${criteria.email!}" />
                    </div>
                </div>
                <div class="sl_li">
                    <label class="frm_label">Status:</label>
                    <div class="frm_controls">
                        	<@ctl.dropdownlist name="criteria.status"    hasAll=true hasChoice=false   groupCode="investorsStatus"  dataType="BizCode"  class="selectpicker" spanClass="frm_select_picker" value="${(criteria.status)!}" />
                    </div>
                </div>
                <div class="sl_li">
                    <label class="frm_label">Register time:</label>
                    <div class="frm_controls group" style="width:auto">
                        <@ctl.datepicker name="userMinTime" value=registerMinTime />
                        <span class="">~</span>
                        <@ctl.datepicker name="userMaxTime" value=registerMaxTime />
                    </div>
                </div>
                <div class="sl_li sl_btn">
                        <@ctl.submit text="search"  />
                </div>
            </div>
        </div>
        <div class="main_list">
            <div class="from_frm">                       	   
                <div class="nowrap_table">
                    <table class="table table-striped table-hover form_wrp_padding" cellpadding="0" cellspacing="0">
                        <thead class="thead">
                            <tr>              
                                <th class="table_cell tl">User name</th>
                                <th class="td_phone tl">Contact phone</th>
                                <th class="table_cell tl">Email</th>
                                <th class="td_date tl">Register time</th>
                                <th class="table_cell tl">Status</th>
                                <th class="table_cell tr">Operate</th>
                            </tr>
                        </thead>
                        <tbody class="tbody" id="">
                        <#list lstWebUser as user>
                            <tr class="">
                                <td class=" table_cell">${user.login}</td>
                                <td class=" table_cell">${user.celphone}</td>
                                <td class=" table_cell">${user.email}</td>
                                <td class=" table_cell "><@h.datetimef value=user.createTime /></td>
                                <td class=" table_cell "><@ctl.statusButton status="${(user.active)}" isView="true" href="javascript:;"/></td>
                                <td class=" table_cell tr">
                                    <@ctl.statusButton status="${(user.active)}" id="user_status" href="updateStatus.htm?receiverId=${user.id!}"/>
                                    <@ctl.linkButton href="javascript:;" title="Phone set" id="bbb" class="operate_icon setphone fancybox" data={'id':'${user.id}'} text="Phone set"/>
                                    <@ctl.linkButton href="javascript:;" title="Email set" id="aaa" class="operate_icon setemail fancybox" data={'id':'${user.id}'} text="Email set"/>                    
                                    <@ctl.operateButton href="${taglibs.allctx}/user/view.htm?id=${user.id}" title="view" class="operate_icon view" text="view"/>
                                </td>
                            </tr>
                         </#list>
                        </tbody>
                    </table>
                    <div class="main_list_opera">
	                    <@ctl.page page=criteria.page />
	                </div>
                </div>            
            </div>
        </div>
       </form> 
    </div>
</div>



<div class="popsize_l fancybox_pop" id="cust_edit_pop"></div>
<div class="popsize_l fancybox_pop" id="cust_edit_pop"></div>
<div class="popsize_l fancybox_pop" id="cust_edit_pop"></div>

<script type="text/javascript">
 $(function(){
 
 
   //禁用设置
    $("#ccc").click(function(){
        $.ajaxLoadFtl({
            url: "${taglibs.allctx}/user/disabled.htm",
            data: {"id":$(this).data=('id')},
            error: function (data, transport) {
                $("#cust_edit_pop").html("服务器繁忙");
            },
            success: function (map) {
                $("#cust_edit_pop").html(map.result);
                $.fancybox.open($("#cust_edit_pop"),{"title":"disable","afterLoad" : function(){
                    this.inner.addClass("heightauto");
                }});
                $(".btn_cancel").click(function() {
                    $.fancybox.close();
                });
            }
        });  
    });   
    
     // 用户电话设置
    $("#bbb").click(function(){
        $.ajaxLoadFtl({
            url: "${taglibs.allctx}/user/setphone.htm",
            data: {"id":$(this).data=('id')},
            error: function (data, transport) {
                $("#cust_edit_pop").html("服务器繁忙");
            },
            success: function (map) {
                $("#cust_edit_pop").html(map.result);
                $.fancybox.open($("#cust_edit_pop"),{"title":"Phone set","afterLoad" : function(){
                    this.inner.addClass("heightauto");
                }});
                $(".btn_cancel").click(function() {
                    $.fancybox.close();
                });
            }
        });  
    });   
        
    // 用户邮箱设置
    $("#aaa").click(function(){   
        $.ajaxLoadFtl({
            url: "${taglibs.allctx}/user/setemail.htm",
            data: {"id":$(this).data("id")},
            error: function (data, transport) {
                $("#cust_edit_pop").html("服务器繁忙");
            },
            success: function (map) {
                $("#cust_edit_pop").html(map.result);
                $.fancybox.open($("#cust_edit_pop"),{"title":"Email set","afterLoad" : function(){
                    this.inner.addClass("heightauto");
                }});
                $(".btn_cancel").click(function() {
                    $.fancybox.close();
                });
            }
        });
    });
 });
 
</script>
</@cl.html>