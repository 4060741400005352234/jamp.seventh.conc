package jamp.sixth.model;

import java.util.LinkedList;
import java.util.List;

public class Queue {

    private final List<String> data = new LinkedList<>();

    public String getData() {
        String str = data.get(data.size() - 1);
        data.remove(data.size() - 1);
        return str;
    }

    public void setData(String str) {
        data.add(str);
    }
}
