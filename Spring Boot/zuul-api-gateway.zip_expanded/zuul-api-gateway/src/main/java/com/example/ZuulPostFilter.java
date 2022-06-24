package com.example;

import org.springframework.context.annotation.Configuration;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
@Configuration
public class ZuulPostFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// we can use request context here and read the correlation id header & add it to the
		// response header
		RequestContext ctx = RequestContext.getCurrentContext();
		String id = ctx.getZuulRequestHeaders().get("correlation-id");
		String time = ctx.getZuulRequestHeaders().get("time");
		System.out.println("---------- POST FILTER ----------");
		System.out.println("Id = "+id+", Time = "+time);
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "post";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}
	
}
