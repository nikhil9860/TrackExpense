package com.example.nikhil.trackexpense;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikhil on 7/6/17.
 */

public class RecentSpendFragment extends Fragment {

    private DataBaseHelper dataBaseHelper;
    private ListView listView;
    private ArrayList <String> arrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.recent_record_fragment,container);
        dataBaseHelper = new DataBaseHelper(getActivity());
        listView = (ListView)view.findViewById(R.id.listviewrecentexpense);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.custom_textview);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        listView.setAdapter(arrayAdapter);
        dataBaseHelper.recendExpense(arrayAdapter);
        return view;

    }
}
