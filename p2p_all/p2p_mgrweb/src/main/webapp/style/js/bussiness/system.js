$("#form的id").validationEngine();
common = {
	version : '1.0'
};

function staffType() {
	if (document.getElementById('platform').checked) {
		document.getElementById('companyid').style.display = 'none';
	} else if (document.getElementById('company').checked) {
		document.getElementById('companyid').style.display = '';

	}
}

function FmRole() {
	alert("admin can not be deleted！");
	return false;
}

function Disable() {
	alert("admin can not be disabled！");
	return false;
}

function staffDisable() {
	alert("admin staff can not be disabled！");
	return false;
}

function staff() {
	alert("admin staff can not be deleted！");
	return false;
}

function companyDisable() {
	alert("campany can not be disabled！");
	return false;
}

function company() {
	alert("campany can not be deleted！");
	return false;
}

common.enabled= function(pid,pver)
{	
	var mver= pver+1;
	var h = '<a href="javascript:common.disabled('+"'"+pid+"'"+','+mver+')" ';
	h = h +'title="diable" id="disabled_'+pid+'></a>';
	h1 = '<div class="td_cont" id="status_'+pid+'">enable</div>' ;
	if(confirm("comfirm to enable？")){
		$.ajax({
			url:"setStatus.htm",
			data:{"id":pid,"ver":pver},
			type:"POST",
			dataType:"JSON",
			async: true,
			success:function()
			{	
				// $("#enabled_"+pid).removeClass("pct_icon
				// enabled").addClass("pct_icon disable");
				$("#enabled_"+pid).replaceWith(h);
				$("#status_"+pid).replaceWith(h1);
				$("#color_"+pid).attr("class","");
				window.location.reload()
			},
			error:function()
			{
				alert("启用操作失误");
			}	
		});		
	}
		
}
common.disabled= function(pid,pver)
{	
	var mver= pver+1;
	var h = '<a href="javascript:common.enabled('+"'"+pid+"'"+','+mver+')" ';
	h = h +'title="enable" id="enabled_'+pid+'></a>';
	h1 = '<div class="td_cont" id="status_'+pid+'">disable</div>' ;
	if(confirm("confirm to disable？")){
		$.ajax({
			url:"setStatus.htm",
			data:{"id":pid,"ver":pver},
			type:"POST",
			dataType:"JSON",
			async: true,
			success:function()
			{			
				// $("#disabled_"+pid).removeClass("pct_icon
				// disable").addClass("pct_icon enabled");
				$("#disabled_"+pid).replaceWith(h);
				$("#status_"+pid).replaceWith(h1);
				$("#color_"+pid).attr("class","error");
				window.location.reload()
			},
			error:function()
			{
				alert("禁用操作失误");
			}	
		});		
	}	
}
