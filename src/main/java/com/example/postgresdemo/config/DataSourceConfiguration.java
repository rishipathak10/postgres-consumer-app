package com.example.postgresdemo.config;

//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cloud.Cloud;
//import org.springframework.cloud.CloudFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;

import com.example.postgresdemo.model.VcapServices;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("cloud")
public class DataSourceConfiguration {

//    @Bean
//    public Cloud cloud() {
//        return new CloudFactory().getCloud();
//    }
    @Value("${VCAP_SERVICES}")
    private String vsJson;
    private static Gson gson=new Gson();


    @Bean
//    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        VcapServices vcapServices=gson.fromJson(vsJson,VcapServices.class);

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        String url=String.format("jdbc:postgresql://%s:%s/postgres",
                vcapServices.getPPostgres().get(0).getCredentials().getHost(),
                vcapServices.getPPostgres().get(0).getCredentials().getPort());
        return dataSourceBuilder
                .url(url)
                .username("postgres")
                .password(vcapServices.getPPostgres().get(0).getCredentials().getPassword())
                .build();

    }



}