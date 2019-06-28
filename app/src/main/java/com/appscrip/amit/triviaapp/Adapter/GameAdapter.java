package com.appscrip.amit.triviaapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appscrip.amit.triviaapp.Activity.SummaryActivity;
import com.appscrip.amit.triviaapp.Model.Game;
import com.appscrip.amit.triviaapp.R;
import com.appscrip.amit.triviaapp.Utils;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewHolder> {

    private List<Game> myList;
    private Context context;

    public GameAdapter(List<Game> myList, Context context) {
        this.myList = myList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.game_history_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Game game = myList.get(i);
        myViewHolder.id.setText(MessageFormat.format("Game :{0}", game.getId()));
        myViewHolder.name.setText(MessageFormat.format("Name : {0}", game.getName().toUpperCase()));
        myViewHolder.date.setText(Utils.convertTimeStampToDate(game.getTime()));
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView date;
        public TextView name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, SummaryActivity.class);
                    intent.putExtra("game_details", (Serializable) myList.get(getAdapterPosition()));
                    intent.setAction("history_details");
                    context.startActivity(intent);
                }
            });
        }
    }
}
