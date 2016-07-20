(function( $ ){
	$.countDownTimer = function(options,$obj){
		this.settings = $.extend( true, {}, $.countDownTimer.defaults, options );
		this.timerObj = $obj;
		if(typeof this.settings.timeNow == "string"){
			this.settings.timeNow = this.formatDate(this.settings.timeNow);
		}
		if(typeof this.settings.timeCountDownTo == "string"){
			this.settings.timeCountDownTo = this.formatDate(this.settings.timeCountDownTo);
		}
		this.init();
	};

	$.extend($.countDownTimer, {
		defaults:{
			timeNow: new Date(),
			timeCountDownTo: null,
			days: null,
			hours: null,
			minutes: null,
			seconds: null,
			dValue: 0,
			timer: null
		},
		prototype:{
			init: function(){
				this.timerObj.empty();
				this.createTarget();
				if(this.checkTime()){
					var timer = this;
					var sNow = this.dateToSeconds(this.settings.timeNow);
					var sTo = this.dateToSeconds(this.settings.timeCountDownTo);
					this.settings.dValue = sTo - sNow;
					this.settings.interval = setInterval(function(){
						timer.countDown(timer);
					}, 1000);
					timer.countDown(timer);
				} else {
					this.zero();
				}
				this.showTime();
			},
			checkTime: function(){
				if(this.settings.timeCountDownTo === null || this.settings.timeNow === null){
					return false;
				} else {
					return true;
				}
			},
			zero: function(){
				this.settings.days.text("00");
				this.settings.hours.text("00");
				this.settings.minutes.text("00");
				this.settings.seconds.text("00");
			},
			countDown:function(timer){
				if(timer.settings.dValue <= 0){
					timer.zero();
					clearInterval(timer.settings.interval);
				} else {
					var days = parseInt(timer.settings.dValue / (3600 * 1000 * 24));
					var hours = parseInt(timer.settings.dValue / (3600 * 1000) - days * 24);
					var minutes = parseInt(timer.settings.dValue / (60 * 1000) - days * 24 * 60 - hours * 60);
					var seconds = parseInt(timer.settings.dValue / 1000 - days * 24 * 3600 - hours * 3600 - minutes * 60);

					timer.settings.days.text(days < 10 ? "0" + days : days );
					timer.settings.hours.text(hours < 10 ? "0" + hours : hours);
					timer.settings.minutes.text(minutes < 10 ? "0" + minutes : minutes);
					timer.settings.seconds.text(seconds < 10 ? "0" + seconds : seconds);

					timer.settings.dValue = timer.settings.dValue - 1000;
				}

			},
			showTime: function(){
				this.timerObj.append(this.settings.days).append("D.").append(this.settings.hours).append("H.").append(this.settings.minutes).append("M.").append(this.settings.seconds).append("S.");
			},
			dateToSeconds:function(d){
				var seconds = d.getTime();
				return seconds;
			},
			createTarget: function(){
				if(this.settings.days === null){
					this.settings.days = $(document.createElement("em"));
				}
				if(this.settings.hours === null){
					this.settings.hours = $(document.createElement("em"));
				}
				if(this.settings.minutes === null){
					this.settings.minutes = $(document.createElement("em"));
				}
				if(this.settings.seconds === null){
					this.settings.seconds = $(document.createElement("em"));
				}
			},
			formatDate: function(dateString){
				var d = dateString.split(/\s|-|\/|:/);
				return new Date(d[0], parseInt(d[1]) - 1, d[2], d[3], d[4], d[5]);
			}

		}
	});

	$.fn.countdown = function(options){
		$.each($(this), function(){
			var countDownTimer = $(this).data( "countDownTimer");
			if(countDownTimer) {

			} else {
				countDownTimer = new $.countDownTimer(options, $(this));
				$(this).data("countDownTimer", countDownTimer );
			}
		});
	};
})(jQuery);

