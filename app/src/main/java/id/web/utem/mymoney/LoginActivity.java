package id.web.utem.mymoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private final String apiLink = "http://mymoney-api.utem.web.id/api/v1/";

    Button btnLogin;
    EditText edtUsername;
    EditText edtPassword;
    ProgressBar progressBar;
    TextView tvRegister;

    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        edtUsername = findViewById(R.id.edtLoginPassword);
        edtPassword = findViewById(R.id.edtLoginPassword);
        progressBar = findViewById(R.id.progressBar);
        tvRegister = findViewById(R.id.tvRegister);

        pref = getApplicationContext().getSharedPreferences("MyMoney_Pref", 0);
        editor = pref.edit();

        if (pref.contains("logged_in"))
        {
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void clickLogin(final View view) {
        progressBar.setVisibility(View.VISIBLE);
        view.setClickable(false);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, apiLink +"auth.php?method=post&auth=login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;

                progressBar.setVisibility(View.INVISIBLE);
                view.setClickable(true);

                try {
                    jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("success"))
                    {
                        editor.putBoolean("logged_in", true);
                        editor.putString("user_id", jsonObject.getString("data"));
                        editor.commit();

                        String message = jsonObject.getString("message");
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                        finish();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    progressBar.setVisibility(View.INVISIBLE);
                    view.setClickable(true);
                    Toast.makeText(getApplicationContext(), "There is an error!", Toast.LENGTH_SHORT).show();
                    Log.d("JSONException", "ERROR ", e);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Unable to make connection to web service", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                view.setClickable(true);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("username", edtUsername.getText().toString());
                params.put("password", edtPassword.getText().toString());
                return params;
            }
        };

        requestQueue.add(stringRequest);

    }

    public void clickRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
