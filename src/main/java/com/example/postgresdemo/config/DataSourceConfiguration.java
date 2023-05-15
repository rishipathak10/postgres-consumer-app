package com.example.postgresdemo.config;

import com.example.postgresdemo.model.VcapServices;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("cloud")
public class DataSourceConfiguration {
    @Value("${VCAP_SERVICES}")
    private String vsJson;
    private static Gson gson=new Gson();

    @Bean
    public DataSource dataSource() {
        VcapServices vcapServices=gson.fromJson(vsJson,VcapServices.class);

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        String url=String.format("jdbc:postgresql://%s:%s/%s",
                vcapServices.getPPostgres().get(0).getCredentials().getHost(),
                vcapServices.getPPostgres().get(0).getCredentials().getPort(),
                vcapServices.getPPostgres().get(0).getCredentials().getDb());
        System.out.println("-------------POSTGRES URL-----------"+ url);
        System.out.println("------VCAP PASSWORD-------"+vcapServices.getPPostgres() );
        return dataSourceBuilder
                .url(url)
                .username( vcapServices.getPPostgres().get(0).getCredentials().getUser())
                .password(vcapServices.getPPostgres().get(0).getCredentials().getPassword())
                .build();

    }


}