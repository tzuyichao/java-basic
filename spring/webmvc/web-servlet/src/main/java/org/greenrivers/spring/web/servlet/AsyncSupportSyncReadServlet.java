package org.greenrivers.spring.web.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Slf4j
@WebServlet(urlPatterns = "/syncRead", asyncSupported = true)
public class AsyncSupportSyncReadServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("----- begin servlet -----");
        final AsyncContext asyncContext = req.startAsync();
        asyncContext.start(() -> {
            try {
                log.info("---- async res begin ----");
                long start = System.currentTimeMillis();
                final ServletInputStream inputStream = asyncContext.getRequest().getInputStream();
                try {
                    byte[] byteBuffer = new byte[1024];
                    int readBytes = 0;
                    int total = 0;
                    while((readBytes = inputStream.read(byteBuffer)) > 0) {
                        total += readBytes;
                    }
                    long cost = System.currentTimeMillis() - start;
                    log.info("Read: {} bytes, cost: {}", total, cost);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                TimeUnit.SECONDS.sleep(3);
                PrintWriter out = asyncContext.getResponse().getWriter();
                out.println("Hello");
                log.info("---- async res end ----");
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                asyncContext.complete();
            }
        });
        log.info("----- end servlet -----");
    }
}
