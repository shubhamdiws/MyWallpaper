package com.example.mywallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageModel>modelClasslist;
    private RecyclerView recyclerView;
    Adapter adapter;
    CardView mnature,mbus,mtrain,mtrending,mcar;
    EditText editText;
    ImageButton search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerView=findViewById(R.id.rec);
        mcar=findViewById(R.id.car);

        mnature=findViewById(R.id.naturecard);
        mbus=findViewById(R.id.buscard);
        mtrain=findViewById(R.id.Train);
        mtrending=findViewById(R.id.trendingCard);
        editText=findViewById(R.id.editSearch1);
        search=findViewById(R.id.Search1);



        modelClasslist=new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        adapter=new Adapter(getApplicationContext(),modelClasslist);
        recyclerView.setAdapter(adapter);

        findphotos();

        mnature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="nature";
                getsearchimage(query);
            }
        });
        mcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="car";
                getsearchimage(query);
            }
        });
        mtrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="train";
                getsearchimage(query);
            }
        });
        mbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="bus";
                getsearchimage(query);
            }
        });
        mtrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findphotos();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query= editText.getText().toString().trim().toLowerCase();
                if (query.isEmpty()){


                    Toast.makeText(MainActivity.this, "Enter something", Toast.LENGTH_SHORT).show();
                }else {
                    getsearchimage(query);
                }
            }
        });








    }

    private void getsearchimage(String query) {
        ApiUtilities.getApiInterface().getSeachImage(query,1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClasslist.clear();
                if (response.isSuccessful()){
                    modelClasslist.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(MainActivity.this, "Not able to get", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }

    private void findphotos() {
        ApiUtilities.getApiInterface().getImage(1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {


                if (response.isSuccessful()){
                    modelClasslist.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(MainActivity.this, "Not able to get", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }
}