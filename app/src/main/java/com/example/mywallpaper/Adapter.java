package com.example.mywallpaper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context context;
    ArrayList<ImageModel>wallpaperlist;

    public Adapter(Context context, ArrayList<ImageModel> wallpaperlist) {
        this.context = context;
        this.wallpaperlist = wallpaperlist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_layout,null,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.Text.setMovementMethod(LinkMovementMethod.getInstance());
                holder.Text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserintent =new Intent(Intent.ACTION_VIEW);
                        browserintent.setData(Uri.parse("https://www.pexels.com/"));
                        browserintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(browserintent);

                    }
                });


            }
        });
        Glide.with(context).load(wallpaperlist.get(position).getSrc().getPortrait()).into(holder.Image);
        holder.Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,setwallpaper.class);
                intent.putExtra("image",wallpaperlist.get(position).getSrc().getPortrait());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wallpaperlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView Image;
        TextView Text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Image=itemView.findViewById(R.id.image2);
            Text=itemView.findViewById(R.id.textview2);

        }
    }
}
