package us.pkfs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PageController {
    @GetMapping("/")
    public String mainPage() {
        return "main-page";
    }
}