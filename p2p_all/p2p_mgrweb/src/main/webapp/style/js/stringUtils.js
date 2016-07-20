(function( $ ){
	$.formatBankCard = function(srcString){
		var newString = "";
		newString = srcString.replace(/(\w{4})/g,"$1"+" ")
		return newString;
	};
	
	$.formatIdCard = function(srcString){
		var newString = "";
		newString = srcString.replace(/(\w{6})(\w{0,8})(\w{0,4})/g,"$1"+" "+"$2"+" "+"$3");
		return newString;
	}
	
	$.formatCellphone = function(srcString){
		var newString = "";
		newString = srcString.replace(/(\w{3})(\w{0,4})(\w{0,4})/g,"$1"+" "+"$2"+" "+"$3")
		return newString;
	}
})(jQuery);