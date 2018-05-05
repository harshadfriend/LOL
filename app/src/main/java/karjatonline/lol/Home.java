package karjatonline.lol;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;

public class Home extends AppCompatActivity {

    Button btnAddRecord,btnDeleteRecord,btnSync,btnView;

    //firebase part
    String dburl="https://lol-tiware.firebaseio.com/";
    Firebase firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Firebase.setAndroidContext(this);
        firebase=new Firebase(dburl);

        btnAddRecord=findViewById(R.id.btnAddRecord);
        btnDeleteRecord=findViewById(R.id.btnDeleteRecord);
        btnSync=findViewById(R.id.btnSync);
        btnView=findViewById(R.id.btnView);

        btnAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,MainActivity.class));
            }
        });

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebase.child("lol").removeValue();
                dbhelper db=new dbhelper(Home.this);

                //test for deleting particular record
                SQLiteDatabase dbase=db.getWritableDatabase();
                dbase.delete("user","name=?",new String[]{String.valueOf("Makarand ")});

                //test for updating particular record
                ContentValues values=new ContentValues();
                values.put("name","harshad dagade");
                dbase.update("user",values,"name=?",new String[]{String.valueOf("harshad ")});

                String[][] str=db.showitems();
                //Toast.makeText(MainActivity.this, ""+str, Toast.LENGTH_SHORT).show();
                fbase fb=new fbase();
                for(int i=0;i<str.length;i++) {
                    fb.setVillagename(str[i][0]);
                    fb.setName(str[i][1]);
                    fb.setAge(str[i][2]);
                    fb.setDob(str[i][3]);
                    fb.setHeight(str[i][4]);
                    fb.setWeight(str[i][5]);
                    fb.setGender(str[i][6]);
                    fb.setMaritalstatus(str[i][7]);
                    fb.setFamilymembers(str[i][8]);
                    fb.setFamilyincome(str[i][9]);
                    fb.setOccupation(str[i][10]);
                    fb.setChiefcomplaints(str[i][11]);
                    fb.setKco(str[i][12]);
                    fb.setPasthistory(str[i][13]);
                    fb.setHabits(str[i][14]);
                    fb.setProbablediagnosis(str[i][15]);
                    fb.setRx(str[i][16]);
                    fb.setAdvice(str[i][17]);
                    fb.setDoctor(str[i][18]);

                    firebase.child("lol").push().setValue(fb);

                }
                Snackbar.make(findViewById(android.R.id.content),"Sync Successfull !",Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
