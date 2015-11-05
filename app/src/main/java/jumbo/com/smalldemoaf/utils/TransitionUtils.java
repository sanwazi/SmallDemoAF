package jumbo.com.smalldemoaf.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Transition;

import jumbo.com.smalldemoaf.R;

/**
 * Created by jumbo on 11/3/15.
 */
public class TransitionUtils {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Transition makeEnterTransition () {

        Explode explodeTransition = new Explode();
        return explodeTransition;
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Transition makeSharedElementEnterTransition(Context context) {

        Transition changeBounds = new ChangeBounds();
        changeBounds.addTarget(R.id.item_promotion_cover);
        return changeBounds;
    }
}
