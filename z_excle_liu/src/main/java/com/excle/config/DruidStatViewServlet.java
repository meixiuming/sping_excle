package com.excle.config;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * Created by: msi 17/1/19 下午5:20
 *
 * alibaba druid 页面监控配置   访问url  http://localhost:8080/项目名/druid/index.html
 *
 * 在使用springboot的时候,需要在启动类使用 @ServletComponentScan注解
 */

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/druid/*",
        initParams={
                @WebInitParam(name="allow",value="127.0.0.1"),// IP白名单(没有配置或者为空，则允许所有访问)
                @WebInitParam(name="deny",value="192.168.1.73"),// IP黑名单 (存在共同时，deny优先于allow)
                @WebInitParam(name="loginUsername",value="admin"),// 用户名
                @WebInitParam(name="loginPassword",value="630227"),// 密码
                @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
        })
/**
 * Created by msi on 2018/2/22.
 */
public class DruidStatViewServlet extends StatViewServlet {
    private static final long serialVersionUID = -2688872071445249539L;

}