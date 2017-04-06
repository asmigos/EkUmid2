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

public class Profile extends AppCompatActivity implements View.OnClickListener {
    private EditText name, contact, address, blood, age;
    private RadioGroup gender;
    private RadioButton male, female;
    private TextView click, textgender;
    private ProgressDialog pb;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference db;
    private Button submit;
    private EditText email, pass, cnfpass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        name = (EditText) findViewById(R.id.name);
        contact = (EditText) findViewById(R.id.contact);
        blood = (EditText) findViewById(R.id.blood);
        age = (EditText) findViewById(R.id.age);
        gender = (RadioGroup) findViewById(R.id.gender);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        address = (EditText) findViewById(R.id.address);

        click = (TextView) findViewById(R.id.click);

        email = (EditText) findViewById(R.id.email);

        pass = (EditText) findViewById(R.id.pass);
        cnfpass = (EditText) findViewById(R.id.cnfpass);

        submit = (Button) findViewById(R.id.submit);


        FirebaseUser user = firebaseAuth.getCurrentUser();
        db = FirebaseDatabase.getInstance().getReference();


        textgender = (TextView) findViewById(R.id.textgender);

        pb = new ProgressDialog(this);


        submit.setOnClickListener(this);
        click.setOnClickListener(this);

    }


    private void registerUser() {
        String em = email.getText().toString();
        String pw = pass.getText().toString();

//        if (TextUtils.isEmpty(em) || TextUtils.isEmpty(pw)) {
//            Toast.makeText(this, "please enter all the details", Toast.LENGTH_SHORT).show();
//            return;
//        }

        pb.setMessage("you req is being processed.. please wait....!! ");
        pb.show();


        firebaseAuth.createUserWithEmailAndPassword(em, pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pb.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(Profile.this, "you are successfully registered", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(Profile.this, Profile.class));


                        } else {
                            Toast.makeText(Profile.this, "you are failed to register", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    private void saveUserInformation() {



        String nm = name.getText().toString().trim();
        String add = address.getText().toString().trim();
        String ag = age.getText().toString().trim();
        String con = contact.getText().toString().trim();
        String bg = blood.getText().toString().trim();
        String gen = textgender.getText().toString().trim();


        UserInformation userInformation = new UserInformation(nm, add, ag, con, bg, gen);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        db.child(user.getUid()).setValue(userInformation);
        Toast.makeText(this, "Information Saved..", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Profile.this, Registration.class);
        startActivity(i);
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.male:
                if (checked)
                    textgender.setText("Male");
                break;
            case R.id.female:
                if (checked)
                    textgender.setText("Female");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == submit) {

            String pas=pass.getText().toString();
            String cpass=cnfpass.getText().toString();

            if (contact.length() != 10||name.equals("")||address.equals("")||age.equals("")||contact.equals("")||blood.equals("")) {
//                contact.setError("enter correct mobile no.");
                Toast.makeText(this,"enter all the fields",Toast.LENGTH_SHORT).show();
            }
//            if(name.equals("")){
//                name.setError("Please enter your name");
//
//            }
//            if(address.equals("")){
//                address.setError("Please enter your address");
//
//            }
//            if(age.equals("")){
//                age.setError("Please enter your age");
//
//            }
//            if(contact.equals("")){
//                contact.setError("Please enter your mobile number");
//
//            }
//            if(blood.equals("")){
//                blood.setError("Please enter your blood group");
//
//            }
            else if (!pas.equals(cpass)){
                cnfpass.setError("password does not match");
            } else {

                saveUserInformation();
                registerUser();

            }


        }
            if (v == click) {
                Intent i = new Intent(Profile.this, Registration.class);
                startActivity(i);
            }

        }
    }

