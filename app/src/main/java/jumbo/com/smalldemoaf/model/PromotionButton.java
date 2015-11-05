package jumbo.com.smalldemoaf.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jumbo on 11/2/15.
 */
public class PromotionButton implements Parcelable {
    String target;
    String title;

    public PromotionButton( String target, String title){
        this.target = target;
        this.title = title;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTarget() {
        return target;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.target);
        dest.writeString(this.title);
    }

    protected PromotionButton(Parcel in) {
        this.target = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<PromotionButton> CREATOR = new Parcelable.Creator<PromotionButton>() {
        public PromotionButton createFromParcel(Parcel source) {
            return new PromotionButton(source);
        }

        public PromotionButton[] newArray(int size) {
            return new PromotionButton[size];
        }
    };
}
