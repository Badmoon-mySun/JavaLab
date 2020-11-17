<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
</head>
<body>
<table>
    <tr>
        <th>First name</th>
        <th>Second name</th>
    </tr>
    <#list users as user>
        <tr>
            <td>${user.firstName}</td>
            <td>${user.secondName}</td>
        </tr>
    </#list>
</table>
</body>
</html>