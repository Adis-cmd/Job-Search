package kg.attractor.jobsearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("world", "world");
        return "index";
    }

}
