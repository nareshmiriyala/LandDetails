package com.dellnaresh.config;

import com.dellnaresh.LandDetailsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Created by nmiriyal on 1/03/2017.
 */
@Configuration
public class LandConfig {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.dellnaresh.wsdl");
        return marshaller;
    }

    @Bean
    public LandDetailsClient landClient(Jaxb2Marshaller marshaller) {
        LandDetailsClient client = new LandDetailsClient();
        client.setDefaultUri(LandDetailsClient.URI);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }


}
