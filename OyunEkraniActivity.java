package com.example.oyunalmas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class OyunEkraniActivity extends AppCompatActivity {
    private ConstraintLayout cl;
    private TextView textViewSkor;
    private  TextView textViewOyunaBasla;
    private ImageView sarıdaire;
    private ImageView kırmıziÜçgen;
    private  ImageView siyahkare;
    private ImageView anakarakter;



    //pozisyonlar
    private int anakarakterX;
    private  int anakarakterY;
    private int sarıdaireX;
    private  int sarıdaireY;
    private int kırmıziÜçgenX;
    private  int kırmıziÜçgenY;
    private int siyahkareX;
    private  int siyahkareY;
    //boyutlara
    private int ekranGenıslıgı;
    private int ekranyukseklığı;
    private int anakaraktergenişliği;
    private int anakarakteryukseklıgı;
    //hızlar
    private int anakarakterHiz;
    private int saridaireHiz;
    private int kırmızıüçgenHiz;
    private int siyahkareHiz;

    //KONTROLLER
    private boolean dokunmaKontrol=false;
    private boolean başlangıckontrol=false;
    private Timer timer=new Timer();
    private Handler handler=new Handler();
    private int skor=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun_ekrani);

        cl=findViewById(R.id.cl);
        textViewSkor=findViewById(R.id.textViewSkor);
        textViewOyunaBasla=findViewById(R.id.textViewOyunabasla);
        anakarakter=findViewById(R.id.anakarakter);
       sarıdaire=findViewById(R.id.sarıdaire);
        siyahkare=findViewById(R.id.siyahkare);
        kırmıziÜçgen=findViewById(R.id.kırmıziÜçgen);
        //cisimleri ekranın dışına çıkarma
       siyahkare.setX(-80);
        siyahkare.setY(-80);
        sarıdaire.setX(-80);
        sarıdaire.setY(-80);
        kırmıziÜçgen.setX(-80);
        kırmıziÜçgen.setY(-80);

      cl.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View view, MotionEvent motionEvent) {

              if (başlangıckontrol){
                  if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                      Log.e("MotionEvent","ekranı dokundu");
                      dokunmaKontrol=true;

                  } if (motionEvent.getAction()==MotionEvent.ACTION_UP){
                      Log.e("MotionEvent","ekranı bıraktı");
                      dokunmaKontrol=false;
                  }

              }else{
                  başlangıckontrol=true;
                  textViewOyunaBasla.setVisibility(view.INVISIBLE);
                  anakarakterX=(int) anakarakter.getX();
                  anakarakterY=(int) anakarakter.getY();
                  anakaraktergenişliği=anakarakter.getWidth();
                  anakarakteryukseklıgı=anakarakter.getHeight();
                  ekranGenıslıgı=cl.getWidth();
                  ekranyukseklığı=cl.getHeight();

                  timer.schedule(new TimerTask() {
                      @Override
                      public void run() {
                          handler.post(new Runnable() {
                              @Override
                              public void run() {
                                  anakarakterHareketettrme();
                                  cisimlerinhareketettır();
                                  çarpısmakontrol();


                              }
                          });

                      }
                  },0,20);

              }







              return true;
          }
      });
    }
    public void anakarakterHareketettrme(){
           anakarakterHiz=Math.round(ekranyukseklığı/60);//1280 /60=20hız

        if(dokunmaKontrol){
            anakarakterY-=anakarakterHiz;

        }else {
            anakarakterY += anakarakterHiz;
        }
        if (anakarakterY<=0){
            anakarakterY=0;
        }
        if (anakarakterY>=ekranyukseklığı-anakarakteryukseklıgı){
            anakarakterY=ekranyukseklığı-anakarakteryukseklıgı;
        }
        anakarakter.setY(anakarakterY);
    }
    public  void  cisimlerinhareketettır(){
        saridaireHiz=Math.round(ekranGenıslıgı/60);
        kırmızıüçgenHiz=Math.round(ekranGenıslıgı/45);
        siyahkareHiz=Math.round(ekranGenıslıgı/60);


       siyahkareX-=siyahkareHiz;
       if (siyahkareX<0){
           siyahkareX=ekranGenıslıgı+20;
           siyahkareY=(int) Math.floor(Math.random()*ekranyukseklığı);

       }
       siyahkare.setX(siyahkareX);
       siyahkare.setY(siyahkareY);

        sarıdaireX-=saridaireHiz;
        if (sarıdaireX<0){
            sarıdaireX=ekranGenıslıgı+20;
            sarıdaireY=(int) Math.floor(Math.random()*ekranyukseklığı);

        }
        sarıdaire.setX(sarıdaireX);
        sarıdaire.setY(sarıdaireY);

        kırmıziÜçgenX-=kırmızıüçgenHiz;
        if (kırmıziÜçgenX<0){
            kırmıziÜçgenX=ekranGenıslıgı+20;
            kırmıziÜçgenY=(int) Math.floor(Math.random()*ekranyukseklığı);

        }
        kırmıziÜçgen.setX(kırmıziÜçgenX);
        kırmıziÜçgen.setY(kırmıziÜçgenY);

    }
    public void  çarpısmakontrol(){
        int sarıdairemerkezX=sarıdaireX+sarıdaire.getWidth()/2;
        int sarıdairemerkezY=sarıdaireY+sarıdaire.getHeight()/2;

        if (0<=sarıdairemerkezX && sarıdairemerkezX<=anakaraktergenişliği &&
                anakarakterY <= sarıdairemerkezY && sarıdairemerkezY <=anakarakterY+anakarakteryukseklıgı){
            skor+=20;
            sarıdaireX=-10;
        }
        int kirmiziüçgenmerkezX=kırmıziÜçgenX+kırmıziÜçgen.getWidth()/2;
        int kirmiziüçgenmerkezY=kırmıziÜçgenY+kırmıziÜçgen.getHeight()/2;

        if (0<=kirmiziüçgenmerkezX && kirmiziüçgenmerkezX<=anakaraktergenişliği &&
                anakarakterY <= kirmiziüçgenmerkezY && kirmiziüçgenmerkezY <=anakarakterY+anakarakteryukseklıgı){
            skor+=50;
            kırmıziÜçgenX=-10;
        }
        int siyahkareMerkezX=siyahkareX+siyahkare.getWidth()/2;
        int siyahkareMerkezY=siyahkareY+siyahkare.getHeight()/2;

        if (0<=siyahkareMerkezX && siyahkareMerkezX<=anakaraktergenişliği &&
                anakarakterY <= siyahkareMerkezY && siyahkareMerkezY <=anakarakterY+anakarakteryukseklıgı){

            siyahkareX=-10;
            //timer durdur.
            timer.cancel();
            timer=null;
           Intent intent = new Intent(OyunEkraniActivity.this,SonucEkraniActivity2.class);
            intent.putExtra("skor",skor);
            startActivity(intent);

        }
        textViewSkor.setText(String.valueOf(skor));
    }
}