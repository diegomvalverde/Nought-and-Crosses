package com.example.curso.gato;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private boolean is_facebook_turn = true;
    private int[] posiciones = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

    public void restartGame(View view)
    {

        // Restart the list of movements
        for (int i = 0; i < posiciones.length ; i++) {
            posiciones[i] = -1;
        }

        // Delete all the icons
        ImageView imageView = findViewById(R.id.imageView00);
        imageView.setImageDrawable(null);
        imageView = findViewById(R.id.imageView01);
        imageView.setImageDrawable(null);
        imageView = findViewById(R.id.imageView02);
        imageView.setImageDrawable(null);
        imageView = findViewById(R.id.imageView03);
        imageView.setImageDrawable(null);
        imageView = findViewById(R.id.imageView04);
        imageView.setImageDrawable(null);
        imageView = findViewById(R.id.imageView05);
        imageView.setImageDrawable(null);
        imageView = findViewById(R.id.imageView06);
        imageView.setImageDrawable(null);
        imageView = findViewById(R.id.imageView07);
        imageView.setImageDrawable(null);
        imageView = findViewById(R.id.imageView08);
        imageView.setImageDrawable(null);

        View view1 = findViewById(R.id.relEndGame);
        view1.setVisibility(View.INVISIBLE);

    }


    // Check is a player won the game
    /*
    return 1 if the player 1 (facebook) won
    2 if Twitter won
    3 if no one won
    0 if another movement is posible
     */
    private int someoneHasWon()
    {
        for (int i = 0; i < 3; i++)
        {
            if(posiciones[i] != -1 && posiciones[i] == posiciones[i+3] && posiciones[i] == posiciones[i+6])
            {
                return posiciones[i];
            }
            else if (posiciones[i] != -1 && i == 0 && posiciones[i] == posiciones[i+4] && posiciones[i] == posiciones[i+8])
            {
                return posiciones[0];
            }
            else if (posiciones[i] != -1 && i == 2 && posiciones[i] == posiciones[i+4] && posiciones[i] == posiciones[i+2])
            {
                return posiciones[i];
            }
        }

        for (int i = 0; i <= 6; i+=3)
        {
            if(posiciones[i] != -1 && posiciones[i] == posiciones[i+1] && posiciones[i] == posiciones[i+2])
            {
                return posiciones[i];
            }

        }

        // Check is another posible movement or not
        boolean end = true;
        for (int posicione : posiciones) {
            if (posicione == -1) {
                end = false;
            }
        }
        if (end)
        {
            return 3;
        }
        else
        {
            return 0;
        }
    }

    public void clickSpot(View view)
    {
        ImageView spot = (ImageView) view;
        int pos = Integer.parseInt(spot.getTag().toString());
        Log.d("Pos", spot.getTag().toString());

        if (posiciones[pos] == -1)
        {
            if (this.is_facebook_turn)
            {
                spot.setImageResource(R.drawable.facebook);
                posiciones[pos] = 1;
            }
            else
            {
                spot.setImageResource(R.drawable.twitter);
                posiciones[pos] = 2;
            }
            spot.setAlpha(1.0f); // Para que se vea la imagen y no salga transparente

            int game = someoneHasWon();
            if(game == 1)
            {
                TextView textView = findViewById(R.id.txtEndGame);
                textView.setText("El ganador es Facebook");
                View view1 = findViewById(R.id.relEndGame);
                view1.setVisibility(View.VISIBLE);

            }
            else if(game == 2)
            {
                TextView textView = findViewById(R.id.txtEndGame);
                textView.setText("El ganador es Twitter");
                View view1 = findViewById(R.id.relEndGame);
                view1.setVisibility(View.VISIBLE);
            }
            else if(game == 3)
            {
                TextView textView = findViewById(R.id.txtEndGame);
                textView.setText("Empate");
                View view1 = findViewById(R.id.relEndGame);
                view1.setVisibility(View.VISIBLE);
            }
            this.is_facebook_turn = !this.is_facebook_turn;

            turn();

        }

    }

    // Upadate a label with the actual player movement
    private void turn()
    {
        TextView textView = findViewById(R.id.textView);
        if (is_facebook_turn)
        {
            textView.setText("Es el turno de Facebook");
        }
        else
        {
            textView.setText("Es el turno de Twitter");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        turn();

    }
}
