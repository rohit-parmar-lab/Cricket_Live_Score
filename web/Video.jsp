<%-- 
    Document   : Video
    Created on : Feb 18, 2026, 12:41:18 PM
    Author     : ROHIT PARMAR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Uploaded Video</title>
        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                flex-direction: column;
                font-family: Arial, sans-serif;
            }
            video {
                max-width: 100%;
                border: 2px solid #ccc;
                border-radius: 8px;
            }
            h2 {
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>

        <h2>Advertisement</h2>
            
        <%
            //String videoName = (String) request.getAttribute("videoName");
            //if(videoName != null){
        %>
        <video width="300" controls autoplay muted preload="metadata">
            <%-- <source src="<%= request.getContextPath() %>/UploadedVideos/<%= videoName %>" type="video/mp4"> --%>
            <source src="UploadedVideos/add.mp4" type="video/mp4">
            Your browser does not support the video tag.
        </video>
        <%--      
      <%
          } else {
      %>
          <p>No video uploaded!</p>
      <%
          }
      %>
        --%>
    </body>
</html>
