package com.majianwei.common;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component // @controller  @service   @repository
public class PageClientCallBackFactory implements FallbackFactory<PageClient> {


    @Override
    public PageClient create(Throwable throwable) {
        return new PageClient() {
            @Override
            public void createPage(Map<String, Object> params) {

            }
        };
    }
}
