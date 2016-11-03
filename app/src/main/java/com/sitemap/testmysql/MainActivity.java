package com.sitemap.testmysql;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button register, login, userInfo;
    private HttpUtil http;
    private EditText user, psw;
    private static final String HOST_HOME = "http://192.168.2.104:8080/MyServer";

    public static final String REGISTER_URL = HOST_HOME + "/UserRegisterServlet?";
    public static final String LOGIN_URL = HOST_HOME + "/UserLoginServlet?";
    public static final String GETUSERINFO = HOST_HOME + "/UserInfoServlet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        user = (EditText) findViewById(R.id.user);
        psw = (EditText) findViewById(R.id.psw);
        userInfo = (Button) findViewById(R.id.getUserInfo);
        userInfo.setOnClickListener(this);
        http = new HttpUtil(handler);
        changeDate("20161101142300");
        Log.i("result", "输出的:" + changeDate("20161101142300"));
    }

    public static String changeDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(date, pos);
        SimpleDateFormat format = new SimpleDateFormat("MMdd");
        String str = format.format(strtodate);
        return str;
    }

    @Override
    public void onClick(View v) {
        if (v == register) {
            http.sendGet(1, register(user.getText().toString(), psw.getText().toString()));
        }
        if (v == login) {
            http.sendGet(2, login(user.getText().toString(), psw.getText().toString()));
        }
        if (v == userInfo) {
            http.sendGet(3, getUserInfo());
        }
    }


    /**
     * @param name
     * @param psw
     * @return
     */
    public String register(String name, String psw) {
        return REGISTER_URL + "name=" + name + "&password=" + psw;
    }

    public String login(String name, String psw) {
        return LOGIN_URL + "name=" + name + "&password=" + psw;
    }

    public String getUserInfo() {
        return GETUSERINFO;
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HttpUtil.SUCCESS:
                    if (msg.arg1 == 1) {
                        try {
                            JSONObject ob = JSON.parseObject(msg.obj.toString());
                            String reason = ob.getString("result");
                            Toast.makeText(MainActivity.this, reason, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    if (msg.arg1 == 2) {
                        try {
                            JSONObject ob = JSON.parseObject(msg.obj.toString());
                            String reason = ob.getString("result");
                            Toast.makeText(MainActivity.this, reason, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    if (msg.arg1 == 3) {
//                        UserInfoModel model = JSONObject.parseObject(msg.obj.toString(), UserInfoModel.class);
//                        Log.e("jack",model.getUserNick()+"\n"+model.getUserAge()+"\n"+model.getUserSex()+"\n"+model.getUserLove());
                    }
                    break;
                case HttpUtil.FAILURE:
                    break;
                default:
                    break;
            }
        }
    };

}
