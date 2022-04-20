package top.rcode.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.rcode.manger.DataManger;

import javax.xml.ws.RequestWrapper;

/**
 * @author ：河神
 * @date ：Created in 2022/3/17 17:26
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        String data = "河神";
        System.out.println("河神大人被访问一下------->2");
        DataManger dataManger = new DataManger();
        return  data+"：hello"+dataManger.version();
    }

}
