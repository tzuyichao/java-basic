package com.example.spring.aop.aspect;

import com.example.spring.aop.model.Book;
import com.example.spring.aop.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ContentAuthorizationAspect {
    private static final Logger log = LoggerFactory.getLogger(ContentAuthorizationAspect.class);
    private UserProfileService userProfileService;

    public ContentAuthorizationAspect(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @Around("execution(com.example.spring.aop.model.Book getBook(..)) && args(id, token)")
    public Object contentPrune(ProceedingJoinPoint pjp, Long id, String token) throws Throwable {
        log.info("called. id={}, token={}", id, token);
        Book book = (Book) pjp.proceed(new Object[] {id, token});
        log.info("before process: {}", book);
        if(token.equals("token1")) {
            book.setCost(null);
        }
        return book;
    }
}
