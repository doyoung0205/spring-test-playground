package me.doyoung.shutdowntest;

import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private static final Logger log = LoggerFactory.getLogger(GracefulShutdown.class);

    private static final int TIMEOUT = 30;

    private volatile Connector connector;

    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        this.connector.pause();

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) this.connector
                .getProtocolHandler()
                .getExecutor();

        threadPoolExecutor.shutdown();

        try {
            threadPoolExecutor.awaitTermination(5, TimeUnit.SECONDS);

            log.info("Web Application Gracefully Stopped.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();

            log.error("Web Application Graceful Shutdown Failed.");
        }
    }


}
