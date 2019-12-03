package com.bootcamp.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RecoverPassDialog extends AppCompatDialogFragment {
    private EditText mResetPass;
    FirebaseAuth mFirebaseAuth;
    ProgressDialog progress;
    ImageView imageView;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater= getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_alert,null);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mResetPass= view.findViewById(R.id.email);
        imageView = view.findViewById(R.id.image);
        Glide.with(getActivity()).load(R.drawable.giphy).into(imageView);
        mResetPass.setHint("Correo");
        mResetPass.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);


        builder.setView(view)
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Recuperar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String email=mResetPass.getText().toString().trim();
                        if (email.length() == 0) {
                            mResetPass.setFocusableInTouchMode(true);
                            mResetPass.requestFocus();
                        }
                        else {
                            beginRecovery(email);
                        }
                    }
                });
        return builder.create();
    }


    private void beginRecovery(String email) {
        progress = ProgressDialog.show(getActivity(), "Cargando",
                "Enviando correo", true);
        progress.show();
        mFirebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progress.dismiss();
                if (task.isSuccessful()) {

                } else {

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progress.dismiss();
            }
        });
    }
}
