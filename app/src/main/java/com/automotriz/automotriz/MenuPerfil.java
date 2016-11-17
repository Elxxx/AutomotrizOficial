package com.automotriz.automotriz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuPerfil extends AppCompatActivity {


    private FirebaseAuth firebaseauth;
    private EditText textonombresession,textotelefono;
    private TextView textoCorreoperfil;
    private Button bDesconectarse;
    private ProgressDialog progresodialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_perfil);

        firebaseauth = FirebaseAuth.getInstance();
        if(firebaseauth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,MenuLogin.class));
        }
        FirebaseUser user = firebaseauth.getCurrentUser();

        textoCorreoperfil = (TextView)findViewById(R.id.txtCorreoSession);
        textoCorreoperfil.setText(user.getEmail());

    }















    private void userActualizar()
    {
        String correo = textoCorreoperfil.getText().toString().trim();

        if(TextUtils.isEmpty(correo)){
            Toast.makeText(this,"Por favor ingrese bien el correo",Toast.LENGTH_LONG).show();
            return;
        }


    }










}
