package karjatonline.lol;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    EditText etName,etAge,etDOB;
    Button btnSubmit,btnShow;
    String dburl="https://lol-tiware.firebaseio.com/";
    Firebase firebase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        firebase=new Firebase(dburl);


        etName=findViewById(R.id.etName);
        etDOB=findViewById(R.id.etDOB);
        etAge=findViewById(R.id.etAge);

        btnSubmit=findViewById(R.id.btnSubmit);
        btnShow=findViewById(R.id.btnShow);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhelper db=new dbhelper(MainActivity.this);
                String result=db.enterdata(etName.getText().toString(),etAge.getText().toString(),etDOB.getText().toString());

                if(result.equalsIgnoreCase("SUCCESS"))
                    Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
//                finish();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebase.child("lol").removeValue();
                dbhelper db=new dbhelper(MainActivity.this);
                String[][] str=db.showitems();
                //Toast.makeText(MainActivity.this, ""+str, Toast.LENGTH_SHORT).show();
                fbase fb=new fbase();
                for(int i=0;i<str.length;i++) {
                    fb.setName(str[i][0]);
                    fb.setAge(str[i][1]);
                    fb.setAdd(str[i][2]);
                    firebase.child("lol").push().setValue(fb);
                }
            }
        });

        bnv=findViewById(R.id.bnv);
        bnv.setItemIconTintList(null);

    }
}
