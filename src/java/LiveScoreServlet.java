import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import org.json.*;

@WebServlet("/LiveScoreServlet")
public class LiveScoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setIntHeader("Refresh", 10);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String apiUrl = "https://api.cricapi.com/v1/currentMatches?apikey=e12ced0b-a2d2-45d2-8368-701c447213bc&offset=0";
        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet(apiUrl);
            String response = client.execute(request,
                    httpResponse -> EntityUtils.toString(httpResponse.getEntity())
            );

            JSONObject json = new JSONObject(response);

            if (json.has("data")) {
                JSONArray data = json.getJSONArray("data");

                for (int i = 0; i < data.length(); i++) {
                    JSONObject match = data.getJSONObject(i);

                    String name = match.optString("name", "Unknown Match");
                    String status = match.optString("status", "Status unavailable");

                    out.println("<div class='match-box'>");
                    out.println("<h3>" + name + "</h3>");
                    out.println("<p><b>Status:</b> " + status + "</p>");

                    if (match.has("score")) {
                        JSONArray scores = match.getJSONArray("score");

                        for (int j = 0; j < scores.length(); j++) {
                            JSONObject s = scores.getJSONObject(j);

                            String inning = s.optString("inning");
                            String r = s.optString("r");
                            String w = s.optString("w");
                            String o = s.optString("o");

                            out.println("<p><b>" + inning + ":</b> "
                                    + r + "/" + w + " in " + o + " overs</p>");
                        }
                    }

                    out.println("</div>");
                    out.println("<hr>");
                }
            } else {
                out.println("<p style='color:black;'>No live matches available.</p>");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            out.println("<p style='color:red;'>Error fetching score: "
                    + ex.getMessage() + "</p>");
        }

        out.close();
    }
}