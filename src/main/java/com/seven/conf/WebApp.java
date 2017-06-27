package com.seven.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by ruslankatsyuryna on 6/26/17.
 */
@Configuration
@EnableWebMvc
@ComponentScan({ "com.seven.conf", "com.seven.controllers" })
public class WebApp {
}
