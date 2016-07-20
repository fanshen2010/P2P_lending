$(function(){

//	
//	$("#add_bank").fancybox({
//		width : 700,
//		height: 700 
//	});
//	
	
	/*
	 * 点击错误提示消失
	 */
	$(document).on("click",".formerror",function(){
		$(this).remove();
	});
	
	if($("#userForm").length > 0){
		$("#userForm").validationEngine({
			promptPosition: "centerRight"	
		});
	}
	
	if($("#birthday").length > 0){
		var myDate = new Date();
		$("#birthday").datepicker({
			changeMonth:true,  //月份下拉列表
			changeYear: true,  //年份下拉列表
			yearRange: "1900:"+myDate.getFullYear(),
			maxDate:myDate.getFullYear() + "-" + (myDate.getUTCMonth() + 1) +"-"+myDate.getDate()
		});
	}
	
	$("form").ready(function(){
		if($(this).find("input:text").length > 0){
			$($(this).find("input:text")[0]).focus();
		}
	});
});



var savebank =  function(){
	var	bankcount = $("*[name='bankcount']",window.parent.document).val() ;
	var newcout = bankcount-0+1 ;
	var bankCode = $("*[name='userBankAccount.bankCode']").val() ;
	var bankName = $("*[name='userBankAccount.bankCode'] option:selected").text();
	var pCode = $("#province").val();
	var pName = $("#province option:selected").text();
	
	var cCode = $("#city").val();
	var cName = $("#city option:selected").text();
	var branch = $("input[name='userBankAccount.branch']").val();
	var accountName = $("input[name='userBankAccount.accountName']").val();
	var bankAccount = $("input[name='userBankAccount.bankAccount']").val();
	var h = '<tr id = "'+bankcount+'"><td class="td_ct"><div class="td_cont">'+newcout+'</div></td>' ;
	h = h+'<td class="td_ct"><div class="td_cont">'+accountName +'</div></td>' ;
	h = h+'<td class="td_ct"><div class="td_cont">'+bankName +'</div></td>' ;
	h = h+'<td class="td_ct"><div class="td_cont">'+bankAccount +'</div></td>' ;
	
	h = h+'<td class="td_ct"><div class="td_operate_link">';

	h = h+'<a id="add_bank" class="fancybox.iframe" href="viewBank.htm?id='+bankcount+'&bankName='+bankName+'&bankCode='+bankCode+'&branch='+branch+'&accountName='+accountName+'&bankAccount='+bankAccount+'&pCode='+pCode+'&pName='+pName+'&cCode='+cCode+'&cName='+cName+'">查看</a>&nbsp;';	
	h = h+'<a id="add_bank" class="fancybox.iframe" href="editBank.htm?id='+bankcount+'&bankName='+bankName+'&bankCode='+bankCode+'&branch='+branch+'&accountName='+accountName+'&bankAccount='+bankAccount+'&pcode1='+pCode+'&pName='+pName+'&ccode1='+cCode+'&cName='+cName+'">修改</a>&nbsp;';
	h = h+'<a href="javascript:deletebank('+bankcount+');" onclick="javascript:if(confirm(确定删除该银行卡信息?))(window.close());" >删除</a>';

	h = h+'</div></td></tr>';
	
	var a = '<div id = "D'+bankcount+'"><input type="hidden" name="userBankAccounts['+bankcount+'].bankOpen.bankId" value="'+bankCode+'"/>' ;
	a = a+'<input type="hidden" name="userBankAccounts['+bankcount+'].branchDistric.code" value="'+cCode+'"/>' ;
	a = a+'<input type="hidden" name="userBankAccounts['+bankcount+'].branch" value="'+branch+'"/>' ;
	a = a+'<input type="hidden" name="userBankAccounts['+bankcount+'].accountName" value="'+accountName+'"/>' ;
	a = a+'<input type="hidden" name="userBankAccounts['+bankcount+'].bankAccount" value="'+bankAccount+'"/></div>' ;
	a = a+'<div id = "bankdiv"></div>'
	h = h+'<tr id = "banklist" > </tr>';
	$("#banklist",window.parent.document).replaceWith(h);
	$("#bankdiv",window.parent.document).replaceWith(a);
	$("#bankcount",window.parent.document).replaceWith('<input type = "hidden" id ="bankcount" name="bankcount" value="'+newcout+'" />');
	parent.$.fancybox.close();
};
var closebank =  function(){
	parent.$.fancybox.close();	
};

