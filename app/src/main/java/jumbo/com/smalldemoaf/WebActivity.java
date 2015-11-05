package jumbo.com.smalldemoaf;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Interpolator;
import android.webkit.WebSettings;
import android.webkit.WebView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import jumbo.com.smalldemoaf.utils.ApiLevelHelper;
import jumbo.com.smalldemoaf.utils.Network;
import jumbo.com.smalldemoaf.utils.WebClient;
/**
 * Created by jumbo on 11/4/15.
 */
public class WebActivity extends AppCompatActivity {

    @InjectView(R.id.web_view)
    WebView mWebView;

    @InjectView(R.id.toolbar_activity_promotion)
    Toolbar mToolbar;

    private Interpolator mInterpolator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        mInterpolator = new FastOutSlowInInterpolator();

        initToolbar();
        if (!Network.isAvailiable(WebActivity.this)) {
            Crouton.makeText(WebActivity.this, "The network is unavailable.", Style.ALERT).show();
        }else{
            mWebView.setWebViewClient(new WebClient());
            mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setLoadsImagesAutomatically(true);

            mWebView.loadUrl(intent.getStringExtra("web_address"));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {

        mToolbar.setBackgroundColor(getResources().getColor(R.color.theme_primary));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setToolbarElevation(boolean shouldElevate) {
        if (ApiLevelHelper.isAtLeast(Build.VERSION_CODES.LOLLIPOP)) {
            mToolbar.setElevation(shouldElevate ?
                    getResources().getDimension(R.dimen.elevation_header) : 0);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (mWebView == null) {

            // Skip the animation if icon or fab are not initialized.
            super.onBackPressed();
            finish();
            return;
        }

        // Scale the icon and fab to 0 size before calling onBackPressed if it exists.
        ViewCompat.animate(mWebView)
                .scaleX(.7f)
                .scaleY(.7f)
                .alpha(0f)
                .setInterpolator(mInterpolator)
                .start();
        mWebView = null;
        finish();
    }
}
