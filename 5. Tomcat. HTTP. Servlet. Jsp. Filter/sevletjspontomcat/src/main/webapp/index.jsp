<html>
<head>
    <title>JSP page</title>
</head>
<body>

<h2>Hello <%= session.getAttribute("name") %>! I know, you're <%= session.getAttribute("age") %>!</h2>

</body>
</html>