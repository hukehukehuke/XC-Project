package xuecheng.test.freemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xuecheng.test.freemarker.model.Student;

import java.util.*;

@Controller  //输出Html网页 不要写成RestController
@RequestMapping("/freemarker")
public class FreemarkerController {

    @RequestMapping("/test1")
    public String test1(Map<String,Object> map){
        map.put("name","传智播客");
        Student stu1 = new Student();
        stu1.setAge(13);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setAge(13);
        stu2.setBirthday(new Date());
        List<Student> list = new ArrayList<>();
        list.add(stu1);
        list.add(stu2);
        map.put("list",list);
        Map<String,Student> studentMap = new HashMap<>();
        studentMap.put("stu1",stu1);
        studentMap.put("stu2",stu2);
        map.put("stu1",stu1);
        map.put("studentMap",studentMap);
        return "test1";
    }
}
