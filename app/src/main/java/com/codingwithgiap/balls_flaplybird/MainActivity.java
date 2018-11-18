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
    //toa do X,Y cua box chay ngang toa do goc vuong phia truoc ben tren
    private int box1X;
    private int box1Y;
    private int box2X;
    private int box2Y;
    private int box3X;
    private int box3Y;
    // dai,rong fram
    private int framheiht;
    private  int boxheight;
    private  int framwidth;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //anh xa view
        anhxa();
        txcore.setText("core");
        //khoi tao cac thuoc tinh view
        khoitao();
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
            boxheight = img.getHeight();
            //ham ghi de,luon chay voi thoi gian tu 0-20 minis
            //di chuyen cac box va xu ly va cham
            changlog();

        }else
            //getaction luon trong trang thai thay doi, gan vao bien phan biet cheker
            if(ev.getAction() == MotionEvent.ACTION_DOWN) {

                checker = true;
            }else if(ev.getAction() == MotionEvent.ACTION_UP){

                checker = false;
            }



        return true;
    }
    //ham chang log cau hinh view dien hoat
    private void changlog() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // box di chuyen len xuong
                        boxlenxuong();

                        // box di chuyen trai phai
                        boxtraiphai();
                        // box cham box
                        vacham();
                        txcore.setText("core");


                    }
                });
            }
        }, 0, 20);
    }

    //khoi tao
    private void khoitao() {
        boxY = 500;
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
        img2 = (ImageView) findViewById(R.id.imageView2);
        img3 = (ImageView) findViewById(R.id.imageView3);
    }
    //box di chuyen len xuong
    private void boxlenxuong(){
        if (checker == true) {
            boxY -= 20;
        } else {
            boxY += 20;
        }
        // box di chuyen trong man hinh
        if (boxY < 0) {
            boxY = 0;
        }
        if (boxY > framheiht - boxheight) {
            boxY = framheiht - boxheight;
        }
        img.setY(boxY);
    }
    // box di chuyen trai phai
    private void boxtraiphai(){
        box1X -= 12;
        box2X -= 12;
        box3X -= 12;
        //box 1
        if (box1X < 0) {
            box1X = framheiht;
            box1Y = (int) Math.floor(Math.random() * (framheiht - img1.getHeight()));
            img1.setY(box1Y);
        }
        img1.setX(box1X);
        //box2
        if (box2X < 0) {
            box2X = framheiht;
            box2Y = (int) Math.floor(Math.random() * (framheiht - img2.getHeight()));
            img2.setY(box2Y);
        }
        img2.setX(box2X);
        //box3
        if (box3X < 0) {
            box3X = framheiht;
            box3Y = (int) Math.floor(Math.random() * (framheiht - img3.getHeight()));
            img1.setY(box1Y);
        }
        img3.setX(box3X);
    }
    // box va cham
    private void vacham(){
        //box tam giac
        //vi tri cua box duoc lay tren goc hinh vuong phia truoc va ben tren
        //chuyen vi tri box ve trung tam box
        boxrunXcenter = (int) (img1.getX() + img1.getWidth() / 2);
        boxrunYcenter = (int) img1.getY() + img1.getHeight() / 2;
        // 0<=boxrunx<=box.width
        //  box.getY<=boxruny<=box.getY+box.height
        if ((boxrunYcenter <= (img.getY() + img.getHeight())) && (boxrunYcenter >= img.getY()) && (boxrunXcenter >= 0) && (boxrunXcenter <= img.getWidth())) {
            box1X = framheiht;

            img.setImageResource(R.drawable.tamgiac);

            //img.refreshDrawableState();
        }
        //box vuong
        //vi tri cua box duoc lay tren goc hinh vuong phia truoc va ben tren
        //chuyen vi tri box ve trung tam box
        box2runXcenter = (int) (img2.getX() + img2.getWidth() / 2);
        box2runYcenter = (int) img2.getY() + img2.getHeight() / 2;
        // 0<=boxrunx<=box.width
        //  box.getY<=boxruny<=box.getY+box.height
        if ((box2runYcenter <= (img.getY() + img.getHeight())) && (box2runYcenter >= img.getY()) && (box2runXcenter >= 0) && (box2runXcenter <= img.getWidth())) {
            box2X = framheiht;

            img.setImageResource(R.drawable.vuong);

            //img.refreshDrawableState();
        }
        //box tron
        //vi tri cua box duoc lay tren goc hinh vuong phia truoc va ben tren
        //chuyen vi tri box ve trung tam box
        box3runXcenter = (int) (img3.getX() + img3.getWidth() / 2);
        box3runYcenter = (int) img3.getY() + img3.getHeight() / 2;
        // 0<=boxrunx<=box.width
        //  box.getY<=boxruny<=box.getY+box.height
        if ((box3runYcenter <= (img.getY() + img.getHeight())) && (box3runYcenter >= img.getY()) && (box3runXcenter >= 0) && (box3runXcenter <= img.getWidth())) {
            box3X = framheiht;

            img.setImageResource(R.drawable.tron);

            //img.refreshDrawableState();
        }
    }


}