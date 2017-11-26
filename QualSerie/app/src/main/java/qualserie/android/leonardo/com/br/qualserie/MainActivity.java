package qualserie.android.leonardo.com.br.qualserie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        seekBar =(SeekBar) findViewById(R.id.seekBarid);
        imageView = (ImageView) findViewById(R.id.imageViewexibicaoid);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int valorprogresso  = progress;
                if (valorprogresso == 1){
                    imageView.setImageResource(R.drawable.pouco);
                }else if(valorprogresso == 2 ){
                    imageView.setImageResource(R.drawable.medio);
                }else if (valorprogresso == 3 ){
                    imageView.setImageResource(R.drawable.muito);
                }else if (valorprogresso == 4 ){
                    imageView.setImageResource(R.drawable.susto);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
