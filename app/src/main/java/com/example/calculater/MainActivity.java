package com.example.calculater;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import  org.mariuszgromada.math.mxparser.*;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
        EditText display;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.dispaly);

            display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });
    }

        private void updateText(String strToadd) {
        String oldStr = display.getText().toString();
        int cursurPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0,cursurPos);
        String rightStr = oldStr.substring(cursurPos);
        if(getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToadd);
            display.setSelection(cursurPos+1);

        } else {
            display.setText(String.format("%s%s%s", leftStr, strToadd, rightStr));
            display.setSelection(cursurPos + 1);
        }

        }
    public void zeroBTN(View view) {
            updateText("0");
    }

    public void oneBTN(View view) {
        updateText("1");
    }

    public void twoBTN(View view) {
        updateText("2");
    }

    public void threeBTN(View view) {
        updateText("3");
    }
    public void fourBTN(View view) {
        updateText("4");
    }

    public void fiveBTN(View view) {
        updateText("5");
    }
    public void sixBTN(View view) {
        updateText("6");
    }
    public void sevenBTN(View view) {
        updateText("7");
    }
    public void eightBtn(View view) {
        updateText("8");
    }
    public void nineBTN(View view) {
        updateText("9");
    }
    public void clear(View view) {
        updateText("");
    }

    public void backBTn(View view) {
        int cursurPos = display.getSelectionStart();
        int len = display.getText().length();

        if(cursurPos != 0 && len != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursurPos-1,cursurPos,"");
            display.setText(selection);
            display.setSelection(cursurPos-1);

        }
    }

    public void plusBtn(View view) {
        updateText("+");
    }
    public void dividedBTN(View view) {
        updateText("÷");
    }
    public void mulBTN(View view) {
        updateText("×");
    }
    public void plusminsBTN(View view) {
        updateText("-");
    }
    public void pointsBTN(View view) {
        updateText(".");
    }
    public void parenthessesBTN(View view) {
        int cursorPos = display.getSelectionStart();
        int openp = 0;
        int closep = 0;

        int textlen = display.getText().length();
        for (int i = 0; i < closep; i++) {
            if (display.getText().toString().substring(i, i + 1).equals("(")) {
                openp += 1;
            }
            if (display.getText().toString().substring(i, i + 1).equals("(")) {
                closep += 1;
            }
        }

        if(openp == closep || display.getText().toString().toString().substring(textlen-1,textlen).equals("(")) {
            updateText("(");
        } else if (closep < openp && ! display.getText().toString().substring(textlen-1,textlen).equals(")")) {
            updateText(")");
        }
        display.setSelection(cursorPos + 1);
    }

    public void expBTN(View view) {
      updateText("^"); }
    public void equalsBTN(View view) {
        String userexp = display.getText().toString();
        userexp = userexp.replaceAll("÷","/");
        userexp = userexp.replaceAll("×","*");

        Expression exp = new Expression(userexp);
        String result;
        result = String.valueOf(exp.calculate());
        display.setText(result);
display.setSelection(result.length());
}
}
