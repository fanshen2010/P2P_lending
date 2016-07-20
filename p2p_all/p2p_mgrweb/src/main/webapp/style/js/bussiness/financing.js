$(function() {

	$("#select_user").fancybox({
		width : 580,
		minHeight : 660
	});
	$("#select_bank").fancybox({
		width : 580,
		minHeight : 340
	});
	$(".ultra_returned").fancybox();
	$(".fancybox").fancybox();

	$(document).on(
			"click",
			"#auditForm button:submit",
			function() {
				if ($(this).data("action") != undefined
						&& $(this).data("action") != null
						&& $(this).data("action") != "") {
					$("#auditForm").attr(
							"action",
							$("#auditForm").attr("action")
									+ $(this).data("action") + ".htm");
				}
			});

	/* 将融资信息中的利率拼接到项目说明书中的利率文本中 */
	var detail_rates_init = $("#detail_rates").val();
	$(document).on("change", "#rates", function() {
		$("#detail_rates").val(detail_rates_init + $(this).val() + "%");
	});

	/* 根据起止日期，计算天数差 */
	$(document).on(
			"change",
			"#date_from,#date_to",
			function() {
				if ($("#date_from").val() != "" && $("#date_to").val() != "") {
					var oDateFrom = new Date($("#date_from").val());
					var oDateTo = new Date($("#date_to").val());
					var iDays = parseInt(Math.abs(oDateTo - oDateFrom) / 1000
							/ 60 / 60 / 24); // 把相差的毫秒数转换为天数
					$("#loan_limit").text(iDays + "天");
				}
			});

	/* yyyy-mm-dd hh:mm:ss 日历 */
	$("#loanEndTime").datepicker({
		showSecond : true,
		timeFormat : "hh:mm:ss",
		duration : '',
		showTime : true,
		constrainInput : false,
		time24h : true,
		defaultH : 17,
		defaultM : 0,
		defatulS : 0
	});
	$("#postDate").datepicker({
		showSecond : true,
		timeFormat : "hh:mm:ss",
		duration : '',
		showTime : true,
		constrainInput : false,
		time24h : true,
		defaultH : 17,
		defaultM : 0,
		defatulS : 0
	});

	/* 校验 */
	$("#loanForm").validationEngine();

});

/**
 * safari浏览器中$("#"+id).val()取出的值为空,$(obj).find("[selected='selected']").attr("value")取值正确，
 * IE8浏览器中$("#"+id).val()取出的值正确,$(obj).find("[selected='selected']").attr("value")取值为空，
 */
