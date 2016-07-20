<@cl.html title="后台管理_新增组织机构">
<div class="col_main">
    <div class="main_hd">
    	<div class="clearfix">
            <h2><@ctl.linkButton href="index.htm" title="return" text="return"/>add</h2>
        </div>
    </div>
    <div class="main_bd ptb20 ">
    	<div class="highlight_box">
        	<div class="clearfix">
                <div class="fl" >
                    <span>Organization Type:</span>
                    <span class="frm_select_picker" >
                        <select id="organize_type" class="selectpicker">
                            <option value="">select</option>
                            <option value="organize_type_department">Department</option>
                            <option value="organize_type_branch">Branch</option>
                            <option value="organize_type_guarantee">Guarantee</option>
                        </select>
                    </span>
                </div>
            </div>
        </div>
        <div class="">
        
            <#include "${taglibs.ftlctx}/system/organize/branch/branch_add.ftl" >
            <#include "${taglibs.ftlctx}/system/organize/depart/depart_add.ftl" >
       		<#include "${taglibs.ftlctx}/system/organize/guarantee/guarantee_add.ftl" >
        </div>    	
    </div>
</div>
<script type="text/javascript">
	$("#organize_type").change(function(){
		var processVal = $(this).find("option:selected").val();
		if(processVal=="organize_type_department"){
			$("#organize_type_department").show();
			$("#organize_type_branch").hide();
			$("#organize_type_guarantee").hide();
			$("#department").attr("value",1);
		}
		 if(processVal=="organize_type_branch"){
			$("#organize_type_branch").show();
			$("#organize_type_department").hide();
			$("#organize_type_guarantee").hide();
			$("#branch").attr("value",2)
		}
		  if(processVal=="organize_type_guarantee"){
			$("#organize_type_guarantee").show();
			$("#organize_type_branch").hide();
			$("#organize_type_department").hide();
			$("#guarantee").attr("value",3)
		}
	});
	
	
	
</script>
</@cl.html>