<@cl.html title="内容管理_广告位管理">
        <div class="col_main">
            <div class="main_hd" id="main_hd">
                <div class="clearfix">
                    <h2><@ctl.linkButton href="#ad_add_pop" isFancybox="true" title="add" text="add"/>Advertisement Banner Manage</h2>
                </div> 
             </div>
            <div class="main_bd">
                <div class="main_list">
                    <div class="nowrap_table">
                        <table class="table">
                            <thead class="thead">
                                <tr>
                                    <th class="table_cell tl">Banner name</th>
                                    <th class="table_cell tl">Banner code</th>
                                    <th class="table_cell tl">Status</th>
                                    <th class="table_cell tr">Operate</th>
                                </tr>
                            </thead>
                            <tbody class="tbody" id="">
                            <#list advertisings as advertising>
                                <tr class="level_first" id="level001" data-status="open">
                                    <td class=" table_cell">${advertising.adverName}</td>
                                    <td class=" table_cell">${advertising.adverCode}</td> 
                                    <td class=" table_cell">
                                        <@ctl.statusButton status="${(advertising.status)}" isView="true" href="javascript:;"/>
                                    </td>
                                    <td class=" table_cell tr">
                                        <a name='ad_edit' advertising="${advertising.id}" href="javascript:void(0);" title="modify" class="operate_icon edit fancybox">modify</a>
                                        <@ctl.delButton href="delete.htm?advertising.id=${advertising.id}" title="delete" text="delete"/>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- 广告位添加 -->
  <#-- ***************************************************************** add   BEGIN ************************************************** -->
        <div class="popsize_l fancybox_pop" id="ad_add_pop">
<#--表单在此--><form action="save.htm" method="POST">
                <div class="form_wrp">
                    <table class="table">
                        <tbody>
                            <tr id="">
                                <td class="size1of1" colspan="2">
                                    <label class="frm_label">Banner name:</label>
                                    <div class="frm_controls">
                                        <@ctl.text name="advertising.adverName" required="true" fieldName="Banner name" validate={"maxlength":"20","minlength":"1"}/>
                                    </div>
                                </td>
                            </tr>
                            <tr id="">
                                <td class="size1of1" colspan="2">
                                    <label class="frm_label">Banner code:</label>
                                    <div class="frm_controls">
                                        <@ctl.text name="advertising.adverCode" required="true" fieldName="Banner code" validate={"maxlength":"18","minlength":"6","remote":"checkAdvertisingCode.htm"}/>
                                    </div>
                                </td>
                            </tr>
                            <tr id="">
                                <td class="size1of1" colspan="2">
                                    <label class="frm_label">Status:</label>
                                    <div class="frm_controls ">
                                        <@ctl.radiobuttonlist groupCode="status" dataType="BizCode" name="advertising.status" value="1"/>
                                    </div>
                                </td>
                            </tr>
                            <tr class="last">
                                <td class="size1of1" colspan="2">
                                    <label class="frm_label">Remark:</label>
                                    <div class="frm_controls">
                                        <@ctl.textarea name="advertising.remark" fieldName="Remark" validate={"maxlength":"1000"}/>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="form_btn">
                        <@ctl.submit title="save" text="save"/>
                        <@ctl.linkButton href="javascript:;" id="cancel" title="cancel" text="cancel"/>
                    </div>
                </div>    
            </form><#--表单在此-->
        </div>
        <#-- ***************************************************************** add  END ************************************************** -->
        <div class="popsize_l fancybox_pop" id="editct"></div>
        <script type="text/javascript">
            $(function(){
                $("#cancel").on("click",function(){
                    $.fancybox.close();
                });
                
                $("a[name='ad_edit']").on("click",function(){
                   $.ajaxLoadFtl({
                        type: "POST",
                        dataType: "html",
                        url: "edit.htm",
                        data:{
                            "advertising.id":$(this).attr("advertising"),
                         },
                        error: function (data, transport) {
                        },
                        success: function (response) {
                           $("#editct").html(response);
                           $.fancybox.open($("#editct"),{"title":"modify"});
                        }
                    });
                 });
            });
        </script>
</@cl.html>