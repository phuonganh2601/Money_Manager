package com.example.moneymanager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.moneymanager.data.InputDataBaseHelper;
import com.example.moneymanager.repositories.Input;

import java.math.BigDecimal;
import java.util.Date;

public class IncomeFragment extends Fragment {

    Date d = java.util.Calendar.getInstance().getTime();
    InputDataBaseHelper dbHelper;
    Integer amount = 0;
    String category = "";
    String currency = "";
    Integer date = d.getDate();
    Integer month = d.getMonth() + 1;
    Integer year = d.getYear() + 1900;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dbHelper = new InputDataBaseHelper(getContext());
        return inflater.inflate(R.layout.fragment_income, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        EditText amountField = view.findViewById(R.id.et_income_amount);
        RadioGroup categoryField = view.findViewById(R.id.rg_category);
        Button enterButton = view.findViewById(R.id.enter_button);
        Spinner currSpinner = view.findViewById(R.id.spinner_currencies);

        amountField.addTextChangedListener(new MoneyTextWatcher(amountField));

        getAmount(amountField);

        getCategoryChoice(categoryField, view);

        setUpSpinner(currSpinner, amountField);

        enter(enterButton, amountField);

    }

    private void enter(Button enterButton, EditText amountField) {
        enterButton.setOnClickListener(view -> {
            clearAllFocus(amountField);
            if (amount != 0) {
                String show = amountField.getText() + " " + " " + category + " " + date + "/" + month + "/" + year;
                addToDB();
                reInit(amountField);
                Toast.makeText(IncomeFragment.super.getContext(), show, Toast.LENGTH_SHORT).show();
                //Toast.makeText(IncomeFragment.super.getContext(), "Đã thêm khoản thu nhập mới", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(IncomeFragment.super.getContext(), "Hãy nhập vào số tiền", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addToDB() {
        dbHelper.addInput(new Input(amount, currency, category, 2), date, month, year);
    }

    private void clearAllFocus(EditText amountField) {
        amountField.clearFocus();
    }

    private void reInit(EditText amountField) {
        amountField.setText("");
        amount = 0;
    }

    private void getAmount(EditText amountField) {
        amountField.setOnFocusChangeListener((view, b) -> {
            BigDecimal value = MoneyTextWatcher.parseCurrencyValue(amountField.getText().toString());
            String temp = String.valueOf(value);
            if (!temp.equals("")) {
                try {
                    amount = Integer.parseInt(temp);
                    Log.v("amount", "added");
                } catch (NumberFormatException e) {
                    Toast.makeText(IncomeFragment.super.getContext(), "Hãy nhập lại số tiền", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void getCategoryChoice(RadioGroup categoryField, View view) {
        int id = categoryField.getCheckedRadioButtonId();
        RadioButton button = view.findViewById(id);
        category = button.getText().toString();
        categoryField.setOnCheckedChangeListener((radioGroup, i) -> {
            int id1 = categoryField.getCheckedRadioButtonId();
            RadioButton button1 = view.findViewById(id1);
            category = button1.getText().toString();
        });
    }

    private void setUpSpinner(Spinner spinner, EditText editText) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.currencies, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currency = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(IncomeFragment.super.getContext(), currency, Toast.LENGTH_SHORT).show();
                if (currency.equals("VND")) {
                    editText.setHint(R.string.amount_in_VND);
                } else {
                    editText.setHint(R.string.amount_in_USD);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}