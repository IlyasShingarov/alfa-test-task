package ru.ishingarov.alfatesttask.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model for working with GIPHY API
 * Holds GIF Data and MetaData
 */
@Data
@NoArgsConstructor
public class GiphyEntity {

    private GiphyData data;
    private GiphyMeta meta;

    @Data
    @NoArgsConstructor
    public static class GiphyData {

        @JsonProperty(value = "id", required = true)
        private String gifId;

        @JsonProperty(value = "embed_url", required = true)
        private String embedUrl;
    }

    @Data
    @NoArgsConstructor
    public static class GiphyMeta {

        @JsonProperty(value = "status", required = true)
        private Integer statusCode;

        @JsonProperty(value = "msg", required = true)
        private String message;
    }
}
