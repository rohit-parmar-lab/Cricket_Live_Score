import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/Upload_Advertisement")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,
    maxFileSize = 1024 * 1024 * 50,
    maxRequestSize = 1024 * 1024 * 100
)
public class Upload_Advertisement extends HttpServlet {

    private static final String UPLOAD_DIR = "UploadedVideos";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1️⃣ Folder path
        String uploadPath = getServletContext().getRealPath("/" + UPLOAD_DIR);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        // 2️⃣ Get file
        Part filePart = request.getPart("video");
        String fileName = filePart.getSubmittedFileName();

        if (fileName != null && !fileName.isEmpty()) {
            filePart.write(uploadPath + File.separator + "add.mp4");

            // 3️⃣ Send filename to JSP
            request.setAttribute("videoName", fileName);
            request.getRequestDispatcher("/Video.jsp").forward(request, response);
        } else {
            response.getWriter().println("No video selected!");
        }
    }
}