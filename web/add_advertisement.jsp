<%-- 
    Document   : add_advertisement
    Created on : Feb 17, 2026, 6:43:24 PM
    Author     : ROHIT PARMAR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Upload Advertisement Video</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, Helvetica, sans-serif;
            background: linear-gradient(135deg, #4e73df, #1cc88a);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background: #ffffff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 15px 35px rgba(0,0,0,0.2);
            width: 350px;
            text-align: center;
        }

        h2 {
            margin-bottom: 25px;
            color: #333;
        }

        .file-input {
            margin-bottom: 20px;
        }

        input[type="file"] {
            padding: 8px;
            width: 100%;
            border: 1px solid #ddd;
            border-radius: 6px;
        }

        .btn {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 6px;
            background-color: #4e73df;
            color: white;
            font-size: 16px;
            cursor: pointer;
            transition: 0.3s ease;
        }

        .btn:hover {
            background-color: #2e59d9;
        }

        .note {
            font-size: 12px;
            color: #777;
            margin-top: 10px;
        }
/*        .back-btn {
                display: inline-block;
                background-color: #2980b9;
                color: white;
                text-align: center;
                text-decoration: none;
            }
            .back-btn:hover {
                background-color: #1f6690;
            }
            .buttons {
                display: flex;
                justify-content: space-between;
            }*/

        @media (max-width: 400px) {
            .container {
                width: 90%;
                padding: 25px;
            }
        }
    </style>
</head>

<body>

    <div class="container">
        <h2>Upload Advertisement Video</h2>

        <form action="Upload_Advertisement" method="post" enctype="multipart/form-data">
            
            <div class="file-input">
                <input type="file" name="video" accept="video/mp4" required>
            </div>

            <input type="submit" value="Upload Video" class="btn">
<!--            <a href="Admin_Home" class="back-btn">Back</a>-->
            
            <div class="note">
                Only MP4 video format is allowed.
            </div>

        </form>
    </div>

</body>
</html>
