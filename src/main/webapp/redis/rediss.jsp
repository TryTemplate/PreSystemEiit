<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ljggg
  Date: 2022/8/10
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Redis Test Page</title>
</head>
<body>

<h3>Redis Test Page</h3>

<br /><br /><br /><br />


<c:forEach items="${value}" var="val" varStatus="vs">
${val}<br />
</c:forEach>


</body>
</html>
