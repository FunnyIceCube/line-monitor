package com.lzh.linemonitor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MapController {

    @RequestMapping("/map")
    public String map(Model model) {
        return "map";
    }

    @RequestMapping("/map/submit")
    @ResponseBody
    public String submit(String start, String end) {
        System.out.println("start"+start);
        System.out.println("end"+end);
        return "200";
    }
}
