package karjatonline.lol;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    EditText etName,etAge,etDOB,etVillageName,etHeight,etWeight,etFamilyMembers,etTotalFamilyIncome,etOccupation,
            etOtherKCO,etOtherHabits,etDoctor;
    RadioButton rbMale,rbFemale,rbTrans,rbSingle,rbMarried,rbSeparated,rbDivorced,rbWidowed;
    CheckBox cbHyperTension,cbDiabetes,cbBP,cbAsthma,cbAnemia,cbPiles,cbOtherKCO,cbTobaccoChewing,cbGutkha,cbSmoking,
            cbMasheri,cbAlcohol,cbOtherHabits;
    AutoCompleteTextView actvChiefComplaints,actvPastHistory,actvProbableDiagnosis,actvRx,actvAdvSuggRef;
    Button btnSubmit,btnShow;
    String dburl="https://lol-tiware.firebaseio.com/";
    Firebase firebase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=findViewById(R.id.etName);
        etDOB=findViewById(R.id.etDOB);
        etAge=findViewById(R.id.etAge);
        etVillageName=findViewById(R.id.etVillageName);
        etHeight=findViewById(R.id.etHeight);
        etWeight=findViewById(R.id.etWeight);
        etFamilyMembers=findViewById(R.id.etFamilyMembers);
        etTotalFamilyIncome=findViewById(R.id.etTotalFamilyIncome);
        etOccupation=findViewById(R.id.etOccupation);
        etOtherKCO=findViewById(R.id.etOtherKCO);
        etOtherHabits=findViewById(R.id.etOtherHabits);
        etDoctor=findViewById(R.id.etDoctor);

        rbMale=findViewById(R.id.rbMale);
        rbFemale=findViewById(R.id.rbFemale);
        rbTrans=findViewById(R.id.rbTrans);
        rbSingle=findViewById(R.id.rbSingle);
        rbMarried=findViewById(R.id.rbMarried);
        rbSeparated=findViewById(R.id.rbSeparated);
        rbDivorced=findViewById(R.id.rbDivorced);
        rbWidowed=findViewById(R.id.rbWidowed);

        cbHyperTension=findViewById(R.id.cbHyperTension);
        cbDiabetes=findViewById(R.id.cbDiabetes);
        cbBP=findViewById(R.id.cbBP);
        cbAsthma=findViewById(R.id.cbAsthma);
        cbAnemia=findViewById(R.id.cbAnemia);
        cbPiles=findViewById(R.id.cbPiles);
        cbOtherKCO=findViewById(R.id.cbOtherKCO);
        cbTobaccoChewing=findViewById(R.id.cbTobaccoChewing);
        cbGutkha=findViewById(R.id.cbGutkha);
        cbSmoking=findViewById(R.id.cbSmoking);
        cbMasheri=findViewById(R.id.cbMasheri);
        cbAlcohol=findViewById(R.id.cbAlcohol);
        cbOtherHabits=findViewById(R.id.cbOtherHabits);

        cbOtherKCO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    etOtherKCO.setEnabled(true);
                else {
                    etOtherKCO.setEnabled(false);
                    etOtherKCO.setText("");
                }
            }
        });

        cbOtherHabits.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    etOtherHabits.setEnabled(true);
                else {
                    etOtherHabits.setEnabled(false);
                    etOtherHabits.setText("");
                }
            }
        });

        Firebase.setAndroidContext(this);
        firebase=new Firebase(dburl);




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
                SQLiteDatabase dbase=db.getWritableDatabase();
                dbase.delete("user","name=?",new String[]{String.valueOf("Makarand ")});
                ContentValues values=new ContentValues();
                values.put("name","harshad dagade");
                dbase.update("user",values,"name=?",new String[]{String.valueOf("harshad ")});
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
