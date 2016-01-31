package jamp.third.model;

import java.util.ArrayList;
import java.util.List;

public class Channel {

    private long id;
    private String title;
    private String description;
    private String imageURL;
    private List<Listing> listings;

    public Channel(long id, String title, String description, String imageURL) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<Listing> getListings() {
        if (listings == null) {
            listings = new ArrayList<Listing>();
        }
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }
}
