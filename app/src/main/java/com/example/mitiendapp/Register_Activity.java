package com.example.mitiendapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class Register_Activity extends AppCompatActivity {

    CircleImageView mCircleImageView;
    TextInputEditText mTextImputUsername;
    TextInputEditText mTextImputEmail;
    TextInputEditText mTextImputPassword;
    TextInputEditText mTextImputConfirmPassword;
    Button mButtonRegister;
    FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

         /*ojo*/
        mTextImputUsername=findViewById(R.id.textImputUsername);
        mTextImputEmail=findViewById(R.id.textImputEmailr);
        mTextImputPassword=findViewById(R.id.textImputPasswordr);
        mTextImputConfirmPassword=findViewById(R.id.textImputConfirPasswordr);
        mButtonRegister=findViewById(R.id.btnregister);

        mAuth=FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                register();
            }
        });

        mCircleImageView=findViewById(R.id.circleImagenback); /*ojo*/
        mCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    /*Al ingresar los textos por el register activity*/
    private void register() {
        String username=mTextImputUsername.getText().toString();   /*para que me tome los textos del registro como String*/
        String email=mTextImputEmail.getText().toString();
        String password=mTextImputPassword.getText().toString();
        String confirmpassword=mTextImputConfirmPassword.getText().toString();

        if(!username.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmpassword.isEmpty()){
            if(isEmailValid(email)){
                if(password.equals(confirmpassword)){

                    if (password.length() >=6){
                        createrUser(username,email,password);

                    }else{
                        Toast.makeText(this, "la contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(this, "las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(this, "Insertaste todos los campos pero el correo no es valido", Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(this,"Has insertado todos los campos", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "Para continuar inserta todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    private void createrUser(final String username,final String email,String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    String id=mAuth.getCurrentUser().getUid();
                    Map<String,Object> map = new HashMap<>();
                    map.put("email",email);
                    map.put("username",username);
                    map.put("password",password);
                    mFirestore.collection("Users").document(id).set(map);

                    Toast.makeText(Register_Activity.this, "El usuario se registro correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Register_Activity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*Verifica si un email es valido */
    public boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
 }