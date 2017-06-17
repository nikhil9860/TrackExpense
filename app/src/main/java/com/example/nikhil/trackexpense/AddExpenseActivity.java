package com.example.nikhil.trackexpense;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class AddExpenseActivity extends Activity implements AdapterView.OnItemSelectedListener{


    String setectedCategoery,slectedMOP;
    private  DataBaseHelper dataBaseHelper;
    private int datet,month,year;
    private Spinner spinner,spinner1;
    private DatePicker datePicker;

    Intent i;
    //private String cato [] = {"Category","Food","Entertainment","Travel","Fuel","Other"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        dataBaseHelper = new DataBaseHelper(this);
        datePicker=(DatePicker)findViewById(R.id.editdate);
        spinner =(Spinner)findViewById(R.id.editTextSpinnerCategory);
        spinner1 = (Spinner)findViewById(R.id.editTextSpinnerMOP);
        spinner.setOnItemSelectedListener(this);
        spinner1.setOnItemSelectedListener(this);

        List <String>  cat  = new ArrayList<String>();
        cat.add("Category");
        cat.add("Food");
        cat.add("Entertainment");
        cat.add("Travel");
        cat.add("Shopping");
        cat.add("Bill");
        cat.add("Other");

        ArrayAdapter<String> arrayAdapter  = new ArrayAdapter <String> (this,android.R.layout.simple_spinner_item,cat);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);


        List<String> mop = new ArrayList<String>();
        mop.add("Mode of Payment");
        mop.add("Cash");
        mop.add("Debit Card");
        mop.add("Credit Card");

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mop);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter1);

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        setectedCategoery = spinner.getSelectedItem().toString();
        slectedMOP = spinner1.getSelectedItem().toString();

        //Toast.makeText(this,"Selected :"+setectedCategoery,Toast.LENGTH_LONG).show();



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void saveExp(View view){
        EditText editTextAmount = (EditText)findViewById(R.id.editTextAmount);
        EditText editTextDiscreption = (EditText)findViewById(R.id.editTextDescription);
        int amont= Integer.parseInt(editTextAmount.getText().toString());
        String disception = editTextDiscreption.getText().toString();
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

}
