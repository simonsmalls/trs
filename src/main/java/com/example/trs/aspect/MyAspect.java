package com.example.trs.aspect;

import com.example.trs.exceptions.EmployeeNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;

@Component
@Aspect
public class MyAspect {
    Logger log= LogManager.getLogger("exceptionLogger");




    @AfterThrowing(pointcut="Mypointcut.login()",throwing="exc")
    public void err(Exception exc){
        if (exc instanceof EmployeeNotFoundException) {
            System.out.println("exception thrown");


            log.error(exc.getMessage());
            log.info(exc.getMessage());
        }
    }





}
