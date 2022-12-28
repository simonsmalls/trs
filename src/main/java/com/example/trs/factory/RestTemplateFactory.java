package com.example.trs.factory;

import com.example.trs.interceptor.MyHttpRequestInterceptor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.apache.http.HttpHost;

@Component
public class RestTemplateFactory
        implements FactoryBean<RestTemplate>, InitializingBean {
    private RestTemplate restTemplate;
    public RestTemplate getObject() {
        return restTemplate;
    }
    public Class<RestTemplate> getObjectType() {
        return RestTemplate.class;
    }
    public boolean isSingleton() {
        return true;
    }
    public void afterPropertiesSet() {
        HttpHost host = new HttpHost("localhost", 8888, "http");
        restTemplate = new RestTemplate
                (new MyHttpRequestInterceptor(host));
    }
}