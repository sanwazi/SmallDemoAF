package jumbo.com.smalldemoaf.DatabaseManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jumbo on 11/4/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    public static String DATABASE_NAME = "af_database";
    public static String TABLE_NAME = "promotion";

    public static String COLUMN_PROMOTION_ID = "promotionID";
    public static String COLUMN_PROMOTION_TITLE = "title";
    public static String COLUMN_PROMOTION_BUTTON_TARGET = "target";
    public static String COLUMN_PROMOTION_BUTTON_TITLE="buttontitle";
    public static String COLUMN_PROMOTION_DESCRIPTION = "description";
    public static String COLUM_PROMOTION_FOOTER = "footer";
    public static String COLUMN_PROMOTION_IMAGE_NAME="image";
    public static String COLUMN_PROMOTION_IMAGE_ADDRESS = "imageaddress";

    private static int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_PROMOTION_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_PROMOTION_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_PROMOTION_TITLE + " TEXT UNIQUE NOT NULL, "
                + COLUMN_PROMOTION_BUTTON_TARGET+ " TEXT NOT NULL, "
                + COLUMN_PROMOTION_BUTTON_TITLE + " TEXT NOT NULL, "
                + COLUMN_PROMOTION_DESCRIPTION + " TEXT, "
                + COLUM_PROMOTION_FOOTER + " TEXT, "
                + COLUMN_PROMOTION_IMAGE_NAME + " TEXT, "
                + COLUMN_PROMOTION_IMAGE_ADDRESS + " TEXT);";

        db.execSQL(SQL_CREATE_PROMOTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String upgradeTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(upgradeTableQuery);
    }
}
