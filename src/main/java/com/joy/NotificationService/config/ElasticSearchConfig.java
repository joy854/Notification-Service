package com.joy.NotificationService.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackages = "com.joy.NotificationService.repository")
@Configuration
public class ElasticSearchConfig {

    /**
     *  index builder bean to add
     **/

    @Bean
    public RestHighLevelClient elasticsearchClient() {


        RestHighLevelClient client = new RestHighLevelClient(RestClient
                .builder(new HttpHost("localhost",9200,"http")));

        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }
}
