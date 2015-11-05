package jumbo.com.smalldemoaf.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import jumbo.com.smalldemoaf.R;
import jumbo.com.smalldemoaf.model.Promotion;
import jumbo.com.smalldemoaf.utils.RecyclerViewClickListener;

/**
 * Created by jumbo on 11/2/15.
 */
public class PromotionAdapter extends RecyclerView.Adapter<PromotionViewHolder> {

    private Context mContext;
    private List<Promotion> mPromotionList;
    private RecyclerViewClickListener mRecyclerClickListener;

    public PromotionAdapter(List<Promotion> mPromotionList, Context context) {

        this.mPromotionList = mPromotionList;
        mContext = context;
    }

    public List<Promotion> getPromotionList() {

        return mPromotionList;
    }

    public void setRecyclerListListener(RecyclerViewClickListener mRecyclerClickListener) {
        this.mRecyclerClickListener = mRecyclerClickListener;
    }

    @Override
    public PromotionViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View rowView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_promotion, viewGroup, false);

        this.mContext = viewGroup.getContext();

        return new PromotionViewHolder(rowView, mRecyclerClickListener);
    }

    @Override
    public void onBindViewHolder(final PromotionViewHolder holder, final int position) {

        Promotion selectedPromotion = mPromotionList.get(position);

        holder.titleTextView.setText(selectedPromotion.getTitle());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            holder.coverImageView.setTransitionName("cover" + position);

        String posterURL = selectedPromotion.getImage();

        if( posterURL.indexOf("http") == 0 ){
            Picasso.with(mContext)
                    .load(posterURL)
                    .fit().centerCrop()
                    .into(holder.coverImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            mPromotionList.get(position).setPromotionReady(true);
                        }

                        @Override
                        public void onError() {

                        }
                    });
        }else{

            Picasso.with(mContext)
                    .load(new File(posterURL))
                    .fit().centerCrop()
                    .into(holder.coverImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            mPromotionList.get(position).setPromotionReady(true);
                        }

                        @Override
                        public void onError() {

                        }
                    });
        }


    }

//    public boolean isPromotionReady(int position) {
//
//        return mPromotionList.get(position).isPromotionReady();
//    }

    @Override
    public int getItemCount() {

        return mPromotionList.size();
    }

    public void appendPromotion(List<Promotion> promotionList) {

        mPromotionList.addAll(promotionList);
        notifyDataSetChanged();
    }
}

class PromotionViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {

    private final RecyclerViewClickListener onClickListener;
    TextView titleTextView;
    TextView authorTextView;
    ImageView coverImageView;

    public PromotionViewHolder(View itemView, RecyclerViewClickListener onClickListener) {

        super(itemView);

        titleTextView = (TextView) itemView.findViewById(R.id.item_promotion_title);
        coverImageView = (ImageView) itemView.findViewById(R.id.item_promotion_cover);
        coverImageView.setDrawingCacheEnabled(true);
        coverImageView.setOnTouchListener(this);
        this.onClickListener = onClickListener;
    }

    public void setReady(boolean ready) {

        coverImageView.setTag(ready);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP && event.getAction() != MotionEvent.ACTION_MOVE) {

            onClickListener.onClick(v, getPosition(), event.getX(), event.getY());
        }
        return true;
    }


}
