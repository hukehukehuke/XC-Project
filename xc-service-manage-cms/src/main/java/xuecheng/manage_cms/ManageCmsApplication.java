package xuecheng.manage_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain.cms") //扫描实体类
@ComponentScan(basePackages = "com.xuecheng.api.config.cms") //扫描接口
@ComponentScan(basePackages = "com.xuecheng.framework" )  //扫描common项目下边的包
@ComponentScan(basePackages = "xuecheng.manage_cms" )  //扫描本项目下边的包
public class ManageCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageCmsApplication.class,args);
    }
}
