package jumbo.com.smalldemoaf;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import jumbo.com.smalldemoaf.custom_views.LobsterTextView;
import jumbo.com.smalldemoaf.model.Promotion;
import jumbo.com.smalldemoaf.utils.ApiLevelHelper;
import jumbo.com.smalldemoaf.utils.Network;

/**
 * Created by jumbo on 11/3/15.
 */
public class PromotionDetailActivity extends AppCompatActivity {

    private static final String STATE_IS_PLAYING = "isPlaying";

    private boolean mSavedStateIsPlaying;
    private Interpolator mInterpolator;
    private Animation animLinear;

    @InjectView(R.id.icon_promotion_detail)
    ImageView mCoverImageView;

    @InjectView(R.id.toolbar_activity_promotion)
    Toolbar mToolbar;
    @InjectView(R.id.toolbar_promotion_title)
    LobsterTextView toolBarTitle;

    @InjectView(R.id.fab_shop)
    FloatingActionButton fabShop;

    @InjectView(R.id.activity_detail_content)
    TextView descriptionText;

    @InjectView(R.id.activity_promotion_detail_footer)
    TextView promotionFooter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);

        Promotion promotion = (Promotion) getIntent().getParcelableExtra("promotion");

        initLayout(promotion.getButton().getTarget());

        if( !Network.isAvailiable(PromotionDetailActivity.this )){
            Crouton.makeText(PromotionDetailActivity.this, "The network is unavailable.", Style.ALERT).show();
            Picasso.with(PromotionDetailActivity.this)
                    .load( new File(promotion.getImage()) )
                    .fit().centerCrop()
                    .into(mCoverImageView);
        }else{
            Picasso.with(PromotionDetailActivity.this)
                    .load(promotion.getImage())
                    .fit().centerCrop()
                    .into(mCoverImageView);
        }



        mInterpolator = new FastOutSlowInInterpolator();
        animLinear = AnimationUtils.loadAnimation(PromotionDetailActivity.this, R.anim.anima_linear);


        initToolbar(promotion.getTitle());
        setDescription(promotion.getDescription());
        if (promotion.getFooter() != null) {
            String footer = promotion.getFooter().substring(0, promotion.getFooter().indexOf("<a"));
            promotionFooter.setText(footer);
        } else {
            promotionFooter.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(STATE_IS_PLAYING, fabShop.getVisibility() == View.GONE);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {

        fabShop.setScaleX(1);
        fabShop.setScaleY(1);
        fabShop.show();
        super.onResume();
    }

    private void initToolbar(String title) {

        mToolbar.setBackgroundColor(getResources().getColor(R.color.theme_primary));
        toolBarTitle.setText(title);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (mSavedStateIsPlaying) {
            // the toolbar should not have more elevation than the content while playing
            setToolbarElevation(false);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setToolbarElevation(boolean shouldElevate) {
        if (ApiLevelHelper.isAtLeast(Build.VERSION_CODES.LOLLIPOP)) {
            mToolbar.setElevation(shouldElevate ?
                    getResources().getDimension(R.dimen.elevation_header) : 0);
        }
    }

    @Override
    public void onBackPressed() {
        if (mCoverImageView == null || fabShop == null) {

            // Skip the animation if icon or fab are not initialized.
            super.onBackPressed();
            return;
        }

        // Scale the icon and fab to 0 size before calling onBackPressed if it exists.
        ViewCompat.animate(mCoverImageView)
                .scaleX(.5f)
                .scaleY(.5f)
                .alpha(0f)
                .setInterpolator(mInterpolator)
                .start();

        PromotionDetailActivity.super.onBackPressed();
    }


    private void initLayout(final String address) {
        //noinspection PrivateResource
        ViewCompat.animate(mCoverImageView)
                .scaleX(1)
                .scaleY(1)
                .alpha(1)
                .setInterpolator(mInterpolator)
                .setStartDelay(300)
                .start();
        fabShop.setImageResource(R.drawable.ic_blcak_shop);
        fabShop.show();

        fabShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(PromotionDetailActivity.this, WebActivity.class);
                intent.putExtra("web_address", address);
                ViewCompat.animate(v)
                        .scaleX(0f)
                        .scaleY(0f)
                        .setInterpolator(mInterpolator)
                        .setStartDelay(100)
                        .setListener(new ViewPropertyAnimatorListenerAdapter() {
                            @SuppressLint("NewApi")
                            @Override
                            public void onAnimationEnd(View view) {
                                if (isFinishing() ||
                                        (ApiLevelHelper.isAtLeast(Build.VERSION_CODES.JELLY_BEAN_MR1)
                                                && isDestroyed())) {
                                    return;
                                }
                                startActivity(intent);
                            }
                        })
                        .start();
            }
        });
    }

    public void setDescription(String description) {

        descriptionText.setVisibility(View.VISIBLE);
        descriptionText.setText(description);
        descriptionText.startAnimation(animLinear);
    }

}
