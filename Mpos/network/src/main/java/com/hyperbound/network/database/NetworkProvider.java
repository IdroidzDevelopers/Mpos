package com.hyperbound.network.database;

/**
 * Created by aarokiax on 12/27/2016.
 */


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Aron on 12/28/2016.
 * Provider class for Network module
 */
public class NetworkProvider extends ContentProvider {

    private static final String TAG = NetworkProvider.class.getSimpleName();
    private static final boolean DEBUG = true;

    public static final String DATABASE_NAME = "mpos.db";
    public static final String TABLE_CATEGORY = "category_table";
    public static final String TABLE_SUBCATEGORY = "subcategory_table";
    public static final String TABLE_ITEM = "item_table";
    public static final String TABLE_DISCOUNT = "discount_table";
    public static final String TABLE_OTHER_CHARGES = "ocharges_table";
    private static final int DATABASE_VERSION = 1;

    public static final String AUTHORITY = "com.hyperbound.network.database.NetworkProvider";
    private static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final Uri CONTENT_URI_CATEGORY_TABLE = Uri.parse(CONTENT_URI + "/" + TABLE_CATEGORY);
    public static final Uri CONTENT_URI_SUBCATEGORY_TABLE = Uri.parse(CONTENT_URI + "/"
            + TABLE_SUBCATEGORY);
    public static final Uri CONTENT_URI_ITEM_TABLE = Uri.parse(CONTENT_URI + "/" + TABLE_ITEM);
    public static final Uri CONTENT_URI_DISCOUNT_TABLE = Uri.parse(CONTENT_URI + "/" + TABLE_DISCOUNT);
    public static final Uri CONTENT_URI_OTHER_CHARGES_TABLE = Uri.parse(CONTENT_URI + "/" + TABLE_OTHER_CHARGES);


    public DatabaseHelper mDbHelper;
    private static final UriMatcher sUriMatcher;
    /**
     * Columns for the table
     */
    public static final String CATEGORY_ID = "categoryid";
    public static final String LCATEGORY_ID = "lcategoryid";
    public static final String CATEGORY_KEY = "categorykey";
    public static final String CATEGORY_NAME = "categoryname";
    public static final String SUB_CATEGORY_ID = "subcategoryid";
    public static final String LSUB_CATEGORY_ID = "lsubcategoryid";
    public static final String SUB_CATEGORY_KEY = "subcategorykey";
    public static final String SUB_CATEGORY_NAME = "subcategoryname";
    public static final String ITEM_ID = "itemid";
    public static final String LITEM_ID = "litemid";
    public static final String ITEM_NAME = "itemname";
    public static final String ITEM_UNIT_PRICE = "itemunitprice";
    public static final String ITEM_CATEGORY = "itemcategory";
    public static final String ITEM_SUB_CATEGORY = "itemsubcategory";
    public static final String ITEM_IMAGE = "itemimage";
    public static final String DISCOUNT_ID = "discountid";
    public static final String DISCOUNT_NAME = "discountname";
    public static final String DISCOUNT_PERCENT = "discountpercent";
    public static final String OTHER_CHARGES_ID = "ochargesid";
    public static final String OTHER_CHARGES_NAME = "ochargesname";
    public static final String OTHER_CHARGES_VALUE = "ochargesvalue";

    private static final String CREATE_TABLE_CATEGORY_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_CATEGORY + "(" + LCATEGORY_ID + " TEXT PRIMARY KEY," + CATEGORY_ID + " TEXT,"
            + CATEGORY_NAME + " TEXT)";
    private static final String CREATE_TABLE_SUBCATEGORY_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_SUBCATEGORY + "(" + LSUB_CATEGORY_ID + " TEXT PRIMARY KEY," + SUB_CATEGORY_ID + " TEXT,"
            + SUB_CATEGORY_NAME + " TEXT)";
    private static final String CREATE_TABLE_ITEM_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_ITEM + "(" + LITEM_ID + " TEXT PRIMARY KEY," + ITEM_ID + " TEXT,"
            + ITEM_NAME + " TEXT," + ITEM_CATEGORY + " TEXT," + ITEM_SUB_CATEGORY + " TEXT," + ITEM_UNIT_PRICE + " INTEGER," + ITEM_IMAGE + " BLOB)";
    private static final String CREATE_TABLE_DISCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_DISCOUNT + "(" + DISCOUNT_ID + " TEXT PRIMARY KEY," + DISCOUNT_NAME + " TEXT,"
            + DISCOUNT_PERCENT + " INTEGER)";
    private static final String CREATE_TABLE_OTHER_CHARGES_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_OTHER_CHARGES + "(" + OTHER_CHARGES_ID + " TEXT PRIMARY KEY," + OTHER_CHARGES_NAME + " TEXT,"
            + OTHER_CHARGES_VALUE + " INTEGER)";


