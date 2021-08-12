package sg.edu.rp.c346.id20007998.gamenote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gameNote.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NOTE = "note";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_DESCRIPTION = "description";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createIslandTableSql="CREATE TABLE " + TABLE_NOTE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COLUMN_TITLE + " TEXT, "+ COLUMN_AUTHOR + " TEXT, "+ COLUMN_NAME + " TEXT, " + COLUMN_TYPE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT ) ";
        db.execSQL(createIslandTableSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        onCreate(db);

    }

    public ArrayList<Note> getAllNote(){
        ArrayList<Note> notes = new ArrayList<Note>();

        String selectQuery = "SELECT " + COLUMN_ID + ","+COLUMN_TITLE+","+COLUMN_AUTHOR+","
                + COLUMN_NAME +","+ COLUMN_TYPE+","
                + COLUMN_DESCRIPTION+" FROM " + TABLE_NOTE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do{
                int id=cursor.getInt(0);
                String title=cursor.getString(1);
                String author=cursor.getString(2);
                String name=cursor.getString(3);
                String type=cursor.getString(4);
                String description=cursor.getString(5);

                Note note=new Note(id,title,author,name,type,description);
                notes.add(note);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;

    }
    public long insertNote(String title,String author,String name,String type,String description){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE,title);
        values.put(COLUMN_AUTHOR,author);
        values.put(COLUMN_NAME,name);
        values.put(COLUMN_TYPE,type);
        values.put(COLUMN_DESCRIPTION,description);
        long result=db.insert(TABLE_NOTE,null,values);
        db.close();
        return result;
    }
    public int updateNote(Note data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_AUTHOR, data.getAuthor());
        values.put(COLUMN_NAME, data.getName());
        values.put(COLUMN_TYPE, data.getType());
        values.put(COLUMN_DESCRIPTION, data.getDescription());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_NOTE, values, condition, args);
        db.close();
        return result;
    }
    public int deleteNote(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_NOTE, condition, args);
        db.close();
        return result;
    }
}
