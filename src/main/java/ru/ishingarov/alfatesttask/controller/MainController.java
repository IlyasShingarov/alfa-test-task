package ru.ishingarov.alfatesttask.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ishingarov.alfatesttask.service.ExchangeRateService;
import ru.ishingarov.alfatesttask.service.GifService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final ExchangeRateService exchangeRateService;
    private final GifService gifService;

    @GetMapping("/gif")
    public String getGifPage(Model model) {
        boolean isRich = exchangeRateService.getAndCompareRates();
        log.info("IsRich == {}", isRich);
        model.addAttribute("url", gifService.getGifURL(isRich));
        return "gifpage";
    }
}
