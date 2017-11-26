package somdosbichos.android.leonardo.com.br.somdosbichos;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity  implements  View.OnClickListener{
    private ImageView cao;
    private ImageView gato;
    private ImageView leao;
    private ImageView macaco;
    private ImageView ovelha;
    private ImageView vaca;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cao = (ImageView) findViewById(R.id.caoid);
        gato = (ImageView) findViewById(R.id.gatoid);
        leao  = (ImageView) findViewById(R.id.leaoid);
        macaco = (ImageView) findViewById(R.id.macacoid);
        ovelha  = (ImageView) findViewById(R.id.ovelhaid);
        vaca = (ImageView) findViewById(R.id.vacaid);

        cao.setOnClickListener(this);
        gato.setOnClickListener(this);
        leao.setOnClickListener(this);
        macaco.setOnClickListener(this);
        ovelha.setOnClickListener(this);
        vaca.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.caoid:
                mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.cao);
                tocarsom();
                break;
            case R.id.gatoid:
                mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.gato);
                tocarsom();
                break;
            case R.id.leaoid:
                mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.leao);
                tocarsom();
                break;
            case R.id.macacoid:
                mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.macaco);
                tocarsom();
                break;
            case R.id.ovelhaid:
                mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.ovelha);
                tocarsom();
                break;
            case R.id.vacaid:
                mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.vaca);
                tocarsom();
                break;
        }

    }


    public void tocarsom(){
        if (mediaPlayer != null){
            mediaPlayer.start();
        }

    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }

        super.onDestroy();
    }
}
