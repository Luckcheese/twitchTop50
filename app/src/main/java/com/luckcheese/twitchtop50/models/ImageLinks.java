package com.luckcheese.twitchtop50.models;

import java.io.Serializable;

public class ImageLinks implements Serializable {
    private String large;
    private String medium;
    private String small;

    public enum ImageSize {
        Large, Medium, Small;

        public String get(ImageLinks links) {
            switch (this) {
                case Large:
                    return links.large;
                case Medium:
                    return links.medium;
                default:
                    return links.small;
            }
        }
    }
}
