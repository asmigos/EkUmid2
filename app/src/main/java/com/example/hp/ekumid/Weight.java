package com.example.hp.ekumid;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Weight extends AppCompatActivity {

    private EditText rcno,weight;
    private Button submit;
    TextView tv;
    final public String REGISTER_URL = "http://ekumeed.esy.es/weight.php";


    static String rc,weg;

    public Weight() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        rcno=(EditText)findViewById(R.id.rcno);
        weight=(EditText)findViewById(R.id.weight);
        submit=(Button)findViewById(R.id.submit);




      submit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(v == submit){

                  rc=rcno.getText().toString();
                  weg=weight.getText().toString();
                  registerUser();
              }
          }
      });


    }



    private void registerUser() {
        //String name = editTextName.getText().toString().trim().toLowerCase();




        Boolean flag = true;



            register(rc, weg);
        }


    private void register(final String reg, final String weight) {
        class RegisterUser extends AsyncTask<String,Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                loading = ProgressDialog.show(Weight.this, "Please wait", "Loading...");
            }


            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

 tv.setText(getApplication());
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                // data.put("name",params[0]);
                data.put("rc",reg);
                data.put("weight",weight);


                String result = ruc.sendPostRequest(REGISTER_URL,data);

                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute();
    }
}