<div id="main_hd" class="main_hd">
	<div class="clearfix">
	    <h2><a id="" class="btn btn_primary" href="index.htm">return</a>set</h2>
	</div> 
</div>
<div class="main_bd">
    <div class="main_list">
        <div class="main_list">
	    	<div class="form_wrp">
	    		<form method="post" action="businessSave.htm">
		            <table class="table" cellpadding="0" cellspacing="0">
		                <tbody class="tbody" id="">
		                    <tr>
		                        <td class=" table_right_border size1of1">
		                            <label class="frm_label">Title:</label>
		                            <div class="frm_controls">
		                                <span class="frm_val">${(messageTemplete.msgTitle)!}</span>
		                            </div>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class=" table_right_border size1of1">
		                            <label class="frm_label">Content:</label>
		                            <div class="frm_controls">
		                                <span class="frm_val">${(messageTemplete.msgTeml)!}</span>
		                            </div>
		                        </td>
		                    </tr>
		                    <tr class="last">
		                        <td class=" table_right_border size1of1">
		                            <label class="frm_label">Choose Staff:</label>
		                            <div class="frm_controls edit_recipient">
		                                <div class="edit_recipient_left">
		                                    <div class="edit_recipient_hd clearfix">
		                                    	<span class="fl tit">All Staff</span>
		                                        <span class="fr frm_input_box search with_del append edit_recipient_search">
		                                            <a id="search_btn" href="javascript:" class="frm_input_append"><i class="icon16_common search_gray">search</i>&nbsp;</a>
		                                            <input id="keyInput" type="text" placeholder="Name" value="${(realName)!}" class="frm_input">
		                                        </span>
		                                    </div>
		                                    <div id="recipient_list_left" class="recipient_list">
		                                    	<ul>
		                                        	<li class="th">
		                                            	<span class="name">Name</span>
		                                                <span class="organization">Department</span>
		                                                <span class="job">Position</span>
		                                                <span class="reci_oper">Operate</span>
		                                            </li>
		                                            <#assign a=0>
		                                            <#list messageReceiverDto.pfmUsers as pfmUsers>
			                                            <li class="">
			                                            	<span class="name">${(pfmUsers.realName)!}</span>
			                                                <span class="organization">${(pfmUsers.departName)!}</span>
			                                                <span class="job">${(pfmUsers.postName)!}</span>
			                                                <span class="reci_oper"><a id="" class="reci_oper_arrow" href="javascript:;"></a></span>
			                                                <input type="hidden" name="pfmuserid" value="${(pfmUsers.id)!}">
			                                            </li>
			                                            <#assign a=a+1>
		                                            </#list>
		                                        </ul>
		                                    </div>
		                                </div>
		                                <div class="recipient_arrow">
		                                	<a id="ltor" class="ltor" href="javascript:;"></a>
		                                </div>
		                                <form method="post" action="businessSave.htm">
		                                <div class="edit_recipient_right">
										    <div class="edit_recipient_hd">
		                                    	<span class="tit">Receive Staff</span>                                                           
		                                    </div>                                                       
		                                    <div id="recipient_list_right" class="recipient_list">
		                                    	<ul>
		                                        	<li class="th">
		                                            	<span class="name">Name</span>
		                                                <span class="organization">Department</span>
		                                                <span class="job">Position</span>
		                                                <span class="reci_oper">Operate</span>
		                                            </li>
		                                            <#list messageReceiverDto.messageUsers as messageUsers>
			                                            <li class="">
	                                                    	<span class="name">${(messageUsers.realName)!}</span>
	                                                        <span class="organization">${(messageUsers.departName)!}</span>
	                                                        <span class="job">${(messageUsers.postName)!}</span>
															<span class="reci_oper"><a id="" class="reci_oper_del" href="javascript:;"></a></span>
															<input type="hidden" name="receiveuserList" value="${(messageUsers.id)!}">
	                                                    </li>
	                                                    <#assign a=a+1>
		                                            </#list>
		                                            <input type="hidden" id="receiverId" name="receiverId" value="${(messageTemplete.id)!}">
		                                            <input type="hidden" id="realName" name="realName" value="">
		                                        </ul>
		                                    </div>
		                                </div>
		                            </div>
		                        </td>
		                    </tr>
		                 </tbody>
		            </table>
		            <div id="" class="form_btn" >
		                <div class="fr">
		                    <button id="receiver" type="submit" class="btn btn_primary" >save</button>
		                </div>
		            </div>
		        </form>
	        </div>
    	</div>
    </div>
</div>
