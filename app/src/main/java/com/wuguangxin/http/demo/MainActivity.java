package com.wuguangxin.http.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wuguangxin.http.business.ResponseHandler;
import com.wuguangxin.http.demo.http.HttpUtils;

public class MainActivity extends AppCompatActivity {
    private TextView mResult;
    private EditText mEditText;
    private String url = "http://api.k780.com/?app=time.world&city_en=new-york-ny&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(url);
            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mEditText.getText().toString().trim();
                if(TextUtils.isEmpty(text)){
                    Toast.makeText(MainActivity.this, "请输入正确的地址", Toast.LENGTH_SHORT).show();
                } else {
                    testGet(text);
                }
           }
        });
        mEditText = (EditText) findViewById(R.id.editText);
        mResult = (TextView) findViewById(R.id.result);
        mEditText.setText(url);
    }

    protected void testGet(String url) {
        HttpUtils.get(url, new ResponseHandler() {
            @Override
            public void onStart(String key) {
                super.onStart(key);
                Log.e("WGX", "开始：" + key);
                mResult.setText("开始：" + key);
            }

            @Override
            public void onSuccess(String strResponse) {
                super.onSuccess(strResponse);
                mResult.setText(strResponse);
            }

            @Override
            public void onFailure(String msg) {
                super.onFailure(msg);
                mResult.setText(msg);
            }
        });
    }
}
