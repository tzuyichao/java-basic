package org.greenrivers.spring.web.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@WebServlet(urlPatterns = "/asyncListener", asyncSupported = true)
public class AsyncListenerTestServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("----- enter async listener servlet -----");
        final AsyncContext asyncContext = req.startAsync();
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent asyncEvent) throws IOException {
                log.info("onComplete");
            }

            @Override
            public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                log.info("onTimeout");
            }

            @Override
            public void onError(AsyncEvent asyncEvent) throws IOException {
                log.info("onError");
            }

            @Override
            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
                log.info("onStartAsync");
            }
        });
        try {
            PrintWriter out = asyncContext.getResponse().getWriter();
            out.println("Hello");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            asyncContext.complete();
        }
        log.info("----- end async listener servlet -----");
    }
}
