<%@page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>出异常啦！</title>
		<link href="${allctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${allctx}/css/content.css" rel="stylesheet" type="text/css" />
		<script src="${ctx}/js/jquery/jquery.js" type="text/javascript"></script>
		
		<style type="text/css">
			body{
				font-size: 12px;
				margin:0px auto;
			}
		</style>
		<script type="text/javascript">
			if ($(self.parent.document).find("#tab-loading-mask")) {
	 			$(self.parent.document).find("#tab-loading-mask").fadeOut();
	 		}
	 	</script>		
	</head>
	<body>
		<div class="page-error">
			<div class="page-img img500"></div>
			<div class="page-solve">
				<span class="p-s-t500">很抱歉，服务出现故障，请尝试以下方法：</span>
				<span >1.检查网址是否正确。</span>  
				<span >2.将情况反馈给系统管理员。</span>
				<span >3.<a href = "${ctx}/j_admin_logout" >重新登录</a></span>
			</div>
			<div class="clear"></div>
			   
      <%--       <s:property value="exceptionStack"/>    
            <s:property value="exception.message"/>   --%>   
       		   
            <%  
              //final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());  
              //logger.error("系统异常>>>用户："+cn.gov.syzj.common.util.UserUtils.getCurrentUser().getLoginName());
              //logger.error("系统异常>>>错误堆栈："+request.getAttribute("exceptionStack"));  
            %>  
		</div>
	</body>
</html>