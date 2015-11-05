package jumbo.com.smalldemoaf.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jumbo on 11/2/15.
 */
public class Promotion implements Parcelable {

    private PromotionButton button;
    private String description;
    private String footer;
    private String imageAddress;
    private String title;

    private boolean promotionReady;


    public Promotion(PromotionButton button) {
        this.button = button;
    }

    public Promotion(PromotionButton button, String description, String footer, String imageAdd, String title) {
        this.button = button;
        this.description = description;
        this.footer = footer;
        this.imageAddress = imageAdd;
        this.title = title;
    }

    public Promotion() {
        super();
    }


    public PromotionButton getButton() {
        return button;
    }

    public void setButton(PromotionButton button) {
        this.button = button;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getImage() {
        return imageAddress;
    }

    public void setImage(String image) {
        this.imageAddress = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPromotionReady(boolean promtionReady) {
        this.promotionReady = promotionReady;
    }

    public boolean getPromotionReady() {
        return promotionReady;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.button, 0);
        dest.writeString(this.description);
        dest.writeString(this.footer);
        dest.writeString(this.imageAddress);
        dest.writeString(this.title);
        dest.writeByte(promotionReady ? (byte) 1 : (byte) 0);
    }

    protected Promotion(Parcel in) {
        this.button = in.readParcelable(PromotionButton.class.getClassLoader());
        this.description = in.readString();
        this.footer = in.readString();
        this.imageAddress = in.readString();
        this.title = in.readString();
        this.promotionReady = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Promotion> CREATOR = new Parcelable.Creator<Promotion>() {
        public Promotion createFromParcel(Parcel source) {
            return new Promotion(source);
        }

        public Promotion[] newArray(int size) {
            return new Promotion[size];
        }
    };
}
