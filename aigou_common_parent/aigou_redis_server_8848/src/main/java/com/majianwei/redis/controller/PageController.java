package com.majianwei.redis.controller;

import com.majianwei.common.PageClient;
import com.majianwei.redis.util.VelocityUtils;
import com.majianwei.util.constant.GlobalConstant;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/common")
public class PageController implements PageClient {

    /**
     * 告诉我使用哪一个模板；
     * 使用哪些数据
     * 生成的静态页面要放到哪里去？
     * @param params
     */
    @RequestMapping(value = "/page",method = RequestMethod.POST)
    @Override
    public void createPage(@RequestBody Map<String, Object> params) {

        //使用velocity完成生成静态页面：
        // 使用某一个模板，结合需要的数据，在指定位置生成一个HTML页面
        // staticByTemplate(Object model, String templateFilePathAndName, String targetFilePathAndName)
        System.out.println(params);

        //     public  static  final  String PAGE_MODEL="page_model";//页面静态需要的数据
        //    public  static  final  String TEMPLATE_FILE="template_file";//页面静态需要的模板路径
        //    public  static  final  String TARGET_FILE="target_file";//页面静态生成的静态页面路
        Object page_model = params.get(GlobalConstant.PAGE_MODEL);
        String  template_file = (String)params.get(GlobalConstant.TEMPLATE_FILE);
        String target_file = (String)params.get(GlobalConstant.TARGET_FILE);
        System.out.println("page_model:"+page_model);
        System.out.println("template_file:"+template_file);
        System.out.println("target_file:"+target_file);
        VelocityUtils.staticByTemplate(page_model,template_file,target_file);

    }
}
