package com.example.inimfonakpabio.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Float.*;

public class MainActivity extends AppCompatActivity {

    float curNum, prevNum, result;
    TextView display;
    boolean executing;
    String curText, OP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (TextView) findViewById(R.id.display);
        executing = false;
        curNum = 0;
        prevNum = 0;
        result = 0;
        OP = "0";
    }

    public void EnterNumeral(View view) {
        if (executing) {
            curText = ((Button) view).getText().toString();
            display.setText(curText);
            try {
                curNum = (float) valueOf(curText);
            } catch (Exception e) {
                Log.d("NINI", "Couldnt convert to float " + curText);
            }
            executing = false;

        } else {
            curText = display.getText().toString() + ((Button) view).getText().toString();
            display.setText(curText);
            try {
                curNum = (float) valueOf(curText);
            } catch (Exception e) {
                Log.d("NINI", "Couldnt convert to float " + curText);
            }
        }

    }

    public void EnterOp(View view) {
        curText = display.getText().toString();
        try {
            prevNum = (view.getId() != R.id.bEqual) ? valueOf(curText) : prevNum;
        } catch (Exception e) {
            Log.d("NINI", "Couldnt convert to float " + curText);
        }

        switch (view.getId()) {
            case R.id.bAdd:
                display.setText("+");
                OP = "+";
                executing = true;
                break;

            case R.id.bSub:
                display.setText("-");
                OP = "-";
                executing = true;
                break;

            case R.id.bMul:
                display.setText("x");
                OP = "x";
                executing = true;
                break;

            case R.id.bEqual:
                executing = true;
                switch (OP) {
                    case "+":
                        result = prevNum + curNum;
                        display.setText("" + result);
                        break;

                    case "-":
                        result = prevNum - curNum;
                        display.setText("" + result);
                        break;

                    case "x":
                        result = prevNum * curNum;
                        display.setText("" + result);
                        break;

                    default:
                        //do nothng
                        break;

                }

            default:
                //do nothin
                break;
        }
    }

    public void Clear(View view) {
        CleanUp();
    }

    public void CleanUp() {
        curNum = 0;
        prevNum = 0;
        result = 0;
        OP = "0";
        display.setText("");
    }
}
