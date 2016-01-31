package jamp.third.processor;

import jamp.third.model.Channel;
import jamp.third.model.Listing;

import java.util.ArrayList;
import java.util.List;

public class ChannelProcessor {

    private List<Channel> channels = new ArrayList<>();

    public ChannelProcessor() {
    }

    public Channel createChannel(String title, String description, String imageURL) {
        long id = IdGenerator.INSTANCE.getNextId();
        Channel channel = new Channel(id, title, description, imageURL);
        channels.add(channel);
        return channel;
    }

    public void addListing(Channel channel, String title, double rate) {
        long id = IdGenerator.INSTANCE.getNextId();
        Listing listing = new Listing(id, title, rate, channel);
        channel.getListings().add(listing);
    }
}
