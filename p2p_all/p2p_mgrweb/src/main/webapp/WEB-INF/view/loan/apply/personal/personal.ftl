<@cl.html title="后台管理_新增融资">
	<div class="col_main">
            <div class="main_hd">
            	<div class="clearfix">
                    <h2>New Loan </h2>
                </div>
            </div>
            <div class="main_bd">
                <div class="highlight_box">
                	<div class="clearfix">
                	<form id="loanfrm"  method="POST">
                        <div class="fl" >
                            <span>Please fill in Personal ID number:</span>
                            <span class="frm_input_box search with_del append ">
                                <a class="del_btn" href="javascript:" id="searchCloseBt">
                                    <i class="icon_search_del"></i>&nbsp;
                                </a>
                                <a id="search_btn" href="javascript:" class="frm_input_append"><i class="icon16_common search_gray">search</i>&nbsp;</a>
                                <input id="keyInput" type="text" placeholder="ID number" value="" name="customer.creditCode" class="frm_input">
                                <input  type="hidden"  name="customer.type" value="1"/>
                                
                            </span>
                        </div>
                        </form>
                        <a href="choose.htm" class="btn btn_primary fr" id="">return</a>
                    </div>
                </div>
                <div id="notice_y" class="hide">
                </div>
            </div>
        </div>	 
        <#include "${taglibs.ftlctx}/loan/apply/viewtab/script.ftl" >  
 </@cl.html>