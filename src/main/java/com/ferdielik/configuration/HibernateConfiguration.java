package com.ferdielik.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:application.properties")
public class HibernateConfiguration
{
    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory()
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.ferdielik.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource()
    {
        final HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(Integer.parseInt(environment.getRequiredProperty("jdbc.maxPoolSize")));
        ds.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        ds.addDataSourceProperty("url", environment.getRequiredProperty("jdbc.url"));
        ds.addDataSourceProperty("user", environment.getRequiredProperty("jdbc.username"));
        ds.addDataSourceProperty("password", environment.getRequiredProperty("jdbc.password"));
        ds.addDataSourceProperty("cachePrepStmts", true);
        ds.addDataSourceProperty("prepStmtCacheSize", 250);
        ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        ds.addDataSourceProperty("useServerPrepStmts", true);
        ds.addDataSourceProperty("characterEncoding", "UTF-8");
        ds.addDataSourceProperty("useUnicode", "true");
        ds.setConnectionTestQuery("SELECT 1");
        return ds;
    }

    private Properties hibernateProperties()
    {
        Properties properties = new Properties();
        addPropertiesWithMessage(properties, "hibernate.dialect");
        addPropertiesWithMessage(properties, "hibernate.show_sql");
        addPropertiesWithMessage(properties, "hibernate.format_sql");
        addPropertiesWithMessage(properties, "hibernate.hbm2ddl.auto");
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s)
    {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

    private void addPropertiesWithMessage(Properties properties, String messageKey)
    {
        properties.put(messageKey, environment.getRequiredProperty(messageKey));
    }
}
