package com.spring.security.Spring.Security.App;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    public String home()
    {
        return ("<h>Welcome Home!</h>");
    }

    @GetMapping("/user")
    public String user()
    {
        return ("<h>Welcome User!</h>");
    }

    @GetMapping("/admin")
    public String admin()
    {
        return ("<h>Welcome Admin!</h>");
    }

}
