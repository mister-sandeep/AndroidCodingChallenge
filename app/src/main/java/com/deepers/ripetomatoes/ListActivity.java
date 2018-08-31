package com.deepers.ripetomatoes;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.deepers.ripetomatoes.data.MovieReview;

import java.util.ArrayList;
import java.util.List;

/**
 * This activity displays a list of movie reviews using a RecyclerView instead of the
 * legacy ListView.
 */
public class ListActivity extends AppCompatActivity {
    MovieReviewModel mViewModel;
    List<MovieReview> mReviews;
    ReviewsAdapter mReviewsAdapter;
    RecyclerView mRecyclerView;
    ProgressBar mSpinner;
    View mEmtpyOverlay;
    View mErrorOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mSpinner = findViewById(R.id.spinner);
        mErrorOverlay = findViewById(R.id.error_overlay);
        mEmtpyOverlay = findViewById(R.id.empty_overlay);

        mReviews = new ArrayList<>();
        mReviewsAdapter = new ReviewsAdapter(mReviews, this);
        mRecyclerView = findViewById(R.id.rv_movies);
        mRecyclerView.setAdapter(mReviewsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mRecyclerView.setHasFixedSize(true); // no longer true due to text wrapping

        // add a divider between rows of the RecyclerView
        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(getDrawable(R.drawable.list_divider));
        mRecyclerView.addItemDecoration(itemDecorator);

        // add a click listener to the RecyclerView
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        MovieReview movie = mReviews.get(position);

                        // normally the Details screen would fetch its own data and we just need
                        // to pass in the Movie ID, but as we don't have the API info to fetch
                        // detailed movie review data, we'll just pass in what we have via a
                        // an intent with serialized extras.
                        Intent launchDetailsScreen = new Intent(ListActivity.this, DetailsActivity.class);
                        launchDetailsScreen.putExtra("review", movie);
                        startActivity(launchDetailsScreen);
                    }
                }
        );

        // ViewModel with LiveData serves as our data source and decouples the concerns of
        // loading/parsing data from our UI. This also allows us to cache our data set in
        // the event the user rotates the device or our activity is otherwise destroyed.
        mViewModel = ViewModelProviders.of(this).get(MovieReviewModel.class);
        LiveData<List<MovieReview>> liveData = mViewModel.getReviews();
        liveData.observe(this, new Observer<List<MovieReview>>() {
            @Override
            public void onChanged(@Nullable List<MovieReview> movieReviews) {
                mSpinner.setVisibility(View.INVISIBLE);
                // update the RecyclerView.Adapter's data. DO NOT change the reference.
                if (movieReviews == null) {
                    // adjust error message if this is not the first chunk of data
                    mErrorOverlay.setVisibility(View.VISIBLE);
                    mEmtpyOverlay.setVisibility(View.GONE);
                } else if (movieReviews.size() == 0) {
                    // adjust error message if this is not the first chunk of data
                    mEmtpyOverlay.setVisibility(View.VISIBLE);
                    mErrorOverlay.setVisibility(View.GONE);
                } else {
                    mEmtpyOverlay.setVisibility(View.GONE);
                    mErrorOverlay.setVisibility(View.GONE);

                    mReviews.clear();
                    mReviews.addAll(movieReviews);
                    // notify adapter
                    mReviewsAdapter.notifyDataSetChanged(); // TODO replace with something more efficient, see https://guides.codepath.com/android/using-the-recyclerview
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        // remove overlays if visible
        if (mErrorOverlay.getVisibility() == View.VISIBLE) {
            mErrorOverlay.setVisibility(View.GONE);
        } else if (mEmtpyOverlay.getVisibility() == View.VISIBLE) {
            mEmtpyOverlay.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }
}
