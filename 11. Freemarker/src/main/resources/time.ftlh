<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<#if time??>
    This template was used at ${time?date?string["HH:mm, dd.MM.yyyy"]}
<#else>
    No time(((
</#if>
</body>
</html>