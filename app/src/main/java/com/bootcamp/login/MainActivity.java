package com.bootcamp.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.midi.MidiDeviceService;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    /*private EditText emailId,password;
    private Button btnLogin;*/

    //Declarando variables miembro
    EditText emailId, password;
    TextView mRecoverPass;
    Button SignInBtn;
    FirebaseAuth mFirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    ProgressDialog progress;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //
            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();

            mFirebaseAuth = FirebaseAuth.getInstance();
            //Inflando widgets
            emailId = findViewById(R.id.email);
            password = findViewById(R.id.password);
            SignInBtn = findViewById(R.id.access);
            mRecoverPass=findViewById(R.id.recover_pass);

            mAuthStateListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                    if( mFirebaseUser != null ) {
                        Toast.makeText(MainActivity.this, "Bienvenido!", Toast.LENGTH_SHORT);
                        Intent i = new Intent(MainActivity.this, Home.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Por favor inicie sesión", Toast.LENGTH_SHORT);
                    }
                }
            };
            SignInBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String email = emailId.getText().toString();
                    String psw = password.getText().toString();
                    //Revisar campos estan vacios
                    if (email.isEmpty()) {
                        emailId.setError("Introduzca su correo");
                        emailId.requestFocus();
                    }
                    else if (psw.isEmpty()) {

                        password.setError("Introduzca una contraseña");
                        password.requestFocus();
                    }
                    else if (email.isEmpty() && psw.isEmpty()){
                        Toast.makeText(MainActivity.this, "Hay campos vacíos!!", Toast.LENGTH_SHORT).show();
                    }
                    else if (!(email.isEmpty() && psw.isEmpty())) {
                        progress = ProgressDialog.show(MainActivity.this, "Cargando",
                                "Espere un momento", true);
                        mFirebaseAuth.signInWithEmailAndPassword(email,psw).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(MainActivity.this, "No pudo iniciar sesión!", Toast.LENGTH_SHORT).show();
                                    progress.dismiss();
                                }
                                else {

                                    Intent intToHome = new Intent(MainActivity.this, Home.class);
                                    startActivity(intToHome);
                                }
                            }
                        });
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Ha ocurrido un error!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            mRecoverPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDialog();
                }
            });

    }

    @Override
        public void onStart() {
            super.onStart();
            mFirebaseAuth.addAuthStateListener(mAuthStateListener);
        }

        @Override
        public void onStop() {
            super.onStop();
            if (mAuthStateListener != null) {
                mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
            }
        }

        public void openDialog(){
         RecoverPassDialog passDialog = new RecoverPassDialog();
         passDialog.show(getSupportFragmentManager(),"dialog");
        }

}
