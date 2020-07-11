package com.example.connect_me;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;
    int gameState[]={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1500);
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(activePlayer==0){
            counter.setImageResource(R.drawable.yellow);
            gameState[tappedCounter]=0;
            activePlayer=1;
        }
        else{
            counter.setImageResource(R.drawable.red);
            gameState[tappedCounter]=1;
            activePlayer=0;
        }

        for(int[] winningPosition : winningPositions){
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[1]]==gameState[winningPosition[2]]&&gameState[winningPosition[0]]!=-1){
                Toast.makeText(this, activePlayer==1?"Yellow has won!":"Red has won!", Toast.LENGTH_SHORT).show();
            }
        }
        counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
