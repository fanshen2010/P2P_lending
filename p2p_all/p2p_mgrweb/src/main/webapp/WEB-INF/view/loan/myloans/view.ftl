<@cl.html title="后台管理_查看融资">
                <div class="col_main">
                    <div id="main_hd" class="main_hd">
                    	<div class="clearfix">
                            <h2><a class="btn btn_primary" href="myloans.htm">return</a>view</h2>
                        </div> 
                    </div>
                    <div class="main_bd">
                    <#if loanDto.projectInfoDto.loanStatus=="03">
	                    	<div class="form_wrp pb20">
	                            <table class="table" cellpadding="0" cellspacing="0">
	                                <tbody class="tbody" id="">
	                                    <tr class="last">
	                                        <td class="border_bottom_none size1of1" colspan="2">
	                                            <label class="frm_label">驳回原因:</label>
	                                            <div class="frm_controls">
	                                                <span class="frm_val" for="">
	                                                <#if (loanDto.lstRecord)??>
	            										${loanDto.lstRecord.get(0).remark}
	        										</#if>
	        										</span>
	                                            </div>	
	                                        </td>
	                                    </tr>
	                                </tbody>
	                            </table>
	                        </div>
                        </#if>
                    	<div class="tab">
                        	<ul class="tab_navs tab-hd  position_tab title_tab mb20" id="originalTab">
                                <li class="tab-hd-con tab_nav first active"><a href="javascript:;">Loan Info</a></li>
                                <li class="tab-hd-con tab_nav " ><a href="javascript:;"> <#if loanDto.projectInfoDto.loanType=="1">Enterprise<#else>Personal</#if> Info</a></li>
                            </ul>
                            <div class="tab-bd">
                            	<div class="tab-bd-con active">
                                	<#include "${taglibs.ftlctx}/loan/viewtab/loanProjectInfo.ftl" />
                                </div>
                            	<div class="tab-bd-con hide">
                                	<@bizTag.loanType  type=loanDto.projectInfoDto.loanType  eFtl="/loan/viewtab/enterpriseInfo.ftl" pFtl="/loan/viewtab/personalInfo.ftl" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
 </@cl.html>