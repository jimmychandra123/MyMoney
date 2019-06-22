package id.web.utem.mymoney;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText edtRegisterUsername;
    EditText edtRegisterPassword;
    EditText edtRegisterEmail;
    Button btnRegister;
    Button btnBackToLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtRegisterUsername = findViewById(R.id.edtRegisterUsername);
        edtRegisterPassword = findViewById(R.id.edtRegisterPassword);
        edtRegisterEmail = findViewById(R.id.edtRegisterEmail);
        btnRegister = findViewById(R.id.btnRegister);
        btnBackToLogin = findViewById(R.id.btnBackToLogin);
    }

    public void fnRegister(View view) {

    }

    public void fnBackToLogin(View view) {
        finish();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
