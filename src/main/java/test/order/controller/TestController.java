package test.order.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import test.order.service.impl.TesttServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@RestController
public class TestController {
    @Resource
    private TesttServiceImpl testtServiceImpl;
    //http://localhost:8080/testselect
    @RequestMapping(value = "/testselect")
    @ResponseBody
    public void testselect(HttpServletRequest request){
        String str="";
        testtServiceImpl.query();
        //

    }
    //http://localhost:8080/testinsert
    @RequestMapping(value = "/testinsert")
    @ResponseBody
    public void testinsert(HttpServletRequest request){
        testtServiceImpl.insert();
    }
}
