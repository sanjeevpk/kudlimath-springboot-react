package com.sansys.kudlimathspringbootreact.services;

import javax.net.ssl.SSLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;


@Component
public class AipayService {
      private static final Logger LOG = LoggerFactory.getLogger(AipayService.class);
     
//     private static final String AIPAY_URL = "https://caller.atomtech.in/ots/aipay/auth?";
	   private static final String AIPAY_URL = "https://payment1.atomtech.in/ots/aipay/auth?";
	   private static final String AIPAY_TRANSACTION_STATUS_URL = "https://payment1.atomtech.in/ots/payment/status?";

	  public static String getAtomTokenId(String merchantId, String serverResp)
	  {
	    String result = "";
	    try
	    {
	      LOG.info("In get atomtokenID method AIPAY_URL = {}, merchantId = {}, serverResp = {}", AIPAY_URL, merchantId, serverResp);
	      WebClient.Builder webClientBuilder = WebClient.builder();
	      webClientBuilder = getCertificateSkippedRestObject();
	      result = webClientBuilder.build().post()
	    		  .uri(AIPAY_URL + "merchId=" + merchantId + "&encData=" + serverResp)
	    		  .contentType(MediaType.APPLICATION_JSON)
	    		  .retrieve()
	    		  .bodyToMono(String.class)
	    		  .block();
	      LOG.info("In get atomtokenID() Server result----------: {} =", result);
	    } catch (Exception e) {
	      LOG.error("Exception in atomtokenID()", e);
	      e.getStackTrace();
	    }

	    return result;
	  }
	  
	  public static String getTransactionStatus(String merchantId, String serverResp)
      {
        String result = "";
        try
        {
          LOG.info("In get getTransactionStatus() URL = {}, merchantId = {}, serverResp = {}", AIPAY_TRANSACTION_STATUS_URL, merchantId, serverResp);
          WebClient.Builder webClientBuilder = WebClient.builder();
          webClientBuilder = getCertificateSkippedRestObject();
          result = webClientBuilder.build().post()
                  .uri(AIPAY_TRANSACTION_STATUS_URL + "merchId=" + merchantId + "&encData=" + serverResp)
                  .contentType(MediaType.APPLICATION_JSON)
                  .retrieve()
                  .bodyToMono(String.class)
                  .block();
          LOG.info("In get getTransactionStatus() Server result----------: {} =", result);
        } catch (Exception e) {
          LOG.error("Exception in getTransactionStatus()", e);
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
