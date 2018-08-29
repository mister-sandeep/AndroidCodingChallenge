package com.deepers.ripetomatoes;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mSpinner = findViewById(R.id.spinner);

        mReviews = new ArrayList<>();
        mReviewsAdapter = new ReviewsAdapter(mReviews, this);
        mRecyclerView = findViewById(R.id.rv_movies);
        mRecyclerView.setAdapter(mReviewsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        // add a divider between rows of the RecyclerView
        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(getDrawable(R.drawable.list_divider));
        mRecyclerView.addItemDecoration(itemDecorator);

        // add a click listener to the RecyclerView
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        // launch browser to full review content
                        Intent launchFullReview = new Intent(Intent.ACTION_VIEW);
                        MovieReview movie = mReviews.get(position);
                        if (movie != null && movie.getLink() != null) {
                            String uri = movie.getLink().getUrl();
                            if (!uri.isEmpty()) {
                                launchFullReview.setData(Uri.parse(uri));
                                startActivity(launchFullReview);
                            }
                        }
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
                if (movieReviews != null && movieReviews.size() > 0) {
                    mReviews.clear();
                    mReviews.addAll(movieReviews);
                    // notify adapter
                    mReviewsAdapter.notifyDataSetChanged(); // TODO replace with something more efficient, see https://guides.codepath.com/android/using-the-recyclerview
                } else {
                    // something went wrong... we got an empty list. Display appropriate error.
                }
            }
        });
    }

}
