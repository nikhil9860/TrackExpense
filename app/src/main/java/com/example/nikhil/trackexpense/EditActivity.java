package com.example.nikhil.trackexpense;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    EditText editText,editText1;
    String zero,one,two,three,four,last;
    String date,day,month,year;
    RadioGroup radioGroup;
    RadioButton radioButton,radioButton1,radioButton2,radioButton3,radioButton4,radioButton5;
    RadioButton radioButtoncash,radioButtondebit,radioButtoncredit;
    DatePicker datePicker;
    Button save,cancel;
    int counter =0;
    int counter1=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        radioGroup=new RadioGroup(EditActivity.this);
        radioButton = (RadioButton) findViewById(R.id.editradiofood);
        radioButton1 = (RadioButton) findViewById(R.id.editradioentertaimnet);
        radioButton2 = (RadioButton) findViewById(R.id.editradiotravel);
        radioButton3 = (RadioButton) findViewById(R.id.editradioshopping);
        radioButton4 = (RadioButton) findViewById(R.id.editradiobills);
        radioButton5 = (RadioButton) findViewById(R.id.editradioother);
        radioButtoncash =(RadioButton)findViewById(R.id.editmopcash);
        radioButtondebit =(RadioButton)findViewById(R.id.editmopdebitcard);
        radioButtoncredit= (RadioButton) findViewById(R.id.editmopcreditcard);
        cancel= (Button) findViewById(R.id.cancel_button);

        datePicker = (DatePicker) findViewById(R.id.editinfodatepicker);

        Intent i = getIntent();
       String s= i.getStringExtra("editinfo");

        for (String a :s.split(" ") ){

            if (counter==0){
                zero=a;
            }

            else if (counter==1){
                one=a;
            }
            else if (counter==2){
                two=a;
            }

            counter++;

            last=a;
        }


        editText = (EditText)findViewById(R.id.editamontinfo);
        editText1 = (EditText)findViewById(R.id.editinfodisriptioninfo);

        dataBaseHelper.setAmount(zero);
        dataBaseHelper.setCat(one);
        dataBaseHelper.setDiscp(two);
        dataBaseHelper.setDt(last);
        dataBaseHelper.updateExpense();


        editText.setText(dataBaseHelper.amt);

        String st =dataBaseHelper.c;

        if (st.equals("Food")){

            radioButton.setChecked(true);
        }else if (st.equals("Entertainment")){

            radioButton1.setChecked(true);
        }else if (st.equals("Travel")){
            radioButton2.setChecked(true);

        }else if (st.equals("Shopping")){
            radioButton3.setChecked(true);
        }else if (st.equals("Bill")){
            radioButton4.setChecked(true);
        }else if (st.equals("Other")){
            radioButton5.setChecked(true);
        }

        editText1.setText(dataBaseHelper.dis);

        String mop=dataBaseHelper.m;

        if (mop.equals("Cash")){
            radioButtoncash.setChecked(true);
        } else if (mop.equals("Debit Card")){
            radioButtondebit.setChecked(true);
        }else if(mop.equals("Credit Card")){
            radioButtoncredit.setChecked(true);
        }

        date=dataBaseHelper.d;

        for (String a:date.split("-")){
            if (counter1==0){
                day=a;
            }else if (counter1==1){
                month=a;
            }else if (counter1==2){
                year=a;
            }
            counter1++;

        }


        datePicker.updateDate(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(EditActivity.this,HomeActivity.class);
                startActivity(i);

            }
        });




    }


}
