package com.deepers.ripetomatoes;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.deepers.ripetomatoes.data.MovieReview;
import com.deepers.ripetomatoes.data.ReviewApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Deepers on 8/25/2018.
 *
 * The current implementation only fetches the first batch of results. The JSON returned by this
 * API contains root level elements for "has_more" and "num_results" which should be hooked up
 * to either the scrollable view (once user scrolls to bottom) or a 'more' button of sorts. The
 * updateReviews() method below should then be augmented to track offset and pass the
 * request range as a query param.
 *
 * MutableLiveData is used here to provide for future support of changing data.
 * OkHttp is used to vastly simplify the networking implementation.
 * Jackson is used to marshall to and from json.
 */
public class MovieReviewModel extends ViewModel {
    private static final String TAG = MovieReviewModel.class.getSimpleName();
    private static final String API_ENDPOINT = "http://api.nytimes.com/svc/movies/v2/reviews/dvd-picks.json?order=by-date&api-key=b75da00e12d54774a2d362adddcc9bef";
    private static final int API_TIMEOUT = 5; // seconds
    private MutableLiveData<List<MovieReview>> mReviews;
    private OkHttpClient mClient;

    public MovieReviewModel() {
        mClient = new OkHttpClient.Builder()
                .readTimeout(API_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    public LiveData<List<MovieReview>> getReviews() {
        if (mReviews == null) {
            mReviews = new MutableLiveData<List<MovieReview>>();
            loadReviews();
        }
        return mReviews;
    }

    public void updateReviews() {
        loadReviews();
    }

    private void loadReviews() {
        // do async fetch of data using OkHttp and update live data
        final Request request = new Request.Builder()
                .url(API_ENDPOINT)
                .build();

        Log.d(TAG, String.format("Request: %s", request.toString()));


        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mReviews.postValue(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, String.format("Response Status: %d (%s)", response.code(), response.message()));
                String bodyString;
                ReviewApiResponse apiResponse = null;

                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    bodyString = body.string();

                    ObjectMapper objectMapper = new ObjectMapper();
                    apiResponse = objectMapper.readValue(bodyString, ReviewApiResponse.class);

                    Log.d(TAG, "Response body: " + bodyString);

                    List<MovieReview> results = apiResponse.getResults();

                    if (apiResponse.getNumResults() != results.size()) {
                        mReviews.postValue(null);
                    } else {
                        mReviews.postValue(results); // even if results.size == 0
                    }

                } else {
                    mReviews.postValue(null);
                }
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        // do any cleanup of data or OkHttp if needed
    }
}
