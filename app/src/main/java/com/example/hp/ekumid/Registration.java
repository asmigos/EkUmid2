package com.example.hp.ekumid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    private Button login;
    private EditText email,pass;
    private TextView submit;
    private ProgressDialog pb;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firebaseAuth=FirebaseAuth.getInstance();
        submit=(TextView) findViewById(R.id.submit);
        login=(Button)findViewById(R.id.login);
        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);


        pb=new ProgressDialog(this);
        submit.setOnClickListener(this);
        login.setOnClickListener(this);

    }






    private void loginUser() {
        String em = email.getText().toString();
        String pw = pass.getText().toString();

        if (TextUtils.isEmpty(em)|| TextUtils.isEmpty(pw)) {
            Toast.makeText(this, "please enter all the details", Toast.LENGTH_SHORT).show();
            return;
        }

        pb.setMessage("Your request is being processed.. please wait....!! ");
        pb.show();


        firebaseAuth.signInWithEmailAndPassword(em, pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            //start profile activity here
                            pb.dismiss();
                            finish();
                            startActivity(new Intent(Registration.this, Weight.class));
                        }

                        else {
                            pb.dismiss();
                             Toast.makeText(getApplicationContext(),"Please register first...or check the entries!!",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

        @Override
    public void onClick(View v) {
        if(v == submit){
//
           Intent i=new Intent(Registration.this,Profile.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            }


        if(v == login){
             loginUser();

        }

    }
}
