package com.example.nikhil.trackexpense;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.TextView;
import android.widget.Toast;


public class AddExpenseActivity extends Activity {


    String setectedCategoery,slectedMOP,disception;
    private  DataBaseHelper dataBaseHelper;
    private int datet,month,year;
    private DatePicker datePicker;
    private RadioGroup radioGroup,radioGroup1;
    EditText editTextAmount,editTextDiscreption;
    TextView textView,textView1;
    int amont=0;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        dataBaseHelper = new DataBaseHelper(this);
        datePicker=(DatePicker)findViewById(R.id.editdate);
         editTextAmount = (EditText)findViewById(R.id.editTextAmount);
         editTextDiscreption = (EditText)findViewById(R.id.editTextDescription);
        textView= (TextView) findViewById(R.id.moptextview);
        textView1= (TextView) findViewById(R.id.textviewcatogey);
        radioGroup = (RadioGroup)findViewById(R.id.radiocatergorygroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                if (checkedId==R.id.radiofood){

                    setectedCategoery="Food";

                }else  if (checkedId==R.id.radioentertaimnet){

                    setectedCategoery="Entertainment";

                }else  if (checkedId==R.id.radioshopping){

                    setectedCategoery="Shopping";

                }else  if (checkedId==R.id.radiotravel){

                    setectedCategoery="Travel";

                }else  if (checkedId==R.id.radiobills){

                    setectedCategoery="Bill";

                }

            }
        });

        radioGroup1 = (RadioGroup)findViewById(R.id.mopcategory);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                if (checkedId==R.id.mopcash){

                    slectedMOP="Cash";

                }else if (checkedId==R.id.mopcreditcard){
                    slectedMOP="Credit Card";
                }else if (checkedId==R.id.mopdebitcard){
                    slectedMOP="Debit Card";
                }

            }
        });

    }



    public void validate(View view){

        disception = editTextDiscreption.getText().toString();
        if (editTextAmount.getText().length()!=0){

                amont= Integer.parseInt(editTextAmount.getText().toString());
            }else {
                amont=0;
            }


            if (radioGroup.getCheckedRadioButtonId()<=0){
                textView1.setText("Select Catogery");
                textView1.setBackgroundColor(Color.RED);

            } else if (radioGroup1.getCheckedRadioButtonId()<=0){
                textView.setText("Select Payment Mode ");
                //textView.setTextColor(Color.RED);
                textView.setBackgroundColor(Color.RED);
            }
        else if (disception.length()==0){
            editTextDiscreption.setError("Cannot be empty");
        }else if (amont<=0){
            amont=0;
            editTextAmount.setError("Amount cannot be Zero");
        } else {
            this.saveExp();
        }

            if (amont>=1500){
                sendNotification();
            }

    }


    public void saveExp(){

        datet= datePicker.getDayOfMonth();
        month=datePicker.getMonth();
        month++;
        year=datePicker.getYear();
        String date= datet+"-"+month+"-"+year;

        if (dataBaseHelper.saveExp(amont,setectedCategoery,disception,slectedMOP,date,month)){
            Toast.makeText(this,"Expense Saved",Toast.LENGTH_LONG).show();

        }else {

            Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show();
        }

            i = new Intent(AddExpenseActivity.this,HomeActivity.class);
            startActivity(i);

    }



    private void sendNotification(){


        Notification.Builder nt = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_menu_manage)
                .setContentTitle("Expense Alert")
                .setContentText("Cotrol your Expense").setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0,nt.build());

    }

}
