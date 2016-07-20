<@cl.html title="内容管理_广告管理">
    <div class="col_main">
        <div class="main_hd" id="main_hd">
            <div class="clearfix">
                <h2>Advertisement Detail Manage</h2>
            </div> 
         </div>
        <div class="main_bd">
            <div class="main_list">
<#--表单在此--><form action="updateOrder.htm" method="POST">
                <div class="form_wrp">
                    <div class="nowrap_table">
                        <table class="table table_bgfff table-hover form_wrp_padding" cellpadding="0" cellspacing="0">
                            <thead class="thead">
                                <tr>
                                    <th class="table_cell tl">Order</th>
                                    <th class="table_cell tl">Title</th>
                                    <th class="table_cell tl">Status</th>
                                    <th class="table_cell tr">Operate</th>
                                </tr>
                            </thead>
                            <tbody class="tbody" id="">
                        	
                        	<#assign i=0 /><#-- 定义排序对象，用户将当前记录的ID和orderNum，赋值到同一个Advertisement实体中 -->
                            <#list dto.advertisings as advertising>
                                <tr class="ad_tit_th">
                                    <td class=" table_cell tl">
                                        <span class="">${advertising.adverName}</span>
                                        <#-- 这里code属性为广告栏目的编码，value属性为广告栏目的名称，用于广告内容添加界面时隐藏域的值 -->
                                        <span class="menu_add" code="${advertising.adverCode}" value="${advertising.adverName}"></span>
                                    </td>
                                    <td class=" table_cell tl"></td>
                                    <td class=" table_cell tl"></td>
                                    <td class=" table_cell tr"></td>
                                </tr>
                                <#list dto.adLists[advertising_index] as advertisement>
                                <tr class="">
                                    <td class=" table_cell tl">
                                        <div class="orderby_input">
                                           <input type="hidden" name="dto.advertisements[${i}].id" value="${advertisement.id}">
                                           <@ctl.text name="dto.advertisements[${i}].orderNum" value="${advertisement.orderNum}" validate={"maxlength":"3","min":"1","max":"999"}/>
                                        </div>
                                    </td>
                                    <td class=" table_cell tl">${advertisement.title}</td>
                                    <td class=" table_cell tl">
                                        <@ctl.statusButton status="${(advertisement.status)}" isView="true" href="javascript:;"/>
                                    </td>
                                    <td class=" table_cell tr">
                                        <a name="ad_edit" advertising="${advertising.id}" advertisement="${advertisement.id}" href="javascript:void(0);" title="modify" class="operate_icon edit fancybox">modify</a>
                                        <@ctl.delButton href="delete.htm?dto.advertisement.id=${advertisement.id}" title="delete" text="delete"/>
                                    </td>
                                </tr>
                                <#assign i=i+1 />
                                </#list>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                    <div class="main_list_opera">
                        <div class="form_btn">
                            <@ctl.submit title="save order" class="btn" text="save order"/>
                        </div>
                    </div>
   <#--表单在此--></form>
                </div>
            </div>
        </div>
    </div>
    <div class="popsize_l fancybox_pop" id="editct"></div>
      <script type="text/javascript">
            $(function(){
                $(".menu_add").click(function(){
                    var params={"dto.advertisement.adverCode":$(this).attr("code"),"dto.advertisement.title":$(this).attr("value")}
                      $.ajaxLoadFtl({
                        type: "POST",
                        dataType: "html",
                        url: "add.htm",
                        data:params,
                        error: function (data, transport) {
                        },
                        success: function (response) {
                           $("#editct").html(response);
                           $.fancybox.open($("#editct"),{"title":"add","afterLoad" : function(){
								this.inner.addClass("overflow-auto");
							}});
                        }
                    });
                });
                
                $("body").on("click","#cancel",function(){
                	$.fancybox.close();
                });
                $("a[name='ad_edit']").on("click",function(){
                   $.ajaxLoadFtl({
                        type: "POST",
                        dataType: "html",
                        url: "edit.htm",
                        data:{
                            "dto.advertisement.id":$(this).attr("advertisement"),
                            "dto.advertising.id":$(this).attr("advertising")
                         },
                        error: function (data, transport) {
                        },
                        success: function (response) {
                           $("#editct").html(response);
                           $.fancybox.open($("#editct"),{"title":"modify","afterLoad" : function(){
								this.inner.addClass("overflow-auto");
							}});
                        }
                    });
                 });
            });
        </script>
</@cl.html>