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

@Configuration
@MapperScan(
        value = "com.example.multidatasource.slaveMapper",
        sqlSessionTemplateRef = "slaveSqlSessionTemplate",
        sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveDataSourceConfig {

    /**
     * 数据源配置
     * 这里通过@ConfigurationProperties 将配置注入
     * 也可以通过代码设置
     * */
    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.second.hikari")
    public DataSource slaveDataSource() {
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
    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDataSource") DataSource slaveDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(slaveDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:slaveMapping/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }


    @Bean(name = "slaveTransactionManager")
    public DataSourceTransactionManager slaveTransactionManager(@Qualifier("slaveDataSource") DataSource slaveDataSource) {
        return new DataSourceTransactionManager(slaveDataSource);
    }

    @Bean(name = "slaveSqlSessionTemplate")
    public SqlSessionTemplate slaveSqlSessionTemplate(@Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
