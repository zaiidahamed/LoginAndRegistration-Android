package com.example.loginandregistrationform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class regiterActivity extends AppCompatActivity {

    EditText un, pw1, pw2;
    Button btnReg;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiter);

        un = findViewById(R.id.et_username1);
        pw1 = findViewById(R.id.et_password1);
        pw2 = findViewById(R.id.et_Repassword1);

        btnReg = findViewById(R.id.btn_register1);

        DB = new DBHelper(this);

        //register button
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uName = un.getText().toString();
                String pw = pw1.getText().toString();
                String rePw = pw2.getText().toString();

                if (uName.equals("") || pw.equals("") || rePw.equals("")) {
                    Toast.makeText(regiterActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pw.equals(rePw)){
                        boolean checkUser = DB.checkUsername(uName);
                        if (checkUser == false) {
                            Boolean insert = DB.insertData(uName, pw);
                            if (insert == true) {
                                Toast.makeText(regiterActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), home.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(regiterActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(regiterActivity.this, "USer already exists!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(regiterActivity.this, "Password mismatch!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}