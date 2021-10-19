package ru.ishingarov.alfatesttask.controller;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.ishingarov.alfatesttask.gif.service.GifService;
import ru.ishingarov.alfatesttask.rates.service.ExchangeRateService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GifRatesController {
    private final ExchangeRateService exchangeRateService;
    private final GifService gifService;

    @GetMapping("/gif/{currency}")
    public String getGifByCurrency(Model model, @PathVariable("currency") String CurrencyCode) {
        try {
            boolean isRich = exchangeRateService.compareRatesByCode(CurrencyCode);
            model.addAttribute("url", gifService.getGifURL(isRich));
            return "gifpage";
        } catch (FeignException.Forbidden e) {
            log.info(e.getMessage());
            return "ForbiddenCurrency";
        }
    }

    @GetMapping("/gif")
    public String getGifPage(Model model) {
        boolean isRich = exchangeRateService.compareRates();
        log.info("IsRich == {}", isRich);
        model.addAttribute("url", gifService.getGifURL(isRich));
        return "gifpage";
    }
}
