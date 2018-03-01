package com.lzh.linemonitor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapController {

    @RequestMapping("/map")
    public String map(Model model) {
        return "map";
    }
}
