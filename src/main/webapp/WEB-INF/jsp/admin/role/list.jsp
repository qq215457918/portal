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
    <style type="text/css">
    	th {
    		text-align: center;
    	}
    	.table {
    		margin-left: 10%;
    		margin-top: 1%;
    	}
    </style>
</head>
<body>
<shiro:hasPermission name="role:create">
    <a href="admin/role/create" style="float: right; margin-right: 12%;">新增角色</a><br/>
</shiro:hasPermission>
<table class="table">
    <thead style="border-top: 1px solid #666;">
        <tr>
            <th style="width: 15%;">角色名称</th>
            <th style="width: 15%;">角色描述</th>
            <th>拥有的资源</th>
            <th style="width: 10%;">操作</th>
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
                        <a class="deleteBtn" href="javascript:;" data-id="${role.id}">删除</a>
                    </shiro:hasPermission>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<script>
    $(function() {
    	var basePath = $("base").attr('href');
        $(".deleteBtn").click(function() {
        	var roleId = $(this).data("id");
        	$.ajax({
                "dataType": 'json',
                "type": "POST",
                "url": basePath + "admin/role/checkHasPromi",
                "data": {
                	roleId : roleId
                },
                "success": function(data){
                	if(data) {
                		if(data.status == 1) {
                    		if(confirm("该角色关联权限, 会移除关联关系, 确认删除吗?")) {
                                location.href = basePath + "admin/role/" + roleId + "/delete";
                            }
                    	}else {
                    		if(confirm("确认删除吗?")) {
                                location.href = basePath + "admin/role/" + roleId + "/delete";
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