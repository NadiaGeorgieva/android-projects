package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input,author_input,pages_input;
    Button update_button , delete_button;

    String id,title,author,pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input=findViewById(R.id.title_input2);
        author_input=findViewById(R.id.author_input2);
        pages_input=findViewById(R.id.pages_input2);
        update_button=findViewById(R.id.update_button);
        delete_button=findViewById(R.id.delete_button);
        GetAndSetData();
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db=new Database(UpdateActivity.this);
                title=title_input.getText().toString().trim();
                author=author_input.getText().toString().trim();
                pages=pages_input.getText().toString().trim();
                db.UpdateData(id,title,author,pages);
            }
        });
         delete_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 ConfirmDialog();
             }
         });


    }
    void GetAndSetData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {
            //Gating data from intent
            id=getIntent().getStringExtra("id");
            title=getIntent().getStringExtra("title");
            author=getIntent().getStringExtra("author");
            pages=getIntent().getStringExtra("pages");
            //Setting data from intent
            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);
        }
            else{
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
                }
    }
    void ConfirmDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Delete "+title+" ?");
        builder.setMessage("Are you sure you want to delete "+title+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Database db=new Database(UpdateActivity.this);
                db.DeleteData(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}