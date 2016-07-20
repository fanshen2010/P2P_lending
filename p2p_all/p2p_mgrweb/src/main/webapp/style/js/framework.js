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

/*
 * 分级checkbox
 * */
(function( $ ){
	$.fn.checkboxlist = function(){
		var config = {
			level1Selector:".chk1",
			level2Selector:".chk2",
			level3Selector:".chk3"
		};
		var $lev1Obj;
		var $lev2Obj;
		
		/*
		 * 监听checkBox的change事件
		 * */
		$(this).change(function(){
			var isChecked = $(this).is(":checked");
			if($(this).hasClass("chk1")){
				// 第一级按钮点击
				lev1Change($(this), isChecked);
			} else if($(this).hasClass("chk2")){
				// 第二级按钮点击
				lev2Change($(this), isChecked);
			} else if($(this).hasClass("chk3")){
				// 第三季按钮点击
				lev3Change($(this), isChecked);
			}
			// 重新渲染页面
			$.uniform.update("input:checkbox");
		});
		
		function lev1Change($changeObj, isChecked){
			// 第一级按钮点击时，将下级按钮的状态全部置为第一级按钮的状态
			// 即第一级按钮选中，则所有下级按钮选中
			// 第一级按钮未选中，则所有下级按钮未选中
			$changeObj.parents(".purv_lev1").find("input:checkbox").prop("checked", isChecked);
		}
		
		function lev2Change($changeObj, isChecked){
			// 获取第二级按钮的上一级按钮
			$lev1Obj = $changeObj.parents(".purv_lev1").find("input:checkbox.chk1");
			// 20150417 edit by  
			// 二级按钮不需要全部选中，只要有一个被选中，则对应的上级按钮就应该被选中
			/*if(!isChecked){
				// 如果第二级按钮未选中，则一级按钮未选中
				$lev1Obj.prop("checked", false);
			} else {
				// 如果二级按钮选中了，判断兄弟按钮是否全部选中，如果全部选中，则选中一级按钮
				selAllLevel1($changeObj);
			}*/
			if(isChecked){
				$lev1Obj.prop("checked", true);
			} else {
				selAllLevel1($changeObj);
			}
			// 第二级按钮点击时，将下级按钮的状态全部置为第二级按钮的状态
			// 即第二级按钮选中，则所有下级按钮选中
			// 第二级按钮未选中，则所有下级按钮未选中
			$changeObj.parents(".purv_lev2").find("input:checkbox").prop("checked", isChecked);
		}
		
		function lev3Change($changeObj, isChecked){
			// 获取第一级按钮对象
			$lev1Obj = $changeObj.parents(".purv_lev1").find("input:checkbox.chk1");
			// 获取第二级按钮对象
			$lev2Obj = $changeObj.parents(".purv_lev2").find("input:checkbox.chk2");
			// 20150417 edit by  
			// 二级按钮不需要全部选中，只要有一个被选中，则对应的上级按钮就应该被选中
			/*if(!isChecked){
				// 如果第三级按钮未选中，则对应的第一级、第二级按钮均未选中
				$lev1Obj.prop("checked", false);
				$lev2Obj.prop("checked", false);
			} else {
				// 如果第三级按钮选中，判断兄弟按钮是否全部选中
				selAllLevel2($changeObj);
				selAllLevel1($changeObj);
			}*/
			if(isChecked){
				$lev1Obj.prop("checked", true);
				$lev2Obj.prop("checked", true);
			} else {
				selAllLevel2($changeObj);
				selAllLevel1($changeObj);
			}
		}
		
		function selAllLevel1($level2Obj){
			// 如果二级按钮全部未选中，则取消选中一级按钮
			var checkedCount = $level2Obj.parents(".purv_lev1").find("input:checkbox.chk2:checked").length;
			var totalCount = $level2Obj.parents(".purv_lev1").find("input:checkbox.chk2").length;
			if(checkedCount != 0){
				$lev1Obj.prop("checked", true);
			} else {
				$lev1Obj.prop("checked", false);
			}
		}
		
		function selAllLevel2($level3Obj){
			// 如果三级按钮全部未选中，则取消选中二级按钮
			var checkedCount = $level3Obj.parents(".purv_lev2").find("input:checkbox.chk3:checked").length;
			var totalCount = $level3Obj.parents(".purv_lev2").find("input:checkbox.chk3").length;
			if(checkedCount != 0){
				$lev2Obj.prop("checked", true);
			} else {
				$lev2Obj.prop("checked", false);
			}
		}
		
	}
})(jQuery);

/*
 * 通过ajax获取ftl模板
 * */
