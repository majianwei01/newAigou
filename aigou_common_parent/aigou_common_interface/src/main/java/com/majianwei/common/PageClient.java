package com.majianwei.common;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@RequestMapping("/common")
@FeignClient(value = "COMMON-PRIVODER",fallbackFactory = PageClientCallBackFactory.class)//映射的服务名：在注册中心的服务名,告诉使用哪一个托底类
public interface PageClient {

    @RequestMapping(value = "/page",method = RequestMethod.POST)
        public  void createPage(@RequestBody Map<String, Object> params);

}
