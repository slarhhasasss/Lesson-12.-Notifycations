package ru.kolesnikovdmitry.lesson12notifications;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    TextView mTextViewActSec;
    Button   mBtnActSec;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mBtnActSec = findViewById(R.id.btnOkActSecond);
        mBtnActSec.setText("Все!");
        mTextViewActSec = findViewById(R.id.textViewActSecond);
        mTextViewActSec.setText("ВСЕ?");
    }


    public void onClickActSecond(View view) {
        switch (view.getId()) {
            case R.id.btnOkActSecond:
                finish();
                break;
            default:
                break;
        }
    }
}
