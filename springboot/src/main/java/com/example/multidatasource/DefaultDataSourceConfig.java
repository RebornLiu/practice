package com.example.multidatasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * 需要注意以下几点
 * 1。主source最好使用@Primary
 * 2。主辅的mapper和xml要分隔在不同的包
 * 3。SqlSessionFactory要设置对应的xml位置
 * 4. MapperScan 要设置对应的mapper位置以及使用的template和factory
 * 5. 最好配置driver-name 遇到过不配置获取不到driver的情况。注意connector的版本 driver名不一样
 * 6. mybatis三大件的bean注入要使用@Qualifier注明对应的bean
 * */
@Configuration
@MapperScan(
        value = "com.example.multidatasource.defaultMapper",
        sqlSessionTemplateRef = "sqlSessionTemplate",
        sqlSessionFactoryRef = "sqlSessionFactory")
public class DefaultDataSourceConfig {

    /**
     * 数据源配置
     * 这里通过@ConfigurationProperties 将配置注入
     * 也可以通过代码设置
     * */
    @Bean(name = "dataSource")
    //@Primary
    @ConfigurationProperties(prefix = "spring.datasource.first.hikari")
    public DataSource defaultDataSource() {
        //配置文件注入
        return DataSourceBuilder.create().build();

        //代码设置
       /* return DataSourceBuilder.create().type(HikariDataSource.class)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("")
                .username("")
                .password("")
                .build();*/
    }

    /****** mybatis设置 三大件 ******/
    @Bean(name = "sqlSessionFactory")
    //@Primary
    public SqlSessionFactory defaultSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:defaultMapping/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }


    @Bean(name = "transactionManager")
    //@Primary
    public DataSourceTransactionManager defaultTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplate")
    //@Primary
    public SqlSessionTemplate defaultSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
