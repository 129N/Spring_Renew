package org.mik.yftwrg.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseWEBController {

    @GetMapping("/base")
    public String basePage(){
        return "base";  // This loads base.html
    }
}
