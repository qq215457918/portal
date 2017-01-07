<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>组织机构管理</title>
    <jsp:include page="/WEB-INF/jsp/admin/head.jsp" />
    <link rel="stylesheet" href="${basePath}resources/css/admin/layout-default-latest.css">
</head>
<body>

<iframe name="content" class="ui-layout-center"
        src="" frameborder="0" scrolling="auto"></iframe>

<iframe name="tree" class="ui-layout-west"
        src="${basePath}admin/organization/tree" frameborder="0" scrolling="auto"></iframe>
<script src="${basePath}resources/js/jquery-1.10.2.min.js"></script>
<script src="${basePath}resources/js/jquery.layout-latest.min.js"></script>
<script>
    $(function () {
        $(document).ready(function () {
            $('body').layout({ applyDemoStyles: true });
        });
    });
</script>
</body>
</html>