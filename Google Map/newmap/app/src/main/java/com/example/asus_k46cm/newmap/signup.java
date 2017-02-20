package com.example.asus_k46cm.newmap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class signup extends AppCompatActivity {
    Button btOk;
    EditText edName,edPass,edPass1,edMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btOk = (Button) findViewById(R.id.btOk);
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //các thông tin đăng ký gửi lên sever
                    SignUp();



            }
        });


    }
    public void SignUp()
    {
        edName=(EditText)findViewById(R.id.edName);
        edPass=(EditText)findViewById(R.id.edPassword);
        edPass1=(EditText)findViewById(R.id.edPassword1);
        edMail=(EditText)findViewById(R.id.edMail);
        ParseUser user = new ParseUser();
        user.setUsername(edName.getText().toString());
        user.setPassword(edPass.getText().toString());
        user.setEmail(edMail.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}