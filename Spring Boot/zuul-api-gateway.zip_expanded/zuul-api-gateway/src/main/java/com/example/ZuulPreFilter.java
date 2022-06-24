package com.example;

import java.time.LocalTime;

import org.springframework.context.annotation.Configuration;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
@Configuration
public class ZuulPreFilter extends ZuulFilter{

	// shouldFilter must be true to apply filters
	@Override
	public boolean shouldFilter() {
		return true;
	}
	// this is the core method of ZuulFilter, we can identify whether
	// request has any correlation id, authorization header and so on
	@Override
	public Object run() throws ZuulException {
		// when request passed to multiple microservices we can add correlation id if 
		// the id is not present else we will not add
		System.out.println("------------ PRE FILTER ---------------");
		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.setSendZuulResponse(false);
		int randomNumber = new Double(Math.random() * 100000).intValue();
		LocalTime time = LocalTime.now();
		ctx.addZuulRequestHeader("correlation-id", String.valueOf(randomNumber));
		ctx.addZuulRequestHeader("time", time.toString());
		System.out.println("Added Id: "+randomNumber);
		System.out.println("--------Request URI: "+ctx.getRequest().getRequestURI()+"----------");
		return null;
	}

	// we can return values like pre, route, post
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
