package com.sbogdanschi.springboot.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public abstract class BaseController {

    private static final Logger LOGGER = LogManager.getLogger(BaseController.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(final HttpServletRequest request, final Exception exception, ModelAndView view) {
        LOGGER.error("Request Error call.", exception);
        view.setViewName("error");
        return view;
    }

}