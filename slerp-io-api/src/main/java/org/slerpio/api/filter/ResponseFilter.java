package org.slerpio.api.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slerp.core.Domain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

public class ResponseFilter implements Filter {
	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void destroy() {
		log.info("Closed");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
		chain.doFilter(request, responseWrapper);
		log.info("Address >>> {}", request.getRemoteAddr());
		log.info("Host >>> {}", request.getRemoteHost());
		log.info("Port >>> {}", request.getRemotePort());			
		String responseStr = new String(responseWrapper.getContentAsByteArray());
		if (responseStr != null && responseStr.length() > 0) {
			log.info("Response >>> {}", responseStr);
			Domain responseDomain = new Domain(responseStr);
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

			if (responseDomain.containsKey("status") && responseDomain.getInt("status") == 1) {
				response.getWriter().write(responseStr);
			} else {
				Domain successDomain = new Domain();
				successDomain.put("status", 0);
				successDomain.put("body", responseDomain);
				response.getWriter().write(successDomain.toString());
			}
			response.getWriter().close();
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		log.info("Initialize Respons filter ", arg0);
	}
}
