package com.example.jonas.eduquest;

/**
 * Created by jonas on 2016-12-20.
 */

public class UrlResponse {
    private final String url;
    private final String statusCode;

    public UrlResponse(String url, String status) {
        this.url = url;
        this.statusCode = status;
    }

    public String getUrl() {
        return url;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
