$(function(){
	var todayInvestAmount = 0;
	var yesterdayInvestAmount = 0;
	var lastmonthInvestAmount = 0;
	var amountTotalHour = [];
	var amountHour = [];
	var investCountTotal = 0;
	var investAmountAvg = 0;
	var lastLoans = [];
	var investFlows = [];
	var countNow = [];
	var countMax = [];
	var provinceSort = [];
	var provinceInvest = {};
	var now = {};
	//var defaultOptions = Highcharts.getOptions();
	
	$("#top button:button.btn-navbar").trigger("click");
	
	/*
	 * 获取图表数据
	 */
	function getData(){
		$.ajax({
			url: $("#allctx").val() + "/getMonitorData.htm",
			dataType: "JSON",
			type: "GET",
			data:{},
			success: function(response){
				responseHandle(response);
				showTotal();
				showLineChart();
				showLastLoans();
				showCircle();
				showColumn();
			}
		});
	}
	
	function getDataRegion(){
		$.ajax({
			url: $("#allctx").val() + "/getMonitorDataRegion.htm",
			dataType: "JSON",
			type: "GET",
			data:{},
			success: function(response){
				$("#investToday").text(response.REGION.INVEST_TODAY);
				$("#investTotal").text(response.REGION.INVEST_TOTAL);
				provinceInvest = response.REGION.INVEST_REGION;
				$.each(response.REGION.INVEST_REGION, function(key, value){
					provinceSort.push({province:key, invest: value});
				});
				var newSort = provinceSort.sort(function(a, b){
					return !(a.invest - b.invest);
				});
				$.each(newSort, function(i, val){
					if(i < 4){
						$("#provinceSort").append("<li>" + val.province + "：" + val.invest + "</li>");
					}
				});
				showMap();
			}
		});
	}
	
	/*
	 * AJAX响应数据处理
	 * */
	function responseHandle(response){
		todayInvestAmount = Highcharts.numberFormat(response.TODAY_INVEST_AMOUNT/10000,2,".",",");
		yesterdayInvestAmount = Highcharts.numberFormat(response.YESTERDAY_INVEST_AMOUNT/10000,2,".",",");
		lastmonthInvestAmount = Highcharts.numberFormat(response.LASTMONTH_INVEST_AMOUNT/10000,2,".",",");
		amountTotalHour = response.IMMEDIATE_MONITOR.AMOUNT_TOTAL_HOUR;
		amountHour = response.IMMEDIATE_MONITOR.AMOUNT_HOUR;
		investCountTotal = response.IMMEDIATE_MONITOR.COUNT;
		investAmountAvg = Highcharts.numberFormat(response.IMMEDIATE_MONITOR.AMOUNT_TOTAL / (investCountTotal * 10000), 2, ".", ",");
		lastLoans = response.LAST_LOANS;
		$.each(response.INVEST_FLOWS, function(){
			investFlows.push([this.TITLE, this.TOTAL_INVEST]);
		});
		countNow = response.ONLINE_USER.COUNT_NOW;
		countMax = response.ONLINE_USER.COUNT_MAX;
		now = response.ONLINE_USER.NOW;
	}
	
	/*
	 * 展示总额
	 */
	function showTotal(){
		$("#todayInvestAmount").prev().hide();
		$("#yesterdayInvestAmount").prev().hide();
		$("#lastmonthInvestAmount").prev().hide();
		$("#todayInvestAmount").text(todayInvestAmount).show();
		$("#yesterdayInvestAmount").text(yesterdayInvestAmount).show();
		$("#lastmonthInvestAmount").text(lastmonthInvestAmount).show();
		
	}
	
	/*
	 * 展示实时走势图
	 * */
	function showLineChart(){	
		$('#chart1').highcharts({
			colors: ["#4276a6", "#368c5b"],
	        chart: {
	            type: 'area',
	            backgroundColor: null,
	    		style: {
	    			fontFamily: '"微软雅黑", Tahoma, Arial, Helvetica, STHeiti'
	    		}
	        },
	        title: {
	            text: '当日共有投资：' + investCountTotal + '笔&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;平均每笔：' + investAmountAvg + '万元',
	            useHTML: true,
	            style: {
	    			color: '#ffffff',
	    			fontSize: '20px'
	    		}
	        },
	        xAxis: {
	            labels: {
	                formatter: function () {
	                	if(this.value < 10){
	                		return "0" + this.value + ":00";
	                	}else{
	                		return this.value + ":00";
	                	}
	                     
	                },
	                style: {
	    				color: "#acadb5",
	    				fontSize: '16px'
	    			}
	            },
	            gridLineWidth: 3,
	    		gridLineColor: "#4c4e5e",
	    		min: 0,
	    		max: 24,
	    		startOnTick: true,
	    		endOnTick: true,
	    		tickInterval: 2
	        },
	        yAxis: {
	            title: {
	                text: '',
	                style: {
	    				textTransform: 'uppercase'
	    			}
	            },
	            labels: {
	                formatter: function () {
	                    return Highcharts.numberFormat(this.value/10000,2,".",",") + '万';
	                },
	                style: {
	    				color: "#acadb5",
	    				fontSize: '24px'
	    			}
	            },
	            gridLineWidth: 3,
	    		gridLineColor: "#4c4e5e",
	    		min: 0
	        },
	        tooltip: {
	        	headerFormat: '',
	            pointFormat: '{series.name} <b>{point.y:,.2f}</b>元',
	            borderWidth: 0,
	    		backgroundColor: 'rgba(219,219,216,0.8)',
	    		shadow: false
	        },
	        legend: {
	    		itemStyle: {
	    			fontSize: '18px',
	    			color: '#ffffff'
	    		},
	    		itemHoverStyle: {
	    			color: '#ffffff'
	    		}
	    	},
	        series: [{
	            name: '总投资额',
	            data: amountTotalHour
	        }, {
	            name: '每小时投资额',
	            data: amountHour
	        }],
	        plotOptions: {
	    		area: {
	                marker: {
	                    enabled: false
	                },
	                lineWidth: 3
	            },
	    		candlestick: {
	    			lineColor: '#404048',
	    			fontSize: '16px'
	    		}
	    	}
	    	
	    });
	}
	
	/*
	 * 展示最新融资
	 * */
	function showLastLoans(){
		if(lastLoans.length == 0){
			$(".new-project .loaing_chart_small").attr("src", "style/images/no_data.png").addClass("no_data");
		}else{
			$.each(lastLoans, function(i, loan){
				//var time = loan.LOAN_ENDTIME.split('-');
				var date = loan.LOAN_ENDTIME.split(' ')[0];
				var time = loan.LOAN_ENDTIME.split(' ')[1];
				var loanEndtime = new Date(date.split('-')[0], date.split('-')[1] - 1, date.split('-')[2], time.split(':')[0], time.split(':')[1], time.split(':')[2]);
				var now = new Date();
				var diff = (Date.parse(loanEndtime) - Date.parse(now))/1000;
				var countdown = secondsToDatetime(diff);
				var html = '';
				html += '<li>';
				html += '<div class="project-li">';
				html += '<p class="project-name">项目:[' + loan.TITLE + ']</p>';
				html += '<p class="project-rate">利率：' + loan.INTEREST_RATES + '%</p>';
				html += '<p class="project-countdown">剩余：<em data-seconds="' + diff + '">' + countdown + '</em></p>';
				html += '</div>';
				html += '<div class="project-li">';
				html += '<div class="project-amount">' + Highcharts.numberFormat(loan.AMOUNT/10000,2,".",",") + '万</div>';
				html += '<div class="project-schedule">';
				html += '<span class="schedule-txt">进度</span>';
				html += '<div class="schedule-num">';
				html += '<p class="schedule-num-end" style="width:' + (loan.INVESTED_SHARE * 100/(loan.SURPLUS_SHARE + loan.INVESTED_SHARE)) + '%"></p>';
				html += '</div>';
				html += '</div>';
				html += '</div>';
				html += '</li>';
				$(".new-project-list").append(html);
			});
			if($(".new-project-list").children().length > 0){
				setInterval(function(){
					$.each($(".new-project-list").children(), function(){
						var seconds = $(this).find(".project-countdown em").data("seconds");
						seconds -= 1;
						var countdown = secondsToDatetime(seconds);
						$(this).find(".project-countdown em").data("seconds", seconds).text(countdown);
					});
				}, 1000);
			}
			$(".new-project .loaing_chart_small").hide();
			$(".new-project-list").show();
		}
	}
	
	function secondsToDatetime(seconds){
		var day = parseInt(seconds / 86400);
		var hour = parseInt((seconds - 86400 * day) / 3600);
		var minute = parseInt((seconds - 86400 * day - hour * 3600) / 60);
		var second = seconds - 86400 * day - hour * 3600 - minute * 60;
		var countdown = (day < 10 ? '0' + day : day)  + '天' + (hour < 10 ? '0' + hour : hour) + 'hour' + (minute < 10 ? '0' + minute : minute) + '分' + (second < 10 ? '0' + second : second) + '秒';
		return countdown;
	}
	
	function showCircle(){
		if(investFlows.length == 0){
			$(".capital-flows .loaing_chart_small").attr("src", "style/images/no_data.png").addClass("no_data");
		}else{
			$(".capital-flows .loaing_chart_small").hide();
			$('#chart3').highcharts({
				colors: ["#a5be4e", "#5c95ca", "#dc6263"],
				chart: {
		            backgroundColor: null,
		            reflow: true
		        },
		        title: {
		            text: ''
		        },
		        tooltip: {
		            pointFormat: ''
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: false,
		                cursor: 'pointer',
		                dataLabels: {
		                	format: '<b>{point.percentage:.2f}%</b>',
		                	enabled: true,
		                    style: {
		                        color: 'white'
		                    }
		                },
		                borderWidth: 0,
		                size: '20',
		                innerSize: '10',
		                center: ['50%', '56%'],
		                showInLegend: true
		            }
		        },
		        legend: {
		        	labelFormatter: function(){
		        		return "[" + this.name.replace(/((\s|\S){10})((\s|\S)*)/, '$1...') + "]";
		        	},
		        	layout: 'vertical',  
		            align: 'right',  
		            verticalAlign: 'top',
		            borderWidth: 0,
		            symbolRadius: 4,
		            symbolHeight: 8,
		            symbolWidth: 8,
		            itemStyle:{
		            	fontWeight: "normal",
		            	fontSize: "12px",
		            	color: "white"
		            }
		        },
		        series: [{
		            type: 'pie',
		            name: '',
		            data: investFlows	            
		        }]
		    });
		}
	}
	
	function showColumn(){
		$(".monitor-item-bottom em:eq(0)").text(now.COUNT_NOW);
		$(".monitor-item-bottom em:eq(1)").text(now.COUNT_MAX);
	    $('#chart2').highcharts({
	    	colors: ["#bad55e", "#dc6263"],
	        chart: {
	        	backgroundColor: null,
	            type: 'column',
	            style: {
	    			fontFamily: '"微软雅黑", Tahoma, Arial, Helvetica, STHeiti'
	    		}
	        },
	        title: {
	            text: ''
	        },
	        tooltip: {
	            headerFormat: ''
	        },
	        xAxis: {
		        gridLineWidth: 1,
	    		gridLineColor: "#4c4e5e",
	    		startOnTick: true,
	    		endOnTick: false,
	    		min: 0,
	    		max: 24,
	    		labels: {
	                formatter: function () {
	                	if(this.value < 10){
	                		return "0" + this.value + ":00";
	                	}else{
	                		return this.value + ":00";
	                	}
	                },
	                style: {
	    				color: "#acadb5"
	    			}
	            }
	        },
	        yAxis: {
	        	title: {
	                text: '',
	                style: {
	    				textTransform: 'uppercase'
	    			}
	            },
	            labels: {
	                style: {
	    				color: "#acadb5"
	    			}
	            },
	        	gridLineWidth: 1,
	    		gridLineColor: "#4c4e5e",
	    		allowDecimals: false,
	    		min: 0
	        },
	        legend: {
	            enabled: false
	        },
	        credits: {
	            enabled: false
	        },
	        plotOptions: {
	        	column:{
	        		borderWidth: 0
	        	}
	        },
	        series: [{
	            name: '在线用户人数',
	            data: countNow
	        }]
	    });
	}
	
	function showMap(){
		$(".invest-region .loaing_chart_small").hide();
		$(".invest-region-right").show();
		var data = [];
		$.getJSON('style/js/highcharts/cn-all-sar-taiwan.geo.json', function (geojson) {
			$.each(geojson.features, function(n,obj){
				if(provinceInvest[obj.properties["hc-key"]] != undefined && provinceInvest[obj.properties["hc-key"]] != null){
					data.push({"code":obj.properties["hc-key"], "value":provinceInvest[obj.properties["hc-key"]]});
				}else{
					data.push({"code":obj.properties["hc-key"], "value":0});
				}
				
			});
			$('#chart4').highcharts('Map', {
				
				chart: {
		            backgroundColor: null
		        },
	            title : {
	                text : ''
	            },
				legend: {
					enabled: false
				},
				colorAxis: {
		            dataClasses: [{
		                from: 0,
		                to: 0,
		                color: "#7cb5ec"
		            }, {
		                from: 1,
		                to: 3,
		                color: "#5aa0e4"
		            }, {
		                from: 4,
		                to: 9,
		                color: "#4c91d4"
		            }, {
		                from: 10,
		                to: 20,
		                color: "#2c7fd0"
		            }, {
		                from: 20,
		                color: "#136abf"
		            }]
		        },
	            series : [{
	            	animation: {
	                    duration: 100
	                },
	                data : data,
	                mapData: geojson,
	                joinBy: ['hc-key', 'code'],
	                name: '投资笔数',
	                states: {
	                    hover: {
	                        color: '#BADA55'
	                    }
	                },
	                dataLabels: {
	                    enabled: false,
	                    format: '{point.properties.postal}'
	                }
	            }]
	        });
		});
	}
	
	getData();	
	getDataRegion();
});

var theme = {};

theme["map"] = {};
