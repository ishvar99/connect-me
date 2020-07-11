package com.example.connect_me;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;
    boolean gameActive=true;
    int gameState[]={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1500);
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==-1 && gameActive) {
            gameState[tappedCounter] = activePlayer;
        }
        if(activePlayer==0){
            counter.setImageResource(R.drawable.yellow);
            activePlayer=1;
        }
        else{
            counter.setImageResource(R.drawable.red);
            activePlayer=0;
        }

        for(int[] winningPosition : winningPositions){
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[1]]==gameState[winningPosition[2]]&&gameState[winningPosition[0]]!=-1){
                gameActive=false;
                TextView winnerText =findViewById(R.id.winnerText);
                Button playAgain=findViewById(R.id.button);
                winnerText.setText(activePlayer==1?"Yellow has won!":"Red has won!");
                winnerText.setVisibility(View.VISIBLE);
                playAgain.setVisibility(View.VISIBLE);
            }
        }
        counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
    }
    public void resetGame(View view){
        GridLayout gridLayout= findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView)gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
        }
        gameActive=true;
        Arrays.fill(gameState, -1);
        activePlayer=0;
        TextView winnerText =findViewById(R.id.winnerText);
        Button playAgain=findViewById(R.id.button);
        winnerText.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
