package com.eatit.user.eatit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.eatit.user.eatit.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    MaterialEditText PhoneNumberEditor, NameEditor, PasswordEditor;
    Button SignUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        PhoneNumberEditor = (MaterialEditText)findViewById(R.id.PhoneNumberEditor);
        NameEditor = (MaterialEditText)findViewById(R.id.NameEditor);
        PasswordEditor = (MaterialEditText)findViewById(R.id.PasswordEditor);
        SignUpBtn = (Button)findViewById(R.id.SignUpBtn);

        // Setup Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference user_Table = database.getReference("Users");

        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog Dialog = new ProgressDialog(SignUp.this);
                Dialog.setMessage("Please Wait ...");
                Dialog.show();

                user_Table.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Dialog.dismiss();

                        // Check if Already Exists
                        if (dataSnapshot.child(PhoneNumberEditor.getText().toString()).exists()){
                            Toast.makeText(SignUp.this, "Phone Number Already Exists!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Users user = new Users(NameEditor.getText().toString(), PasswordEditor.getText().toString(), PhoneNumberEditor.getText().toString());
                            user_Table.child(PhoneNumberEditor.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Sign Up Success! Please Sign in.", Toast.LENGTH_SHORT).show();
                            finish();
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
