package com.foresee.gpz.boot.consumer.filters;

import com.foresee.gpz.boot.consumer.constants.ConsumerConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;

/**
 * @Auther: guhao
 * @Date: 2018/6/10 22:39
 * @Description:
 */
public class RemoteHostFilter implements Filter {

    private String isEnabled;

    /**
     * 记录日志
     */
    private Logger log = LoggerFactory.getLogger(RemoteHostFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        isEnabled = filterConfig.getInitParameter("isEnabled");
        log.info("RemoteHostFilter 过滤器开启标志：{}", isEnabled);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(!StringUtils.isEmpty(isEnabled) || ConsumerConstant.HOST_FILTER_ISENABLED.equals(isEnabled)){
            HttpServletRequest request = (HttpServletRequest)servletRequest;
            //设置ip黑名单
            String host = request.getRemoteHost();
            //获取本机ip地址
            String localhost = InetAddress.getLocalHost().getHostAddress().toString();
            log.info("本机IP地址：{}", localhost);
            if(StringUtils.isEmpty(host) || !localhost.equals(host) || !"0:0:0:0:0:0:0:1".equals(host)){
                log.info("对不起你的ip地址：{}被设置为黑名单，不能访问！", host);
                return;
            }
        }

        //不被拦截正常执行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
