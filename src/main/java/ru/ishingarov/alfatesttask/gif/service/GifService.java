package ru.ishingarov.alfatesttask.gif.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.ishingarov.alfatesttask.gif.feignclient.FeignGiphyClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class GifService {

    @Value("${api.giphy.apikey}")
    private String apiKey;

    @Value("${giphy.rich}")
    private String rich;

    @Value("${giphy.broke}")
    private String broke;

    private final FeignGiphyClient giphyClient;

    /**
     * Gets gif url to embed it on a page
     * @param isRich Determines whether rich or broke gif is retrieved
     * @return embed_url for corresponding gif
     */
    public String getGifURL(boolean isRich) {
        return giphyClient.getGifByTag(apiKey, isRich ? rich : broke).getData().getEmbedUrl();
    }
}
