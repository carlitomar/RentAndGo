package com.cmmr.rentgo.Utilitys;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

public class currencyChange implements TextWatcher {

    private final EditText editText;
    private String current = "";
    private DecimalFormat decimalFormat;

    public currencyChange(EditText editText) {
        this.editText = editText;
        decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance(Locale.getDefault());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!s.toString().equals(current)) {
            editText.removeTextChangedListener(this);

            try {
                String cleanString = s.toString().replaceAll("[€,.]", "");
                Number parsed = decimalFormat.parse(cleanString);
                String formatted = decimalFormat.format(parsed);
                current = formatted;
                editText.setText(formatted);
                editText.setSelection(formatted.length());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            editText.addTextChangedListener(this);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
