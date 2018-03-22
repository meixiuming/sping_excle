package com.excle.config;

import javax.servlet.annotation.WebFilter;

import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * Created by: msi 17/1/19 下午5:20
 *
 * alibaba druid的过滤
 */

@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
        initParams={
                @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源
        }
)
/**
 * Created by msi on 2018/2/22.
 */
public class DruidStatFilter extends WebStatFilter {

}