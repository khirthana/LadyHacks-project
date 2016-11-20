package ladyhacks_project.ladyhacks_project;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.*;

public class ProductDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "product";
    private static final String TABLE_PRODUCT_DETAIL = "productdetail";

    private static final String KEY_id = "product_id";
    private static final String KEY_name = "product_name";
    private static final String KEY_exp = "product_exp";

    public ProductDBHelper (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    public void onCreate (SQLiteDatabase db){
        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT_DETAIL + "("
                + KEY_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_name + " TEXT,"
                + KEY_exp+ " TEXT" + ")";

        db.execSQL(CREATE_PRODUCT_TABLE);
    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT_DETAIL);

        onCreate(db);
    }


    //function for inserting a new product into the database
    public boolean addData (String name, String exp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_name,name);
        contentValues.put(KEY_exp,exp);
        long result = db.insert (TABLE_PRODUCT_DETAIL, null, contentValues);

        if (result == 1)
            return false;
        else
            return true;
    }

    //function for querying the database, finding all products
    public List<Product> getAllData (){
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor res = db.rawQuery("Select * From "+ TABLE_PRODUCT_DETAIL, null);
        //return res;

        ArrayList<Product> results = new ArrayList<>();

        String[] columns = new String[] { KEY_id, KEY_name,KEY_exp};
        String where = "";
        String[] whereArgs = new String[] {};
        String groupBy = "";
        String groupArgs = "";
        String orderBy =KEY_id;

        Cursor cursor = db.query(TABLE_PRODUCT_DETAIL, columns, where, whereArgs,groupBy, groupArgs, orderBy);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            int product_id=cursor.getInt(0);
            String product_name= cursor.getString(1);
            String product_exp=cursor.getString(2);

            results.add(new Product(product_name,product_exp));

            cursor.moveToNext();
        }

        return results;
    }

    //  function for deleting a product from the database
    public Integer deleteData (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_PRODUCT_DETAIL, "ID = ?", new String [] {id});
    }
}