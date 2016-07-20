$(function(){
	// 页面初始化时，如果窗体高度大于body高度，移除info_operate_fixed样式
	// 让bottomOperate部分absolute
	// 否则添加info_operate_fixed，让bottomOperate部分fixed
	if($(window).height() > $(document.body).height()){
		$("#bottomOperate").removeClass("info_operate_fixed");
	} else {
		$("#bottomOperate").addClass("info_operate_fixed");
	}
	
	// 点击tab切换tab页显示
	$(".tab-hd .tab-hd-con").click(function () {  
		// 被点击的tab添加active样式
		$(this).addClass("active");
		// 被点击的tab的兄弟节点移除active样式
		$(this).siblings().removeClass("active");
		// 获得tab的主体
		var parentsEl = $(this).parents(".tab");
		// 查找tab的内容部分，全部添加hide样式，移除active样式
		parentsEl.find(".tab-bd .tab-bd-con").addClass("hide").removeClass("active");
		// 获取所有的tab头部分
		var ary = parentsEl.find(".tab-hd .tab-hd-con");
		// 将与所点击的tab头部分索引相同的tab内容部分，添加active样式，移除hide样式
		// 至此已完成点击tab头，切换显示tab内容的功能
		parentsEl.find(".tab-bd .tab-bd-con:eq(" + $.inArray(this, ary) + ")").addClass("active").removeClass("hide");
		// 以下实现滚动到顶部的功能
		if(parentsEl.find(".position_tab").length > 0){
			// 获取页面主体部分距离顶部的高度
			var bodyscroll = parentsEl.parents("#body").offset().top;
			// 如果页面滚动的距离大于页面主体部分距离顶部的高度
			// 那么将页面滚动至主体部分的顶部
			if($(document).scrollTop()>bodyscroll){
				$("html,body").animate({ scrollTop:bodyscroll+33},400);
				return false;
			}
			// 如果页面高度比较小，点击切换的时候，下面的操作按钮条不会浮动上来
			if($(window).height() > $(document.body).height()){
				$("#bottomOperate").removeClass("info_operate_fixed");
			} else {
				$("#bottomOperate").addClass("info_operate_fixed");
			}
		}
	});
	if($(".tab").length > 0){
		$(window).scroll(function(){
			var tab_top = $(".tab").offset().top;
			var scroll_top = $(document).scrollTop();
			var mainHd_top = $("#main_hd").offset().top + $("#main_hd").height(); 
			if( scroll_top > tab_top){
				$(".title_tab").addClass("nav-fixed");
				if($(".originalTab_fixed").length == 0){
					$(".title_tab").before("<div class='originalTab_fixed'></div>");
				}
			}else{
				$(".title_tab").removeClass("nav-fixed");
				$(".originalTab_fixed").remove();
			};
			var bottom = $(document).height()-$("#foot_operate_area").offset().top-$("#foot_operate_area").height();
			var scroll_bottom = $(document).height()-$(document).scrollTop()-$(window).height();
			var scroll_top = $(document).scrollTop()+$(window).height(); 
			var body_top = $("#body").offset().top+$("#body").height(); 
			if( scroll_top <= body_top +31 ){
				$("#bottomOperate").addClass("info_operate_fixed");
			}
			else{
				$("#bottomOperate").removeClass("info_operate_fixed");
			}
		});
	}
	
	$("body").delegate(".popoverbox","mouseenter",function(){
		$(this).children(".popover-validate").show();
	});
	$("body").delegate(".popoverbox","mouseleave",function(){
		$(this).children(".popover-validate").hide();
	});
	
	// menu toggle
	$("#menuBar .menu .menu_title").click(function(){
		$(this).toggleClass("active")
		$(this).siblings(".menu_item").slideToggle();
	});
	
	// search droplist
	$(".dropdown_menu").hover(function(){
		$(this).children(".dropdown_data_container").show();
	},function(){
		$(this).children(".dropdown_data_container").hide();
	});
	
	// question mark notice
	$(".question-mark").mouseenter(function(){
			$(this).find(".question-mark-notice").show();
		}).mouseleave(function(){
			$(this).find(".question-mark-notice").hide();
	});

	// table max width
	$(".nowrap_table").css("width",$("#body").width()-251);
	
	// select
	$(".selectpicker").selectpicker();
	
	// checkbox radio
	$('.uniformjs').find("input[type=checkbox],input[type=radio]").uniform();
	
	// datepicker time
	$(".datepicker_time").datepicker({
    	duration: '',
        showTime: true,
        constrainInput: false
     });
	 // datepicker
	$(".datepicker").datepicker();
	// fancybox
	$(".fancybox").fancybox({
		"afterLoad" : function(){
			this.inner.addClass("heightauto");
		}
	});

	$(".address").address();
	
	$(".frm_checkboxlist").checkboxlist();
	
	$("form").each(function(){
		$(this).validate({
			onfocusout: function(element){
				if( element.type === "file" || $(element).hasClass("datepicker_time") || $(element).hasClass("datepicker") ){
					return;
				}
				$(element).valid();
				$(".popover-validate").hide();
				$(element).parent().siblings(".popoverbox").children(".popover-validate").show();
			}
		});
	});
	
	$("#goPage").onlynum({allowZero: false});
	
	/*
	 * 删除按钮共通功能
	 * */
	$("a.operate_icon.del").on("click", function(){
		var requestUrl = $(this).attr("href").substring(0, $(this).attr("href").indexOf("?"));
		var params = $(this).attr("href").substring($(this).attr("href").indexOf("?") + 1, $(this).attr("href").length);
		var trObj = $(this).parents("tr");
		var successFunction = $(this).data("success_callback");
		var failureFunction = $(this).data("failure_callback");
		confirm("confirm to delete？", "delete", function(){
			$.ajax({
				url: requestUrl,
				data: params,
				dataType: "json",
				type: "POST",
				context: trObj,
				success: function(response){
					if(response === true || response === "true"){
						$(".fancybox-close").trigger("click");
						info("success！");
						$(this).remove();
						var timer = setInterval(function(){
							if($("#operate_success_pop").length <= 0){
								var pageNow = $(".page_num label:eq(0)").text();
								var pageTotal = $(".page_num label:eq(1)").text();
								if($(".main_list table tr").length == 1){
									$("#currentPage").val(pageNow == 1 ? 1 : pageNow - 1);
								} else {
									$("#currentPage").val(pageNow);
								}
								$("#searchForm").submit();
								clearInterval(timer);
							}
						}, 10);
						eval(successFunction + "()");
					} else {
						$(".fancybox-close").trigger("click");
						info("error！");
						eval(failureFunction + "()");
					}
				},
				complete: function(){
					
				},
				error: function(){
					$(".fancybox-close").trigger("click");
					info("网络异常！");
				},
				beforeSend: function(){
					
				}
			});
		});
		return false;
	});
	
	/*
	 * 启用禁用按钮共通功能
	 * */
	$("body").on("click", "a.operate_icon.disabled, a.operate_icon.enabled", function(){
		var requestUrl = $(this).attr("href").substring(0, $(this).attr("href").indexOf("?"));
		var params = $(this).attr("href").substring($(this).attr("href").indexOf("?") + 1, $(this).attr("href").length);
		params += "&status=" + $(this).data("status");
		var btnName = "";
		var newBtnName = "";
		var newStatus = "";
		if($(this).hasClass("disabled")){
			btnName = "enable";
			newBtnName = "disable";
			newStatus = "1";
		} else if($(this).hasClass("enabled")){
			btnName = "enable";
			newBtnName = "disable";
			newStatus = "0";
		}
		var trObj = $(this).parents("tr");
		confirm("comfirm to" + btnName + "？", btnName, function(){
			$.ajax({
				url: requestUrl,
				data: params,
				dataType: "json",
				type: "POST",
				context: trObj,
				success: function(response){
					if(response === true || response === "true"){
						// 状态文字修改，样式修改
						trObj.find(".dev_status").toggleClass("mark_green").toggleClass("mark_red").text("already" + btnName);
						// 按钮切换
						trObj.find(".dev_btn_status").toggleClass("disabled").toggleClass("enabled").data("status", newStatus).text(newBtnName).attr("title", newBtnName);
						$(".fancybox-close").trigger("click");
						info(btnName + "success！");
					} else {
						$(".fancybox-close").trigger("click");
						info(btnName + "error！");
					}
				},
				complete: function(){
					
				},
				error: function(){
					$(".fancybox-close").trigger("click");
					info("网络异常！");
				},
				beforeSend: function(){
					
				}
			});
		});
		return false;
	});
	
	/*
	 * 重写confirm方法
	 * */
	window.confirm = function(msg, title, callback){
		var h = ""
			+ "<div class='confirm_pop_s'>"
			+ "<p class='tc ptb20'>" + msg + "</p>"
			+ "<p class='form_btn'>"
			+ "<a href='javascript:;' id='confirm_submit' class='btn btn_primary'>confirm</a>"
			+ "<a href='javascript:;' id='confirm_cancle' class='btn'>cancel</a>"
			+ "</p>"
			+ "</div>";
		$.fancybox({
			content: h,
			title: title,
			afterShow: function(){
				$('#confirm_submit').on('click', function () {
					callback();
                });
			}
		});
		$("#confirm_cancle").on("click", function(){
			$(".fancybox-close").trigger("click");
		});
	}
	
	// 重写info方法
	window.info = function(msg){
		var h = "" 
			+ "<div id='operate_success_pop' class='oper_suces_pop'>"
			+ "<div class='oper_suces_bd'>" + msg + "</div>"
			+ "</div>";
		$("body").append(h);
		$("#operate_success_pop").show().animate({top:"100px",opacity:"1"}, 800);
		setTimeout(function(){
			$("#operate_success_pop").fadeOut(800).remove();
		},1500);
	}
	
	// 文件上传控件编辑按钮
	$(".dev_file_edit").on("click", function(){
		$(this).parents("div.frm_controls").siblings("input:hidden").val("");
		$(this).parent().siblings(".fileupload").removeClass("dn");
		$(this).parent().remove();
	});
	
	// 刷新验证码
	$(".verifyCode").verifyCode();
	
});