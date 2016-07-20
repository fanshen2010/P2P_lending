<@cl.html title="后台管理_职务管理_新增">
                <div class="col_main">
                    <div id="main_hd" class="main_hd"> 
                        <div class="clearfix">
                            <h2><@ctl.linkButton href="index.htm" title="return" text="return"/>add</h2>
                        </div>
                    </div>
<#-- 表单在此 --><form method="POST" action="save.htm">
                    <div class="main_bd">
                        <div class="form_wrp">
         
                            <table class="table" cellpadding="0" cellspacing="0">
                                <tbody class="tbody" id="">
                                    <tr>
                                        <td class="size1of1">
                                            <label class="frm_label">Position:</label>
                                            <div class="frm_controls">
                                                <@ctl.text name="postDto.post.postName" value="" required="true" validate={"maxlength":"64","remote":"checkPostName.htm"} fieldName="Position"/>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="border_bottom_none size1of1">
                                            <div class="">
                                                <label class="frm_label">Choose role:</label>
                                                <div class="frm_controls checkbox_row">
                                                    <span class="uniformjs">
                                                    <#-- 显示顺序有问题 -->
                                                       <@ctl.checkboxlist name="postDto.roleIds" groupCode="RolesQuery" dataType="BizQuery"/>
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="purview_preview">
                                                <div class="purview_preview_tit">
                                                    <i class="icon16_common search_gray">search</i>
                                                    <span class="frm_val">Access menu</span>
                                                    <a id="open_tip" class="frm_val open_tip" href="javascript:;" title="" >more</a>
                                                    <i class="open_icon down"></i>
                                                </div>
                                                <div id="purview_preview_tip" class="purview_preview_tip popover normal_flow pos_left page_global_tips" >
                                                    <i class="popover_arrow popover_arrow_out"></i>
                                                    <i class="popover_arrow popover_arrow_in"></i>
                                                    <div class="popover_inner">
                                                        <div class="popover_content">
                                                            <div class="system_role_manage">
                                                            <#-- ID用于控制DIV的显示和隐藏，点击角色列表后，DIV的内容有AJAX控制 -->
                                                               <div id="menus" class="hide">
                                                                  <@ctl.checkboxlist listData=postDto.menuMap disabled="true"/>
                                                               </div>
                                                            </div>  
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="border_bottom_none size1of1">
                                            <label class="frm_label">Status:</label>
                                            <div class="frm_controls">
                                               <@ctl.radiobuttonlist groupCode="status" value="1" name="postDto.post.validFlag" dataType="BizCode"/>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr class="last">
                                        <td class=" size1of1">
                                            <label class="frm_label">Description:</label>
                                            <div class="frm_controls">
                                                <@ctl.textarea name="postDto.post.postMem" class="frm_textarea" value=""  validate={"maxlength":"1000"}/>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="foot_operate_area">
                        <div id="bottomOperate" class="info_operate info_operate_fixed clearfix" >                                                
                            <div class="fr">
                                <@ctl.button id="repayment_btn" type="submit" title="save" text="save"/>
                            </div>
                        </div>
                    </div>
               <form><#-- 表单结束 -->
            </div>
<script type="text/javascript">
            $(function(){
                //tab
                $(window).scroll(function(){
                    var bottom = $(document).height()-$("#foot_operate_area").offset().top-$("#foot_operate_area").height();
                    var scroll_bottom = $(document).height()-$(document).scrollTop()-$(window).height();
                    var scroll_top = $(document).scrollTop()+$(window).height(); 
                    var body_top = $("#body").offset().top+$("#body").height(); 
                    if( scroll_top <= body_top +31 ){
                        $("#bottomOperate").addClass("info_operate_fixed");
                    }
                    else{
                        $("#bottomOperate").removeClass("info_operate_fixed");
                    }
                });
                //
                $("#keyInput").keyup(function(){
                    if(event.keyCode == 13){
                        $(".role_name_area").removeClass("hide").slideDown();
                    }
                });
                //purview_preview
                $("#open_tip, .open_icon").click(function(){
                    if($("#open_tip").text()=="more"){
                        $("#purview_preview_tip").slideDown();
                        $("#open_tip").text("收起");
                        $(".open_icon").removeClass("down").addClass("up");
                    }
                    
                    else if ($("#open_tip, .open_icon").text()=="收起"){
                        $("#purview_preview_tip").slideUp();
                        $("#open_tip").text("more");
                        $(".open_icon").removeClass("up").addClass("down");
                    }
                });
                
                <#-- zhushanyu 2015-04-02 14:53  显示或隐藏角色对应的菜单DIV -->
                // 对角色列表（checkbox）添加点击事件
                $("input[name='postDto.roleIds']").click(function(){
                   var roleIds = [];   // 存储已经选中的角色ID
                   var check = $("input[name='postDto.roleIds']:checked"); // 存储已经选中的角色对应的checkbox对象
                   $(check).each(function(index){       // 循环获取已经选中的角色ID
                       roleIds[index] = $(this).val();
                   });
                   
                   $.ajaxLoadFtl({             // 利用ajax，获取选中角色对应的菜单列表，并去除重复的菜单
                      type: 'POST',
                      url: 'assembleMenus.htm',      //  对应的action
                      dataType:"html",
                      data: {
                          // postDto.roleIds 是List<String>对象，在这里仅仅利用了第一个空间
                          // roleIds.join(",") 是将数组转换成字符串，并以","分割
                          // 注意:当数组为空时,postDto.roleIds存储的第一个元素是长度为0的字符转，不是null
                          'postDto.roleIds': roleIds.join(","),   
                      },
                      success: function(response){
                         // 将生成的组合后菜单列表替换原有的菜单列表
                        $("#menus").html(response);
                        // 如果选中的元素大于0，则显示菜单列表，否则隐藏
                        if($(check).size()>0){
                           $("#menus").show();
	                    }else{
                           $("#menus").hide();
	                    }
                      },
                      error: function (data, transport) {
                          // 错误消息
		              },
                   });
                });<#--  显示或隐藏角色对应的菜单DIV,  END   -->
        });
        </script>
 </@cl.html>