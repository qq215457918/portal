<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<base href="${basePath}">
    <link rel="stylesheet" href="${basePath}resources/css/admin/css.css">
</head>
<body>
    <form:form id="form" method="post" commandName="child">
        <form:hidden path="id"/>
        <form:hidden path="parentsId"/>

        <div class="form-group">
            <label>父节点名称：</label>
            ${parent.name}
        </div>

        <div class="form-group">
            <form:label path="name">子节点名称：</form:label>
            <form:input path="name"/>
        </div>

        <form:button id="appendChildBtn">保存</form:button>
    </form:form>
    
    <script src="${basePath}resources/js/jquery-1.11.0.min.js"></script>
    <script>
    	base = $("base").attr('href');
        $(function() {
            $("#appendChildBtn").click(function() {
                $("#form")
                    .attr("action", base + "admin/organization/-1/update")
                    .submit();
                return false;
            });
        });
    </script>
</body>
</html>