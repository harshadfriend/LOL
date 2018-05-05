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
        String table_create_query="create table User(vname text,name text,age text,dob text,height text,weight text," +
                "gender text,mstatus text,fmembers text,fincome text,occupation text,chiefcomplaints text,kco text," +
                "pasthistory text,habits text,probablediagnosis text,rx text,advice text,doctor text)";
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


    String enterdata(String vname,String name,String age,String dob,String height,String weight,String gender,String mstatus,
                     String fmembers,String fincome,String occupation,String chiefcomplaints,String kco,String pasthistory,
                     String habits,String probablediagnosis,String rx,String advice,String doctor){
        try{
            SQLiteDatabase dbase=getWritableDatabase();
            ContentValues values=new ContentValues();

            values.put("vname",vname);              //1
            values.put("name",name);
            values.put("age",age);
            values.put("dob",dob);
            values.put("height",height);            //5
            values.put("weight",weight);
            values.put("gender",gender);
            values.put("mstatus",mstatus);
            values.put("fmembers",fmembers);
            values.put("fincome",fincome);          //10
            values.put("occupation",occupation);
            values.put("chiefcomplaints",chiefcomplaints);
            values.put("kco",kco);
            values.put("pasthistory",pasthistory);
            values.put("habits",habits);            //15
            values.put("probablediagnosis",probablediagnosis);
            values.put("rx",rx);
            values.put("advice",advice);
            values.put("doctor",doctor);            //19

            dbase.insert("user",null,values);

            return "success";

        }
        catch (Exception e){
            Log.d("log",""+e.getMessage());
            return "Failed"+e.getMessage();

        }
    }
}