package de.ait.demo1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @GetMapping("/info")
    public String infoPage() {
        return "info"; // имя файла info.html в папке templates
    }
}
