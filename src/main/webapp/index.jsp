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
    <title>Title</title>
</head>
<body>
<h3>查询部门列表</h3>

<button>添加</button>
<c:if test="${errorInfo==null && errorInfo!=''}"><span>${errorInfo}</span></c:if>

<table>
    <tr>
        <td align="center">序号</td>
        <td align="center">id</td>
        <td align="center">名称</td>
        <td align="center">操作</td>
    </tr>

        <c:forEach items="${deptList}" var="dept" varStatus="vs">

            <tr align="center">
                <td>${(page.current-1)*page.size+vs.count}</td>

                <%--
                <td>${vs.index}</td>
                <!-- 自动序号 -->
                <td>${vs.count}</td>
                <td>${vs.first}</td>
                <td>${vs.last}</td>
                --%>

                <td>${dept.id}</td>
                <td>${dept.deptName}</td>
                <td>
                    <a href="/dept/del?id=${dept.id}">删除</a>
                </td>
            </tr>
        </c:forEach>

</table>



<c:if test="true">
    <c:if test="${page.current!=1}">
        <a href="/dept/list?pageNum=1&pageSize=${page.size}">首页</a>
    </c:if>

    <c:if test="${page.current==1}">
        <span>首页</span>
    </c:if>

</c:if>

<c:if test="true">
    <c:if test="${page.current>1}">
        <a href="/dept/list?pageNum=${page.current-1}&pageSize=${page.size}">上一页</a>
    </c:if>

    <c:if test="${page.current<=1}">
        <span>上一页</span>
    </c:if>
</c:if>

<span>&nbsp;&nbsp;${page.current}&nbsp;&nbsp;</span>

<c:if test="true">
    <c:if test="${page.current*page.size<page.total}">
        <a href="/dept/list?pageNum=${page.current+1}&pageSize=${page.size}">下一页</a>
    </c:if>

    <c:if test="${page.current*page.size>=page.total}">
        <span>下一页</span>
    </c:if>
</c:if>

<c:if test="true">

    <c:if test="${page.current*page.size<page.total}">
        <a href="/dept/list?pageNum=${page.pages}&pageSize=${page.size}">尾页</a>
    </c:if>

    <c:if test="${page.current*page.size>=page.total}">
        <span>尾页</span>
    </c:if>
</c:if>


</body>
</html>
