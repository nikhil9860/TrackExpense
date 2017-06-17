package com.example.nikhil.trackexpense;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by nikhil on 19/5/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

        Calendar calendar;
        private Context context;

    private String amount,discp,cat,dt;
    private EditActivity editActivity;


    public void setDiscp(String discp) {
        this.discp = discp;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public DataBaseHelper(Context context){
        super(context,"expense.db",null,1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists expense");
        this.onCreate(db);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table expense (amount number,category text,discreption text,Mop text,expdate date,expense_month text)");


    }

    public boolean saveExp(int amount, String category,String discription, String mop, String date ,int month ){

        try{

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put("amount ", amount );
            cv.put("category",category);
            cv.put("discreption",discription);
            cv.put("Mop",mop);
            cv.put("expdate",date);
            cv.put("expense_month",month);


            db.insert("expense",null,cv);

            return true;

        }catch (Exception ex){
            ex.printStackTrace();
        }


    return false;

    }


    public Boolean deleteExpense(String amount,String cat,String dis){
        try{
            //String sql = "delete  from expense where discreption like'"+dis+" and expense_month like'"+dt+"'";
            String sql = "delete from expense where amount like '"+amount+"' and category like '"+cat+"'and discreption like '"+dis+"%'";
            SQLiteDatabase sb = this.getWritableDatabase();
            sb.execSQL(sql);


            return  true;
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }


public void updateExpense(){
    try {

       editActivity  = new EditActivity();
        editActivity.amt=12;

        editActivity.setDispcriton("nikhil");

        SQLiteDatabase sb= getReadableDatabase();
        String sql="select * from expense where amount like '"+amount+"' and category like '"+cat+"' and discreption like '"+discp+"%' and expdate like '"+dt+"'";
        Cursor cursor = sb.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){



                cursor.moveToNext();
            }
        }

    }catch (Exception ex){
        ex.printStackTrace();
    }
}

    public void viewExpense(ArrayAdapter<String> adapter ){

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from expense order by expense_month  asc ",null);

            if (cursor.moveToFirst()){
                adapter.clear();

                while (!cursor.isAfterLast()){

                    String record = cursor.getInt(0)+" ";
                    record += cursor.getString(1)+" ";
                    record += cursor.getString(2)+" ";
                    record += cursor.getString(3)+" ";
                    record += cursor.getString(4)+" ";

                    adapter.add(record);
                    cursor.moveToNext();
                }

            }
            cursor.close();


        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    public void viewCurrentMonthExpense(ArrayAdapter<String>arrayAdapter){
        try{
            int month;
            SQLiteDatabase sb = this.getReadableDatabase();
            calendar = Calendar.getInstance();
            month = calendar.get(Calendar.MONTH);
            month++;
            String sql ="select * from expense  where  expense_month like'"+month+"'";

            Cursor cursor = sb.rawQuery(sql,null);
            if (cursor.moveToFirst()){
                arrayAdapter.clear();
                while (!cursor.isAfterLast()){

                    String record = "Rs "+cursor.getInt(0)+" ";
                    record += "Category "+cursor.getString(1)+" ";
                    record += cursor.getString(2)+" ";
                    arrayAdapter.add(record);
                    cursor.moveToNext();

                }

            }
            cursor.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void recendExpense(ArrayAdapter<String> adapter ){
        try {

            int day,month,year;
            String date;
            calendar = Calendar.getInstance();
            day = calendar.get(Calendar.DATE);
            month = calendar.get(Calendar.MONTH);
            month++;
            year=calendar.get(Calendar.YEAR);
            date = day+"-"+month+"-"+year;

            SQLiteDatabase db = getReadableDatabase();
            //String sql = "select * from expense order by expdate desc limit 3 ";
            String sql = "select * from expense where expdate like '"+date+"'";
            Cursor cursor = db.rawQuery(sql,null);
            if (cursor.moveToFirst()){
                adapter.clear();
                while (!cursor.isAfterLast()){
                    String record = "Rs "+cursor.getInt(0)+" ";
                    record += "Category "+cursor.getString(1)+" ";
                    record += cursor.getString(2)+" ";
                    adapter.add(record);
                    cursor.moveToNext();
                }
            }



        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public  String totalExpense (){

        String st;
        int i;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select sum(amount) as Total from expense",null);
            c.moveToFirst();

            i = c.getInt(0);
            st= ""+i;
        c.close();



        return  st;
    }

    public String totalMonthExpense(){
        String totalmonthexpense;
        int i,month;

        calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH);
        month++;

        SQLiteDatabase sb = getReadableDatabase();
        String sql= "select sum (amount) as Total from expense where expense_month like '"+month+"'";
        Cursor c = sb.rawQuery(sql,null);
        c.moveToFirst();
        i=c.getInt(0);


        totalmonthexpense=i+"";




        return  totalmonthexpense;

    }




}