(function( $ ){
	var defaults = {
		content: "",
		title: ""
	};

	$.dialog = function(options){
		$(".dialog").remove();
		$.extend( defaults, options);
		var dialogBG = "<div class='dialog'>"
			+ "<div class='dialog-infobg'>"
			+ "<div class='dialog-info'>"
			+ "<div class='dialog-hd'>"
			+ "<div class='dialog-tit'>" + defaults.title + "</div>"
			+ "<div class='clear'></div>"
			+ "</div>"
			+ "<div class='dialog-bd'>"
			+ defaults.content
			+ "</div>"
			+ "</div>"
			+ "<a href='javascript:;' title='close' class='dialog-close'>close</a>"
			+ "</div>"
			+ "</div>";
		$(document.body).append(dialogBG);
		$(".dialog").height($(document.body).height());
		$(".dialog").show();
		center($(".dialog").find(".dialog-infobg"));
		$(".dialog-close").on("click", function(){
			$(".dialog").remove();
		});
	};
})(jQuery);

(function( $ ){
	var defaults = {
		title: "operate title",
		msg: "operate success！",
		btnName: "return to security",
		btnUrl: "javascript:;",
		showTelephone: true
	};
	window.info = function(options){
		$.extend(defaults, options);
		var alertBD = "<div class='alter-phonenum-pop success'>";
		if($.isArray(defaults.msg)){
			for(i = 0; i < defaults.msg.length; i++){
				alertBD += "<p>" + defaults.msg[i] + "</p>";
			}
		} else if(typeof defaults.msg === "string") {
			alertBD += "<p>" + defaults.msg + "</p>";
		}
		alertBD += "<p><a class='btn btn-primary' href='" + defaults.btnUrl + "' title=''>" + defaults.btnName + "</a></p>";
		if(defaults.showTelephone){
			alertBD += "<p class='notice'>if you have problem, please contact with our services：0411-84370330</p>";
		}
		alertBD += "</div>";
		$.fancybox({
			title: defaults.title,
			content: alertBD
		});
	};
})(jQuery);

/*
 * 刷新验证码
 * */
(function( $ ){
	$.fn.verifyCode = function(){
		$(this).on("click", function(){
			var timenow = new Date().getTime();
			var newUrl = "";
			if($(this).attr("src").indexOf("?") >= 0){
				newUrl = $(this).attr("src").substring(0, $(this).attr("src").indexOf("?")) + "?d=" + timenow;
			} else {
				newUrl = $(this).attr("src") + "?d=" + timenow;
			}
			$(this).attr("src", newUrl);
		});
	};
})(jQuery);

/*
 * 地址级联
 * */
(function( $ ){
	$.fn.address = function(){
		$(this).change(function(){
			if($(this).val() == $(this).data("last")){
				// 如果这次的选项和上次的相同，不做任何操作
			} else {
				if($(this).val() == ""){
					// 请选择
					if($(this).data("type") == "province"){
						// 省份下拉列表请选择，清空市和区县下拉列表
						_clearSelect($(this).parent().siblings(".city,.county").find("select.address"));
					} else if($(this).data("type") == "city"){
						// 市下拉列表请选择，区县下拉列表
						_clearSelect($(this).parent().siblings(".county").find("select.address"));
					}
				} else {
					// 有选择项
					if($(this).data("type") == "province"){
						// 选择省份，级联市
						_changeSelect($(this), $(this).parent().siblings(".city").find("select.address"));
						_clearSelect($(this).parent().siblings(".county").find("select.address"));
					} else if($(this).data("type") == "city"){
						// 选择市，级联区县
						_changeSelect($(this), $(this).parent().siblings(".county").find("select.address"));
					}
				}
				
				$(this).data("last", $(this).val());
			}
		});
	}
	
	_clearSelect = function(obj){
		obj.html("<option value='' selected=selected>请选择</option>");
		obj.trigger("change");
	}
	
	_changeSelect = function(changeObj, obj){
		
		var options = [];
		
		$.ajax({
			url: webName + "/getAddress.htm",
			cache: false,
			data: {code:changeObj.val()},
			dataType: "json",
			type: "GET",
			success: function(response){
				options = response.address;
				if(options.length > 0){
					obj.find("option:not(:first)").remove();
					$.each(options, function(i, e){
						obj.find("option:last").after("<option value='"+e.code+"'>"+e.name+"</option>");
					});
					obj.trigger("change");
				}
			},
			complete: function(){
				
			},
			error: function(){
				
			},
			beforeSend: function(){
				
			}
		});
	}
})(jQuery);

