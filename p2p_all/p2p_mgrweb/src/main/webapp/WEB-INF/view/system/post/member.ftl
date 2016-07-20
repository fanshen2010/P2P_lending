<@cl.html title="后台管理_职务管理_成员设置">
                <div class="col_main">
                    <div id="main_hd" class="main_hd"> 
                        <div class="clearfix">
                            <h2><a class="btn btn_primary" href="index.htm">return</a>Staff Set</h2>
                        </div>
                    </div>
                    <div class="main_bd">
                        <div class="tab">
                            <div class="tab-bd">
                                    <div class="lr_area role_area clearfix">
                                        <div class="left item">
                                            <div class="item_hd">
                                                <strong class="item_hd_title">Staff List</strong>
                                            </div>
                                            <div class="item_bd">
                                                <#-- 这里使用JS控制 no_list ，display_list 的显示，判断条件为tbody#flag的内容是否为空-->
                                                <div id="no_list" class="notice_area hide">No data</div>
                                                <div id="display_list" class="form_wrp">
                                                    <div class="">
                                                        <table class="table form_wrp_padding table-striped table-hover" cellpadding="0" cellspacing="0">
                                                            <thead class="thead">
                                                                <tr>
                                                                    <th class="table_cell tl">User name</th>
                                                                    <th class="table_cell tl">Name</th>
                                                                    <th class="table_cell tl">Position</th>
                                                                    <th class="table_cell tr">Operate</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody class="tbody" id="flag">
                                                            	<#if postDto.users?has_content>
                                                                <#list postDto.users as user>
                                                                    <tr>
                                                                        <td class=" table_cell tl">${(user.userName)}</td>
                                                                        <td class=" table_cell tl">${(user.realName)}</td>
                                                                        <td class=" table_cell tl">${(postDto.post.postName)}</td>
                                                                        <td class=" table_cell tr">
                                                                           <@ctl.delButton href="delmember.htm?postDto.user.id=${(user.id)}" title="delete" text="delete" data={"success_callback":"member"}/>
                                                                        </td>
                                                                    </tr>
                                                                </#list>
                                                                <#else>
																  <tr><td colspan="4">No data</td></tr>
																</#if>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="right item">
                                            <div class="item_hd">
                                                <strong class="item_hd_title">add staff</strong>
                                            </div>
                                            <div class="item_bd">
                                                <p>
                                                    <span class="frm_input_box search with_del append role_area_search">
                                                        <a id="search_btn" href="javascript:" class="frm_input_append"><i class="icon16_common search_gray">search</i>&nbsp;</a>
                                                        <input id="search_ct" type="text" placeholder="业务人员姓名" value="${(postDto.user.realName)!}" class="frm_input">
                                                    </span>
                                                </p>
                                                <#-- AJAX填充内容，span标签仅仅为了设置内容的方便，没有样式和特别意义 -->
                                                <span id="menber"></span>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
      <script type="text/javascript">
        $(function(){
        
           // 点击搜索按钮时，AJAX获取没有职位的所有用户
            $("#search_btn").on("click",function(){
	            $.ajaxLoadFtl({
	                 type:"POST",
	                 url:"getmember.htm",
	                 dataType:"html",
	                 data:{
	                    "postDto.user.realName": $("#search_ct").val(),
	                    "postDto.post.postCd": '${(postDto.post.postCd)}'
	                 },
	                 success:function(response){
	                     $("#menber").html(response);
	                 },
	            });
            });
            
            //初始化界面时，调用点击事件
            $("#search_btn").trigger("click");  //让系统自动执行单击事件
            
            // 控制显示表格还是没有成员
            flag($("#flag").html());
            
            // 搜索框失去焦点时，为添加成员隐藏域设置搜索关键字
            // 由于点击新增成员后，关键字要求回显
            $("#search_ct").on("blur",function(){
                $("#keyword").val($(this).val());
            });
        });
        
        // 控制显示表格还是没有成员
        var flag = function(content){
            if($.trim(content) == ""){
                $("#no_list").show();
                $("#display_list").hide();
            }else{
                $("#no_list").hide();
                $("#display_list").show();
            }
        };
        
        // 执行删除后的回调函数
        var member = function(){
           // 删除成功后重新请求member，
           // 根据tbody#flag是否有内容来决定,控制显示表格还是没有成员
           $.ajaxLoadFtl({
                 type:"POST",
                 url:"member.htm",
                 dataType:"html",
                 data:{
                    "postDto.post.postCd": '${(postDto.post.postCd)}'
                 },
                 success:function(response){
                    var content = $(response).find("tbody#flag").html();
                    flag(content);
                 },
            });
            
            // 调用点击事件，获取没有职位的用户
           $("#search_btn").trigger("click");  //让系统自动执行单击事件
         };
     </script>
 </@cl.html>