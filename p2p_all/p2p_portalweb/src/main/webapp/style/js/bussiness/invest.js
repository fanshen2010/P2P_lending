$(function(){
	// 20150521 同步前端demo 我要投资筛选条件
	var filter_top = $("#filter").offset().top;
	var bodyheight = $(document.body).height();
	var windowheight = $(window).height();
	var heighttop = $(".invest-list").offset().top;
	var heightbody = $(".invest-list").height();
	//var footertop = $(".footer").offset().top;
	//var footerheight = $(".footer").height();
	var filterheight =$("#filter").height();
	var paginationH =$(".pagination").height()+40;
	var maxTopheight =heightbody-filterheight - paginationH;
	if(heightbody > filterheight){
		$(window).scroll(function(){
			var scrollTop = $(document.body).scrollTop();
			var filterScroll = scrollTop - filter_top;
			var maxSc = 0 - ( scrollTop - ( filter_top + maxTopheight) );
			if(filterScroll >maxTopheight ){
				$("#filter").css({"position":"fixed","top":maxSc,"margin-top":"0"});
			}else if(filterScroll > 0){
				$("#filter").css({"position":"fixed","top":"0","margin-top":"0"});
			}else if(filterScroll <= 0){
				$("#filter").css({"position":"static","top":"0"});
			}
		});
	}
	/*$(window).scroll(function(){
		var filter_top = $("#filter").offset().top;
		var scrollTop = $(document.body).scrollTop();
		var bodyheight = $(document.body).height();
		var windowheight = $(window).height();
		var heighttop = $(".invest-list").offset().top;
		var heightbody = $(".invest-list").height();
		var footertop = $(".footer").offset().top;
		var footerheight = $(".footer").height();
		var filterheight =$("#filter").height();
		if(filter_top < scrollTop ){
			$("#filter").css({"position":"fixed","top":"0","margin-top":"0"});
			
		}else if( scrollTop <= heighttop){
			$("#filter").css({"position":"relative","margin-top":""});
		}else if(scrollTop +footerheight+30   >= footertop ){
			$("#filter").css({"position":"relative","margin-top":""});
		}
	});	*/
	
	$("a.investList").click(function(){
		var elemId=$(this).data("id");
		var elemVal=$(this).data("key");
		$("#"+elemId).val(elemVal);
		$("#searchForm").submit();
	});
});
