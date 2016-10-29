package com.example.mycalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.nio.DoubleBuffer;

public class MainActivity extends AppCompatActivity {
    private Button calcilatorButton;
    private EditText weightEdittext;
    private RadioButton manRadioButton;
    private RadioButton womenRadioButton;
    private TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcilatorButton = (Button) findViewById(R.id.calculate);
        weightEdittext = (EditText) findViewById(R.id.weight);
        manRadioButton = (RadioButton) findViewById(R.id.man);
        womenRadioButton = (RadioButton) findViewById(R.id.women);
        resultTextView = (TextView) findViewById(R.id.result);


    }
    protected void onStart(){
        super.onStart();
        registerEvent();

    }
    private void registerEvent(){
        calcilatorButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!weightEdittext.getText().toString().trim().equals("")) {
                    if (manRadioButton.isChecked() || womenRadioButton.isChecked()) {
                        Double weight = Double.parseDouble(weightEdittext.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("--------评估结果--------\n");
                        double result = 0;
                        if (manRadioButton.isChecked()) {
                            sb.append("男性标准身高：");
                            sb.append((int) result + "厘米");
                        } else {
                            sb.append("女性标准身高：");
                            result = evaluateHeight(weight, "女");

                        }
                        resultTextView.setText(sb.toString());
                    }else {
                        showMessage("请选择性别");
                    }
                }else {
                    showMessage("请输入体重！");
                }
            }
        });
    }
    private void showMessage(String message){
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("系统消息");
        alert.setMessage(message);
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"确定",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which){

            }
        });
        alert.show();
    }
    private double evaluateHeight(double weight,String sex){
        double height;
        if (sex=="男"){
            height=170-(62-weight)/0.6;

        }else {
            height=158-(52-weight);
        }
        return  height;
    }
    public boolean onCreateOptionMenu(Menu menu){
        menu.add(0,1,0,"退出");
        return  super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1:
                finish();;
                break;
        }
        return  super.onOptionsItemSelected(item);

    }
}
