package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author boot
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class BootApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BootApplication.class, args);
        System.out.println("      服务启动成功             \n" +
                " ██████     ███████     ███████   ██████████\n" +
                "░█░░░░██   ██░░░░░██   ██░░░░░██ ░░░░░██░░░ \n" +
                "░█   ░██  ██     ░░██ ██     ░░██    ░██    \n" +
                "░██████  ░██      ░██░██      ░██    ░██    \n" +
                "░█░░░░ ██░██      ░██░██      ░██    ░██    \n" +
                "░█    ░██░░██     ██ ░░██     ██     ░██    \n" +
                "░███████  ░░███████   ░░███████      ░██    \n" +
                "░░░░░░░    ░░░░░░░     ░░░░░░░       ░░     \n");
    }
}
