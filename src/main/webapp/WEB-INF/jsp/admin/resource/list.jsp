<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>权限菜单管理</title>
    <base href="${basePath}">
    <jsp:include page="/WEB-INF/jsp/admin/head.jsp" />
    <link rel="stylesheet" href="${basePath}/resources/css/admin/css.css">
    <style>
        #table th, #table td {
            font-size: 14px;
            padding : 8px;
        }
    </style>
</head>
<body>

<shiro:hasPermission name="resource:create">
    <a href="${basePath}admin/resource/-1/appendChild" style="float: right; margin-right: 12%;">新增权限菜单</a><br/>
</shiro:hasPermission>

<table id="table" style="margin-left: 15%; width: 70%;">
    <thead>
        <tr>
            <th>名称</th>
            <th>类型</th>
            <th>URL路径</th>
            <th>权限字符串</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${resourceList}" var="resource">
            <tr data-tt-id='${resource.id}' <c:if test="${not resource.rootNode}">data-tt-parent-id='${resource.parentId}'</c:if>>
                <td>${resource.name}</td>
                <td>${resource.type.info}</td>
                <td>${resource.url}</td>
                <td>${resource.permission}</td>
                <td>
                    <shiro:hasPermission name="resource:create">
                        <c:if test="${resource.type ne 'button'}">
                        	<a href="${basePath}admin/resource/${resource.id}/appendChild">添加子菜单</a>
                        </c:if>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="resource:update">
                        <a href="${basePath}admin/resource/${resource.id}/update">修改</a>
                    </shiro:hasPermission>
                    <c:if test="${not resource.rootNode}">
	                    <shiro:hasPermission name="resource:delete">
	                        <a class="deleteBtn" href="javascript:;" data-id="${resource.id}">删除</a>
	                    </shiro:hasPermission>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<script>
    $(function() {
    	var basePath = $("base").attr('href');
    	
        $(".deleteBtn").click(function() {
        	var resourcesId = $(this).data("id");
        	$.ajax({
                "dataType": 'json',
                "type": "POST",
                "url": basePath + "admin/resource/checkHasChildAndPromi",
                "data": {
                	parentId : $(this).data("id")
                },
                "success": function(data){
                	if(data) {
                		if(data.status == 1) {
                    		if(confirm("该菜单含有子菜单或关联权限, 会级联删除或移除关联关系, 确认删除吗?")) {
                                location.href = basePath + "admin/resource/" + resourcesId + "/delete";
                            }
                    	}else {
                    		if(confirm("确认删除吗?")) {
                                location.href = basePath + "admin/resource/" + resourcesId + "/delete";
                            }
                    	}
                	}else {
                		alert("操作失败, 请刷新后重试");
                	}
                },
                "error": function(data){
                    alert("操作失败, 请刷新后重试");
                }
            });
        });
    });
</script>
</body>
</html>