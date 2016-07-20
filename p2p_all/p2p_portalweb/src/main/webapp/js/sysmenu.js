/**
 * 连接到指定页面
 * */
function toLink(url,formid){
	var form = null;
	if(formid == undefined){
		if(document.forms.length>0){
			form=document.forms[0];
		}
	}else{
		form = document.getElementById(formid);
	}
	if(form!=null){
		form.action=url;
		form.submit();
	}
}