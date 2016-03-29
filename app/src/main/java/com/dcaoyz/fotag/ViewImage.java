package com.dcaoyz.fotag;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by dcaoyz on 2016-03-29.
 */
public class ViewImage extends LinearLayout implements Observer {
    private ModelImage modelImage;
    private Model model;
    private RatingBar ratingBar;

    public ViewImage(Context context, ModelImage m, Model mod) {
        super(context);

        View.inflate(context, R.layout.image, this);

        modelImage = m;
        m.addObserver(this);

        this.model = mod;

        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageResource(getResources().getIdentifier(modelImage.resourceName, "drawable", context.getPackageName()));

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating(0);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                modelImage.updateRating((int) rating);
                if (rating < model.filter) {
                    model.broadcast();
                }
            }
        });
    }

    @Override
    public void update(Observable observable, Object data) {
        ratingBar.setRating(modelImage.rating);
    }
}
