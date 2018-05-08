package karjatonline.lol;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.Typeface;
        import android.net.Uri;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.firebase.client.Firebase;

        import java.util.ArrayList;

/**
 * Created by Dell on 09-Mar-18.
 */

public class viewlistadapter extends ArrayAdapter{
    Context context;
    int layoutResourceId;
    String[][] data=null;

    //List<string_item> data=null;

    public viewlistadapter(Context context, int resource, String[][] objects) {
        //public viewlistadapter(Context context, int resource, List<string_item> objects) {
        super(context, resource, objects);
//        super(context,resource,objects);
        this.context = context;
        //this.data = objects;
        this.data=objects;
        this.layoutResourceId = resource;
    }

    static class myHolder {
        TextView tvName,tvVname,tvAge,tvGender,tvDOB,tvWeight,tvHeight,tvMStatus,tvFIncome,tvFMembers,tvOccupation,tvCComplaints,
                tvKCO,tvPHistory,tvHabits,tvDiagnosis,tvRx,tvAdvSuggRef,tvDoctor;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        myHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new myHolder();
            holder.tvName=convertView.findViewById(R.id.tvName);
            holder.tvVname=convertView.findViewById(R.id.tvVName);
            holder.tvAge=convertView.findViewById(R.id.tvAge);
            holder.tvWeight=convertView.findViewById(R.id.tvWeight);
            holder.tvHeight=convertView.findViewById(R.id.tvHeight);
            holder.tvGender=convertView.findViewById(R.id.tvGender);
            holder.tvDOB=convertView.findViewById(R.id.tvDOB);
            holder.tvMStatus=convertView.findViewById(R.id.tvMStatus);
            holder.tvFMembers=convertView.findViewById(R.id.tvFMembers);
            holder.tvFIncome=convertView.findViewById(R.id.tvFIncome);
            holder.tvOccupation=convertView.findViewById(R.id.tvOccupation);
            holder.tvCComplaints=convertView.findViewById(R.id.tvCComplaints);
            holder.tvKCO=convertView.findViewById(R.id.tvKCO);
            holder.tvPHistory=convertView.findViewById(R.id.tvPastHistory);
            holder.tvHabits=convertView.findViewById(R.id.tvHabits);
            holder.tvDiagnosis=convertView.findViewById(R.id.tvPDiagnosis);
            holder.tvRx=convertView.findViewById(R.id.tvRx);
            holder.tvAdvSuggRef=convertView.findViewById(R.id.tvAdvSuggRef);
            holder.tvDoctor=convertView.findViewById(R.id.tvDoctor);



            convertView.setTag(holder);


        } else {
            holder = (myHolder) convertView.getTag();
        }



        //Log.d("logg","name-"+data[position][0]);
        holder.tvVname.setText(data[position][0]);
        holder.tvName.setText(data[position][1]);
        holder.tvAge.setText(data[position][2]);
        holder.tvDOB.setText(data[position][3]);
        holder.tvHeight.setText(data[position][4]);
        holder.tvWeight.setText(data[position][5]);
        holder.tvGender.setText(data[position][6]);
        holder.tvMStatus.setText(data[position][7]);
        holder.tvFMembers.setText(data[position][8]);
        holder.tvFIncome.setText(data[position][9]);
        holder.tvOccupation.setText(data[position][10]);
        holder.tvCComplaints.setText(data[position][11]);
        holder.tvKCO.setText(data[position][12].trim());
        holder.tvPHistory.setText(data[position][13]);
        holder.tvHabits.setText(data[position][14].trim());
        holder.tvDiagnosis.setText(data[position][15]);
        holder.tvRx.setText(data[position][16]);
        holder.tvAdvSuggRef.setText(data[position][17]);
        holder.tvDoctor.setText(data[position][18]);

        return convertView;
    }
}
