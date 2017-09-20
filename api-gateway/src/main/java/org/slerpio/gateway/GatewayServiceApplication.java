package org.slerpio.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class GatewayServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	// @Bean
	// public ZuulFallbackProvider zuulFallbackProvider() {
	// return new ZuulFallbackProvider() {
	// @Override
	// public String getRoute() {
	// return "zuulserver";
	// }
	//
	// @Override
	// public ClientHttpResponse fallbackResponse() {
	// return new ClientHttpResponse() {
	// @Override
	// public HttpStatus getStatusCode() throws IOException {
	// return HttpStatus.OK;
	// }
	//
	// @Override
	// public int getRawStatusCode() throws IOException {
	// return 200;
	// }
	//
	// @Override
	// public String getStatusText() throws IOException {
	// return "OK";
	// }
	//
	// @Override
	// public void close() {
	//
	// }
	//
	// @Override
	// public InputStream getBody() throws IOException {
	// return new ByteArrayInputStream("fallback".getBytes());
	// }
	//
	// @Override
	// public HttpHeaders getHeaders() {
	// HttpHeaders headers = new HttpHeaders();
	// headers.setContentType(MediaType.APPLICATION_JSON);
	// return headers;
	// }
	// };
	// }
	// };
	// }
}
