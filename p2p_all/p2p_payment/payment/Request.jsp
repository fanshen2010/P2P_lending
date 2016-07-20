<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<%@page import="payment.tools.util.XmlUtil"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模拟商户</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/css/Common.css">
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/css/Form.css">
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/css/Table.css">
<script language="JavaScript" type="text/JavaScript">

function doSubmit() {    
   document.form1.submit();
}

</script>
</head>
<body onload="doSubmit()">
	<p class="title">模拟商户</p>

	<form action="${action}" name="form1" method="POST">




		<input type="hidden" name="message" value="${message}" /> <input
			type="hidden" name="signature" value="${signature}" /> <input
			type="hidden" name="txCode" value="${txCode}" /> <input
			type="hidden" name="txName" value="${txName}" /> <input
			type="hidden" name="Flag" value="${Flag}" />
	</form>

</body>
</html>