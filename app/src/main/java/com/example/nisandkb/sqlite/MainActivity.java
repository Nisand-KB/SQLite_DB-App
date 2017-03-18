package com.example.nisandkb.sqlite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
DatabaseHelper mydb;
    EditText et1,et2,et3;
    Button bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
       et1= (EditText) findViewById(R.id.editText);
        et2= (EditText) findViewById(R.id.editText2);
        et3= (EditText) findViewById(R.id.editText3);
        bt1= (Button) findViewById(R.id.button2);
        bt2= (Button) findViewById(R.id.button);
        Adddata();
        viewAll();
    }
    public void Adddata() {
        bt1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if ((et1.getText().toString() != null)&&(et2.getText().toString() != null)&&(et3.getText().toString() != null)) {
                            boolean isinserted = mydb.insertdata(et1.getText().toString(), et2.getText().toString(), et3.getText().toString());
                            if (isinserted = true)
                                Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(MainActivity.this, "not data inserted", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Enter the values",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
    public void viewAll(){
        bt2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = mydb.getAllData();
                        if(res.getCount() == 0){
                            //
                            ShoeMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Name :"+res.getString(0)+"\n");
                            buffer.append("Phone :"+res.getString(1)+"\n");
                            buffer.append("Password :"+res.getString(2)+"\n\n");
                        }
                        //
                        ShoeMessage("Details",buffer.toString());
                    }
                }
        );
    }
    public void ShoeMessage(String title,String Message){
        AlertDialog.Builder builder = new  AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}