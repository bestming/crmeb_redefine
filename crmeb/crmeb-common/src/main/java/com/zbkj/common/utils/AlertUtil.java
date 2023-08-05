package com.zbkj.common.utils;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


import java.net.InetAddress;
/**
 * @author wxm
 * @date 2023/8/5 14:19
 * 应用启动和关闭时,发送告警信息:
 * 比如发到飞书或者钉钉
 */
@Component
public class AlertUtil implements ApplicationRunner, DisposableBean {

    @Value("${spring.profiles.active:default}")
    String active;

    @Value("${spring.application.name:default}")
    String appName;

    @Override
    public void run(ApplicationArguments args) {

        try {
            System.out.println("你好。");
            if ("dev".equals(active)) {
                return;
            }
            InetAddress addr = InetAddress.getLocalHost();
            //获取本机ip
            String ip = addr.getHostAddress();
            //获取本机计算机名称
            String hostName = addr.getHostName();

            String message = "应用启动成功：\n" + "应用：" + appName + "\n环境：" + active + "\nIP：" + ip + "\nhost：" + hostName;

            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try {
            System.out.println("再见。");
            if ("dev".equals(active)) {
                return;
            }
            InetAddress addr = InetAddress.getLocalHost();
            //获取本机ip
            String ip = addr.getHostAddress();
            //获取本机计算机名称
            String hostName = addr.getHostName();

            String message = "应用关闭成功：\n" + "应用：" + appName + "\n环境：" + active + "\nIP：" + ip + "\nhost：" + hostName;
            System.out.println(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
