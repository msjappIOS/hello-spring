package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {


    // web application에서 /hello로 들어오면 자동으로 mapping 해줌
    @GetMapping
    public String hello(Model model) {
        model.addAttribute("data", "문석진 입니다");
        return "hello";
        //resources의 hello.html 이름과 같다. 해당 html 파일로 리소싱 하라.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }
    
    /*
    @ResponseBody가 없으면 Spring은 viewResolver로 template을 반환하려고 한다.
    반환 하려는 것이 객체일 경우는 JSON으로 반환(default).
    HttpMessageConverter(JsonConverter, StringConverter)
     */
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public Hello() {

        }

        public Hello(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
