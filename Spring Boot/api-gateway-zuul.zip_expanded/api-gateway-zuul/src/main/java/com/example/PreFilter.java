package com.example;

import java.time.LocalTime;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Base64Utils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
@Configuration
public class PreFilter  extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(PreFilter.class);
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	// through co-relationID we can identify whether the call is part of chain of 
	// service calls carrying our the users request
	@Override
	public Object run() throws ZuulException {
		// we can generate some correlationID and add to the request header if its not present
		// in the microservices we must able to read the correlationID
		//ctx.getRequest().getHeader("CORRELATION_ID");
		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.addZuulRequestHeader("zuul-header", "Hello: "+LocalTime.now());
		
		logger.info("-----------------Pre Filter--------------------");
		logger.info("Processing request: {}", ctx.getRequest().getRequestURI());
		logger.info("Added header in pre-filter {}.",ctx.getZuulRequestHeaders().get("zuul-header"));
		//ctx.setSendZuulResponse(false);
		System.out.println("*******************Headers**************");
		
		Enumeration<String> it = ctx.getRequest().getHeaderNames();
		while(it.hasMoreElements()) {
			String name = it.nextElement();
			
			if(name.equalsIgnoreCase("authorization")) {
				
				String encryptedData = ctx.getRequest().getHeader(name);
				
				System.out.println(encryptedData);
				
				//ctx.addZuulRequestHeader("authorization", ctx.getRequest().getHeader(name));
				
				System.out.println(ctx.getRequest().getRequestURI());
			}
		}
		System.out.println("*******************Headers**************");
		
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

}