var updatebank =  function(){
	var idCount = $("*[name='userBankAccount.id']").val() ;
	var newcout = idCount-0+1 ;
	var bankCode = $("*[name='userBankAccount.bankCode']").val() ;
	var bankName = $("*[name='userBankAccount.bankCode'] option:selected").text();
	var pCode = $("#province").val();
	var pName = $("#province option:selected").text();
	
	var cCode = $("#city").val();
	var cName = $("#city option:selected").text();
	var branch = $("input[name='userBankAccount.branch']").val();
	var accountName = $("input[name='userBankAccount.accountName']").val();
	var bankAccount = $("input[name='userBankAccount.bankAccount']").val();
	
	var h = '<tr id = "'+idCount+'"><td class="td_ct"><div class="td_cont">'+newcout+'</div></td>' ;
	h = h+'<td class="td_ct"><div class="td_cont">'+accountName +'</div></td>' ;
	h = h+'<td class="td_ct"><div class="td_cont">'+bankName +'</div></td>' ;
	h = h+'<td class="td_ct"><div class="td_cont">'+bankAccount +'</div></td>' ;
	
	h = h+'<td class="td_ct"><div class="td_operate_link">';

	h = h+'<a id="add_bank" class="fancybox.iframe" href="viewBank.htm?id='+idCount+'&bankName='+bankName+'&bankCode='+bankCode+'&branch='+branch+'&accountName='+accountName+'&bankAccount='+bankAccount+'&pCode='+pCode+'&pName='+pName+'&cCode='+cCode+'&cName='+cName+'">查看</a>&nbsp;';	
	h = h+'<a id="add_bank" class="fancybox.iframe" href="editBank.htm?id='+idCount+'&bankName='+bankName+'&bankCode='+bankCode+'&branch='+branch+'&accountName='+accountName+'&bankAccount='+bankAccount+'&pcode1='+pCode+'&pName='+pName+'&ccode1='+cCode+'&cName='+cName+'">修改</a>&nbsp;';
	h = h+'<a href="javascript:deletebank('+idCount+');" onclick="javascript:if(confirm(确定删除该银行卡信息?))(window.close());" >删除</a>';


	h = h+'</div></td></tr>';
	

	var a = '<div id = "D'+idCount+'"><input type="hidden" name="userBankAccounts['+idCount+'].bankOpen.bankId" value="'+bankCode+'"/>' ;
	a = a+'<input type="hidden" name="userBankAccounts['+idCount+'].branchDistric.code" value="'+cCode+'"/>' ;


	a = a+'<input type="hidden" name="userBankAccounts['+idCount+'].branch" value="'+branch+'"/>' ;
	a = a+'<input type="hidden" name="userBankAccounts['+idCount+'].accountName" value="'+accountName+'"/>' ;

	a = a+'<input type="hidden" name="userBankAccounts['+idCount+'].bankAccount" value="'+bankAccount+'"/></div>' ;
	a = a+'<div id = "bankdiv"></div>'
	
	h = h+'<tr id = "banklist" > </tr>'
	$("#"+idCount,window.parent.document).replaceWith(h);
	$("#D"+idCount,window.parent.document).replaceWith(a);
	parent.$.fancybox.close();
};

var saveShareholder =  function(){
	var memberCount = $("#shareholderlist",window.parent.document).children().length;
	var newCount = memberCount-0+1 ;
	var	name = $("input[name='shareholderPartners.name']").val() ;
	var gender = $("*[name='shareholderPartners.gender.dictCode']").val() ;
	var shares = $("input[name='shareholderPartners.shares']").val();
	var address = $("*[name='shareholderPartners.address.code']").val();
	var addressPhone = $("input[name='shareholderPartners.addressPhone']").val();
	var celPhone = $("input[name='shareholderPartners.celPhone']").val();
	
	var shareholderMember = {
			name:name,
			gender:gender,
			shares:shares,
			address:address,
			addressPhone:addressPhone,
			celPhone:celPhone
			
	};
	$.ajax({
		type:"POST",
		url:"saveShareholderByAjax.htm",
		dataType:"json",
		data:shareholderMember,
		success:function(map){
			var menebrId =map.menebrId ;
			
			var h = '<tr id = "'+menebrId+'"><td class="td_ct"><div class="td_cont">'+newCount+'</div></td>' ;
			h = h+'<td class="td_ct"><div class="td_cont">'+name +'</div></td>' ;
			h = h+'<td class="td_ct"><div class="td_cont">'+shares +'</div></td>' ;
			h = h+'<td class="td_ct"><div class="td_cont">'+celPhone +'</div></td>' ;
			
			h = h+'<td class="td_ct"><div class="td_operate_link">';
			
			h = h+'<a id="add_bank" class="fancybox.iframe" href="viewShareholder.htm?shareholderId='+menebrId+'">查看</a>&nbsp;';	
			h = h+'<a id="add_bank" class="fancybox.iframe" href="editShareholder.htm?shareholderId='+menebrId+'">修改</a>&nbsp;';
			h = h+'<a href="javascript:deleteShareholder('+newCount+');" onclick="javascript:if(confirm(确定删除该信息?))(window.close());" >删除</a>';
			
			h = h+'</div></td></tr>';
			
			$("#shareholderlist",window.parent.document).append(h);
			$("#shareholdercount",window.parent.document).val($("#shareholdercount",window.parent.document).val()+','+map.menebrId);

			parent.$.fancybox.close();
		}
	});
	
};

