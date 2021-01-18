package com.example.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private Context context;
    private static final String DataBaseName="Library.db";
    private static final int DataBaseVersion=1;
    private static final String TableName="MyLibrary";
    private static final String ColumnId="_id";
    private static final String ColumnTitle="_title";
    private static final String ColumnAuthor="_author";
    private static final String ColumnPages="_pages";

     public  Database(@Nullable Context context) {
        super(context, DataBaseName, null, DataBaseVersion);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=
                "CREATE TABLE "+TableName+
                        " ("+ColumnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                             ColumnTitle + " TEXT UNIQUE NOT NULL, " +
                             ColumnAuthor + " TEXT UNIQUE NOT NULL, " +
                             ColumnPages + " INTEGER UNIQUE NOT NULL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TableName);
        onCreate(db);
    }

    void AddBook(String title,String author,int pages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ColumnTitle, title);
        cv.put(ColumnAuthor, author);
        cv.put(ColumnPages, pages);
        long result = db.insert(TableName, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }

    }
        Cursor readAllData() {
        String query = "SELECT * FROM " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
           cursor= db.rawQuery(query, null);
        }
        return cursor;
    }
    void UpdateData(String row_id, String title, String author, String pages){
         SQLiteDatabase db=this.getWritableDatabase();
         ContentValues cv=new ContentValues();
         cv.put(ColumnTitle,title);
         cv.put(ColumnAuthor,author);
         cv.put(ColumnPages,pages);

         long result= db.update(TableName,cv,"_id=?",new String[]{row_id});

         if (result==-1){
             Toast.makeText(context,"Failed to update",Toast.LENGTH_SHORT).show();
         }
         else{
             Toast.makeText(context,"Successfully updated",Toast.LENGTH_SHORT).show();
         }
    }
    void DeleteData(String row_id){
        SQLiteDatabase db=this.getWritableDatabase();
         long result= db.delete(TableName,"_id=?",new String[] {row_id});
        if (result==-1){
            Toast.makeText(context,"Failed to delete",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Successfully deleted",Toast.LENGTH_SHORT).show();
        }
    }
}
