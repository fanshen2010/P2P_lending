<@cl.html title="后台管理_新增融资" css="pagination218878.css" >
			<div class="col_main">
                    <div id="main_hd" class="main_hd">
                    	<div class="clearfix">
                            <#if updateDistinguish?? &&updateDistinguish =="1">
                    			 <h2><a class="btn btn_primary" href="${taglibs.ctx}/loan/myloans/myloans.htm">return</a>modify</h2>
                    		<#else>
                    			 <h2>New Loan </h2>
                            </#if>
                        </div> 
                     </div>
                     <div class="main_bd">
                    	<div class="tab" id="originalTab">
                        	<ul class="tab_navs tab-hd position_tab title_tab"><#--<i class="tab_pass">-->
                                <li class="tab-hd-con tab_nav first active"><a href="javascript:;">Basic Info</a><i id="base_infoi" class="<#if export??&& export=="1">tab_pass<#elseif updateDistinguish?? && updateDistinguish=="1">tab_pass<#else>tab_wrong</#if>"></i></li>
                                <li class="tab-hd-con tab_nav " ><a href="javascript:;">Job Info</a><i id="job_infoi" class="<#if export??&& export=="1">tab_pass<#elseif updateDistinguish?? && updateDistinguish=="1">tab_pass<#else>tab_wrong</#if>"></i></li>
                                <li class="tab-hd-con tab_nav " ><a href="javascript:;">Loan info</a><i  id="projects_infoi" class="tab_wrong"></i></li>
                            </ul>   
                            <div class="tab-bd">  
                    			<form id="loanFrm" action="${taglibs.ctx}/loan/apply/personalApply.htm"  method="POST" enctype="multipart/form-data">
                    				<@ctl.token /> 
	                            	<#include "${taglibs.ftlctx}/loan/apply/personal/basic.ftl" />
	                            	<#include "${taglibs.ftlctx}/loan/apply/personal/job.ftl" />
	                            	<#include "${taglibs.ftlctx}/loan/apply/viewtab/loanInfo.ftl" />
	                            	<#include "${taglibs.ftlctx}/loan/apply/viewtab/fancybox.ftl"/>
                            	</form>
                            </div>
                        </div>
                    </div>
                    <div id="foot_operate_area">
                        <div id="bottomOperate" class="info_operate info_operate_fixed clearfix" >
                            <#if updateDistinguish !="1">
	                            <div class="fl">
	                            	<@ctl.linkButton id="returnButton"  text="previous"   />
	                            </div>
                            </#if>
                            <div class="fr">
                            	<span class="btn btn_primary btn_input ">
                            	<@ctl.button id="form_submit" class="js_btn"  text="submit"   />
                            	</span>
                            </div>
                        </div>
                    </div>
                    
                </div>
			    <!--- financing application submit -->
			    <div class="financ_apl_pop fancybox_pop" id="financ_apl_submit">
			    	<p class="tc ptb20">Submit loan apply？</p>
			        <p class="form_btn">
			            <@ctl.linkButton  id="a_submit"  text="confirm"   />
			            <@ctl.linkButton  id="a_cancel"  class="btn"  text="cancel"   />
			        </p>
			    </div>
			    
			    <div class="financ_apl_pop fancybox_pop" id="financ_return_button">
			    	<p class="tc ptb20">>Previous step will lost the information you filled, continue or not？</p>
			        <p class="form_btn">
			            <@ctl.linkButton  href="${taglibs.allctx}/loan/apply/personal.htm" id="return_submit"  text="confirm"   />
			            <@ctl.linkButton  id="return_cancel" class="btn"  text="cancel"   />
			        </p>
			    </div>
                <#include "${taglibs.ftlctx}/loan/apply/personal/javascript.ftl"/>
                <#include "${taglibs.ftlctx}/loan/apply/viewtab/commonscript.ftl"/>
 </@cl.html>