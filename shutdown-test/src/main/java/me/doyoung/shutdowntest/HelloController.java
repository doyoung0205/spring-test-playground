package me.doyoung.shutdowntest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final DomainService domainService;

    @GetMapping("hello")
    public String hello() {
        domainService.doSomeThing();
        return "hello";
    }

}
