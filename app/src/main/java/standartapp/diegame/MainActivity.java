package standartapp.diegame;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

//bruger button og textview, samt scoreview
    TextView scoreView;
    TextView rollResult;
    Button rollButton;
    // holder øje med score;
    int score;

    //random number generator
    Random rand;

    //holde på die value
    int die1;
    int die2;
    int die3;

    // array list til vores die

    ArrayList<Integer> dice;

    // array list til billeder

    ArrayList<ImageView> dImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //set start scoren;
        score = 0;

        //link mellem widgets og java

        rollResult =(TextView) findViewById(R.id.rollResult);
        rollButton = (Button) findViewById(R.id.rollButton);
        scoreView = (TextView) findViewById(R.id.scoreTxt);

        //link mellem iamgeviews og java

        ImageView die1Image = (ImageView) findViewById(R.id.die1Image);
        ImageView die2Image = (ImageView) findViewById(R.id.die2Image);
        ImageView die3Image = (ImageView) findViewById(R.id.die3Image);

        //initialize die images

        dImageView = new ArrayList<ImageView>();
        dImageView.add(die1Image);
        dImageView.add(die2Image);
        dImageView.add(die3Image);

        //initialize rand
        rand = new Random();

        // initialize dice

        dice = new ArrayList<Integer>();

        //lav en hilsen;

        Toast.makeText(getApplicationContext(), "Dice game", Toast.LENGTH_SHORT).show();
    }

    public void rollDice(View v){
        //rollResult.setText("Clicked!");
Toast.makeText(getApplicationContext(),"Clicked!",Toast.LENGTH_SHORT).show();

       die1 = rand.nextInt(6)+1;
        die2 = rand.nextInt(6)+1;
        die3 = rand.nextInt(6)+1;
// tøm arrayliste for gamle tal, og indsæt nye
        dice.clear();

        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        for(int i=0; i < 3; i++) {
            String imageName = "die" + dice.get(i) + ".png";

            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream, null);
                dImageView.get(i).setImageDrawable(d);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            String txt ;

        if(die1 == die2 && die1 == die3){
            int scoreNow;
            scoreNow= die1 * 100;
            txt = "Tripple! 3x"+die1+" your score: "+scoreNow+"-points.";
            score += scoreNow;
        }else if(die1==die2||die2==die3||die3==die1){
            txt = "doubles: 50-points.";
            score += 50;

        } else {
            txt = "try again";
        }
            rollResult.setText(txt);
            scoreView.setText("score: "+ score);



     //   int number = rand.nextInt(6)+1;
     //   String txt = "tallet: "+number;
     //   Toast.makeText(getApplicationContext(),txt,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
