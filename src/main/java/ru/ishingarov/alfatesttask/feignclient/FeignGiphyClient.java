package ru.ishingarov.alfatesttask.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ishingarov.alfatesttask.model.GiphyEntity;

/** Client for interacting with GIPHY API
 */
@FeignClient(name = "GiphyClient", url = "${api.giphy.url}")
public interface FeignGiphyClient {
    /**
     * Get random gif by tag
     * @param apiKey api_key
     * @param tag tag by which a gif is described
     * @return api response from GIPHY
     */
    @GetMapping("/random")
    GiphyEntity getGifByTag(
            @RequestParam("api_key") String apiKey,
            @RequestParam("tag") String tag
    );
}
