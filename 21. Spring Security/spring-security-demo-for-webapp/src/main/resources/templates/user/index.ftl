<html>
<head>
    <#include "../include/coreDependencies.ftl" >
    <title>Users</title>
</head>
<body>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Логін</th>
        <th scope="col">Роль</th>
        <th scope="col">Активний</th>
    </tr>
    </thead>
    <tbody>
    <#list users as user >
        <tr>
            <th scope="row">${user.id}</th>
            <td>${user.username}</td>
            <td>${user.role}</td>
            <td>${user.active?then('активний', 'неактивний')}</td>
        </tr>
    </#list>
    </tbody>
</table>
<a href="/register">Додати користувача</a>
</body>
</html>