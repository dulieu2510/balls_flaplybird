package com.codingwithgiap.balls_flaplybird;

import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    //khai bao thuoc tinh cua view
    //toa do Y cua box di chuyen len xuong
    private int boxY;
    private int boxX;
    //toa do X,Y cua box chay ngang toa do goc vuong phia truoc ben tren
    private int box1X;
    private int box1Y;
    private int box2X;
    private int box2Y;
    private int box3X;
    private int box3Y;
    // dai,rong fram
    public int framheiht;
    private  int boxheight;
    public  int framwidth;
    //toa do X,Y cua box chay ngang nhung lay lai toa do trung tam
    private int  boxrunXcenter;
    private int boxrunYcenter;

    private int  box2runXcenter;
    private int box2runYcenter;

    private int  box3runXcenter;
    private int box3runYcenter;
    //khai bao CLASS tao timer request len view
    private Handler handler = new Handler();
    private Timer timer  = new Timer();
    //khai bao bien phan biet
    private boolean checker = false;
    private boolean checker_start = false;
    private box bx= new box();
    private box bx_ngang= new box();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //anh xa view
        anhxa();
       // txcore.setText("core");
        //khoi tao cac thuoc tinh view



        //bx.box_view.setY(500);
        //set bx - box di chuyen len xuong
        bx.setbox_x(0);
        bx.setbox_y(500);
        bx.setbox_boxrun_x(0);
        bx.setbox_boxrun_y(12);
        bx.setimage(img);
        //set bx_ngang - box di chuyen ngang
        bx_ngang.setbox_x(200);
        bx_ngang.setbox_y(100);
        bx_ngang.setbox_boxrun_x(13);
        bx_ngang.setbox_boxrun_y(0);
        bx_ngang.setimage(img1);
       // bx.box_view.setVisibility(View.GONE);


    }

    // ham nay duoc goi khi khong cham va cham len man hinh
    public boolean onTouchEvent(MotionEvent  ev){
        if(checker_start==false){
            //an view txtap
            checker_start=true;
            txtap.setVisibility(View.GONE);
            //anh xa layout frameheght, vi oncreate khong tao duoc
            FrameLayout fram = (FrameLayout) findViewById(R.id.fr) ;
            //lay height cua box va lay heught cua fram
            framheiht = fram.getHeight();
            framwidth = fram.getWidth();
            boxheight = bx.getimage().getHeight();
            bx.setframx(framheiht);


            bx_ngang.setframy(framwidth);
             changlog();

        }else
            //getaction luon trong trang thai thay doi, gan vao bien phan biet cheker




            if(ev.getAction() == MotionEvent.ACTION_DOWN) {

                checker = true;
            }else if(ev.getAction() == MotionEvent.ACTION_UP){

                checker = false;
            }
        bx.setcheck(checker);


        return true;
    }
    //ham chang log cau hinh view dien hoat

//changlog
    private boolean changlog(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // box di chuyen len xuong
                        bx.getimage().setY(bx.dichuyen());

                        txcore.setText(""+checker+" set "+bx.dichuyen());
                        // box di chuyen ngang
                        bx_ngang.getimage().setX(bx_ngang.dichuyen());
                    }
                });
            }
        }, 0, 20);

return true;
    }
    //khoi tao
    private void khoitao() {
        boxY = 500;
        boxX = 0;
        box1X = -80;
        box1Y = -80;
        box2X = -80;
        box2Y = -80;
        box3X = -80;
        box3Y = -80;
    }
    //anh xa
    private void anhxa() {
        txcore = (TextView) findViewById(R.id.textView);
        txtap = (TextView) findViewById(R.id.textView2);
        img = (ImageView) findViewById(R.id.imageView);
        img1 = (ImageView) findViewById(R.id.imageView1);

    }
    //box di chuyen len xuong



}