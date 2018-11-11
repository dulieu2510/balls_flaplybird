package com.codingwithgiap.balls_flaplybird;

import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView txcore;
    private TextView txtap;
    private ImageView img;
    private int boxY;
    private Handler handler = new Handler();
    private Timer timer  = new Timer();
    private boolean checker = false;
    private boolean checker_start = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txcore = (TextView) findViewById(R.id.textView);
        txtap = (TextView) findViewById(R.id.textView2);
        img = (ImageView) findViewById(R.id.imageView);
        txcore.setText("core");

        boxY = 500;

        Toast.makeText(this, "This is my Toast message! + "+img.getY(),
                Toast.LENGTH_LONG).show();

    }
    public void changlog(){

        if(checker == true){
            boxY -= 20;
        }else{
            boxY += 20;
        }
        img.setY(boxY);
    }
    public boolean onTouchEvent(MotionEvent  ev){
        if(checker_start==false){
            checker_start=true;
            txtap.setVisibility(View.GONE);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changlog();
                        }
                    });
                }
            },0,20);
        }else{
            if(ev.getAction()== MotionEvent.ACTION_DOWN){

                checker = true;
            }else if(ev.getAction()== MotionEvent.ACTION_UP){
                checker = false;
            }

            Toast.makeText(this, "This is my Toast message!"+img.getY(),
                    Toast.LENGTH_LONG).show();
        }

        return true;
    }
}