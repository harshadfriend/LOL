package karjatonline.lol;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewRecords extends AppCompatActivity {

    viewlistadapter vadp;
    ListView lv;
    ArrayAdapter<String> adp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);

        dbhelper db=new dbhelper(this);
        SQLiteDatabase dbase=db.getReadableDatabase();
        String q="select * from user";
        Cursor cursor=dbase.rawQuery(q,null);
        String[][] str=new String[cursor.getCount()][19];
        for(int i=0;i<cursor.getCount();i++){
            cursor.moveToNext();
            str[i][0]=cursor.getString(0);
            str[i][1]=cursor.getString(1);
            str[i][2]=cursor.getString(2);
            str[i][3]=cursor.getString(3);
            str[i][4]=cursor.getString(4);
            str[i][5]=cursor.getString(5);
            str[i][6]=cursor.getString(6);
            str[i][7]=cursor.getString(7);
            str[i][8]=cursor.getString(8);
            str[i][9]=cursor.getString(9);
            str[i][10]=cursor.getString(10);
            str[i][11]=cursor.getString(11);
            str[i][12]=cursor.getString(12);
            str[i][13]=cursor.getString(13);
            str[i][14]=cursor.getString(14);
            str[i][15]=cursor.getString(15);
            str[i][16]=cursor.getString(16);
            str[i][17]=cursor.getString(17);
            str[i][18]=cursor.getString(18);
        }

        lv=findViewById(R.id.lv);
        //adp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,str);
        //adp.setNotifyOnChange(true);
        vadp=new viewlistadapter(this,R.layout.viewlist,str);
        vadp.setNotifyOnChange(true);
        lv.setAdapter(vadp);



    }
}
