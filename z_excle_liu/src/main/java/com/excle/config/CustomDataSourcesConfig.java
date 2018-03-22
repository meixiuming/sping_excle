//package com.excle.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//
///**
// * Created by :  msi   2017/6/26 0026 上午 11:02.
// */
//@Configuration
//@MapperScan(basePackages = CustomDataSourcesConfig.PACKAGES, sqlSessionFactoryRef = "customSqlSessionFactory")
//public class CustomDataSourcesConfig {
//
//    static final String PACKAGES = "com.excle.domain";
//    private static final String MAPPER_LOCAL = "classpath:mapper/*.xml";
//
//    @ConfigurationProperties("custom.datasource.ds1")
//    @Bean(name = "customDataSource")
//    public DruidDataSource druidDataSource() {
//        return new DruidDataSource();
//    }
//
//
//    @Bean(name = "customTransactionManager")
//    public DataSourceTransactionManager customTransactionManager() {
//        return new DataSourceTransactionManager(druidDataSource());
//    }
//
//
//    @Bean(name = "customSqlSessionFactory")
//    public SqlSessionFactory customSqlSessionFactory(@Qualifier("customDataSource") DruidDataSource dataSource) throws Exception {
//        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource);
//        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCAL));
//        return sessionFactoryBean.getObject();
//    }
//
//
//}