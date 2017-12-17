package com.eatit.user.eatit;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button SignUpBtn, SignInBtn;
    TextView SloganText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SignUpBtn = (Button)findViewById(R.id.SignUpBtn);
        SignInBtn = (Button)findViewById(R.id.SignInBtn);

        SloganText = (TextView)findViewById(R.id.SloganText);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/NABILA.TTF");
        SloganText.setTypeface(face);

        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(signUp);
            }
        });

        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIn = new Intent(MainActivity.this, SignIn.class);
                startActivity(signIn);
            }
        });
    }
}
