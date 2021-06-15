package com.virinchi.rms.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AutomateConfiguration {
    private static int i=1;
    //@Scheduled(fixedDelay = 1000)
    public void test(){
        System.out.println("this is test" + i);
        i++;
    }
}
