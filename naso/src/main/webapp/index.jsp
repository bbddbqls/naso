<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Index Page</title>
</head>
<body>
<%
response.sendRedirect(request.getContextPath() + "/post/*");
%>
</body>
</html>