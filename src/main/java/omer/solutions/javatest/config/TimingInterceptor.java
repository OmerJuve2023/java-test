package omer.solutions.javatest.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TimingInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TimingInterceptor.class);
    private static final String START_TIME_ATTRIBUTE = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {
        request.setAttribute(START_TIME_ATTRIBUTE, System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request,
                           @NonNull HttpServletResponse response,
                           @NonNull Object handler,
                           ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                @NonNull Object handler,
                                Exception ex) {
        long startTime = (long) request.getAttribute(START_TIME_ATTRIBUTE);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        String logMessage = String.format("[%s] %s - %dms", request.getMethod(), request.getRequestURI(), duration);

        try {
            Files.write(Paths.get("response_times.log"), (logMessage + System.lineSeparator()).getBytes(),
                    StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            log.info("Response time log: {}", logMessage);
        } catch (IOException e) {
            log.error("Error writing to log file", e);
        }
    }
}