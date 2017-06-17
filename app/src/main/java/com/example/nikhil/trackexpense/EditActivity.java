package com.example.nikhil.trackexpense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    EditText editText,editText1;
    String zero,one,two,three,four,last;
   private String dispcriton;
    int amt;

    public int getAmt() {
        return amt;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }

    public String getDispcriton() {
        return dispcriton;
    }

    public void setDispcriton(String dispcriton) {
        this.dispcriton = dispcriton;
    }

    int counter =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

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
        dataBaseHelper = new DataBaseHelper(getApplicationContext());

        editText = (EditText)findViewById(R.id.editamontinfo);
        editText1 = (EditText)findViewById(R.id.editdisriptioninfo);

        dataBaseHelper.setAmount(zero);
        dataBaseHelper.setCat(one);
        dataBaseHelper.setDiscp(two);
        dataBaseHelper.setDt(last);
        dataBaseHelper.updateExpense();

        editText.setText(this.getDispcriton());
        amt++;






    }
}
