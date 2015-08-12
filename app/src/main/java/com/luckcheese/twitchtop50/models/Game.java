package com.luckcheese.twitchtop50.models;

import java.io.Serializable;

public class Game implements Serializable {

    private String name;
    private long _id;
    private ImageLinks box;
    private ImageLinks logo;

    public String getName() {
        return name;
    }

    public String getBoxImagUrl(ImageLinks.ImageSize size) {
        return size.get(box);
    }

    public String getLogoImageUrl(ImageLinks.ImageSize size) {
        return size.get(logo);
    }

    // ----- Related classes --------------------------------------------------

    public static final class Entry implements Serializable {
        private int viewers;
        private int channels;
        private Game game;

        public int getViewers() {
            return viewers;
        }

        public int getChannels() {
            return channels;
        }

        public Game getGame() {
            return game;
        }
    }

}
