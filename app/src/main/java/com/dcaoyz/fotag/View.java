package com.dcaoyz.fotag;

import android.content.Context;
import android.content.res.Configuration;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by dcaoyz on 2016-03-29.
 */
public class View extends LinearLayout implements Observer {
    private Model model;
    private ArrayList<ViewImage> pictures = new ArrayList<ViewImage>();

    public View(Context context, Model m) {
        super(context);

        View.inflate(context, R.layout.content_main, this);

        this.model = m;
        m.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object data) {
        if (model.collection.size() == 0) {
            pictures = new ArrayList<ViewImage>();
        }

        else if (model.collection.size() > pictures.size()) {
            for (int i = pictures.size(); i < model.collection.size(); i++ ) {
                ViewImage imageView = new ViewImage(getContext(), model.collection.get(i), model);
                pictures.add(imageView);
            }
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ViewGroup viewGroup1 = (ViewGroup) findViewById(R.id.firstLayout);
            ViewGroup viewGroup2 = (ViewGroup) findViewById(R.id.thirdLayout);
            viewGroup1.removeAllViews();
            viewGroup2.removeAllViews();

            boolean left = true;

            for (int i = 0; i < model.collection.size(); i++) {
                if (model.collection.get(i).rating >= model.filter) {
                    ViewGroup currentGroup;
                    if (left) {
                        currentGroup = (ViewGroup) findViewById(R.id.firstLayout);
                        left = false;
                    }
                    else {
                        currentGroup = (ViewGroup) findViewById(R.id.thirdLayout);
                        left = true;
                    }
                    currentGroup.addView(pictures.get(i));
                }
            }
        }
        else {
            ViewGroup viewGroup = (ViewGroup) findViewById(R.id.contentmain);
            viewGroup.removeAllViews();

            for (int i = 0; i < model.collection.size(); i++) {
                if (model.collection.get(i).rating >= model.filter) {
                    viewGroup.addView(pictures.get(i));
                }
            }
        }
    }
}
