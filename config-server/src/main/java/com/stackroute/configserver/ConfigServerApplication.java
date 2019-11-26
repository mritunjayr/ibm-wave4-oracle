/**
 * main package of config server
 */
package com.stackroute.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
/**
 * Main class of server
 */
public class ConfigServerApplication {
    /**
     * Start application from here
     *
     * @param args for provide entry point of spring boot configuration.
     * @return void.
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
