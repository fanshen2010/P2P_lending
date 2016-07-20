$(document).on("click","table.table[id!='selectBanktable'] tbody tr",function(){
	$('#user_name', window.parent.document).val($(this).find("td:eq(0)").text());
	$('#user_id', window.parent.document).val($(this).find("td:eq(0) input:hidden").val());
	
	$.ajax({
		url:"financingInfo.htm",
		data:{
				"userId":$(this).find("td:eq(0) input:hidden").val(),
				"taglibsAllctx":$(this).find("td:eq(0) input:hidden").data("taglibs")
			},
		type: "GET",
		dataType: "json",
		async: true,
		success:function(json)
		{
			$("#tip",window.parent.document).html(json.tip);
			 $("#repaymentWay",window.parent.document).replaceWith(json.BackLoan);
			 $("#repayTypesel",window.parent.document).selectpicker();
			 $("#repayTypesel",window.parent.document).removeAttr("data-validation-engine").attr("data-validation-engine","validate[required]");
			 $("#repayTypesel",window.parent.document).css({"display":"inline-block","position": "absolute","z-index": -999});
			 
			 $("#productSel",window.parent.document).replaceWith(json.msg);
			 $("#productId",window.parent.document).selectpicker();
			 $("#productId",window.parent.document).removeAttr("data-validation-engine").attr("data-validation-engine","validate[required]");
			 $("#productId",window.parent.document).css({"display":"inline-block","position": "absolute","z-index": -999});
			 /*IE8下freemarker插入html后js失效，select无法选中，采用手动选中和执行js方法*/
			 $("#productSel",window.parent.document).find("[href='#']").each(function(i,val){
				 $(this).attr("onClick","selected('"+i+"')");
			 });
			 $("#repaymentWay",window.parent.document).find("[href='#']").each(function(i,val){
				 $(this).attr("onClick","selectrepay('"+i+"')");
			 });
			 /*end*/

			 $("#financingInfo",window.parent.document).html(json.result);
			 $("#repayTypesel",window.parent.document).find("option[value='']").attr("selected",true);
			 $("#repayTypesel",window.parent.document).find("option[value='']").siblings().attr("selected",false);
			 $("#repayTypesel",window.parent.document).next(".bootstrap-select").find(".filter-option").text("请选择");
			 
			 $("#productId",window.parent.document).find("option[value='']").attr("selected",true);
			 $("#productId",window.parent.document).find("option[value='']").siblings().attr("selected",false);
			 $("#productId",window.parent.document).next(".bootstrap-select").find(".filter-option").text("请选择融资产品");
			 $("#repayName",window.parent.document).html("");
			 $("#repayContent",window.parent.document).html("");
			 if($("#vouch_row",window.parent.document).length > 0){
				 $("#productId",window.parent.document).attr("onchange","selectedChange(this.id);");
			 }
		},
		complete: function(){
			window.parent.$.fancybox.close();
		}
	});
});