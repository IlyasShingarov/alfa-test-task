package ru.ishingarov.alfatesttask.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.ishingarov.alfatesttask.rates.service.ExchangeRateService;
import ru.ishingarov.alfatesttask.gif.service.GifService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GifRatesController.class)
public class GifRatesControllerTest {
    @Value("${giphy.rich}")
    private String rich;

    @Value("${giphy.broke}")
    private String broke;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExchangeRateService exchangeRatesService;

    @MockBean
    private GifService gifService;

    @Autowired
    private GifRatesController gifRatesController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void richReturn() throws Exception {
        //prepare
        Mockito.when(exchangeRatesService.compareRates())
                .thenReturn(true);
        //when
        this.mockMvc.perform(get("/gif")).andExpect(status().isOk());
        //assert no exception

    }

    @Test
    public void brokeReturn() throws Exception {
        //prepare
        Mockito.when(exchangeRatesService.compareRates())
                .thenReturn(false);
        //when
        this.mockMvc.perform(get("/gif")).andExpect(status().isOk());
        //assert no exception

    }

}
