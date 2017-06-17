package com.example.nikhil.trackexpense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static android.R.attr.data;

public class ViewExpActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    String selected,amount,Disription,cat;
    ListView listView;
    Intent i;

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exp);

        dataBaseHelper = new DataBaseHelper(this);

        adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item);
        listView = (ListView)findViewById(R.id.expenseListview);
        listView.setAdapter(adapter);
        dataBaseHelper.viewExpense(adapter);

        registerForContextMenu(listView);

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sub_menu,menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();



            switch (item.getItemId()){

                case  R.id.deletemenu :

                        selected= listView.getItemAtPosition(info.position).toString();
                        int counter=0;
                        for (String a:selected.split(" ")){

                            if (counter==0){
                                amount=a;
                            } else if (counter==1){
                                cat=a;
                            }else if (counter==2){
                                Disription=a;
                            }


                            counter++;

                        }
                            if (dataBaseHelper.deleteExpense(amount,cat,Disription)){
                                Toast.makeText(this,"Deleted",Toast.LENGTH_LONG).show();
                                i = new Intent(this,HomeActivity.class);
                                startActivity(i);

                            }else {
                                Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show();
                            }



                        break;

                case R.id.editexpensemenu:
                    selected= listView.getItemAtPosition(info.position).toString();
                    Toast.makeText(this,""+selected,Toast.LENGTH_LONG).show();
                    i = new Intent(ViewExpActivity.this,EditActivity.class);
                    i.putExtra("editinfo",selected);
                    startActivity(i);
                    break;



            }

        return super.onContextItemSelected(item);
    }
}
