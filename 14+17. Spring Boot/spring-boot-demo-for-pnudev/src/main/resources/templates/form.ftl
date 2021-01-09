<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>

<#if errorMessage?? >
    <h2>${errorMessage}</h2>
</#if>

<#if name?? >
    <h2>Submitted data: ${(name)!} ${(surname)!}</h2>
</#if>

<h4>${attr}</h4>

<form method="post">
    <input type="text" name="firstName" placeholder="First name"><br>
    <input type="text" name="secondName" placeholder="Second name"><br>
    <button>Submit</button>
    <button formaction="/second">Submit</button>
    <button formaction="/third">Submit</button>
</form>

</body>
</html>