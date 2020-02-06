<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.1/build/pure-min.css">
        <title>Login Page</title>
    </head>
    <body style="padding: 15px">
        <form class="pure-form" method="post" action="/SpringMVC/mvc/portfolio/login">
            <fieldset>
                <legend>Login form</legend>
                
                <input type="text" name="username" placeholder="Username"><p />
                <input type="password" name="password" placeholder="Password"><p />
                
                <button type="submit" class="pure-button pure-button-primary">Sign in</button>
            </fieldset>
        </form>
    </body>
</html>
