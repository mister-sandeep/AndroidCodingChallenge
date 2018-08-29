package com.deepers.ripetomatoes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.deepers.ripetomatoes.data.MovieReview;

import java.util.List;

/**
 * Created by Deepers on 6/25/2018.
 *
 * Standard RecyclerView.Adapter implementation using the inherent ViewHolder pattern.
 */
public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {
    private List<MovieReview> mReviews;
    private Context mContext; // for Glide image loading

    public ReviewsAdapter(List<MovieReview> reviews, Context context) {
        mReviews = reviews;
        mContext = context;
    }

    @Override
    public ReviewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View movieView = inflater.inflate(R.layout.item_movie, parent, false);

        ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewsAdapter.ViewHolder holder, int position) {
        MovieReview review = mReviews.get(position);

        ImageView thumbnail = holder.thumbnail;
        //thumbnail.setImageURI(Uri.parse(review.getMultimedia().getSrc()));
        Glide.with(mContext)
                .load(review.getMultimedia().getSrc())
                .into(thumbnail);

        TextView title = holder.title;
        title.setText(review.getDisplayTitle());

        TextView pubDate = holder.pubDate;
        pubDate.setText(review.getPublicationDate());

        TextView mpaaRating = holder.mpaaRating;
        String rating = review.getMpaaRating().isEmpty() ? "Unrated" : review.getMpaaRating();
        mpaaRating.setText(rating);

        TextView reviewer = holder.reviewer;
        reviewer.setText(review.getByline());

        TextView headline = holder.headline;
        headline.setText(review.getHeadline());

        TextView summary = holder.summary;
        summary.setText(review.getSummaryShort());
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView thumbnail;
        public TextView title;
        public TextView pubDate;
        public TextView mpaaRating;
        public TextView reviewer;
        public TextView headline;
        public TextView summary;

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
            pubDate = itemView.findViewById(R.id.pub_date);
            mpaaRating = itemView.findViewById(R.id.mpaa_rating);
            reviewer = itemView.findViewById(R.id.reviewer);
            headline = itemView.findViewById(R.id.headline);
            summary = itemView.findViewById(R.id.summary_short);
        }
    }
}
