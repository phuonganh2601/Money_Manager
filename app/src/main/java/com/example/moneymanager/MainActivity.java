package com.example.moneymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moneymanager.data.InputDataBaseHelper;

public class MainActivity extends AppCompatActivity {

    InputDataBaseHelper dbHelper;
    InputScreenFragment inputScreenFragment = new InputScreenFragment();
    SummaryFragment summaryFragment = new SummaryFragment();
    ReportFragment reportFragment = new ReportFragment();
    boolean isInInputScreen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();

        dbHelper = new InputDataBaseHelper(this);
        Button inputScreenButton = (Button) findViewById(R.id.button_input_page);
        Button summaryScreenButton = (Button) findViewById(R.id.button_summary_page);
        Button reportScreenButton = (Button) findViewById(R.id.button_report_page);

        chooseScreen(reportScreenButton, summaryScreenButton, inputScreenButton);
    }

    public void chooseScreen(Button reportScreenButton, Button summaryScreenButton, Button inputScreenButton) {
        inputScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragmentContent(inputScreenFragment);
                isInInputScreen = true;
            }
        });

        summaryScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragmentContent(summaryFragment);
                isInInputScreen = false;
            }
        });

        reportScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragmentContent(reportFragment);
            }
        });

    }

    private void initFragment() {
        InputScreenFragment inputS = new InputScreenFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.replace(R.id.fl_main_page_fragment, inputS);

        ft.commit();
    }

    protected void replaceFragmentContent(Fragment fragment) {

        if (fragment != null) {

            FragmentManager fmgr = getSupportFragmentManager();

            FragmentTransaction ft = fmgr.beginTransaction();

            ft.replace(R.id.fl_main_page_fragment, fragment);

            ft.commit();

        }
    }

}
