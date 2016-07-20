<@cl.html title="">
	    <div class="col_main">
	    	<form action=""  id="dispose" method="POST">
	        <div id="main_hd" class="main_hd">
	        	<div class="clearfix">
	                <h2><@ctl.linkButton  class="btn btn_primary"  href="effective.htm"  text="return"  />Edit</h2>
	            </div> 
	         </div>
	         <div class="main_bd">
	             <div class="form_wrp loan_repayment">
	                <table class="table noborder" cellpadding="0" cellspacing="0">
	                    <tbody class="tbody" id="">
	                        <tr>
	                            <td class="border_bottom_none size1of1" colspan="2">
	                                <label class="frm_label">Loan status:</label>
	                                <div class="frm_controls">
	                                    <div class="frm_val mark"for><@bizTag.loanStatus statusCode=loanDto.projectInfoDto.loanStatus loanAmount=loanDto.projectInfoDto.loanAmount investedAmount=loanDto.projectInfoDto.currentInvestedShare loanEndTime=loanDto.projectInfoDto.loanEndTime /></div>
	                                </div>	
	                            </td>
	                        </tr>
	                        <tr>
	                            <td class="border_bottom_none size1of1" colspan="2">
	                                <label class="frm_label">Total amount:</label>
	                                <div class="frm_controls">
	                                    <div class="frm_val"><@h.numf value=(loanDto.projectInfoDto.loanAmount)!/>dollar</div>
	                                </div>	
	                            </td>
	                        </tr>
	                        <tr>
	                            <td class="border_bottom_none size1of1" colspan="2">
	                                <label class="frm_label">invested amount:</label>
	                                <div class="frm_controls">
	                                    <div class="frm_val"><@h.numf value=(loanDto.projectInfoDto.currentInvestedShare)!/>dollar</div>
	                                </div>	
	                            </td>
	                        </tr>
	                        <tr>
	                            <td class="border_bottom_none size1of1" colspan="2">
	                                <label class="frm_label">remaining amount:</label>
	                                <div class="frm_controls">
		                                <div class="frm_val"><@h.numf value=(loanDto.projectInfoDto.currentSurplusShare)!/>dollar</div>
	                                </div>	
	                            </td>
	                        </tr>
	                    </tbody>
	                </table>
	            </div>
	            <div class="tab">
	                <ul class="tab_navs tab-hd  position_tab title_tab">
	                    <li class="tab-hd-con tab_nav first active"><a href="javascript:;">Loan Info</a></li>
	                    <li class="tab-hd-con tab_nav " ><a href="javascript:;">Invest Record</a></li>
	                </ul>
	                <div class="tab-bd">
	                	<div class="tab-bd-con active">
	                		<#include "${taglibs.ftlctx}/loan/viewtab/loanProjectInfo.ftl" />
	                	</div>
	                    <div class="tab-bd-con hide">
	                    	<#include "${taglibs.ftlctx}/loan/viewtab/investInformation.ftl" />
	                    </div>
	                </div>
	            </div>
	        </div>
	        <div id="foot_operate_area">
	            <div id="bottomOperate" class="info_operate info_operate_fixed clearfix" >
	                <@bizTag.loanStatusButton statusCode=loanDto.projectInfoDto.loanStatus loanAmount=loanDto.projectInfoDto.loanAmount investedAmount=loanDto.projectInfoDto.currentInvestedShare loanEndTime=loanDto.projectInfoDto.loanEndTime />
	            </div>
	        	<div class="foot_operate_fixed"></div>
	        </div>
	        <input  type="hidden"  id="loanCode" name="loanCode" value="${(loanDto.projectInfoDto.loanCode)!}" />
	        <input  type="hidden"  id="loanId" name="loanId" value="${(loanDto.id)!}" />
		</form>
		<#include "${taglibs.ftlctx}/loan/effective/flowPop.ftl" />
	    <#include "${taglibs.ftlctx}/loan/effective/loanPop.ftl" />
		 <div class="popsize_l company_addInfo_pop" id="audit_view">
	        
		</div>
 </div>   
     <script type="text/javascript">
     	//tab
		$(window).scroll(function(){
			var bottom = $(document).height()-$("#foot_operate_area").offset().top-$("#foot_operate_area").height();
			var scroll_bottom = $(document).height()-$(document).scrollTop()-$(window).height();
			var scroll_top = $(document).scrollTop()+$(window).height(); 
			var body_top = $("#body").offset().top+$("#body").height(); 
			if( scroll_top <= body_top +41 ){
				$("#bottomOperate").addClass("info_operate_fixed");
				}
			else{
				$("#bottomOperate").removeClass("info_operate_fixed");
				}
		});
   		// 启用
   		$("#start_btn").click(function(){
   			$("#dispose").attr("action","${taglibs.allctx}/loan/effective/start.htm");
        	$("#dispose").submit();
	   	});
	   	// 禁用
	 	$("#forbidden_btn").click(function(){
	 		$("#dispose").attr("action","${taglibs.allctx}/loan/effective/forbidden.htm");
        	$("#dispose").submit();
	   	});
 	// 流标审核
 	$("#flow_btn").click(function(){  
     	$.fancybox({
        	'href' : '#flow_pop',
			'title' : 'Fail Loan Confirm'
    	});
 	});
 	// 放款审核
 	$("#loan_btn").click(function(){  
     	$.fancybox({
        	'href' : '#loan_pop',
			'title' : 'Effect Loan Confirm'
    	});
 	});
	</script>
</@cl.html>