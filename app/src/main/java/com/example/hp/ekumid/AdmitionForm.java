package com.example.hp.ekumid;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AdmitionForm extends AppCompatActivity implements View.OnClickListener{

    private EditText name,age,weight,category,injplace,blood;
    private Button submit;
    private TextView textinjury;
    private RadioGroup injtype;
    private RadioButton seviour,minor;
private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admition_form);

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
       weight = (EditText) findViewById(R.id.weight);
        category = (EditText) findViewById(R.id.category);
        injplace = (EditText) findViewById(R.id.injplace);
        blood = (EditText) findViewById(R.id.blood);


        submit = (Button) findViewById(R.id.submit);

        textinjury = (TextView) findViewById(R.id.textinjury);

        injtype = (RadioGroup) findViewById(R.id.injtype);

        seviour = (RadioButton) findViewById(R.id.seviour);
        minor = (RadioButton) findViewById(R.id.minor);



        progressDialog = new ProgressDialog(this);
        submit.setOnClickListener(this);



        String nm = name.getText().toString().trim();
        String wei = weight.getText().toString().trim();
        String ag = age.getText().toString().trim();
        String cat = category.getText().toString().trim();
        String bl = blood.getText().toString().trim();
        String inj = textinjury.getText().toString().trim();
        String injpla = injplace.getText().toString().trim();


    }


    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();


        switch (view.getId()) {
            case R.id.seviour:
                if (checked)
                    textinjury.setText("Seviour");
                break;
            case R.id.minor:
                if (checked)
                    textinjury.setText("Minor");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if(v==submit){

        }
    }
}
