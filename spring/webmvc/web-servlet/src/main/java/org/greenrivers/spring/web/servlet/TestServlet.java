package org.greenrivers.spring.web.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Slf4j
@WebServlet(urlPatterns = "/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("----- begin servlet -----");
        try {
            TimeUnit.SECONDS.sleep(3);
            resp.setContentType(MediaType.TEXT_HTML_VALUE);
            PrintWriter out = resp.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Hello, World!</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Welcome!</h1>");
            out.println("</body>");
            out.println("</html>");

        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
        }
        log.info("----- end servlet -----");
    }
}
