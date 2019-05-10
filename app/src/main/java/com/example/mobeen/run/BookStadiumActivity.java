package com.example.mobeen.run;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobeen.run.Models.Ground;

import java.util.ArrayList;
import java.util.List;

public class BookStadiumActivity extends AppCompatActivity {

    Button booked;
    TextView name;
    TextView price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_statdium);

        RecyclerView recyclerView = findViewById(R.id.bookingList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerView.setAdapter(new BookingAdapter(getApplicationContext(), MapsActivity.grounds));

    }

}

class BookingHolder extends RecyclerView.ViewHolder{

    TextView name;
    TextView price;
    View background;

    Button booked;

    public BookingHolder(@NonNull View itemView) {
        super(itemView);


        background = itemView.findViewById(R.id.background);
        name = itemView.findViewById(R.id.s_name);
        price = itemView.findViewById(R.id.s_price);
        booked = itemView.findViewById(R.id.booked_btn);

    }
}

class BookingAdapter extends RecyclerView.Adapter<BookingHolder>{


    Context context;
    List<Ground> grounds;

    BookingAdapter(Context context, List<Ground> groundList)
    {
        this.context = context;
        grounds = groundList;
    }

    @NonNull
    @Override
    public BookingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_stadium_item, viewGroup, false);
        return new BookingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingHolder bookingHolder, final int i) {

        bookingHolder.name.setText(grounds.get(i).getName());
        bookingHolder.price.setText(grounds.get(i).getPrice());

        bookingHolder.booked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grounds.get(i).isBooked())
                {
                    grounds.get(i).setBooked(false);
                }
                else
                {
                    grounds.get(i).setBooked(true);
                }
            }
        });

        if(grounds.get(i).isBooked())
        {
            bookingHolder.background.setBackgroundColor(Color.GREEN);
        }
        else
        {
            bookingHolder.background.setBackgroundColor(Color.WHITE);
        }

    }

    @Override
    public int getItemCount() {
        return grounds.size();
    }
}
