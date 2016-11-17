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

public class MenuRegistro extends AppCompatActivity implements View.OnClickListener{

    EditText txtnombre, txtemail, txtpassword;
    Button btnregistrarse,btnvolver;
    private ProgressDialog progresodialogo;
    private FirebaseAuth firebaseauth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_registro);
        firebaseauth = FirebaseAuth.getInstance();
        progresodialogo = new ProgressDialog(this);


        txtemail = (EditText) findViewById(R.id.txtCorreo);
        txtpassword = (EditText) findViewById(R.id.txtpass);

        btnregistrarse = (Button) findViewById(R.id.btLogin);
        btnvolver = (Button) findViewById(R.id.btnVolver);
        initControl();
    }



    private void initControl() {
        Button BTNLOGIN = (Button) findViewById(R.id.btLogin);
        Button btnVOLVER = (Button) findViewById(R.id.btnVolver);
        BTNLOGIN.setOnClickListener(this);
        btnVOLVER.setOnClickListener(this);
    }

  private void registrarUsuario(){


    String correo = txtemail.getText().toString();
    String pass = txtpassword.getText().toString();

    if(TextUtils.isEmpty(correo)){
        Toast.makeText(this,"Por favor ingrese un correo",Toast.LENGTH_LONG).show();
        return;
    }
    if(TextUtils.isEmpty(pass)){
        Toast.makeText(this,"Por favor ingrese una contraseña",Toast.LENGTH_LONG).show();
        return;
    }

    progresodialogo.setMessage("Registrando usuario");
    progresodialogo.show();

    firebaseauth.createUserWithEmailAndPassword(correo,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                Toast.makeText(MenuRegistro.this, "Registro realizado", Toast.LENGTH_LONG).show();
                progresodialogo.dismiss();
            }else{
                Toast.makeText(MenuRegistro.this, "Error de registro, verifique su correo o contraseña", Toast.LENGTH_LONG).show();
                progresodialogo.dismiss();
            }
        }


    });



}

    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btLogin:
                registrarUsuario();
                break;
            case R.id.btnVolver:
                Intent x = new Intent(this, MenuLogin.class);
                startActivity(x);
                break;

        }
    }




}
