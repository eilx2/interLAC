package com.mun.minh_minhh.interlac.Events.Arts;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alexvasilkov.android.commons.texts.SpannableBuilder;
import com.alexvasilkov.android.commons.utils.Views;
import com.alexvasilkov.foldablelayout.UnfoldableView;
import com.mun.minh_minhh.interlac.R;
import com.mun.minh_minhh.interlac.Events.Arts.items.Painting;
import com.mun.minh_minhh.interlac.Events.Arts.items.PaintingsAdapter;
import com.mun.minh_minhh.interlac.Events.Arts.utils.GlideHelper;

public class ArtMain extends AppCompatActivity {

    private ListView mListView;
    private View mListTouchInterceptor;
    private View mDetailsLayout;
    private UnfoldableView mUnfoldableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfoldable_details);

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(new PaintingsAdapter(this));
        mListTouchInterceptor = findViewById(R.id.touch_interceptor_view);
        mListTouchInterceptor.setClickable(false);

        mDetailsLayout = findViewById(R.id.details_layout);
        mDetailsLayout.setVisibility(View.INVISIBLE);

        mUnfoldableView = (UnfoldableView) findViewById(R.id.unfoldable_view);

        mUnfoldableView.setOnFoldingListener(new UnfoldableView.SimpleFoldingListener() {
            @Override
            public void onUnfolding(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(true);
                mDetailsLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onUnfolded(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(false);
            }

            @Override
            public void onFoldingBack(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(true);
            }

            @Override
            public void onFoldedBack(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(false);
                mDetailsLayout.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mUnfoldableView != null
                && (mUnfoldableView.isUnfolded() || mUnfoldableView.isUnfolding())) {
            mUnfoldableView.foldBack();
        } else {
            super.onBackPressed();
        }
    }

    public void openDetails(View coverView, Painting painting) {
        final ImageView image = Views.find(mDetailsLayout, R.id.details_image);
        final TextView title = Views.find(mDetailsLayout, R.id.details_title);
        final TextView description = Views.find(mDetailsLayout, R.id.details_text);

        GlideHelper.loadPaintingImage(image, painting);
        title.setText(painting.getTitle());

        SpannableBuilder builder = new SpannableBuilder(this);
        builder
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append("Year").append(": ")
                .clearStyle()
                .append(painting.getYear()).append("\n")
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append("Location").append(": ")
                .clearStyle()
                .append(painting.getLocation());
        description.setText(builder.build());

        mUnfoldableView.unfold(coverView, mDetailsLayout);

    }
}
