package com.dcaoyz.fotag;

import java.util.Observable;
import java.util.ArrayList;

/**
 * Created by dcaoyz on 2016-03-27.
 */
public class Model extends Observable implements IRating {
    ArrayList<ImageModel> collection = new ArrayList<ImageModel>();
    int filter = 0;

    Model() {
        setChanged();
    }

    public void addImage(ImageModel image) {
        collection.add(image);
        setChanged();
        notifyObservers();
    }

    @Override
    public void updateRating(int rating) {
        filter = rating;
        setChanged();
        notifyObservers();
    }

    @Override
    public int getRating() {
        return filter;
    }
}
