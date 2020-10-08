package com.heo.dae.msgbot.common;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.SSLContext;

import com.heo.dae.msgbot.enums.Messengers;
import com.heo.dae.msgbot.enums.Property;
import com.heo.dae.msgbot.exception.PropertyException;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestUtil implements InitializingBean {

    @Autowired
    RestTemplate restTemplate;

    @Value("${line.channel_access_token}")
    String LINE_CHANNEL_ACCESS_TOKEN;

    @Bean
    public RestTemplate restTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        TrustStrategy acceptingTrustStrategy = (new TrustStrategy() {

            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                // TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String
                // authType) -> true;
                return true;
            }
        });

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        return restTemplate;
    }

    public <T> void post(String url, Map<String, Object> req, Messengers type) throws Exception {
        HttpHeaders headers = createHttpHeaders(type);
        
        HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(req, headers);
        
        restTemplate().postForLocation(url, entity);
    }

    /**
     * create header
     * @return HttpHeaders
     */
    private HttpHeaders createHttpHeaders(Messengers type) {
        HttpHeaders headers = new HttpHeaders();
        
        if(type.equals(Messengers.LINE)){
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer " + LINE_CHANNEL_ACCESS_TOKEN);
        }

        return headers;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(LINE_CHANNEL_ACCESS_TOKEN.isEmpty()){
            throw new PropertyException(Property.LINE_CHANNEL_ACCESS_TOKEN);
        }

    }
}