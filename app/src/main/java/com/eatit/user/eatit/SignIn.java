package com.eatit.user.eatit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eatit.user.eatit.Common.Common;
import com.eatit.user.eatit.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {

    EditText PhoneNumberEditor, PasswordEditor;
    Button SignInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        PhoneNumberEditor = (MaterialEditText) findViewById(R.id.PhoneNumberEditor);
        PasswordEditor = (MaterialEditText) findViewById(R.id.PasswordEditor);
        SignInBtn = (Button) findViewById(R.id.SignInBtn);

        // Setup Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference user_Table = database.getReference("Users");

        // Action Listener
        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog Dialog = new ProgressDialog(SignIn.this);
                Dialog.setMessage("Please Wait ...");
                Dialog.show();

                user_Table.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Dialog.dismiss();

                        if (dataSnapshot.child(PhoneNumberEditor.getText().toString()).exists()) {

                            Users user = dataSnapshot.child(PhoneNumberEditor.getText().toString()).getValue(Users.class);
                            user.setPhone(PhoneNumberEditor.getText().toString()); // Set Phone Number to user

                            if (user.getPassword().equals(PasswordEditor.getText().toString())) {
                                Intent home = new Intent(SignIn.this, Home.class);
                                Common.currentUser = user;
                                startActivity(home);
                                finish();
                            } else {
                                Toast.makeText(SignIn.this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(SignIn.this, "User not Found!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });

    }
}
