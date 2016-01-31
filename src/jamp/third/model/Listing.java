package jamp.third.model;

public class Listing {

    private long id;
    private String title;
    private double rate = 0;
    private Channel channel;

    public Listing(long id, String title, double rate, Channel channel) {
        this.id = id;
        this.title = title;
        this.rate = rate;
        this.channel = channel;
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

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
