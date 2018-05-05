package karjatonline.lol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by family on 2/5/18.
 */

public class dbhelper extends SQLiteOpenHelper {
    private static final String db_name="user.db";
    private static final int db_version=1;


    public dbhelper(Context context){
        super(context,db_name,null,db_version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_create_query="create table User(name text,age text,address text)";
        db.execSQL(table_create_query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table if user exists.");
        onCreate(db);// create table again

    }

    String[][] showitems(){

        SQLiteDatabase dbase=getReadableDatabase();
        String query="select * from user";
        Cursor cursor=dbase.rawQuery(query,null);
        String[][] str=new String[cursor.getCount()][3];
        if(cursor.getCount()>0){
            for(int i=0;i<cursor.getCount();i++){
                cursor.moveToNext();
//                str=str+" "+cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+"\n";
                str[i][0]=cursor.getString(0);
                str[i][1]=cursor.getString(1);
                str[i][2]=cursor.getString(2);
            }
        }
        return str;
    }

    String enterdata(String name,String age,String add){
        try{
            SQLiteDatabase dbase=getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put("name",name);
            values.put("age",age);
            values.put("address",add);

            dbase.insert("user",null,values);

            return "success";

        }
        catch (Exception e){
            Log.d("log",""+e.getMessage());
            return "Failed"+e.getMessage();

        }
    }
}