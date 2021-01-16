package com.example.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.http.SslCertificate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import javax.xml.namespace.QName;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

   private Context context;
   Activity activity ;
   private ArrayList book_id,book_title,book_author,book_pages;

   int position;
    CustomAdapter(Activity activity,Context context,ArrayList book_id,ArrayList book_title, ArrayList book_author, ArrayList book_pages){
                    this.activity=activity;
                    this.context=context;
                    this.book_id=book_id;
                    this.book_title=book_title;
                    this.book_author=book_author;
                    this.book_pages=book_pages;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
       View view= inflater.inflate(R.layout.table_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, final int position) {


        holder.book_id.setText(String.valueOf(book_id.get(position)));
        holder.book_title.setText(String.valueOf(book_title.get(position)));
        holder.book_author.setText(String.valueOf(book_author.get(position)));
        holder.book_pages.setText(String.valueOf(book_pages.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent update=new Intent(context,UpdateActivity.class);
                update.putExtra("id",String.valueOf(book_id.get(position)));
                update.putExtra("title",String.valueOf(book_title.get(position)));
                update.putExtra("author",String.valueOf(book_author.get(position)));
                update.putExtra("pages",String.valueOf(book_pages.get(position)));
                activity.startActivityForResult(update,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  book_id,book_title,book_author,book_pages;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id=itemView.findViewById(R.id.book_id);
            book_title=itemView.findViewById(R.id.book_title);
            book_author=itemView.findViewById(R.id.book_author);
            book_pages=itemView.findViewById(R.id.book_pages);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