function changeRepay(obj, url) {
	$(obj).validationEngine("validate");
	$("#repayName").html("");
	$("#repayContent").html("");
	var val = $(obj).val(); // IE8浏览器$(obj).val()取值才对，所以在$(obj).find("[selected='selected']").attr("value")取值为空时采用
	// alert(val);
	// alert($(obj).find("[selected='selected']").attr("value"));
	if (val == '') {
		val = $(obj).find("[selected='selected']").attr("value"); // IE8浏览器中$(obj).find("[selected='selected']").attr("value")取值为空
		if (val != '') {
			$(obj).removeAttr("data-validation-engine"); // safari中选择后仍校验为空，so..当有选中时去除校验
			$(".repayTypeselformError").remove(); // 同时去除可能已经出现的校验信息
		}
	}
	var userId = $("#user_id").val();
	if (val != "") {
		if (userId == "") {
			// $(obj).eq(0).attr("selected",true);
			$(obj).find("option[value='']").attr("selected", true);
			$(obj).find("option[value='']").siblings().attr("selected", false);
			$(obj).next(".bootstrap-select").find(".filter-option").text("请选择");
			alert("请先选择客户");
			return false;
		}
		var params = {
			userId : userId,
			payType : val
		};
		$
				.ajax({
					type : "POST",
					url : url + "/financing/addfinancing/checkUserAccount.htm",
					dataType : "json",
					data : params,
					success : function(json) {
						if (json.type == 0) {
							$(obj).find("option[value='']").attr("selected",
									true);
							$(obj).find("option[value='']").siblings().attr(
									"selected", false);
							$(obj).next(".bootstrap-select").find(
									".filter-option").text("请选择");
							$("#repayName").html("");
							$("#repayContent").html("");
						} else {
							if (json.type == 1) {
								if (json.status) {
									$("#repayName").html("支付用户名：");
									$("#repayContent").html(json.text);
								} else {
									alert(json.text);
									$(obj).find("option[value='']").attr(
											"selected", true);
									$(obj).find("option[value='']").siblings()
											.attr("selected", false);
									$(obj).next(".bootstrap-select").find(
											".filter-option").text("请选择");
									$("#repayName").html("");
									$("#repayContent").html("");
								}

							} else if (json.type == 2) {
								if (json.status) {
									$("#repayName").html("银行卡：");
									var h = '<input class="" onclick="openBankSelect();" type="text" id="card" readonly="true" data-validation-engine="validate[required]">';
									h = h
											+ '<input class=""  type="hidden" id="card_id" name="payBankId">';
									h = h
											+ '<span class="required-star">*</span>';
									h = h
											+ '<a id="select_bank" class="btn btn-primary fancybox.iframe" href="'
											+ url
											+ '/financing/addfinancing/selectBank.htm?userId='
											+ userId + '">选择银行卡</a>';
									$("#repayContent").html(h);
								} else {
									alert(json.text);
									$(obj).find("option[value='']").attr(
											"selected", true);
									$(obj).find("option[value='']").siblings()
											.attr("selected", false);
									$(obj).next(".bootstrap-select").find(
											".filter-option").text("请选择");
									$("#repayName").html("");
									$("#repayContent").html("");
								}
							}
						}
					}
				});
	}
}
function changeCompany(obj, url) {
	$("#repayName").html("");
	$("#repayContent").html("");
	var val = $(obj).val();
	if (val != "") {
		var params = {
			payType : val
		};
		$
				.ajax({
					type : "POST",
					url : url + "/system/company/checkUserAccount.htm",
					dataType : "json",
					data : params,
					success : function(json) {
						if (json.type == 0) {
							alert(json.text);
							$(obj).find("option[value='']").attr("selected",
									true);
							$(obj).find("option[value='']").siblings().attr(
									"selected", false);
							$(obj).next(".bootstrap-select").find(
									".filter-option").text("请选择");
							$("#repayName").html("");
							$("#repayContent").html("");
						} else {
							if (json.type == 1) {
								if (json.status) {
									// $("#repayName").html("中金支付账户：");

									var h = '<tr id="repayContent"><td class="td_tt td_ttp td_w140"><div class="td_title" >中金支付账户：</div></td>';
									h = h
											+ '<td class="td_ct td_ctp" colspan="4">';
									h = h
											+ '<input class=""  type="text" id="card" name="loanRepayMsg.accountName" data-validation-engine="validate[required]">';
									h = h
											+ '<input class=""  type="hidden" id="card_id" name="loanRepayMsg.accountName">';
									h = h
											+ '<span class="required-star">*</span>';
									h = h + '</td></tr>';

									$("#repayContent").replaceWith(h);
								} else {
									alert(json.text);
									$(obj).find("option[value='']").attr(
											"selected", true);
									$(obj).find("option[value='']").siblings()
											.attr("selected", false);
									$(obj).next(".bootstrap-select").find(
											".filter-option").text("请选择");
									$("#repayName").html("");
									$("#repayContent").html("");
								}

							} else if (json.type == 2) {
								if (json.status) {

									/*
									 * $.ajax({ url:"getaddbankftl.htm",
									 * data:{}, type:"POST", dataType:"JSON",
									 * success:function(map) {
									 * $("#repayContent").replaceWith(map.result); },
									 * });
									 */

									/*
									 * $("#repayName").html("所属支行："); var h = '<input
									 * class="" type="text" id="card"
									 * data-validation-engine="validate[required]">';
									 * h = h + '<input class="" type="hidden"
									 * id="card_id"
									 * name="loanRepayMsg.accountBankBranch">';
									 * h = h + '<span class="required-star">*</span>';
									 * $("#repayContent").html(h);
									 */
								} else {
									alert(json.text);
									$(obj).find("option[value='']").attr(
											"selected", true);
									$(obj).find("option[value='']").siblings()
											.attr("selected", false);
									$(obj).next(".bootstrap-select").find(
											".filter-option").text("请选择");
									$("#repayName").html("");
									$("#repayContent").html("");
								}
							}
						}
					}
				});
	}
}
function Bank(obj, url) {
	var way = $("#repayTypesel").val();
	if (way == "1cicc") {
		$("#a2bank31").val("");
		$("#a2bank32").val("");
		$("#a1cicc1").html("中金账户名：");
		$("#a1cicc2").html("中金账号：");
		$("#a2bank1").hide();
		$("#a2bank2").hide();
		$("#a2bank3").show();
	} else if (way == "2bank_card") {
		$("#a2bank31").val("");
		$("#a2bank32").val("");
		$("#a2bank1").show();
		$("#a2bank2").show();
		$("#a2bank3").show();
		$("#a1cicc1").html("账户名：");
		$("#a1cicc2").html("账号：");
		var Height = $(document.body).height();
		$("#container").css({
			"min-height" : Height - 70,
			"height" : Height - 70,
			"overflow-y" : "auto"
		});
	} else {
		$("#a2bank1").hide();
		$("#a2bank2").hide();
		$("#a2bank3").hide();
	}

	var type = $("#compTypeInfo").val();
	if (type == "comp_platform") {
		$("#repayWay1").hide();
		$("#repayWay2").hide();
		$("#a2bank1").hide();
		$("#a2bank2").hide();
		$("#a2bank3").hide();
		$("#colspan_td").attr("colspan", 3);
	} else if (type == "comp_insurance") {
		$("#repayWay1").show();
		$("#repayWay2").show();
		$("#colspan_td").removeAttr("colspan", 0);
	} else if (type == "comp_war") {
		$("#repayWay1").show();
		$("#repayWay2").show();
		$("#colspan_td").removeAttr("colspan", 0);
	} else if (type == "comp_sloan") {
		$("#repayWay1").show();
		$("#repayWay2").show();
		$("#colspan_td").removeAttr("colspan", 0);
	} else {
		if (type != null) {
			$("#repayWay1").hide();
			$("#repayWay2").hide();
			$("#a2bank1").hide();
			$("#a2bank2").hide();
			$("#a2bank3").hide();
			$("#colspan_td").attr("colspan", 3);
		}
	}

}

