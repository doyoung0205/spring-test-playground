package me.doyoung.shutdowntest;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Configuration(proxyBeanMethods = false)
public class GracefulShutDownConfig {
//    @Bean
    public GracefulShutdown gracefulShutdown() {
        System.out.println("ShutdownTestApplication.gracefulShutdown");
        return new GracefulShutdown();
    }

//    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(final GracefulShutdown gracefulShutdown) {
        System.out.println("ShutdownTestApplication.webServerFactory");
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(gracefulShutdown);
        return factory;
    }

}
