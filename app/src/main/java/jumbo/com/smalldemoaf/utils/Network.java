package jumbo.com.smalldemoaf.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
 * Created by jumbo on 11/2/15.
 */
public class Network {
	public static boolean isAvailiable(Context ctx) {
		ConnectivityManager conMgr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo i = conMgr.getActiveNetworkInfo();
		if (i == null)
			return false;
		if (!i.isConnected())
			return false;
		if (!i.isAvailable())
			return false;
		return true;
	}
}