function BankHD(obj, url) {
	var way = $("#repayTypesel").val();
	if (way == "1cicc") {
		$("#a1cicc1").html("中金账户名：");
		$("#a1cicc2").html("中金账号：");
		$("#a2bank1").hide();
		$("#a2bank2").hide();
		$("#a2bank3").show();
	} else if (way == "2bank_card") {
		$("#a2bank1").show();
		$("#a2bank2").show();
		$("#a2bank3").show();
		$("#a1cicc1").html("账户名：");
		$("#a1cicc2").html("账号：");
		var Height = $(document.body).height();
		$("#container").css({
			"min-height" : Height - 70,
			"height" : Height - 70,
			"overflow-y" : "auto"
		});
	} else {
		$("#a2bank1").hide();
		$("#a2bank2").hide();
		$("#a2bank3").hide();
	}

	var type = $("#compTypeInfo").val();
	if (type == "comp_platform") {
		$("#repayWay1").hide();
		$("#repayWay2").hide();
		$("#a2bank1").hide();
		$("#a2bank2").hide();
		$("#a2bank3").hide();
		$("#colspan_td").attr("colspan", 3);
	} else if (type == "comp_insurance") {
		$("#repayWay1").show();
		$("#repayWay2").show();
		$("#colspan_td").removeAttr("colspan", 0);
	} else if (type == "comp_war") {
		$("#repayWay1").show();
		$("#repayWay2").show();
		$("#colspan_td").removeAttr("colspan", 0);
	} else if (type == "comp_sloan") {
		$("#repayWay1").show();
		$("#repayWay2").show();
		$("#colspan_td").removeAttr("colspan", 0);
	} else {
		if (type != null) {
			$("#repayWay1").hide();
			$("#repayWay2").hide();
			$("#a2bank1").hide();
			$("#a2bank2").hide();
			$("#a2bank3").hide();
			$("#colspan_td").attr("colspan", 3);
		}
	}

}
/**
 * safari浏览器中$("#"+id).val()取出的值为空，$("#"+id).find("[selected='selected']").attr("value")取值正确
 * IE8浏览器中$("#"+id).val()取出的值为正确，$("#"+id).find("[selected='selected']").attr("value")取值为第一个option的value值。
 */