    private static final int CASE_CATEGORY_TABLE = 1;
    private static final int CASE_SUBCATEGORY_TABLE = 2;
    private static final int CASE_ITEM_TABLE = 3;
    private static final int CASE_DISCOUNT_TABLE = 4;
    private static final int CASE_OTHER_CHARGES_TABLE = 5;
    private static final int CASE_DEFAULT = 6;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, TABLE_CATEGORY, CASE_CATEGORY_TABLE);
        sUriMatcher.addURI(AUTHORITY, TABLE_SUBCATEGORY, CASE_SUBCATEGORY_TABLE);
        sUriMatcher.addURI(AUTHORITY, TABLE_ITEM, CASE_ITEM_TABLE);
        sUriMatcher.addURI(AUTHORITY, TABLE_DISCOUNT, CASE_DISCOUNT_TABLE);
        sUriMatcher.addURI(AUTHORITY, TABLE_OTHER_CHARGES, CASE_OTHER_CHARGES_TABLE);
        sUriMatcher.addURI(AUTHORITY, "/*", CASE_DEFAULT);
    }

    @Override
    public String getType(Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case CASE_CATEGORY_TABLE:
                return AUTHORITY + "/" + TABLE_CATEGORY;
            case CASE_SUBCATEGORY_TABLE:
                return AUTHORITY + "/" + TABLE_SUBCATEGORY;
            case CASE_ITEM_TABLE:
                return AUTHORITY + "/" + TABLE_ITEM;
            case CASE_DISCOUNT_TABLE:
                return AUTHORITY + "/" + TABLE_DISCOUNT;
            case CASE_OTHER_CHARGES_TABLE:
                return AUTHORITY + "/" + TABLE_OTHER_CHARGES;
            case CASE_DEFAULT:
                return AUTHORITY + "/*";
            default:
                return null;
        }
    }


    @Override
    public boolean onCreate() {
        mDbHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL(CREATE_TABLE_CATEGORY_TABLE);
        db.execSQL(CREATE_TABLE_SUBCATEGORY_TABLE);
        db.execSQL(CREATE_TABLE_ITEM_TABLE);
        db.execSQL(CREATE_TABLE_DISCOUNT_TABLE);
        db.execSQL(CREATE_TABLE_OTHER_CHARGES_TABLE);
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor lCursor = null;
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        switch (sUriMatcher.match(uri)) {
            case CASE_CATEGORY_TABLE:
                queryBuilder.setTables(uri.getLastPathSegment());
                lCursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case CASE_SUBCATEGORY_TABLE:
                queryBuilder.setTables(uri.getLastPathSegment());
                lCursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case CASE_ITEM_TABLE:
                queryBuilder.setTables(uri.getLastPathSegment());
                lCursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case CASE_DISCOUNT_TABLE:
                queryBuilder.setTables(uri.getLastPathSegment());
                lCursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case CASE_OTHER_CHARGES_TABLE:
                queryBuilder.setTables(uri.getLastPathSegment());
                lCursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                break;
        }
        return lCursor;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase lDb = mDbHelper.getWritableDatabase();
        Uri lInsertedUri = null;
        long lRowId = 0;
        switch (sUriMatcher.match(uri)) {
            case CASE_CATEGORY_TABLE:
                lRowId = lDb.insertOrThrow(uri.getLastPathSegment(), null, values);
                break;
            case CASE_SUBCATEGORY_TABLE:
                lRowId = lDb.insertOrThrow(uri.getLastPathSegment(), null, values);
                break;
            case CASE_ITEM_TABLE:
                lRowId = lDb.insertOrThrow(uri.getLastPathSegment(), null, values);
                break;
            case CASE_DISCOUNT_TABLE:
                lRowId = lDb.insertOrThrow(uri.getLastPathSegment(), null, values);
                break;
            case CASE_OTHER_CHARGES_TABLE:
                lRowId = lDb.insertOrThrow(uri.getLastPathSegment(), null, values);
                break;
            default:
                break;
        }
        if (lRowId > 0) {
            lInsertedUri = ContentUris.withAppendedId(uri, lRowId);
        }
        if (DEBUG) Log.d(TAG, "inserted uri is " + lInsertedUri);
        return lInsertedUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case CASE_CATEGORY_TABLE:
                count = db.delete(uri.getLastPathSegment(), selection, selectionArgs);
                break;
            case CASE_SUBCATEGORY_TABLE:
                count = db.delete(uri.getLastPathSegment(), selection, selectionArgs);
                break;
            case CASE_ITEM_TABLE:
                count = db.delete(uri.getLastPathSegment(), selection, selectionArgs);
                break;
            case CASE_DISCOUNT_TABLE:
                count = db.delete(uri.getLastPathSegment(), selection, selectionArgs);
                break;
            case CASE_OTHER_CHARGES_TABLE:
                count = db.delete(uri.getLastPathSegment(), selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int lCount = 0;
        SQLiteDatabase lDb = mDbHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case CASE_CATEGORY_TABLE:
                lCount = lDb.update(uri.getLastPathSegment(), values, selection, selectionArgs);
                break;
            case CASE_SUBCATEGORY_TABLE:
                lCount = lDb.update(uri.getLastPathSegment(), values, selection, selectionArgs);
                break;
            case CASE_ITEM_TABLE:
                lCount = lDb.update(uri.getLastPathSegment(), values, selection, selectionArgs);
                break;
            case CASE_DISCOUNT_TABLE:
                lCount = lDb.update(uri.getLastPathSegment(), values, selection, selectionArgs);
                break;
            case CASE_OTHER_CHARGES_TABLE:
                lCount = lDb.update(uri.getLastPathSegment(), values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }
        if (DEBUG) Log.d(TAG, "updated lCount  " + lCount);
        return lCount;
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {


        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public boolean isTableExists(SQLiteDatabase db, String tableName) {
            if (DEBUG) Log.d(TAG, "isTableExists()");
            if (tableName == null || db == null || !db.isOpen()) {
                return false;
            }
            Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[]{"table", tableName});
            if (!cursor.moveToFirst()) {
                return false;
            }
            int count = cursor.getInt(0);
            cursor.close();
            return count > 0;
        }

        public ArrayList<Cursor> getData(String Query) {
            //get writable database
            SQLiteDatabase sqlDB = this.getWritableDatabase();
            String[] columns = new String[]{"mesage"};
            //an array list of cursor to save two cursors one has results from the query
            //other cursor stores error message if any errors are triggered
            ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
            MatrixCursor Cursor2 = new MatrixCursor(columns);
            alc.add(null);
            alc.add(null);


            try {
                String maxQuery = Query;
                //execute the query results will be save in Cursor c
                Cursor c = sqlDB.rawQuery(maxQuery, null);


                //add value to cursor2
                Cursor2.addRow(new Object[]{"Success"});

                alc.set(1, Cursor2);
                if (null != c && c.getCount() > 0) {


                    alc.set(0, c);
                    c.moveToFirst();

                    return alc;
                }
                return alc;
            } catch (SQLException sqlEx) {
                Log.d("printing exception", sqlEx.getMessage());
                //if any exceptions are triggered save the error message to cursor an return the arraylist
                Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
                alc.set(1, Cursor2);
                return alc;
            } catch (Exception ex) {

                Log.d("printing exception", ex.getMessage());

                //if any exceptions are triggered save the error message to cursor an return the arraylist
                Cursor2.addRow(new Object[]{"" + ex.getMessage()});
                alc.set(1, Cursor2);
                return alc;
            }


        }
    }
}

