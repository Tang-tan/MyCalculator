package com.example.administrator.mycalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //数字
    private Button num0;
    private Button num1;
    private Button num2;
    private Button num3;
    private Button num4;
    private Button num5;
    private Button num6;
    private Button num7;
    private Button num8;
    private Button num9;
    //运算符
    private Button plus_btn;
    private Button minus_btn;
    private Button mul_btn;
    private Button div_btn;
    private Button equal_btn;
    //其它
    private Button AC_btn;
    private Button DEL_btn;
    private Button point_btn;
    //目前的字符
    private String existedText;
    //显示结果
    private EditText resultText;
    //运算结果
    private double resultNum;
    //运算字符
    private int c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getControl();
        listen();
    }

    //初始化
    private void getControl() {
        //初始化数字
        num0 = (Button) findViewById(R.id.zero);
        num1 = (Button) findViewById(R.id.one);
        num2 = (Button) findViewById(R.id.two);
        num3 = (Button) findViewById(R.id.three);
        num4 = (Button) findViewById(R.id.four);
        num5 = (Button) findViewById(R.id.five);
        num6 = (Button) findViewById(R.id.six);
        num7 = (Button) findViewById(R.id.seven);
        num8 = (Button) findViewById(R.id.eight);
        num9 = (Button) findViewById(R.id.nine);
        //初始化运算符
        plus_btn = (Button) findViewById(R.id.plus);
        minus_btn = (Button) findViewById(R.id.minus);
        mul_btn = (Button) findViewById(R.id.multi);
        div_btn = (Button) findViewById(R.id.div);
        equal_btn = (Button) findViewById(R.id.equals);
        //初始化其它
        AC_btn = (Button) findViewById(R.id.clear);
        DEL_btn = (Button) findViewById(R.id.del);
        point_btn = (Button) findViewById(R.id.dot);
        //显示结果
        resultText = (EditText) findViewById(R.id.edittext);
        //已输入
        existedText = resultText.getText().toString();
        //计算结果
        resultNum = 0;
    }

    //listener
    private void listen() {
        num0.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);


        plus_btn.setOnClickListener(this);
        minus_btn.setOnClickListener(this);
        mul_btn.setOnClickListener(this);
        div_btn.setOnClickListener(this);
        equal_btn.setOnClickListener(this);


        point_btn.setOnClickListener(this);
        AC_btn.setOnClickListener(this);
        DEL_btn.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zero:
                existedText += "0";
                break;
            case R.id.one:
                existedText=existedText+"1";
                break;
            case R.id.two:
                existedText += "2";
                break;
            case R.id.three:
                existedText += "3";
                break;
            case R.id.four:
                existedText += "4";
                break;
            case R.id.five:
                existedText += "5";
                break;
            case R.id.six:
                existedText += "6";
                break;
            case R.id.seven:
                existedText += "7";
                break;
            case R.id.eight:
                existedText += "8";
                break;
            case R.id.nine:
                existedText += "9";
                break;
            case R.id.plus:
                if (c != 0) {
                    existedText = String.valueOf(getResultNum())+"+";
                }
                else
                    existedText +="+";
                c = 1;
                break;
            case R.id.minus:
                if (c != 0) {
                    existedText = String.valueOf(getResultNum())+"-";
                }
                else
                    existedText +="-";
                c = 2;
                break;
            case R.id.multi:
                if (c != 0) {
                    existedText = String.valueOf(getResultNum())+"*";
                }
                else
                    existedText +="*";
                c = 3;
                break;
            case R.id.div:
                if (c != 0) {
                    if(!computerSign(existedText.substring(existedText.length()))) {
                        existedText = String.valueOf(getResultNum()) + "/";
                        c = 4;
                    }
                }
                else{
                    existedText +="/";
                    c = 4;
                }
                break;
            case R.id.equals:
                if (c != 0) {
                    if(!computerSign(existedText.substring(existedText.length()))){
                        existedText = String.valueOf(getResultNum());
                        c = 0;
                    }
                }
                break;
            case R.id.dot:
                existedText += ".";
                break;
            case R.id.clear:
                existedText = "0";
                c = 0;
                break;
            case R.id.del:
                String s=existedText.substring(existedText.length());
                if(s=="+"||s=="-"||s=="*"||s=="/")
                {
                    c=0;
                }
                existedText = existedText.substring(0, existedText.length() - 1);
                break;
        }
        resultText.setText(existedText);
        resultText.setTextColor(0xffffffff);
    }

    private  boolean computerSign(String s){
        boolean is=false;
        if(s=="+"||s=="-"||s=="*"||s=="/"||s=="."){
            is=true;
        }
        return is;
    }
    private double getResultNum() {
        String []str;
        if (c == 1) {
            str=existedText.split("\\u002B");
            resultNum = Double.parseDouble(str[0])+Double.parseDouble(str[1]);
        }
        if (c == 2) {
            str=existedText.split("-");
            resultNum = Double.parseDouble(str[0])-Double.parseDouble(str[1]);
        }
        if (c == 3) {
            str=existedText.split("\\u002A");
            resultNum = Double.parseDouble(str[0])*Double.parseDouble(str[1]);
        }
        if (c == 4) {
            str=existedText.split("/");
            resultNum = Double.parseDouble(str[0])/Double.parseDouble(str[1]);
        }
        return resultNum;
    }
}
