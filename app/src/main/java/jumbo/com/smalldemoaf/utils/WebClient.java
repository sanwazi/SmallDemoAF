package jumbo.com.smalldemoaf.utils;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by jumbo on 11/4/15.
 */
public class WebClient extends WebViewClient{
//    @Override
//    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        if(Uri.parse(url).getHost().endsWith("html5rocks.com")) {
//            return false;
//        }
//
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//        view.getContext().startActivity(intent);
//        return true;
//    }
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
