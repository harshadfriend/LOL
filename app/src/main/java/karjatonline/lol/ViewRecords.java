package karjatonline.lol;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewRecords extends AppCompatActivity {

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
        String[] str=new String[cursor.getCount()];
        for(int i=0;i<cursor.getCount();i++){
            cursor.moveToNext();
            str[i]=cursor.getString(0)+"\n"+cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+
                    cursor.getString(3)+"\n"+cursor.getString(4)+"\n"+cursor.getString(5)+"\n"+
                    cursor.getString(6)+"\n"+cursor.getString(7)+"\n"+cursor.getString(8)+"\n"+
                    cursor.getString(9)+"\n"+cursor.getString(10)+"\n"+cursor.getString(11)+"\n"+
                    cursor.getString(12)+"\n"+cursor.getString(13)+"\n"+cursor.getString(14)+"\n"+
                    cursor.getString(15)+"\n"+cursor.getString(16)+"\n"+cursor.getString(17)+"\n"+
                    cursor.getString(18);
        }

        lv=findViewById(R.id.lv);
        adp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,str);
        adp.setNotifyOnChange(true);
        lv.setAdapter(adp);



    }
}
