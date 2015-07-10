<%@page import="com.hxy.icsLogConvert.Client"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>转换ICS日志中SQL脚本</title>
</head>
<body>
	<form action="convert_session.jsp">
		待转换脚本 <input type="text" name="ICSSQL"> <input type="submit"
			value="提交">
	</form>

	<%
		String ICSSQL = request.getParameter("ICSSQL");
		String converted = Client.getSQL(ICSSQL);
		/* session.setAttribute("LogName", Name); */
	%>
	<br /> 转换结果：
	<br />
	<%=converted%>
</body>
</html>