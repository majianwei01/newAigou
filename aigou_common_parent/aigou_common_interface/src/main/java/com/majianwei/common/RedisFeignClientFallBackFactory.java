package com.majianwei.common;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RedisFeignClientFallBackFactory implements FallbackFactory<RedisFeignClient> {
    @Override
    public RedisFeignClient create(Throwable throwable) {
        return new RedisFeignClient() {
            @Override
            public void set(String key, String value) {

            }

            @Override
            public String get(String key) {
                return "该页面正在抢修。。。";
            }
        };
    }
}
