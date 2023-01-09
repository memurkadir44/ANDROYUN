package com.example.oyunalmas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SonucEkraniActivity2 extends AppCompatActivity {
private TextView textViewToplamSkor;
private  TextView textViewEnyuksekskor;
private Button buttonTekrarDene;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc_ekrani2);
        textViewToplamSkor=findViewById(R.id.textViewToplamSkor);
        textViewToplamSkor=findViewById(R.id.textViewEnyuksekskor);
        buttonTekrarDene= findViewById(R.id.buttonTekrarDene);
        int skor=getIntent().getIntExtra("skor",0);
        textViewToplamSkor.setText(String.valueOf(skor));
        SharedPreferences sp = getSharedPreferences("sonuç", Context.MODE_PRIVATE);
        int enYuksekSkor = sp.getInt("enYüksekSkor",0);

        if (skor>enYuksekSkor){

            SharedPreferences.Editor editor= sp.edit();
            editor.putInt("enYüksekSkor",skor);
            editor.commit();
            textViewEnyuksekskor.setText(String.valueOf(skor));
        }else {

            textViewEnyuksekskor.setText(String.valueOf(enYuksekSkor));
        }
        buttonTekrarDene.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                startActivity(new Intent(SonucEkraniActivity2.this,MainActivity.class));
                finish();
            }
        });
    }
}