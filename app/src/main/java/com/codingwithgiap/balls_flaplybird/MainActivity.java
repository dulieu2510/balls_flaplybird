package com.codingwithgiap.balls_flaplybird;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    //khai bao cac view
    private TextView txcore;
    private TextView txtap;
    private ImageView img;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    //khai bao doi tuong timer,handler dien hoat su thay doi cua view
    private Handler handler = new Handler();
    private Timer timer  = new Timer();
    //khai bao bien phan biet
    private boolean checker = false;
    private boolean checker_start = false;
    //khai bao doi tuong nguoi dung
    user us = new user();
    // khai bao doi tuong box
    private box bx= new box();
    private box bx_ngang= new box();
    private box bx_ngang2= new box();
    private box bx_ngang3= new box();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //anh xa view
        anhxa();
        //-----------hieu ung animotion--------------------------------------------
                // Animation animation=AnimationUtils.loadAnimation(MainActivity.this,R.anim.fadein);
                // bx.box_view.startAnimation(animation);
        //-----------hieu ung animotion--------------------------------------------

        //-----------hieu ung tat mo den0--------------------------------------------
        // img.setImageResource(R.drawable.tran_on_off);
        //-----------hieu ung tat mo den0--------------------------------------------

        //-----------hieu ung tat mo den0--------------------------------------------
        // TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
        //                        -500.0f, 0.0f);
        //animation.setDuration(70);
        //img.startAnimation(animation);
        //-----------hieu ung tat mo den0--------------------------------------------

        //-----------hieu ung tat mo den0--------------------------------------------
        //-----------hieu ung tat mo den0--------------------------------------------

        //-----------demo animotion--------------------------------------------
        final Button bt= (Button) findViewById(R.id.button);
       bt.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
               //Animation ani = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate);
             // bt.startAnimation(ani); Intent resu =new Intent()
              Intent  resu = new Intent(MainActivity.this,result.class);
              startActivity(resu);

          }
      });
        //-----------demo animotion--------------------------------------------
        //----------------animotion--------------
        //set user
        us.setCore(0);
        //set bx - box di chuyen len xuong
        bx.setbox_x(200);
        bx.setbox_y(800);
        bx.setbox_boxrun_x(0);
        bx.setbox_boxrun_y(20);
        bx.setimage(img);
        bx.setObjec(0);
        //set bx_ngang - box di chuyen ngang
        bx_ngang.setbox_x(-80);
        bx_ngang.setbox_y(-80);
        bx_ngang.setbox_boxrun_x(13);
        bx_ngang.setbox_boxrun_y(0);
        bx_ngang.setimage(img1);
        bx_ngang.setObjec(1);
        //set bx_ngang - box di chuyen ngang 2
        bx_ngang2.setbox_x(-80);
        bx_ngang2.setbox_y(-80);
        bx_ngang2.setbox_boxrun_x(13);
        bx_ngang2.setbox_boxrun_y(0);
        bx_ngang2.setimage(img2);
        bx_ngang2.setObjec(2);
        //set bx_ngang - box di chuyen ngang 3
        bx_ngang3.setbox_x(-80);
        bx_ngang3.setbox_y(-80);
        bx_ngang3.setbox_boxrun_x(13);
        bx_ngang3.setbox_boxrun_y(0);
        bx_ngang3.setimage(img3);
        bx_ngang3.setObjec(3);
    }
    // ham nay duoc goi khi khong cham va cham len man hinh
    public boolean onTouchEvent(MotionEvent  ev){
        if(checker_start==false){
            //an view txtap
            checker_start=true;
            txtap.setVisibility(View.GONE);
            //anh xa layout frameheght, vi oncreate khong tao duoc
            FrameLayout fram = (FrameLayout) findViewById(R.id.fr) ;
            //lay height fram cho bx
            bx.setframx(fram.getHeight());
            bx.setframy( fram.getWidth());
            //lay height fram cho bx_ngang
            bx_ngang.setframy( fram.getWidth());
            bx_ngang.setframx(fram.getHeight());
            bx_ngang2.setframy( fram.getWidth());
            bx_ngang2.setframx(fram.getHeight());
            bx_ngang3.setframy( fram.getWidth());
            bx_ngang3.setframx(fram.getHeight());
            changlog();
        }else
            //get action luon trong trang thai thay doi, gan vao phan biet cheker

            if(ev.getAction() == MotionEvent.ACTION_DOWN) {

                us.setTouch_checker(true);

            }else if(ev.getAction() == MotionEvent.ACTION_UP){
                us.setTouch_checker(false);
            }
        bx.setcheck(us.isTouch_checker());


        return true;
    }
    //ham chang log dien hoat thay doi cua view
    private boolean changlog(){

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // box di chuyen len xuong
                        bx.review();

                        // box di chuyen ngang
                        bx_ngang.review();
                        bx_ngang2.review();
                        bx_ngang3.review();
                        //box va cham
                        bx.vacham(bx.getBox1center_x(),bx.getBox1center_y(),bx.getObjec1());
                        bx_ngang.vacham(bx.getBox1center_x(),bx.getBox1center_y(),bx.getObjec1());
                        bx_ngang2.vacham(bx.getBox1center_x(),bx.getBox1center_y(),bx.getObjec1());
                        bx_ngang3.vacham(bx.getBox1center_x(),bx.getBox1center_y(),bx.getObjec1());
                        // tinh diem core
                        // us.setCore();
                        // tinh diem core
                        txcore.setText( "core : "+us.getCore() +" ob "+bx.getObjec1()+"positon "+bx.getimage().getY());

                    }
                });
            }
        }, 0,20);
        return true;
    }
    //anh xa
    private void anhxa() {
        txcore = (TextView) findViewById(R.id.textView);
        txtap = (TextView) findViewById(R.id.textView2);
        img = (ImageView) findViewById(R.id.imageView);
        img1 = (ImageView) findViewById(R.id.imageView1);
        img2 = (ImageView) findViewById(R.id.imageView2);
        img3 = (ImageView) findViewById(R.id.imageView3);

    }

}