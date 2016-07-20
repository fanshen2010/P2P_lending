$(function() {
	/* 20131112 body background auto */
	$(window).on("load resize", function() {
		var Height = $(window).height();
		var bHeight = $(document.body).height();
		if (Height >= bHeight) {
			$("#container").css({
				"min-height" : Height - 70,
				"overflow" : "visible"
			});
		}
		$(".selectpicker").css({
			"display" : "inline-block",
			"position" : "absolute",
			"width" : "220px",
			"z-index" : -999
		});
		menuscroll();

	});
	$(window).scroll(function() {
		menuscroll();
	});
	// 滚动条处理
	function menuscroll() {
		var Height = $(window).height();
		var w_scrollTop = $(window).scrollTop();
		var menuheight = $(".menu-ul").height();
		var bHeight = $(document.body).height();
		if (w_scrollTop > 50) {
			$(".menu-ul").css({
				"height" : Height,
				"overflow-y" : "auto",
				"position" : "fixed",
				"width" : "179px",
				"background" : "#fff",
				"border-right" : "1px solid #dadada",
				"top" : 0
			});
		} else if (w_scrollTop < 50) {
			$(".menu-ul").css({
				"height" : Height - 50 + w_scrollTop,
				"overflow-y" : "auto",
				"position" : "relative",
				"top" : 0
			});
		}
	}
	// main menu -> submenus
	/*
	 * // 20130801 default show first Begin $('#menu .lm_accd
	 * .lm_liunfold:first').addClass('lm_active'); $('#menu .lm_accd
	 * .lm_liunfold:first .lm_icon').addClass('icon-white'); $('#menu .lm_accd
	 * .lm_liunfold:first .collapse').addClass('in'); // 20130801 default show
	 * first End
	 */
	$('#menu .collapse').on(
			'show',
			function() {
				$(this).parent().siblings('.lm_liunfold').removeClass(
						'lm_active');
				$(this).parent().siblings('.lm_liunfold').find(".lm_icon")
						.removeClass('icon-white');
				$(this).parent('.lm_liunfold:first').addClass('lm_active');
				$(this).prev().find('.lm_icon').addClass('icon-white');
			}).on('hidden', function() {
		$(this).parent('.lm_liunfold:first').removeClass('lm_active');
		$(this).prev().find('.lm_icon').removeClass('icon-white');
	});
	// main menu visibility toggle
	$('#top .btn-navbar').click(function() {
		$('.container-fluid:first').toggleClass('menu-hidden');
		$('#menu').toggleClass('hidden-phone');

		if (typeof masonryGallery != 'undefined')
			masonryGallery();
	});

	// tooltips
	$('[data-toggle="tooltip"]').tooltip();

	if ($('.widget-activity').length)
		$('.widget-activity .filters .glyphicons').click(function() {
			$('.widget-activity .filters .active').toggleClass('active');
			$(this).toggleClass('active');
		});

	/*
	 * $(window).resize(function() { if (!$('#menu').is(':visible') &&
	 * !$('.container-fluid:first').is('.menu-hidden') &&
	 * !$('.container-fluid:first').is('.documentation') &&
	 * !$('.container-fluid:first').is('.login'))
	 * $('.container-fluid:first').addClass('menu-hidden'); });
	 */

	$(window).resize();

	$('.btn-source-toggle').click(function(e) {
		e.preventDefault();
		$('.code').toggleClass('hide');
	});

	$('#toggle-menu-position').on(
			'change',
			function() {
				$('.container-fluid:first').toggleClass('menu-right');
				$.cookie('rightMenu', $(this).prop('checked') ? $(this).prop(
						'checked') : null);
			});

	/*
	 * if ($.cookie('rightMenu') && $('#toggle-menu-position').length) {
	 * $('#toggle-menu-position').prop('checked', true);
	 * $('.container-fluid:first').not('.menu-right').addClass('menu-right'); }
	 */

	/* wysihtml5 */
	if ($('textarea.wysihtml5').size() > 0)
		$('textarea.wysihtml5').wysihtml5();

	/* DataTables */
	if ($('.dynamicTable').size() > 0) {
		$('.dynamicTable')
				.dataTable(
						{
							"sPaginationType" : "bootstrap",
							"sDom" : "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
							"oLanguage" : {
								"sLengthMenu" : "_MENU_ records per page"
							}
						});
	}

	/*
	 * Helper function for JQueryUI Sliders Create event
	 */
	function JQSliderCreate() {
		$(this).removeClass('ui-corner-all ui-widget-content').wrap(
				'<span class="ui-slider-wrap"></span>').find(
				'.ui-slider-handle').removeClass(
				'ui-corner-all ui-state-default');
	}

	/*
	 * JQueryUI Slider: Default slider
	 */
	if ($('.slider-single').size() > 0) {
		$(".slider-single").slider({
			create : JQSliderCreate,
			value : 10,
			animate : true,
			start : function() {
				if (typeof mainYScroller != 'undefined')
					mainYScroller.disable();
			},
			stop : function() {
				if (typeof mainYScroller != 'undefined')
					mainYScroller.enable();
			}
		});
	}

	/*
	 * JQueryUI Slider: Multiple Vertical Sliders
	 */
	$(".sliders-vertical > span").each(function() {
		var value = parseInt($(this).text(), 10);
		$(this).empty().slider({
			create : JQSliderCreate,
			value : value,
			range : "min",
			animate : true,
			orientation : "vertical",
			start : function() {
				if (typeof mainYScroller != 'undefined')
					mainYScroller.disable();
			},
			stop : function() {
				if (typeof mainYScroller != 'undefined')
					mainYScroller.enable();
			}
		});
	});

	/*
	 * JQueryUI Slider: Range Slider
	 */
	if ($('.range-slider').size() > 0) {
		$(".range-slider .slider").slider(
				{
					create : JQSliderCreate,
					range : true,
					min : 0,
					max : 500,
					values : [ 75, 300 ],
					slide : function(event, ui) {
						$(".range-slider .amount").val(
								"$" + ui.values[0] + " - $" + ui.values[1]);
					},
					start : function() {
						if (typeof mainYScroller != 'undefined')
							mainYScroller.disable();
					},
					stop : function() {
						if (typeof mainYScroller != 'undefined')
							mainYScroller.enable();
					}
				});
		$(".range-slider .amount").val(
				"$" + $(".range-slider .slider").slider("values", 0) + " - $"
						+ $(".range-slider .slider").slider("values", 1));
	}

	/*
	 * JQueryUI Slider: Snap to Increments
	 */
	if ($('.increments-slider').size() > 0) {
		$(".increments-slider .slider").slider({
			create : JQSliderCreate,
			value : 100,
			min : 0,
			max : 500,
			step : 50,
			slide : function(event, ui) {
				$(".increments-slider .amount").val("$" + ui.value);
			},
			start : function() {
				if (typeof mainYScroller != 'undefined')
					mainYScroller.disable();
			},
			stop : function() {
				if (typeof mainYScroller != 'undefined')
					mainYScroller.enable();
			}
		});
		$(".increments-slider .amount").val(
				"$" + $(".increments-slider .slider").slider("value"));
	}

	/*
	 * JQueryUI Slider: Vertical Range Slider
	 */
	if ($('.vertical-range-slider').size() > 0) {
		$(".vertical-range-slider .slider").slider(
				{
					create : JQSliderCreate,
					orientation : "vertical",
					range : true,
					min : 0,
					max : 500,
					values : [ 100, 400 ],
					slide : function(event, ui) {
						$(".vertical-range-slider .amount").val(
								"$" + ui.values[0] + " - $" + ui.values[1]);
					},
					start : function() {
						if (typeof mainYScroller != 'undefined')
							mainYScroller.disable();
					},
					stop : function() {
						if (typeof mainYScroller != 'undefined')
							mainYScroller.enable();
					}
				});
		$(".vertical-range-slider .amount").val(
				"$"
						+ $(".vertical-range-slider .slider").slider("values",
								0)
						+ " - $"
						+ $(".vertical-range-slider .slider").slider("values",
								1));
	}

	/*
	 * JQueryUI Slider: Range fixed minimum
	 */
	if ($('.slider-range-min').size() > 0) {
		$(".slider-range-min .slider").slider({
			create : JQSliderCreate,
			range : "min",
			value : 150,
			min : 1,
			max : 700,
			slide : function(event, ui) {
				$(".slider-range-min .amount").val("$" + ui.value);
			},
			start : function() {
				if (typeof mainYScroller != 'undefined')
					mainYScroller.disable();
			},
			stop : function() {
				if (typeof mainYScroller != 'undefined')
					mainYScroller.enable();
			}
		});
		$(".slider-range-min .amount").val(
				"$" + $(".slider-range-min .slider").slider("value"));
	}

	/*
	 * JQueryUI Slider: Range fixed maximum
	 */
	if ($('.slider-range-max').size() > 0) {
		$(".slider-range-max .slider").slider({
			create : JQSliderCreate,
			range : "max",
			min : 1,
			max : 700,
			value : 150,
			slide : function(event, ui) {
				$(".slider-range-max .amount").val("$" + ui.value);
			},
			start : function() {
				if (typeof mainYScroller != 'undefined')
					mainYScroller.disable();
			},
			stop : function() {
				if (typeof mainYScroller != 'undefined')
					mainYScroller.enable();
			}
		});
		$(".slider-range-max .amount").val(
				"$" + $(".slider-range-max .slider").slider("value"));
	}

	/*
	 * Boostrap Extended
	 */
	// custom select for Boostrap using dropdowns
	$('.selectpicker').selectpicker();

	// bootstrap-toggle-buttons
	$('.toggle-button').toggleButtons();

	// datepicker

	// 20130801 datepicker UI Begin
	// $( ".datepicker" ).datepicker();
	$(".datepicker").datepicker({
		changeMonth : true, // 月份下拉列表
		changeYear : true
	// 年份下拉列表
	});
	// $( "#newdatepickere" ).datepicker();
	// 20130801 datepicker UI End

	if ($('#datepicker').length) {
		$("#datepicker").datepicker({
			showOtherMonths : true
		});
	}
	if ($('#datepicker-inline').length) {
		$('#datepicker-inline').datepicker({
			inline : true,
			showOtherMonths : true
		});
	}

	// colorpicker
	if ($('#colorpicker').size() > 0) {
		$('#colorpicker').farbtastic('#colorpickerColor');
	}

	// bookings daterange
	if ($('#dateRangeFrom').length && $('#dateRangeTo').length) {
		$("#dateRangeFrom").datepicker(
				{
					defaultDate : "+1w",
					changeMonth : false,
					numberOfMonths : 2,
					onClose : function(selectedDate) {
						$("#dateRangeTo").datepicker("option", "minDate",
								selectedDate);
					}
				}).datepicker("option", "maxDate", $('#dateRangeTo').val());

		$("#dateRangeTo").datepicker(
				{
					defaultDate : "+1w",
					changeMonth : false,
					numberOfMonths : 2,
					onClose : function(selectedDate) {
						$("#dateRangeFrom").datepicker("option", "maxDate",
								selectedDate);
					}
				}).datepicker("option", "minDate", $('#dateRangeFrom').val());
	}

	$('.checkboxs thead :checkbox')
			.change(
					function() {
						if ($(this).is(':checked')) {
							$('.checkboxs tbody :checkbox').prop('checked',
									true).parent().addClass('checked');
							$('.checkboxs tbody tr.selectable').addClass(
									'selected');
							$('.checkboxs_actions').show();
						} else {
							$('.checkboxs tbody :checkbox').prop('checked',
									false).parent().removeClass('checked');
							$('.checkboxs tbody tr.selectable').removeClass(
									'selected');
							$('.checkboxs_actions').hide();
						}
					});

	$('.checkboxs tbody').on(
			'click',
			'tr.selectable',
			function(e) {
				var c = $(this).find(':checkbox');
				var s = $(e.srcElement);

				if (e.srcElement.nodeName == 'INPUT') {
					if (c.is(':checked'))
						$(this).addClass('selected');
					else
						$(this).removeClass('selected');
				} else if (e.srcElement.nodeName != 'TD'
						&& e.srcElement.nodeName != 'TR'
						&& e.srcElement.nodeName != 'DIV') {
					return true;
				} else {
					if (c.is(':checked')) {
						c.prop('checked', false).parent()
								.removeClass('checked');
						$(this).removeClass('selected');
					} else {
						c.prop('checked', true).parent().addClass('checked');
						$(this).addClass('selected');
					}
				}
				if ($('.checkboxs tr.selectable :checked').size() == $(
						'.checkboxs tr.selectable :checkbox').size())
					$('.checkboxs thead :checkbox').prop('checked', true)
							.parent().addClass('checked');
				else
					$('.checkboxs thead :checkbox').prop('checked', false)
							.parent().removeClass('checked');

				if ($('.checkboxs tr.selectable :checked').size() >= 1)
					$('.checkboxs_actions').show();
				else
					$('.checkboxs_actions').hide();
			});

	if ($('.checkboxs tbody :checked').size() == $('.checkboxs tbody :checkbox')
			.size()
			&& $('.checkboxs tbody :checked').length)
		$('.checkboxs thead :checkbox').prop('checked', true).parent()
				.addClass('checked');

	if ($('.checkboxs tbody :checked').length)
		$('.checkboxs_actions').show();

	$('.radioboxs tbody tr.selectable').click(
			function(e) {
				var c = $(this).find(':radio');
				if (e.srcElement.nodeName == 'INPUT') {
					if (c.is(':checked'))
						$(this).addClass('selected');
					else
						$(this).removeClass('selected');
				} else if (e.srcElement.nodeName != 'TD'
						&& e.srcElement.nodeName != 'TR') {
					return true;
				} else {
					if (c.is(':checked')) {
						c.attr('checked', false);
						$(this).removeClass('selected');
					} else {
						c.attr('checked', true);
						$('.radioboxs tbody tr.selectable').removeClass(
								'selected');
						$(this).addClass('selected');
					}
				}
			});

	// sortable tables
	if ($(".js-table-sortable").length) {
		$(".js-table-sortable").sortable(
				{
					placeholder : "ui-state-highlight",
					items : "tbody tr",
					handle : ".js-sortable-handle",
					forcePlaceholderSize : true,
					helper : function(e, ui) {
						ui.children().each(function() {
							$(this).width($(this).width());
						});
						return ui;
					},
					start : function(event, ui) {
						if (typeof mainYScroller != 'undefined')
							mainYScroller.disable();
						ui.placeholder.html('<td colspan="'
								+ $(this).find('tbody tr:first td').size()
								+ '">&nbsp;</td>');
					},
					stop : function() {
						if (typeof mainYScroller != 'undefined')
							mainYScroller.enable();
					}
				});
	}
	/*
	 * UniformJS: Sexy form elements
	 */
	if ('undefined' != typeof (document.body.style.maxHeight)) {
		$('.uniformjs').find("select, input, button, textarea").uniform();
	}

	$(".image_link").fancybox({
		'hideOnContentClick' : true
	});

	$(document)
			.on(
					"click",
					".fileupload-edit",
					function() {
						var fileHtml = "<div class=\"fileupload fileupload-new\" data-provides=\"fileupload\" >"
								+ "<div class=\"input-append\">"
								+ "<div class=\"uneditable-input\">"
								+ "<i class=\"icon-file fileupload-exists\"></i>"
								+ "<span class=\"fileupload-preview\"></span>"
								+ "</div>"
								+ "<span class=\"btn btn-file\">"
								+ "<span class=\"fileupload-new\">选择文件</span>"
								+ "<span class=\"fileupload-exists\">再选择文件</span>"
								+ "<input type=\"file\" name=\""
								+ $(this).data("name")
								+ "\" accept=\""
								+ $(this).data("accept")
								+ "\" style='width:70px;' />"
								+ ($(this).data("value") != "" ? "<input type=\"hidden\" name=\""
										+ $(this).data("name")
										+ "PropertyName\" value=\""
										+ $(this).data("property_name")
										+ "\" />"
										: "")
								+ "</span>"
								+ "<a href=\"#\" class=\"btn fileupload-exists\" data-dismiss=\"fileupload\">清除</a>"
								+ "<a href=\"javascript:;\" class=\"btn fileupload-cancel\">取消</a>"
								+ "</div>" + "</div>";
						$(this).parent().parent().append(fileHtml);
						$(this).parent().hide();
					});
	//文件上传删除按钮功能	
	$(document)
	.on(
			"click",
			".fileupload-del",
			function() {
				if(confirm("确认要删除此图片么？")){
					var fileHtml = "<div class=\"fileupload fileupload-new\" data-provides=\"fileupload\" >"
						+ "<div class=\"input-append\">"
						+ "<div class=\"uneditable-input\">"
						+ "<i class=\"icon-file fileupload-exists\"></i>"
						+ "<span class=\"fileupload-preview\"></span>"
						+ "</div>"
						+ "<span class=\"btn btn-file\">"
						+ "<span class=\"fileupload-new\">选择文件</span>"
						+ "<span class=\"fileupload-exists\">再选择文件</span>"
						+ "<input type=\"file\" name=\""
						+ $(this).data("name")
						+ "\" accept=\""
						+ $(this).data("accept")
						+ "\" style='width:70px;' />"
						+ ($(this).data("value") != "" ? "<input type=\"hidden\" name=\""
								+ $(this).data("name")
								+ "PropertyName\" value=\""
								+ $(this).data("property_name")
								+ "\" />"
								: "")
						//当点击删除按钮时，把原来上传上的图片放入隐藏项传入Action
						+ ($(this).data("value") != "" ? "<input type=\"hidden\" name=\"uploadFileId\" value=\""
								+ $(this).data("file_id")
								+ "\" />"
								: "")
						+ "</span>"
						+ "<a href=\"#\" class=\"btn fileupload-exists\" data-dismiss=\"fileupload\">清除</a>"
						+ "</div>" + "</div>";
					$(this).parent().parent().append(fileHtml);
					$(this).parent().remove();
					//根据传入的不同code判断点击的哪个按钮，设置不同返回Action的隐藏域值
					//系统设定中logo、icon 删除用
					var readCode = $(this).data("setting_code");
					if(readCode == "fero_logo"){
						$("#rlogo").val("null");
					}else if(readCode == "mgrweb_logo"){
						$("#mlogo").val("null");
					}else if(readCode == "pro_icon"){
						$("#ricon").val("null");
					}else if(readCode == "mgr_icon"){
						$("#micon").val("null");
					}
					//公司管理修改页 公司图片、电子印章  删除用
					if(readCode == "companySeal"){
						$("#companySealFlag").val("sealYes");
					}else if(readCode == "companyPic"){
						$("#companyPicFlag").val("picYes");
					}
					//待审核融资：电子签章，委托借款协议
					if(readCode == "loanSeal"){
						$("#loanSealFlag").val("sealYes");
					}else if(readCode == "loanContract"){
						$("#loanContractFlag").val("contractYes");
					}else if(readCode == "loanContractCovering"){
						$("#loanContractCoveringFlag").val("contractCoveringYes");
					}
				}
			});
	
	$(document).on("click", ".friendlink-file .fileupload-cancel", function() {
		$(this).parent().parent().next().remove();
	});
	$(document).on("click", ".fileupload-cancel", function() {
		$(this).parent().parent().prev().show();
		$(this).parent().parent().remove();
	});

	$(document).on(
			"click",
			".friendlink-file .fileupload-edit",
			function() {
				$(this).parent().parent().append(
						"<span class='required-star'>*</span>");
			});

	/*
	 * 查询按钮点击事件，将页码置为1
	 */
	$(document).on("click", "button:submit", function() {
		$("#pageNo").val("1");
	});

	$("form").on("mouseenter", function() {
		$('form').validationEngine('attach');
	});
	$("form").on("mouseleave", function() {
		$('form').validationEngine('detach');
	});

	$("input.fancybox_callback[readonly='true']").on(
			"click",
			function() {
				$(this).siblings("a.fancybox_open,button.fancybox_open")
						.trigger("click");
			});

});