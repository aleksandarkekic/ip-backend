package com.example.ip_backend.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhotoUploadResponse {
    private boolean success;
    private ImageData data;
    private String error;

    public boolean isSuccess() {
        return success;
    }

    public ImageData getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    public static class ImageData {
        private String url;
        @JsonProperty("url")
        public String getUrl() {
            return url;
        }
    }
}