package com.heo.dae.msgbot.common;

import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestUtil {

    @Autowired
    RestTemplate restTemplate;

    @Bean
	public RestTemplate restTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		TrustStrategy acceptingTrustStrategy = (new TrustStrategy() {

            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                //TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
                return true;
            }
        });

		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();
		
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

		CloseableHttpClient httpClient = HttpClients.custom()
		        .setSSLSocketFactory(csf)
		        .build();

		HttpComponentsClientHttpRequestFactory requestFactory =
		        new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		
		return restTemplate;
    }

    public <T> void post(String url, HttpMethod method, Map<String,Object> req) throws Exception {
        URI uri = URI.create(url);
        restTemplate().postForLocation(uri, req);
    }
}