package com.mitrran.msulearningapi.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/error")
public class MsuErrorController implements ErrorController {

    @GetMapping("")
    @ResponseBody
    public String error() {
        return "<h4>    Error return to <a href=\"http://localhost:4200/\">home</a> page</h4>";
    }

}
