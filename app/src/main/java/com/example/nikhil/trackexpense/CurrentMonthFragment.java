package com.example.nikhil.trackexpense;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by nikhil on 7/6/17.
 */

public class CurrentMonthFragment extends Fragment {


    private  DataBaseHelper dataBaseHelper;
    private ListView listView;
    private TextView textView;
    Calendar calendar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        dataBaseHelper = new DataBaseHelper(getActivity());
        View view = inflater.inflate(R.layout.current_month_record,container);
        textView = (TextView) view.findViewById(R.id.currentmonthtextview);
        listView = (ListView)view.findViewById(R.id.listviewcurrentexpense);
        calendar = Calendar.getInstance();
        String month = calendar.getDisplayName(calendar.MONTH,calendar.LONG,Locale.getDefault());
        textView.setText(month+" Month Expense");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        listView.setAdapter(adapter);
        dataBaseHelper.viewCurrentMonthExpense(adapter);

        return view;
    }
}

