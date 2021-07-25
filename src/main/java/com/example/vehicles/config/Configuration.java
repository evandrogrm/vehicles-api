package com.example.vehicles.config;

@org.springframework.context.annotation.Configuration
public class Configuration {

//    @Bean(name = "datasource")
//    public DataSource getDataSource() {
//        MysqlDataSource dataSource = new MysqlDataSource();
//        dataSource.setURL("jdbc:mysql://0.0.0.0:3306/vehicles-database?useSSL=false&useLegacyDatetimeCode=false&allowPublicKeyRetrieval=true");
//        dataSource.setUser("user");
//        dataSource.setPassword("user123");
//        return dataSource;
//    }
//
//    @Bean(name = "entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        //JpaVendorAdapteradapter can be autowired as well if it's configured in application properties.
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(false);
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        //Add package to scan for entities.
//        factory.setPackagesToScan("com.example.vehicles");
//        factory.setDataSource(getDataSource());
//        return factory;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory);
//        txManager.setDataSource(getDataSource());
//        return txManager;
//    }
}
