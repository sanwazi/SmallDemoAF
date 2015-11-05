package jumbo.com.smalldemoaf.DatabaseManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import jumbo.com.smalldemoaf.model.Promotion;
import jumbo.com.smalldemoaf.model.PromotionButton;


/**
 * Created by jumbo on 11/4/15.
 */
public class PromotionDao {

    private DatabaseHelper dbHelper;
    private Context context;

    public PromotionDao( Context context ){
        dbHelper = new DatabaseHelper(context);
        this.context = context;
    }


    public void insert( ContentValues values ){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(DatabaseHelper.TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<Promotion> getPromotionList(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor =  db.rawQuery(" select * from " + DatabaseHelper.TABLE_NAME, null);
        ArrayList<Promotion> promotions = new ArrayList<Promotion>();
        while( cursor.moveToNext() ){
            PromotionButton button = new PromotionButton( cursor.getString(2),cursor.getString(3)  );
            if( cursor.getString(6) == null ){
                ImageSaver saver = new ImageSaver( cursor.getString(7), context, cursor.getString(1) );
                saver.execute();
            }
            Promotion promotion = new Promotion( button,
                    cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(1));


            promotions.add(promotion);
        }
        cursor.close();
        db.close();
        return promotions;
    }

    public ArrayList<String> getPromotionTitles(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor =  db.rawQuery(" select title from " + DatabaseHelper.TABLE_NAME, null);
        ArrayList<String> promotions = new ArrayList<String>();
        while( cursor.moveToNext() ){
            promotions.add(cursor.getString(0));
        }
        cursor.close();
        db.close();
        return promotions;
    }

    public void updateImage( String title,String imagePath ){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues( );
        values.put(DatabaseHelper.COLUMN_PROMOTION_IMAGE_NAME, imagePath);
        db.update(DatabaseHelper.TABLE_NAME, values, DatabaseHelper.TABLE_NAME + "." + DatabaseHelper.COLUMN_PROMOTION_TITLE + " = ? ", new String[]{title});

    }

}
