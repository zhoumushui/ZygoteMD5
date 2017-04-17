package com.zhoumushui.zygotemd5;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.zhoumushui.zygotemd5.util.HintUtil;
import com.zhoumushui.zygotemd5.util.MD5Util;

public class MainActivity extends AppCompatActivity {
    private Context context;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        initialLayout();
    }

    private void initialLayout() {
        textResult = (TextView) findViewById(R.id.textResult);

        EditText editPassword = (EditText) findViewById(R.id.editPassword);
        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0)
                    showMd5Result(s.toString());
            }
        });

        SearchView searchTest = (SearchView) findViewById(R.id.searchTest);
        searchTest.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchTest.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { // 当点击搜索按钮时触发该方法
                HintUtil.showToast(context, "onQueryTextSubmit:" + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) { // 当搜索内容改变时触发该方法
                showMd5Result(newText);
                return false;
            }
        });
    }

    void showMd5Result(String str) {
        textResult.setText("MD5x1:" + MD5Util.getStringMD5(str) +
                "\nMD5x2:" + MD5Util.getStringMD5MultiTimes(str, 2) +
                "\nMD5x3:" + MD5Util.getStringMD5MultiTimes(str, 3));
    }

}
