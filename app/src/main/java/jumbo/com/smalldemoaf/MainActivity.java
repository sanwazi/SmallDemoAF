package jumbo.com.smalldemoaf;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import jumbo.com.smalldemoaf.DatabaseManager.DatabaseHelper;
import jumbo.com.smalldemoaf.DatabaseManager.PromotionDao;
import jumbo.com.smalldemoaf.adapter.PromotionAdapter;
import jumbo.com.smalldemoaf.model.Promotion;
import jumbo.com.smalldemoaf.model.PromotionButton;
import jumbo.com.smalldemoaf.utils.Constant;
import jumbo.com.smalldemoaf.utils.Network;
import jumbo.com.smalldemoaf.utils.RecyclerInsetsDecoration;
import jumbo.com.smalldemoaf.utils.RecyclerViewClickListener;
import jumbo.com.smalldemoaf.utils.TransitionHelper;

/**
 * Created by jumbo on 11/2/15.
 */

public class MainActivity extends ActionBarActivity implements RecyclerViewClickListener, View.OnClickListener {
    /**
     * A container used between this activity and PromotionDetailActivity
     * to share a Bitmap with a SharedElementTransition
     */

    public static SparseArray<Bitmap> sPhotoCache = new SparseArray<Bitmap>(1);

    private PromotionAdapter mPromotionAdapter;

    public float mBackgroundTranslation;

    private ArrayList<Promotion> promotionList;

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private PromotionDao promotionDao;

    @InjectView(R.id.activity_promotion_toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.recycler_popular_promotion)
    RecyclerView mRecycler;

    @Optional
    @InjectView(R.id.activity_promotion_background_view)
    View mTabletBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");

        promotionDao = new PromotionDao(MainActivity.this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(
                R.drawable.ic_menu_white_24dp);

        mToolbar.setNavigationOnClickListener(this);

        mRecycler.addItemDecoration(new RecyclerInsetsDecoration(this));

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        promotionList = new ArrayList<Promotion>();
        showPromotions();
    }

    public void onActivityReenter(int resultCode, Intent data) {

        super.onActivityReenter(resultCode, data);
        Log.d("[DEBUG]", "MoviesActivity onActivityReenter - Re-enter");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        if (mPromotionAdapter != null) {
            outState.putFloat("background_translation", mBackgroundTranslation);
        }

    }

    public Context getContext() {
        return this;
    }

    public void showPromotions() {
        if (promotionList.size() == 0) {
            mPromotionAdapter = new PromotionAdapter(new ArrayList<Promotion>(), MainActivity.this);
            mPromotionAdapter.setRecyclerListListener(this);
            mRecycler.setAdapter(mPromotionAdapter);
        }

        if (!Network.isAvailiable(MainActivity.this)) {
            Crouton.makeText(MainActivity.this, "The network is unavailable.", Style.ALERT).show();

            mPromotionAdapter.appendPromotion(promotionDao.getPromotionList());

        } else {
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams mParams = new RequestParams();

            client.get(Constant.BASIC_STATIC_URL, mParams, new AsyncHttpResponseHandler() {

                @Override
                public void onSuccess(String response) {
                    processingJson(response);
                    mPromotionAdapter.appendPromotion(promotionList);

                    ArrayList<String> titles = promotionDao.getPromotionTitles();

                    for (Promotion p : promotionList) {
                        if (!titles.contains(p.getTitle())) {
                            ContentValues values = new ContentValues();
                            values.put(DatabaseHelper.COLUMN_PROMOTION_ID, UUID.randomUUID().hashCode());
                            values.put(DatabaseHelper.COLUMN_PROMOTION_BUTTON_TARGET, p.getButton().getTarget());
                            values.put(DatabaseHelper.COLUMN_PROMOTION_BUTTON_TITLE, p.getButton().getTitle());
                            values.put(DatabaseHelper.COLUM_PROMOTION_FOOTER, p.getFooter());
                            values.put(DatabaseHelper.COLUMN_PROMOTION_DESCRIPTION, p.getDescription());
                            values.put(DatabaseHelper.COLUMN_PROMOTION_IMAGE_ADDRESS, p.getImage());
                            values.put(DatabaseHelper.COLUMN_PROMOTION_TITLE, p.getTitle());

                            promotionDao.insert(values);
                            promotionDao.getPromotionList();
                        }

                    }
                }

                public void onFailure(int statusCode, Throwable error,
                                      String content) {
                    // TODO Auto-generated method stub
                    if (statusCode == 404) {
                        Crouton.makeText(MainActivity.this,
                                "Requested resource not found",
                                Style.ALERT).show();
                    } else if (statusCode == 500) {
                        Crouton.makeText(MainActivity.this,
                                "Something went wrong at server end",
                                Style.ALERT).show();
                    } else {
                        Crouton.makeText(
                                MainActivity.this,
                                "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]",
                                Style.ALERT).show();
                    }
                }
            });
        }

    }

    @Override
    public void onClick(View touchedView, int promotionPosition, float touchedX, float touchedY) {

        Intent promotionDetailActivityIntent = new Intent(
                MainActivity.this, PromotionDetailActivity.class);

        Promotion promotion = mPromotionAdapter.getPromotionList().get(promotionPosition);
        Bundle bundle = new Bundle();
        bundle.putParcelable("promotion", promotion);
        promotionDetailActivityIntent.putExtras(bundle);

        startDetailActivityWithTransition(MainActivity.this, touchedView, promotionDetailActivityIntent);
    }

    private void startDetailActivityWithTransition(Activity activity, View toolbar,
                                                   Intent promotionDetailActivityIntent) {

        final android.support.v4.util.Pair[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, false,
                new android.support.v4.util.Pair<>(toolbar, activity.getString(R.string.transition_toolbar)));
        ActivityOptionsCompat sceneTransitionAnimation = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, pairs);
        // Start the activity with the participants, animating from one to the other.
        final Bundle transitionBundle = sceneTransitionAnimation.toBundle();
        ActivityCompat.startActivity(activity, promotionDetailActivityIntent, transitionBundle);

    }

    @Override
    public void onClick(View v) {
        mNavigationDrawerFragment.openFragment();
    }


    public void processingJson(String strJSON) {
        try {
            JSONObject group = new JSONObject(strJSON);
            JSONArray promotions = group.getJSONArray("promotions");
            Promotion promotion = null;
            PromotionButton button = null;
            for (int i = 0; i < promotions.length(); i++) {
                JSONObject JSONpromotion = promotions.getJSONObject(i);
                JSONObject JSONbutton = null;
                if (i == 1) {
                    JSONArray JSONbuttons = JSONpromotion.getJSONArray("button");
                    JSONbutton = JSONbuttons.getJSONObject(0);
                } else
                    JSONbutton = JSONpromotion.getJSONObject("button");
                button = new PromotionButton(JSONbutton.getString("target"), JSONbutton.getString("title"));
                promotion = new Promotion(button);

                if (JSONpromotion.has("description"))
                    promotion.setDescription(JSONpromotion.getString("description"));
                Log.i("processing description", JSONpromotion.getString("description"));
                if (JSONpromotion.has("footer"))
                    promotion.setFooter(JSONpromotion.getString("footer"));

                if (JSONpromotion.has("image"))
                    promotion.setImage(JSONpromotion.getString("image"));

                if (JSONpromotion.has("title"))
                    promotion.setTitle(JSONpromotion.getString("title"));

                promotionList.add(promotion);
                promotion = null;
                button = null;
            }

        } catch (JSONException e) {
            Crouton.makeText(MainActivity.this, "something is wrong!!!", Style.ALERT).show();
            e.printStackTrace();
        }
    }
}
