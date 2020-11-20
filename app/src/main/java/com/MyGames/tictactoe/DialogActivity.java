package com.MyGames.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);

        ImageView IV = findViewById(R.id.IV);
        TextView TV = findViewById(R.id.TV);
        Button Btn = findViewById(R.id.Btn);

        int status = getIntent().getIntExtra("status", 0);
        this.setFinishOnTouchOutside(false);

        if (status == 0){
            IV.setImageResource(R.drawable.clapping);
            TV.setText("You Played Well");
            TV.setTextColor(getResources().getColor(R.color.draw));
            Btn.getBackground().setTint(getResources().getColor(R.color.draw));
        }else if (status == -1){
            IV.setImageResource(R.drawable.loser);
            TV.setText("You Lose");
            TV.setTextColor(getResources().getColor(R.color.lose));
            Btn.getBackground().setTint(getResources().getColor(R.color.lose));
        }else if (status == 2){
            IV.setImageResource(R.drawable.remove);
            TV.setText("Do you really want to quit?");
            TV.setTextColor(getResources().getColor(R.color.lose));
            Btn.getBackground().setTint(getResources().getColor(R.color.lose));
            this.setFinishOnTouchOutside(true);
        }else if (status == 1){
            IV.setImageResource(R.drawable.trophy);
            TV.setText("You Won");
            TV.setTextColor(getResources().getColor(R.color.win));
            Btn.getBackground().setTint(getResources().getColor(R.color.win));
        }

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DialogActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}