(function($){
	var defaults = {
		url: null,
		cache: false,
		data: null,
		dataType: "json",
		type: "POST",
		success: function(){
			
		},
		complete: function(){
			/*
			 * ajax执行完毕，重新处理控件的样式
			 * 日历框无法在此处进行处理，因为有的日历框需要设置最大最小可选日期
			 * */
			// 处理下拉列表样式
			$(".selectpicker").selectpicker();
			// 处理checkbox和radiobutton的样式
			$('.uniformjs').find("input:checkbox, input:radio").uniform();
			// 地址级联处理
			$(".address").address();
			// 有层级的checkbox效果实现
			$(".frm_checkboxlist").checkboxlist();
			// 图片编辑按钮点击事件
			$(".dev_file_edit").on("click", function(){
				$(this).parents("div.frm_controls").siblings("input:hidden").val("");
				$(this).parent().siblings(".fileupload").removeClass("dn");
				$(this).parent().remove();
				$(".divFileImg").hide();//隐藏图片层
			});
			// 表单验证绑定
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
		},
		error: function(){
			
		},
		beforeSend: function(){
			
		}
	};
	
	$.ajaxLoadFtl = function(options){
		$.extend(defaults, options);
		$.ajax({
			url: defaults.url,
			cache: defaults.cache,
			data: defaults.data,
			dataType: defaults.dataType,
			type: defaults.type,
			success: defaults.success,
			complete: defaults.complete,
			error: defaults.error,
			beforeSend: defaults.beforeSend
		});
	};
})(jQuery);

/*
 * ajax提交表单，富文本编辑器
 * */
(function($){
	$.ckUpdate = function(){
		for(instance in CKEDITOR.instances){
			CKEDITOR.instances[instance].updateElement();
		}    
	};
})(jQuery);

/*
 * 输入提示
 * */
(function( $ ){
	$.tips = {
		defaults: {
			format: "bankCard"
		}
	};
	
	options = {};
	
	$.fn.tips = function(options){
		options = $.extend({}, $.tips.defaults, options);
		
		var $this = $(this);
		var $tips;
		
		var init = function(){
			var $parent = $this.parent();
			$tips = $(document.createElement("div"));
			$tips.addClass("input_tips");
			
			var tipsMinWidth = $parent.width() + "px";
			var tipsLeft = "-" + $parent.css("border-left-width");
			
			$tips.css({
				"min-width": tipsMinWidth,
				"left": tipsLeft,
				"display": "none"
			});
			
			$this.before($tips);
		};
		
		$(this).on("keyup", function(){
			var newString = "";
			switch(options.format){
				case "bankCard":
					newString = $.formatBankCard($(this).val());
					break;
				case "idCard":
					newString = $.formatIdCard($(this).val());
					break;
				case "cellphone":
					newString = $.formatCellphone($(this).val());
					break;
				default:
					break;
			}
			$tips.text(newString);
			if($tips.text()!=""){
				$(this).prev().show();
			} else {
				$(this).prev().hide();
			}
		});
		
		$(this).on("focus", function(){
			if($tips.text()!=""){
				$tips.show();
			}
		});
		
		$(this).on("blur", function(){
			$tips.hide();
		});
		
		init(this);
	}
})(jQuery);

/*
 * 下拉列表框联动
 * */
(function( $ ){
	$.linkage = {
		settings: {
			format: "bankCard"
		}
	};
	
	$.fn.linkage = function(options){
		
	}
})(jQuery);

/*
 * 只能输入数字
 * */
(function( $ ){
	var settings = {
		allowZero: true
	};
	$.fn.onlynum = function(options){
		$.extend(settings, options);
		
		$(this).on("keyup", function(){
			if(settings.allowZero){
				$(this).val($(this).val().replace(/\D/g, ""));
			} else {
				$(this).val($(this).val().replace(/^0|\D/g, ""));
			}
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
 * 时间字符串转Date类型
 * */
(function( $ ){
	$.stringToDatetime = function(datetime){
		var date = datetime.split(" ")[0].split("-");
		var time = datetime.split(" ")[1].split(":");
		return new Date(parseInt(date[0]), parseInt(date[1])-1, parseInt(date[2]), parseInt(time[0]), parseInt(time[1]), parseInt(time[2]));
	};
})(jQuery);

(function( $ ){
	$.fn.resetForm = function(){
		// 处理下拉列表样式
		$(this).find(".selectpicker").selectpicker();
		// 处理checkbox和radiobutton的样式
		$(this).find('.uniformjs').find("input:checkbox, input:radio").uniform();
		// 地址级联处理
		$(this).find(".address").address();
		// 有层级的checkbox效果实现
		$(this).find(".frm_checkboxlist").checkboxlist();
		// 图片编辑按钮点击事件
		$(this).find(".dev_file_edit").on("click", function(){
			$(this).parents("div.frm_controls").siblings("input:hidden").val("");
			$(this).parent().siblings(".fileupload").removeClass("dn");
			$(this).parent().remove();
		});
	};
})(jQuery);