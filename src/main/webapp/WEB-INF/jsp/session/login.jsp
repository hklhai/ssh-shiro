<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2017/4/20
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../commons/taglibs.jsp" %>
<%@ include file="../commons/meta.jsp" %><%@ page isELIgnored="false"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
${pageContext.request.contextPath}
<form method="post" action="${ctx}/saveSe" target="iframe">

    <input name="name" type="text"/>
    <input name="passwd" type="text"/>
    <input name="submit1" type="submit" value="save session"/>
    <br>
    <iframe name="iframe"></iframe>
</form>
<br>
<form method="post" action="${ctx}/getSe" target="iframe1">

    <input name="submit2" type="submit" value="get session"/>
    <br>
    <iframe name="iframe1"></iframe>
</form>
</body>
</html>
