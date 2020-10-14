package com.heo.dae.msgbot.common;

import java.net.http.HttpResponse;
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
import com.heo.dae.msgbot.vo.Values;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestUtil{

    @Bean
    public RestTemplate restTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        TrustStrategy acceptingTrustStrategy = (new TrustStrategy() {

            // ssl 인증 무시
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

    /**
     * 
     * @param <T>
     * @param url
     * @param req
     * @param type
     * @return api 호출 결과 상태 반환
     * @throws Exception
     */
    public <T> int post(String url, Map<String, Object> body, HttpHeaders headers) throws Exception {
        HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(body, headers);

        ResponseEntity<String> response = restTemplate().exchange(url, HttpMethod.POST, entity, String.class);

        int status = response.getStatusCodeValue();

        return status;
    }


}