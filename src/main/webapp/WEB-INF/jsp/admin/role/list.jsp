<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="zhangfn" uri="http://portal/tags/admin-functions" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>角色管理</title>
    <jsp:include page="/WEB-INF/jsp/admin/head.jsp" />
    <base href="${basePath}">
    <link rel="stylesheet" href="resources/css/admin/css.css">
</head>
<body>

<c:if test="${not empty msg}">
    <div class="message">${msg}</div>
</c:if>

<shiro:hasPermission name="role:create">
    <a href="admin/role/create">角色新增</a><br/>
</shiro:hasPermission>
<table class="table">
    <thead>
        <tr>
            <th>角色名称</th>
            <th>角色描述</th>
            <th>拥有的资源</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${roleList}" var="role">
            <tr>
                <td>${role.role}</td>
                <td>${role.description}</td>
               <td>${zhangfn:resourceNames(role.resourceIds)}</td>
                <td>
                    <shiro:hasPermission name="role:update">
                        <a href="admin/role/${role.id}/update">修改</a>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="role:delete">
                        <a href="admin/role/${role.id}/delete">删除</a>
                    </shiro:hasPermission>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>