package com.example.hp.ekumid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {
    TextView welcome,name,address,age,gender,email,blood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        welcome=(TextView)findViewById(R.id.welcome);
        name=(TextView)findViewById(R.id.name);
        address=(TextView)findViewById(R.id.address);
        age=(TextView)findViewById(R.id.age);
        gender=(TextView)findViewById(R.id.tgender);
        email=(TextView)findViewById(R.id.email);
        blood=(TextView)findViewById(R.id.blood);
    }
}
