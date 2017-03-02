package com.dellnaresh;

import com.dellnaresh.wsdl.GetDistrictsResponse;
import com.dellnaresh.wsdl.GetMandalsResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 *
 */
@SpringBootApplication
public class App 
{
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Bean
    CommandLineRunner lookup(LandDetailsClient quoteClient) {
        return args -> {
            String ticker = "MSFT";

            if (args.length > 0) {
                ticker = args[0];
            }
            GetMandalsResponse response = quoteClient.getMandals("2");
            response.getGetMandalsResult().getCascadingDropDownNameValue().stream().forEach(System.out::println);


        };
    }
}
