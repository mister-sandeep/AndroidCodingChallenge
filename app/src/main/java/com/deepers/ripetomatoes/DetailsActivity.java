package com.deepers.ripetomatoes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.deepers.ripetomatoes.data.MovieReview;

/**
 * Created by Deepers on 8/31/2018.
 */
public class DetailsActivity extends AppCompatActivity {
    private ImageView movieArt;
    private TextView criticsPick;
    private TextView title;
    private TextView mpaaRating;
    private TextView releaseDate;
    private TextView pubDate;
    private TextView reviewer;
    private TextView headline;
    private TextView summary;
    private TextView link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        movieArt = findViewById(R.id.thumbnail);
        criticsPick = findViewById(R.id.critics_pick);
        title = findViewById(R.id.title);
        mpaaRating = findViewById(R.id.mpaa_rating);
        releaseDate = findViewById(R.id.release_date);
        pubDate = findViewById(R.id.pub_date);
        reviewer = findViewById(R.id.reviewer);
        headline = findViewById(R.id.headline);
        summary = findViewById(R.id.summary_short);
        link = findViewById(R.id.review_link);

        // normally the Details screen would fetch its own data, but as we don't have the API
        // info to fetch detailed movie review data, we'll retrieve the serialized extras.
        Intent launchingIntent = getIntent();
        final MovieReview review = (MovieReview) launchingIntent.getSerializableExtra("review");

        if (review != null) {
            populateUi(review);
        } else {
            // TODO display an error
            return;
        }

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchFullReview = new Intent(Intent.ACTION_VIEW);
                if (review != null && review.getLink() != null) {
                    String uri = review.getLink().getUrl();
                    if (!uri.isEmpty()) {
                        launchFullReview.setData(Uri.parse(uri));
                        startActivity(launchFullReview);
                    }
                }
            }
        });
    }

    private void populateUi(@NonNull MovieReview review) {

        Glide.with(this)
                .load(review.getMultimedia().getSrc())
                .into(movieArt);

        if (review.getCriticsPick() == false) {
            criticsPick.setVisibility(View.GONE);
        }

        title.setText(review.getDisplayTitle());
        mpaaRating.setText(review.getMpaaRating());

        String relDateText = review.getOpeningDate();
        if (relDateText == null || relDateText.isEmpty()) {
            releaseDate.setText("unknown");
        } else {
            releaseDate.setText(relDateText);
        }

        pubDate.setText(review.getPublicationDate());
        reviewer.setText(review.getByline());
        headline.setText(review.getHeadline());
        summary.setText(review.getSummaryShort());
        if (review.getLink() != null) {
            String linkText = review.getLink().getSuggestedLinkText();
            if (!linkText.isEmpty()) {
                link.setText(linkText);
            } else {
                link.setVisibility(View.GONE);
            }
        }
    }
}
