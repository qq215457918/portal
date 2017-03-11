<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>编辑权限菜单信息</title>
    <base href="${basePath}">
    <jsp:include page="/WEB-INF/jsp/admin/head.jsp" />
    <link rel="stylesheet" href="resources/css/admin/css.css">
</head>
<body>
    <form:form method="post" commandName="resource">
        <c:if test="${not empty parent}">
        	<form:hidden path="id"/>
	        <form:hidden path="available"/>
	        <form:hidden path="parentId"/>
	        <form:hidden path="parentIds"/>
        </c:if>
        <div class="form-group">
            <label>父菜单名称：</label>
           	<select name="parentId" style="width: 154px; height: 27px;">
           		<option value="">请选择</option>
           		<c:forEach items="${resourcesList}" var="parents">
           			<option value="${parents.id }" <c:if test="${parent.id eq parents.id}">selected="selected"</c:if>>${parents.name }</option>
           		</c:forEach>
           	</select>
        </div>

        <div class="form-group">
            <form:label path="name"><c:if test="${not empty parent}">子</c:if>菜单名称：</form:label>
            <form:input path="name" maxlength="100"/>
        </div>
        <div class="form-group">
            <form:label path="type">类型：</form:label>
            <form:select path="type" items="${types}" itemLabel="info"/>
        </div>

        <div class="form-group">
            <form:label path="url">URL路径：</form:label>
            <form:input path="url" maxlength="200"/>
        </div>

        <div class="form-group">
            <form:label path="permission">权限字符串：</form:label>
            <form:input path="permission" maxlength="100"/>
        </div>

        <form:button>${op}</form:button>
    </form:form>
</body>
</html>