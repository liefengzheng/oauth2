package com.dxc.gdc.ddc.leon.edge.filter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessFilter extends ZuulFilter {

	public boolean shouldFilter() {
		return true;
	}

	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        if(!isMarch(request)){
            log.warn("The user information not marching, user isolation caused getting user oauth token unsuccessful!");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        log.info("user isolation check ok!");
        return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	private boolean isMarch(HttpServletRequest request){
		// do something before run 
        return true;
    }
}