function selectedChange(id) {
	$("#" + id).validationEngine("validate");
	if (id == "selectProduct" || id == "productId") {
		newId = $("#" + id).val(); // safari浏览器中$("#"+id).val()取值为空
		// alert(newId);
		// alert($("#"+id).find("[selected='selected']").attr("value"));
		if (newId == '') { // 当前浏览器为safari
			newId = $("#" + id).find("[selected='selected']").attr("value"); // IE8浏览器这句取值不对，$("#"+id).val()取值才对，所以在$("#"+id).val()取值为空时采用
			/*
			 * if (newId != '') {
			 * $("#productId").removeAttr("data-validation-engine"); //
			 * safari中选择后仍校验为空，so..当有选中时去除校验 $(".productIdformError").remove(); //
			 * 同时去除可能已经出现的校验信息 }else{
			 * $("#productId").removeAttr("data-validation-engine").attr("data-validation-engine","validate[required]"); }
			 */
		}
		$.ajax({
			type : "POST",
			url : $("#" + id).parents("#product").data("url")
					+ "/system/production/checkvouch.htm",
			data : {
				id : newId
			},
			dataType : "JSON",
			success : function(response) {
				$("#repayTypeId").replaceWith(response.result);
				$("#repayType").selectpicker().css({
					"display" : "inline-block",
					"position" : "absolute",
					"z-index" : -999
				});
				$("#repayType").change(function() {
					$("#repayType").validationEngine("validate");
				});
				// $("#loanForm").validationEngine("detach");
				// $("#loanForm").validationEngine("attach");
				if (response.vouchFlag) {
					$("#vouch_row").show();
					$("select[name='loan.companyInfo.companyCode']").data(
							"validation-engine", "validate[required]");
					$("#tipVouch").text("提示：请选择担保公司");

				} else {
					$("#tipVouch").text("");
					$("#vouch_row").hide();
				}
			}
		});
	}
}
var pageOnload=function(){
	var userName=$("#user_name").val();
	var userId=$("#user_id").val();
	var taglibsAllctx=$("#taglibsAllctx").val()
	if(userId!=undefined&&userId!=""){
	$.ajax({
		url:taglibsAllctx+"/customer/financcust/financingInfo.htm",
		data:{
				"userId":userId,
				"taglibsAllctx":taglibsAllctx
			},
		type: "GET",
		dataType: "json",
		async: true,
		success:function(json)
		{
			$("#tip").html(json.tip);
			 $("#repaymentWay").replaceWith(json.BackLoan);
			 $("#repayTypesel").selectpicker();
			 $("#repayTypesel").removeAttr("data-validation-engine").attr("data-validation-engine","validate[required]");
			 $("#repayTypesel").css({"display":"inline-block","position": "absolute","z-index": -999});
			 
			 var productTempId = $("#productId").val();
			 if (productTempId.length <= 0) {
				 //极简融资新增传productId，非极简融资新增重置下拉列表
				 $("#productSel").replaceWith(json.msg);
				 $("#productId").selectpicker();
				 $("#productId").removeAttr("data-validation-engine").attr("data-validation-engine","validate[required]");
				 $("#productId").css({"display":"inline-block","position": "absolute","z-index": -999});
			 }			 
			 /*IE8下freemarker插入html后js失效，select无法选中，采用手动选中和执行js方法*/
			 
			 var productTempId = $("#productId").val();
			 if (productTempId.length <= 0) {
				 //极简融资新增传productId，非极简融资新增重置下拉列表
				 $("#productSel").find("[href='#']").each(function(i,val){
					 $(this).attr("onClick","selected('"+i+"')");
				 });
			 }			 
			 $("#repaymentWay").find("[href='#']").each(function(i,val){
				 $(this).attr("onClick","selectrepay('"+i+"')");
			 });
			 /*end*/

			 $("#financingInfo").html(json.result);
			 $("#repayTypesel").find("option[value='']").attr("selected",true);
			 $("#repayTypesel").find("option[value='']").siblings().attr("selected",false);
			 $("#repayTypesel").next(".bootstrap-select").find(".filter-option").text("请选择");			 
			 
			 var productTempId = $("#productId").val();
			 if (productTempId.length <= 0) {
				 //极简融资新增传productId，非极简融资新增重置下拉列表
				 $("#productId").find("option[value='']").attr("selected",true);
				 $("#productId").find("option[value='']").siblings().attr("selected",false);
				 $("#productId").next(".bootstrap-select").find(".filter-option").text("请选择融资产品");
			 }
        	 			
			 $("#repayName").html("");
			 $("#repayContent").html("");
			 if($("#vouch_row").length > 0){
				 $("#productId").attr("onchange","selectedChange(this.id);");
			 }
		}
	});
	}
}

