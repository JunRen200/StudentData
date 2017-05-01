package comqq.example.asus_pc.studentdata;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static comqq.example.asus_pc.studentdata.R.id.number;

public class MainActivity extends AppCompatActivity implements Myadawpter.OnDeleteAndSet {
    private EditText edt_number;
    private EditText edt_name;
    private EditText edt_password;
    private Button btn_add;
    private RecyclerView recyclerView;
    private Myadawpter myadawpter;
    private student1 st;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String a = (String) msg.obj;
                Gson gson = new Gson();
                st = gson.fromJson(a, student1.class);
                myadawpter = new Myadawpter(st.getStudentBean(), MainActivity.this);
                recyclerView.setAdapter(myadawpter);
                myadawpter.setlist(st.getStudentBean());
            }
        }
    };
    ArrayList<student1.StudentBean> list = new ArrayList<student1.StudentBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_number = (EditText) findViewById(number);
        edt_name = (EditText) findViewById(R.id.name);
        edt_password = (EditText) findViewById(R.id.password);
        btn_add = (Button) findViewById(R.id.add);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        query();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }

    private void query() {
        OkHttpClient client = new OkHttpClient();
        String url = "http://192.168.56.1:8080/School/queryStudent";
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("AAA", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String a = response.body().string();
                Message msg = new Message();
                msg.what = 1;
                msg.obj = a;
                handler.sendMessage(msg);
                Log.e("AAA", a);
            }
        });
    }

    private void insert() {
        String number = edt_number.getText().toString();
        String name = edt_number.getText().toString();
        String password = edt_password.getText().toString();
        OkHttpClient client = new OkHttpClient();
        String url = "http://192.168.56.1:8080/School/addStudent";
        RequestBody body = new FormBody.Builder()
                .add("number", number)
                .add("name", name)
                .add("password", password)
                .build();
        Request request = new Request.Builder().url(url).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("AAA", response.body().string());
                query();
            }
        });
    }


    @Override
    public void onItemclickDelete(String number) {
        OkHttpClient client = new OkHttpClient();
        String url = "http://192.168.56.1:8080/School/removeStudent";
        RequestBody body = new FormBody.Builder()
                .add("number", number)
                .build();
        Request request = new Request.Builder().url(url).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("AAA", response.body().string());
                query();
            }
        });

    }

    @Override
    public void onItemclickSet(String number, String name, String password) {
        OkHttpClient client = new OkHttpClient();
        String url = "http://192.168.56.1:8080/School/updateStudent";
        RequestBody body = new FormBody.Builder()
                .add("number", number)
                .add("name", name)
                .add("password", password)
                .build();
        Request request = new Request.Builder().url(url).header("Accept-Charset", "utf-8").post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("AAA", response.body().string());
                query();
            }
        });
    }
}
