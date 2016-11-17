package com.automotriz.automotriz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by elias on 18/10/2016.
 */

public class MenuLogin extends AppCompatActivity implements View.OnClickListener{



    EditText email, pass;
    Button btLogin,btnRegistrer;
    private ProgressDialog progresodialogo;
    private FirebaseAuth firebaseauth;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_login);
        firebaseauth = FirebaseAuth.getInstance();
        if(firebaseauth.getCurrentUser() != null){

            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));

        }
        progresodialogo = new ProgressDialog(this);
        email = (EditText) findViewById(R.id.txtuser);
        pass = (EditText) findViewById(R.id.txtpass);
        btLogin = (Button) findViewById(R.id.btLogin);
        initControl();


    }

    private void initControl() {
        Button btnRe = (Button) findViewById(R.id.btRregistrar);
        Button btnLog = (Button) findViewById(R.id.btLogin);
        btnRe.setOnClickListener(this);
        btnLog.setOnClickListener(this);
    }




    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btRregistrar:
                Intent x = new Intent(this, MenuRegistro.class);
                startActivity(x);
                break;
            case R.id.btLogin:
                userLogin();
                break;

        }
    }


        private void userLogin()
    {
        String correo = email.getText().toString().trim();
        String passs = pass.getText().toString().trim();
        if(TextUtils.isEmpty(correo)){
            Toast.makeText(this,"Por favor ingrese un correo",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(passs)){
            Toast.makeText(this,"Por favor ingrese una contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        progresodialogo.setMessage("Accediendo ...");
        progresodialogo.show();
        firebaseauth.signInWithEmailAndPassword(correo,passs)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progresodialogo.dismiss();
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }

                    }
                }  );

        Toast.makeText(this,"Comprobando ..",Toast.LENGTH_LONG).show();

    }


    }







