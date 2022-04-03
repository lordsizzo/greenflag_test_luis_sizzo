package com.example.greenflag_test_luis_sizzo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greenflag_test_luis_sizzo.local_db.SQLQueries;
import com.example.greenflag_test_luis_sizzo.utils.Dialogs;
import com.example.greenflag_test_luis_sizzo.utils.Validations;

public class RegisterActivity extends AppCompatActivity {

    private ImageView registerBack;

    private EditText et_email;
    private EditText et_password;
    private EditText et_passwordRepeat;

    private LinearLayout llEmailAlertError;
    private LinearLayout llPasswordAlertError;

    private TextView tvAlertErrorPassword;
    private TextView tvAlertErrorEmail;

    private Boolean isValidEmail = false, isValidPassword = false;

    private Validations validations = new Validations();


    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        initViews();
        listenersAndValidations();
    }

    private void initViews() {


        btnRegister = findViewById(R.id.btnRegister);
        llEmailAlertError = findViewById(R.id.llEmailAlertError);
        llPasswordAlertError = findViewById(R.id.llPasswordAlertError);

        tvAlertErrorEmail = findViewById(R.id.tvAlertErrorEmail);
        tvAlertErrorPassword = findViewById(R.id.tvAlertErrorPassword);

        et_email = findViewById(R.id.et_emailAddress);
        et_password = findViewById(R.id.et_password);
        et_passwordRepeat = findViewById(R.id.et_passwordRepeat);
        registerBack = findViewById(R.id.btnRegisterBack);
    }

    private void listenersAndValidations() {



        et_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et_password.setText("");
                et_passwordRepeat.setText("");
                isValidPassword = false;
                if (et_email.getText().toString().isEmpty()) {
                    et_email.setBackgroundColor(getResources().getColor(R.color.white));
                    et_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    llEmailAlertError.setVisibility(LinearLayout.GONE);
                    btnRegister.setEnabled(false);
                    btnRegister.setAlpha(0.5f);
                    isValidEmail = false;
                }else{
                    if(validations.isValidEmail(et_email.getText().toString())){
                        if(SQLQueries.checkAllUsuario(RegisterActivity.this).isEmpty()){
                            et_email.setBackground(getResources().getDrawable(R.drawable.success));
                            et_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                            llEmailAlertError.setVisibility(LinearLayout.GONE);

                            isValidEmail = true;
                        }else{
                            if (!SQLQueries.CheckUsuario(RegisterActivity.this, et_email.getText().toString())) {
                                tvAlertErrorEmail.setText("An account with this email already exists");
                                et_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                et_email.setBackground(getResources().getDrawable(R.drawable.error));
                                llEmailAlertError.setVisibility(LinearLayout.VISIBLE);
                                btnRegister.setEnabled(false);
                                btnRegister.setAlpha(0.5f);
                                isValidEmail = false;
                            }else{
                                et_email.setBackground(getResources().getDrawable(R.drawable.success));
                                et_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                                llEmailAlertError.setVisibility(LinearLayout.GONE);
                                isValidEmail = true;
                            }
                        }
                    }else{
                        et_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        et_email.setBackground(getResources().getDrawable(R.drawable.error));
                        llEmailAlertError.setVisibility(LinearLayout.VISIBLE);
                        tvAlertErrorEmail.setText("Invalid Email");
                        btnRegister.setEnabled(false);
                        btnRegister.setAlpha(0.5f);
                        isValidEmail = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_password.getText().toString().isEmpty()) {
                    et_password.setBackgroundColor(getResources().getColor(R.color.white));
                    et_password.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    llPasswordAlertError.setVisibility(LinearLayout.GONE);
                    btnRegister.setEnabled(false);
                    btnRegister.setAlpha(0.5f);
                    et_passwordRepeat.setText("");
                    isValidPassword = false;
                }else{
                    if(validations.isValidPassword(et_password.getText().toString())){
                        et_password.setBackground(getResources().getDrawable(R.drawable.success));
                        et_password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                        llPasswordAlertError.setVisibility(LinearLayout.GONE);
                        isValidPassword = false;
                    }else{
                        et_password.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        et_password.setBackground(getResources().getDrawable(R.drawable.error));
                        llPasswordAlertError.setVisibility(LinearLayout.VISIBLE);
                        tvAlertErrorPassword.setText("Password incorrect");
                        btnRegister.setEnabled(false);
                        btnRegister.setAlpha(0.5f);
                        et_passwordRepeat.setText("");
                        isValidPassword = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        et_passwordRepeat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_passwordRepeat.getText().toString().isEmpty()) {
                    et_passwordRepeat.setBackgroundColor(getResources().getColor(R.color.white));
                    et_passwordRepeat.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    llPasswordAlertError.setVisibility(LinearLayout.GONE);
                    btnRegister.setEnabled(false);
                    btnRegister.setAlpha(0.5f);
                    isValidPassword = false;
                }else{
                    if(et_passwordRepeat.getText().toString().equals(et_password.getText().toString()) && isValidEmail){
                        et_passwordRepeat.setBackground(getResources().getDrawable(R.drawable.success));
                        et_passwordRepeat.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                        llPasswordAlertError.setVisibility(LinearLayout.GONE);
                        btnRegister.setEnabled(true);
                        btnRegister.setAlpha(1);
                        isValidPassword = true;

                    }else{
                        tvAlertErrorPassword.setText("Passwords do not match");
                        if(!isValidEmail){
                            tvAlertErrorPassword.setText("Email is not valid or already in use");
                        }
                        et_passwordRepeat.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        et_passwordRepeat.setBackground(getResources().getDrawable(R.drawable.error));
                        llPasswordAlertError.setVisibility(LinearLayout.VISIBLE);

                        btnRegister.setEnabled(false);
                        btnRegister.setAlpha(0.5f);
                        isValidPassword = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        registerBack.setOnClickListener( v-> {
            if(!et_email.getText().toString().isEmpty() || !et_password.getText().toString().isEmpty() || !et_passwordRepeat.getText().toString().isEmpty()){
                new Dialogs().showBottomSheetDialog(this);
            }else{
                Intent intent = new Intent(this, SplashActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnRegister.setOnClickListener(v -> {
            if (isValidEmail && isValidPassword) {
                Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show();
                makeRegister();
            }else{
                Toast.makeText(this, "The registration was failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void makeRegister(){

        SQLQueries.InsertUsuario(this, et_email.getText().toString(), et_passwordRepeat.getText().toString());
        et_email.setText("");
        et_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        et_email.setBackgroundColor(getResources().getColor(R.color.white));
        et_password.setText("");
        et_password.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        et_password.setBackgroundColor(getResources().getColor(R.color.white));
        et_passwordRepeat.setText("");
        et_passwordRepeat.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        et_passwordRepeat.setBackgroundColor(getResources().getColor(R.color.white));
        btnRegister.setEnabled(false);
        btnRegister.setAlpha(0.5f);
    }

}