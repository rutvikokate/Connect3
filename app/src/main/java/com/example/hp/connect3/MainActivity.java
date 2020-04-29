package com.example.hp.connect3;

import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0:yellow 1:red  2:empty
    int active=0;
    int[] state={2,2,2,2,2,2,2,2,2,2};
    int[][] winpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};
    boolean gameactive=true;

    public void dropin(View v){
        ImageView counter=(ImageView) v;
        TextView res=(TextView)findViewById(R.id.res);
        TextView text=(TextView)findViewById(R.id.text);
        Log.i("info",counter.getTag().toString());

        int tapped=Integer.parseInt(counter.getTag().toString());
        if(state[tapped]==2 && gameactive) {
            state[tapped] = active;
            counter.setTranslationY(-1500);
            if (active == 0) {
                counter.setImageResource(R.drawable.red);
                active = 1;
            } else {
                counter.setImageResource(R.drawable.yellow);
                active = 0;
            }
            counter.animate().translationYBy(1500).alpha(1).setDuration(300);

            for (int[] winningpos : winpos) {
                if (state[winningpos[0]] == state[winningpos[1]] && state[winningpos[1]] == state[winningpos[2]] && state[winningpos[0]] != 2) {
                    gameactive=false;
                    text.animate().translationXBy(-100).setDuration(300);
                    Button playagain=(Button)findViewById(R.id.button);
                    playagain.setVisibility(View.VISIBLE);
                    if (active == 1) {
                        res.setTranslationY(1500);
                        res.setTextColor(getResources().getColor(R.color.red));
                        res.setText("Red won!");

                        res.animate().translationYBy(-1500).setDuration(300);
                    } else if(active==0){

                        res.setTranslationY(1500);
                        res.setTextColor(getResources().getColor(R.color.yellow));
                        res.setText("Yellow won!");
                        res.animate().translationYBy(-1500).setDuration(300);
                    }
                }

            }

        }
        if(state[tapped]!=2 && active!=1 && active!=0){
            res.setText("draw");
            res.setTranslationY(1500);
            res.animate().translationYBy(-1500).setDuration(300);
        }


    }

    public void playagain(View v){
        Button playagain=(Button)findViewById(R.id.button);
        TextView text=(TextView)findViewById(R.id.text);
        TextView res=(TextView)findViewById(R.id.res);


        res.setText(" ");
        playagain.setVisibility(View.INVISIBLE);
        text.animate().translationXBy(100).setDuration(300);
        GridLayout gridLayout=(GridLayout)findViewById(R.id.grid);
        for(int i=0;i<gridLayout.getChildCount();i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);

        }

        for(int i=0;i<state.length;i++) {
            state[i]=2;
        }
        active=0;

        gameactive=true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final ImageView splash=(ImageView)findViewById(R.id.splash);
        splash.animate().scaleX(1).scaleY(1).setDuration(500);

        final GridLayout grid=(GridLayout)findViewById(R.id.grid);
        final TextView text=(TextView)findViewById(R.id.text);
        final Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                splash.animate().alpha(0).setDuration(1000);
                grid.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
            }
        },2000);


    }
}
