package br.edu.iff.pooa20172.trabalhodb.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.iff.pooa20172.trabalhodb.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttons[] = {findViewById(R.id.btnPeca), findViewById(R.id.btnProp), findViewById(R.id.btnServ)};
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch(v.getId()){
                    case R.id.btnPeca:
                        intent = new Intent(MainActivity.this, ActivitySelectPeca.class);
                        startActivity(intent);
                        break;
                    case R.id.btnProp:
                        intent = new Intent(MainActivity.this, ActivitySelectProprietario.class);
                        startActivity(intent);
                        break;
                    case R.id.btnServ:
                        intent = new Intent(MainActivity.this, ActivitySelectServico.class);
                        startActivity(intent);
                        break;
                }
            }
        };

        for(Button button : buttons){
            button.setOnClickListener(listener);
        }
    }
}
