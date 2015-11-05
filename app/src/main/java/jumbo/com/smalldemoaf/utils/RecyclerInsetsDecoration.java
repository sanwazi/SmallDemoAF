package jumbo.com.smalldemoaf.utils;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import jumbo.com.smalldemoaf.R;

/**
 *
 * Created by jumbo on 11/2/15.
 *
 * ItemDecoration implementation that applies an inset margin
 * around each child of the RecyclerView. The inset value is controlled
 * by a dimension resource.
 *
 */
public class RecyclerInsetsDecoration extends RecyclerView.ItemDecoration {

    private int mInsets;

    public RecyclerInsetsDecoration(Context context) {
        mInsets = context.getResources().getDimensionPixelSize(R.dimen.insets);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        //We can supply forced insets for each item view here in the Rect
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(mInsets, mInsets, mInsets, mInsets);
    }
}