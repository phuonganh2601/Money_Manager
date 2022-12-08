package com.example.moneymanager;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class InputScreenFragment extends Fragment {
    IncomeFragment incomeFragment = new IncomeFragment();
    ExpenseFragment expenseFragment = new ExpenseFragment();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        replaceFragmentContent(expenseFragment);
        return inflater.inflate(R.layout.fragment_input_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button incomeButton = view.findViewById(R.id.button_enter_income);
        Button expenseButton = view.findViewById(R.id.button_enter_expense);


        incomeButton.setOnClickListener(view1 -> replaceFragmentContent(incomeFragment));

        expenseButton.setOnClickListener(view12 -> replaceFragmentContent(expenseFragment));
    }

    protected void replaceFragmentContent(Fragment fragment) {

        if (fragment != null) {

            FragmentManager fmgr = getChildFragmentManager();

            FragmentTransaction ft = fmgr.beginTransaction();

            ft.replace(R.id.fl_input_page_fragment, fragment);

            ft.commit();

        }
    }
}
