package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.xa.DataSourceProxyXA;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @author salter
 */
@SpringBootConfiguration
@MapperScans({@MapperScan(basePackages = "com.example.abc", sqlSessionFactoryRef = "abcFactory"), @MapperScan(basePackages = "com.example.icbc", sqlSessionFactoryRef = "icbcFactory")})
public class DbConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.gs")
    public DruidDataSource abcDataSource() {
        return new DruidDataSource();
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.ny")
    public DruidDataSource icbcDataSource() {
        return new DruidDataSource();
    }


//    /**
//     * ⼿动配置农⾏代理数据源
//     *
//     * @param dataSource 数据源
//     * @return 代理数据源
//     */
//    @Bean
//    public DataSourceProxyXA abcDs(@Qualifier("abcDataSource") DruidDataSource dataSource) {
//        return new DataSourceProxyXA(dataSource);
//    }
//
//    /**
//     * ⼿动代理建⾏数据源
//     *
//     * @param dataSource 数据源
//     * @return 代理数据源
//     */
//    @Bean
//    public DataSourceProxyXA icbcDs(@Qualifier("icbcDataSource") DruidDataSource dataSource) {
//        return new DataSourceProxyXA(dataSource);
//    }
    /**
     * 配置NongSqlSession
     */
    /*@Bean
    public SqlSessionFactory abcFactory(@Qualifier("abcDataSource") DruidDataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionFactory icbcFactory(@Qualifier("icbcDataSource") DruidDataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }*/

    /**
     * 配置农业银行SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory abcFactory(@Qualifier("abcDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionFactory icbcFactory(@Qualifier("icbcDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }


    // 配置事务管理器
    /*  @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("icbcDataSource") DataSource datasource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();

        transactionManager.setDataSource(datasource);
        return transactionManager;
    }*/
}
