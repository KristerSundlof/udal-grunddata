package se.miun.ladok3.udal.udalgrunddata;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "udalCacheEntityManagerFactory",
        transactionManagerRef = "udalCacheTransactionManager",
        basePackages = {"se.miun.ladok3.udal.repository"})
@EntityScan(
        basePackages = {"se.miun.ladok3.udal.model"}
)
public class HibernateConfiguration {
    @Value("${sqlserver.driver}")
    private String DRIVER;

    @Value("${sqlserver.password}")
    private String PASSWORD;

    @Value("${sqlserver.url}")
    private String URL;

    @Value("${sqlserver.username}")
    private String USERNAME;

    @Value("${sqlserver.hibernate.dialect}")
    private String DIALECT;

    @Value("${hibernate.show_sql}")
    private String SHOW_SQL;

    @Value("${hibernate.hbm2ddl.auto}")
    private String HBM2DDL_AUTO;

    @Value("${entitymanager.packagesToScan}")
    private String PACKAGES_TO_SCAN;

    public HibernateConfiguration() {
    }

    /*    @Bean
        public Hibernate5Module hibernate5Module() {
            return new Hibernate5Module();
        }*/

    @Bean
    @Primary
    public DataSource udalCacheDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    @Bean(name = "udalCacheEntityManagerFactory")
    @Primary
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(udalCacheDataSource());
        sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", DIALECT);
        hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
        hibernateProperties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }
    @Bean(name = "udalCacheTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(@Qualifier("udalCacheEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory);
        transactionManager.setEntityManagerFactory(sessionFactory().getObject());
        return transactionManager;
    }
/*    @Bean(name = "someTransactionManager")
    public PlatformTransactionManager udalCacheTransactionManager(@Qualifier("someEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }*/
/*    @Bean(name = "someTransactionManager")
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }*/
}
