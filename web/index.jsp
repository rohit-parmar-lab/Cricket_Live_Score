
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>User Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                margin: 0;
                padding: 0;
                background: linear-gradient(135deg, #6a11cb, #2575fc);
                height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .login-card {
                background: white;
                width: 350px;
                padding: 30px 25px;
                border-radius: 12px;
                box-shadow: 0 15px 25px rgba(0,0,0,0.2);
                text-align: center;
            }

            .login-card h2 {
                margin-bottom: 25px;
                color: #333;
                font-weight: 700;
            }

            .login-card input[type="email"],
            .login-card input[type="password"] {
                width: 100%;
                padding: 12px 10px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 6px;
                font-size: 14px;
            }

            .login-card input[type="submit"] {
                width: 100%;
                padding: 12px;
                margin-top: 15px;
                border: none;
                border-radius: 6px;
                background: linear-gradient(45deg, #6a11cb, #2575fc);
                color: white;
                font-size: 16px;
                font-weight: bold;
                cursor: pointer;
                transition: 0.3s;
            }

            .login-card input[type="submit"]:hover {
                background: linear-gradient(45deg, #2575fc, #6a11cb);
            }

            .login-card .links {
                margin-top: 20px;
            }

            .login-card .links a {
                display: block;
                margin: 6px 0;
                text-decoration: none;
                color: #2575fc;
                font-weight: 500;
                transition: 0.2s;
            }

            .login-card .links a:hover {
                color: #6a11cb;
            }

            /* Optional: small icon above login title */
            .login-card .icon {
                font-size: 50px;
                color: #6a11cb;
                margin-bottom: 15px;
            }
            .login-card .error {
                color: red;         /* red color for the error */
                margin-bottom: 10px;
                font-weight: bold;
            }

        </style>
    </head>
    <body>

        <div class="login-card">
            <div class="icon">🔒</div>
            <h2>Audience Login</h2>

            <form action="CheckLogin" method="post">
                <input type="email" name="email" placeholder="Enter Email" required>
                <input type="password" name="pwd" placeholder="Enter Password" required>
                <!-- Error message -->
                <% if (request.getAttribute("errorMessage") != null) {%>
                <div class="error">
                    <%= request.getAttribute("errorMessage")%>
                </div>
                <% }%>
                <input type="submit" value="Login">
            </form>

            <div class="links">
                <a href="forget.html">Forgot Password?</a>
                <a href="register.html">New User? Register</a>
                <!--<a href="Admin.html">Admin</a>-->
                <a href="LiveScoreServlet">🏏 Cricket Live Score</a>
            </div>
        </div>
    </body>
</html>

