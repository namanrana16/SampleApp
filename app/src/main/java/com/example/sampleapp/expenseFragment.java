package com.example.sampleapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link expenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class expenseFragment extends Fragment {
 Button btn,btn2;
 EditText editText,edtxt;
 TextView expense;
 int budget =0;
        int count,exp;
        String def ="0";

public static final String SHARED_PREFS ="sharedPrefs";
    public static final String TEXT = "text";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public expenseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment expenseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static expenseFragment newInstance(String param1, String param2) {
        expenseFragment fragment = new expenseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expense, container, false);
        loadData();

        expense = view.findViewById(R.id.expensetext);
        btn = view.findViewById(R.id.enterexpense);
        btn2 = view.findViewById(R.id.setbudget);
        editText = view.findViewById(R.id.expenseenter);
        edtxt = view.findViewById(R.id.budgetenter);

        expense.setText(def);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = editText.getText().toString();
                if(s1!=null)
                { exp = Integer.parseInt(s1);
                String ss = expense.getText().toString();
                budget = Integer.parseInt(ss);
                count = budget - exp;
                String s = String.valueOf(count);
                expense.setText(s);
                saveData(); }
            }
        });

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s2 = edtxt.getText().toString();
                    if(s2!=null)
                    { budget=Integer.parseInt(s2);
                    String s0 = String.valueOf(budget);
                    expense.setText(s0);
                    saveData(); }
                }
            });
        return view;
    }


    public void saveData()
    {
        SharedPreferences preferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TEXT,expense.getText().toString());
        editor.apply();

    }

    public void loadData(){

        SharedPreferences sharedPreferences= this.getActivity().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        def = sharedPreferences.getString(TEXT,"");

    }



}