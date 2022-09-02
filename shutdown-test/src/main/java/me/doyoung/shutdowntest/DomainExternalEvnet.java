package me.doyoung.shutdowntest;

import org.springframework.context.ApplicationEvent;

public class DomainExternalEvnet extends ApplicationEvent {
    public DomainExternalEvnet(Object source) {
        super(source);
    }
}
