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
//firebase part
    String dburl="https://lol-tiware.firebaseio.com/";
    Firebase firebase;

    String gender,mstatus,kco,habits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gender="";      mstatus="";     kco="";     habits="";

        actvChiefComplaints=findViewById(R.id.actvChiefComplaints);
        actvPastHistory=findViewById(R.id.actvPastHistory);
        actvRx=findViewById(R.id.actvRx);
        actvAdvSuggRef=findViewById(R.id.actvAdvSuggRef);
        actvProbableDiagnosis=findViewById(R.id.actvProbableDiagnosis);

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
//gender
        rbMale=findViewById(R.id.rbMale);
        rbMale.setChecked(true);
        rbFemale=findViewById(R.id.rbFemale);
        rbTrans=findViewById(R.id.rbTrans);
//marital status
        rbSingle=findViewById(R.id.rbSingle);
        rbSingle.setChecked(true);
        rbMarried=findViewById(R.id.rbMarried);
        rbSeparated=findViewById(R.id.rbSeparated);
        rbDivorced=findViewById(R.id.rbDivorced);
        rbWidowed=findViewById(R.id.rbWidowed);
//kco
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

        //gender selection
        rbMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                gender="Male";
            }
        });
        rbFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                gender="Female";
            }
        });
        rbTrans.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                gender="Trans";
            }
        });

        //marital status selection
        rbSingle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mstatus="Single";
            }
        });
        rbMarried.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mstatus="Married";
            }
        });
        rbSeparated.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mstatus="Separated";
            }
        });
        rbDivorced.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mstatus="Divorced";
            }
        });
        rbWidowed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mstatus="Widowed";
            }
        });

        //kco
     /*   cbHyperTension.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                kco=kco+" "+ cbHyperTension.getText().toString();
            }
        });
        cbDiabetes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                kco=kco+" "+ cbDiabetes.getText().toString();
            }
        });
        cbBP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                kco=kco+" "+ cbBP.getText().toString();
            }
        });
        cbAsthma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                kco=kco+" "+ cbAsthma.getText().toString();
            }
        });
        cbAnemia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                kco=kco+" "+ cbAnemia.getText().toString();
            }
        });

        cbPiles.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                kco=kco+" "+ cbPiles.getText().toString();
            }
        });
       */
        cbOtherKCO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    etOtherKCO.setEnabled(true);
                }
                else {
                    etOtherKCO.setEnabled(false);
                    etOtherKCO.setText("");
                }
            }
        });

        cbOtherHabits.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    etOtherHabits.setEnabled(true);
                }

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
                if(cbHyperTension.isChecked())
                    kco=kco+" "+cbHyperTension.getText().toString();
                if(cbDiabetes.isChecked())
                    kco=kco+" "+cbDiabetes.getText().toString();
                if(cbBP.isChecked())
                    kco=kco+" "+cbBP.getText().toString();
                if(cbAsthma.isChecked())
                    kco=kco+" "+cbAsthma.getText().toString();
                if(cbAnemia.isChecked())
                    kco=kco+" "+cbAnemia.getText().toString();
                if(cbPiles.isChecked())
                    kco=kco+" "+cbPiles.getText().toString();
                if(cbOtherKCO.isChecked())
                    kco=kco+" "+etOtherKCO.getText().toString();

                if(cbTobaccoChewing.isChecked())
                    habits=habits+" "+cbTobaccoChewing.getText().toString();
                if(cbGutkha.isChecked())
                    habits=habits+" "+cbGutkha.getText().toString();
                if(cbSmoking.isChecked())
                    habits=habits+" "+cbSmoking.getText().toString();
                if(cbMasheri.isChecked())
                    habits=habits+" "+cbMasheri.getText().toString();
                if(cbAlcohol.isChecked())
                    habits=habits+" "+cbAlcohol.getText().toString();
                if(cbOtherHabits.isChecked())
                    habits=habits+" "+etOtherHabits.getText().toString();

                dbhelper db=new dbhelper(MainActivity.this);
                String result=db.enterdata(etVillageName.getText().toString(),etName.getText().toString(),etAge.getText().toString(),
                        etDOB.getText().toString(),etHeight.getText().toString(),etWeight.getText().toString(),gender,mstatus,
                        etFamilyMembers.getText().toString(),etTotalFamilyIncome.getText().toString(),etOccupation.getText().toString(),
                        actvChiefComplaints.getText().toString(),kco,actvPastHistory.getText().toString(),habits,
                        actvProbableDiagnosis.getText().toString(),actvRx.getText().toString(),actvAdvSuggRef.getText().toString(),
                        etDoctor.getText().toString());

                if(result.equalsIgnoreCase("SUCCESS"))
                    Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bnv=findViewById(R.id.bnv);
        bnv.setItemIconTintList(null);

    }
}
