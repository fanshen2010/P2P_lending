content = {
	version : '1.0'
};

window.onload = function(){
	isOutside();
};
/**
 * 栏目管理add.htm
 * 副文本编辑器
 */
function isOutside(){	
	var categoryisOutside = document.getElementById('categoryisOutside');

if(categoryisOutside.checked){
	
	//document.getElementById('newsType1').value="";
	//document.getElementById('target1').value="";
	//document.getElementById('perPage1').value="";
	document.getElementById('content1').value="";
	document.getElementById('newsType').style.display = 'none';
	document.getElementById('target').style.display = 'none';
	document.getElementById('pic').style.display = 'none';
	document.getElementById('perPage').style.display = 'none';
	document.getElementById('contentid').style.display = 'none';
	document.getElementById('outsideUrl').style.display = '';
	
}else{
	document.getElementById('newsType').style.display = '';
	document.getElementById('target').style.display = '';
	document.getElementById('pic').style.display = '';
	document.getElementById('perPage').style.display = '';
	document.getElementById('contentid').style.display = '';
	document.getElementById('outsideUrl').style.display = 'none';
}
}

$(function(){
	/*yyyy-mm-dd hh:mm:ss 日历*/
	$("#postAt").datepicker({
		showSecond: true,
		timeFormat: "hh:mm:ss",
		duration: '',
		showTime: true,
		constrainInput: false,
		time24h: true,
		defaultH: 17,
		defaultM: 0,
		defatulS: 0
	});
});
