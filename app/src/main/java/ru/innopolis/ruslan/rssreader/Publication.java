package ru.innopolis.ruslan.rssreader;

/**
 * Created by ruslan on 21.07.2017.
 */

public class Publication {
    private String title, link, description, pubDate, creator, category;

    public Publication(String title, String link, String description, String pubDate, String creator, String category) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
        this.creator = creator;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getCreator() {
        return creator;
    }

    public String getCategory() {
        return category;
    }
}
