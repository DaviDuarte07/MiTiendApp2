package com.example.mitiendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
/*import com.google.firebase.auth.FirebaseAuth;*/

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    TextView mTextViewRegister;  /*Interaccion con las vistas de Android se declara el objeto*/
    TextInputEditText mTextImputEmail;
    TextInputEditText mTextPassword;
    Button mBUttonLogin;
    /*FirebaseAuth mAuth;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewRegister =findViewById(R.id.TextViewRegister); /*Para ubicar el objeto TextViewRegister del android .xml*/
        mTextImputEmail=findViewById(R.id.textImputEmailr);
        mTextPassword=findViewById(R.id.textImputPassword);
        mBUttonLogin=findViewById(R.id.btnlogin);

        mBUttonLogin.setOnClickListener(new View.OnClickListener() { /*Crea la accion oir boton INICIAR SESION*/
            @Override
            public void onClick(View v) {
                login();
            }
        });

        mTextViewRegister.setOnClickListener(new View.OnClickListener() {  /*Le doy funcion a REGISTRARSE con click*/
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(MainActivity.this,Register_Activity.class); /*Intencion, al dar click me lleva desde la mainActivity  al RegisterActivity*/
                startActivity(intent);
            }
        });
    }

    private void login() {
        String email=mTextImputEmail.getText().toString(); /*al oprimir login o INICIAR SESION me esta tomando el texto del email como String*/
        String password= mTextPassword.getText().toString(); /*al oprimir login o INICIAR SESION me esta tomando el texto del password como String*/

        /*Realizamos un campo de datos de forma local, sin base de datos*/
        Log.d("Campo","email");
        Log.d("Campo","password"+password);
    }

}