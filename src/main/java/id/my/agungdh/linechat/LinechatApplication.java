package id.my.agungdh.linechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class LinechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinechatApplication.class, args);
    }

}
