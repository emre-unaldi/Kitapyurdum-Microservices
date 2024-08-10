package patika.kitapyurdumgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class KitapyurdumGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(KitapyurdumGatewayApplication.class, args);
    }

}
