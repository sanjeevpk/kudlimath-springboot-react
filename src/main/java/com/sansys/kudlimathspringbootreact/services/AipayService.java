package com.sansys.kudlimathspringbootreact.services;

import javax.net.ssl.SSLException;

import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;

//import com.nttdatapay.aipayclient.utils.Utility;

public class AipayService {
	 private static final String AIPAY_URL = "https://caller.atomtech.in/ots/aipay/auth?";

	  public static String getAtomTokenId(String merchantId, String serverResp)
	  {
	    String result = "";
	    try
	    {
	    	System.out.println("in get atomtokenID method"+merchantId+serverResp);
//	      result = Utility.httpPostCaller("merchId=" + merchantId + "&encData=" + serverResp, "https://caller.atomtech.in/ots/aipay/auth");
	      WebClient.Builder webClientBuilder=WebClient.builder();
	      webClientBuilder=getCertificateSkippedRestObject();
	      result=webClientBuilder.build().post()
	    		  .uri(AIPAY_URL+"merchId=" + merchantId + "&encData=" + serverResp)
	    		  .contentType(MediaType.APPLICATION_JSON)
	    		  .retrieve()
	    		  .bodyToMono(String.class)
	    		  .block();
	    	System.out.println("Server result----------: " + result);
	    } catch (Exception e) {
	      e.getStackTrace();
	    }

	    return result;
	  }
	  public static Builder getCertificateSkippedRestObject() throws SSLException {

			SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
					.build();
			HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

			return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient));
		}
}
