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
    EditText edtRegisterFullName;
    EditText edtRegisterEmail;
    EditText edtRegisterBirthDate;
    EditText edtRegisterBirthPlace;
    EditText edtRegisterAddress;
    Button btnRegister;
    Button btnBackToLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtRegisterUsername = findViewById(R.id.edtRegisterUsername);
        edtRegisterPassword = findViewById(R.id.edtRegisterPassword);
        edtRegisterFullName = findViewById(R.id.edtRegisterFullname);
        edtRegisterEmail = findViewById(R.id.edtRegisterEmail);
        edtRegisterBirthDate = findViewById(R.id.edtRegisterBirthDate);
        edtRegisterBirthPlace = findViewById(R.id.edtRegisterBirthPlace);
        edtRegisterAddress = findViewById(R.id.edtRegisterAddress);
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