/**
 * Ajax 股东信息的修改 在页面保存id集合
 * author Liuxue
 */
var updateShareholderByAjax =  function(){
	var	memberCount = $("#shareholderlist",window.parent.document).children().length ;
	var newCount = memberCount-0+1 ;
	var shareholderId = $("*[name='shareholderId']").val();
	var	name = $("input[name='shareholderPartners.name']").val() ;
	var gender = $("*[name='shareholderPartners.gender.dictCode']").val() ;
	var shares = $("input[name='shareholderPartners.shares']").val();
	var address = $("*[name='shareholderPartners.address.code']").val();
	var addressPhone = $("input[name='shareholderPartners.addressPhone']").val();
	var celPhone = $("input[name='shareholderPartners.celPhone']").val();
	
	var shareholderMember = {
			shareholderId:shareholderId,
			name:name,
			gender:gender,
			shares:shares,
			address:address,
			addressPhone:addressPhone,
			celPhone:celPhone
			
	};
	$.ajax({
		type: "POST",
		url: "updateShareholderByAjax.htm",
		dataType: "json",
		data:shareholderMember,
		success: function (map) {
			var menebrId =map.menebrId ;
			var h = '<tr id = "'+shareholderId+'"><td class="td_ct"><div class="td_cont" name="memberIndex">'+newCount+'</div></td>' ;
			h = h+'<td class="td_ct"><div class="td_cont">'+name +'</div></td>' ;
			h = h+'<td class="td_ct"><div class="td_cont">'+shares +'</div></td>' ;
			h = h+'<td class="td_ct"><div class="td_cont">'+celPhone +'</div></td>' ;
			
			h = h+'<td class="td_ct"><div class="td_operate_link">';
			
			h = h+'<a id="add_bank" class="fancybox.iframe" href="viewShareholder.htm?shareholderId='+shareholderId+'">查看</a>&nbsp;';	
			h = h+'<a id="add_bank" class="fancybox.iframe" href="editShareholder.htm?shareholderId='+shareholderId+'">修改</a>&nbsp;';
			
			h = h+'</div></td></tr>';
			
			$("#"+shareholderId,window.parent.document).replaceWith(h);
			$("#shareholderlist").children().each(function(index,element){
				$(this).find("[name='memberIndex']").text(index+1);
			});
			parent.$.fancybox.close();
		}
	}) ;
};

var deletebank =  function(idCount){
	
	if (!confirm("确认要删除？")) {
        window.event.returnValue = false;
    }
	$("#"+idCount).remove();
	$("#D"+idCount).remove();
};

var deleteShareholder =  function(newCount){
	
	if (!confirm("确认要删除？")) {
        window.event.returnValue = false;
    }
	$("#"+newCount).remove();
	$("#D"+newCount).remove();
};


var savebankT =  function(){
 
//	 $.ajax({
//         cache: true,
//         type: "POST",
//         url:"saveBankT.htm",
//         data:$('#userBankAccountForm').serialize(),// 你的formid
//         async: false,
//         error: function(request) {
//             alert("Connection error");
//         },
//         success: function(data) {
//             $("#commonLayout_appcreshi").parent().html(data);
//         }
//     });
		$("#userBankAccountForm").submit();
		parent.$.fancybox.close();
		parent.window.location.reload();

	
};

var updatebankT =  function(){
	$("#userBankAccountForm").submit();
	parent.$.fancybox.close();
	parent.window.location.reload();

	
};

//var editbank = function(bankcount){
//	 var bankCode = $("input[name='userBankAccounts["+bankcount+"].bankCode']").val() ;
//	 var accountName = $("input[name='userBankAccounts["+bankcount+"].accountName']").val() ;
//	 $.ajax({
//		 type:"POST",
//		 url: "editBank.htm",
//		 dataType: "json",
//		 data: {"accountName":accountName,"bankCode":bankCode},
//		 error: function(map) {
//			 alert("获取数据失败！") ;
//		 },
//		 success: function(map) {
////			 $(".dialog").replaceWith(map.result) ;
////			 $(".dialog").height($(document.body).height());
////			 $(".dialog").show();
////			 center($(".dialog-infobg"));
//		 }
//	});
//};
