package com.example.trs.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Mypointcut {



    @Pointcut("execution( * com.example.trs.service.AbisEmployeeService.checkLogin(..))")
    public static void login(){


    }




}
