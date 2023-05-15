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

//    static String vsJson= "{\n" +
//            "  \"p.postgres\": [\n" +
//            "   {\n" +
//            "    \"binding_guid\": \"6ad1831a-2b11-4604-a0a4-052d1785e140\",\n" +
//            "    \"binding_name\": null,\n" +
//            "    \"credentials\": {\n" +
//            "     \"db\": \"rOowQulkfe\",\n" +
//            "     \"host\": \"q-s0.postgres-instance.queenblue-services-subnet.service-instance-427e85c1-8eea-418a-ab39-52933a76b28d.bosh\",\n" +
//            "     \"password\": \"o3aR428K95701dg6JDnT\",\n" +
//            "     \"port\": 5432,\n" +
//            "     \"service_gateway_access_port\": 0,\n" +
//            "     \"service_gateway_enabled\": false,\n" +
//            "     \"uri\": \"postgres://pgadmin:o3aR428K95701dg6JDnT@q-s0.postgres-instance.queenblue-services-subnet.service-instance-427e85c1-8eea-418a-ab39-52933a76b28d.bosh:5432/rOowQulkfe\",\n" +
//            "     \"user\": \"pgadmin\"\n" +
//            "    },\n" +
//            "    \"instance_guid\": \"427e85c1-8eea-418a-ab39-52933a76b28d\",\n" +
//            "    \"instance_name\": \"pg_instance_05\",\n" +
//            "    \"label\": \"p.postgres\",\n" +
//            "    \"name\": \"pg_instance_05\",\n" +
//            "    \"plan\": \"on-demand-cache\",\n" +
//            "    \"provider\": null,\n" +
//            "    \"syslog_drain_url\": null,\n" +
//            "    \"tags\": [\n" +
//            "     \"postgres\",\n" +
//            "     \"pivotal\",\n" +
//            "     \"on-demand\"\n" +
//            "    ],\n" +
//            "    \"volume_mounts\": []\n" +
//            "   }\n" +
//            "  ]\n" +
//            " }";
    @Bean
//    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
//        System.out.println("vsjson: "+ vsJson);
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