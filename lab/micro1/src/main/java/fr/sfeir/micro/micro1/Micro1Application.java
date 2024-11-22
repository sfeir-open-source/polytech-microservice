package fr.sfeir.micro.micro1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.http.EurekaClientHttpRequestFactorySupplier;
import org.springframework.cloud.netflix.eureka.http.RestTemplateDiscoveryClientOptionalArgs;
import org.springframework.cloud.netflix.eureka.http.RestTemplateTransportClientFactories;
import org.springframework.context.annotation.Bean;

import com.netflix.discovery.AbstractDiscoveryClientOptionalArgs;
import com.netflix.discovery.shared.transport.jersey.TransportClientFactories;

@SpringBootApplication
@EnableDiscoveryClient
public class Micro1Application {

    public static void main(String[] args) {

        SpringApplication.run(Micro1Application.class, args);
    }

    
    @Bean
    @ConditionalOnClass(name = { "org.springframework.web.client.RestTemplate", "org.glassfish.jersey.client.JerseyClient" })
    @ConditionalOnMissingBean(value = { AbstractDiscoveryClientOptionalArgs.class }, search = SearchStrategy.CURRENT)
    public RestTemplateDiscoveryClientOptionalArgs restTemplateDiscoveryClientOptionalArgs(EurekaClientHttpRequestFactorySupplier eurekaClientHttpRequestFactorySupplier) {
        return new RestTemplateDiscoveryClientOptionalArgs(eurekaClientHttpRequestFactorySupplier);
    }

    @Bean
    @ConditionalOnClass(name = { "org.springframework.web.client.RestTemplate", "org.glassfish.jersey.client.JerseyClient" })
    @ConditionalOnMissingBean(value = { TransportClientFactories.class }, search = SearchStrategy.CURRENT)
    public RestTemplateTransportClientFactories restTemplateTransportClientFactories(RestTemplateDiscoveryClientOptionalArgs optionalArgs) {
        return new RestTemplateTransportClientFactories(optionalArgs);
    }
}
