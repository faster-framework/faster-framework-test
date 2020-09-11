package cn.org.faster.framework.test.modules.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangbowen
 * @date 2018/5/25 13:39
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("testPost")
    public String testPost(@RequestBody String request) {
        System.out.println(request);
        return "{\"code\":\"0000\"}";
    }

    @GetMapping("/testGet/{id}")
    public String testGet(@PathVariable String id, @RequestParam("name") String name) {
        System.out.println(id);
        System.out.println(name);
        return "{\"code\":\"0000\"}";
    }

    @GetMapping("/testRestTemplate")
    public ResponseEntity<String> test() {
        return restTemplate.getForEntity("xxx", String.class);
    }
}
