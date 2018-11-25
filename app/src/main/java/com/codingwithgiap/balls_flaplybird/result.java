package com.codingwithgiap.balls_flaplybird;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class result extends AppCompatActivity {
    private Button result_bt;
    private TextView result_core;
    private TextView result_hight_core;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        anhxa();
        result_core.setText("core: "+getIntent().getIntExtra("core",0)+"");
        user.showhightcore(result_hight_core);
        result_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resu1 = new Intent(getApplication(),MainActivity.class);
                startActivity(resu1);
            }
        });

    }

    private void anhxa() {
        result_core = (TextView) findViewById(R.id.result_core);
        result_hight_core = (TextView) findViewById(R.id.result_hightcore);
        result_bt = (Button) findViewById(R.id.result_tryagain);
    }
    //ghi de dis key back
    @Override
    public boolean dispatchKeyEvent(KeyEvent event){
        if(event.getAction()==KeyEvent.ACTION_DOWN){
            switch (event.getKeyCode()){
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
