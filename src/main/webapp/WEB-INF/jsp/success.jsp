<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Success Page</title>
</head>
<body>

Success！<br>
${requestScope.name}
<br><br>
${requestScope.names}
<br><br>

request:${requestScope.user }
<br><br>

session:${sessionScope.user }
<br><br>

ModelAttribute:${requestScope.user }
<br><br>


ModelAttribute:${requestScope.user }
<br><br>

<fmt:message key="messages.name"></fmt:message>


</body>
</html>