package br.com.instagram.cursoandroid.instagram.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import br.com.instagram.cursoandroid.instagram.R;
import br.com.instagram.cursoandroid.instagram.adapter.HomeAdapter;
import br.com.instagram.cursoandroid.instagram.adapter.TabsAdapter;

import br.com.instagram.cursoandroid.instagram.fragments.Homefragment;
import br.com.instagram.cursoandroid.instagram.util.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarPrincipal;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**************************************************************************
         * configurar Toolbar
         *
         **************************************************************************/

        toolbarPrincipal = (Toolbar) findViewById(R.id.toolbar_principal);
        toolbarPrincipal.setLogo(R.drawable.instagramlogo);
        setSupportActionBar(toolbarPrincipal);


        /**************************************************************************
        * configurar abas
        *
        **************************************************************************/

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tab_main);
        viewPager = (ViewPager) findViewById(R.id.view_pager_main);

        /**************************************************************************
        * configurar adapter
        *
        **************************************************************************/
        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(tabsAdapter);
        slidingTabLayout.setCustomTabView(R.layout.tabs_view,R.id.text_item_tab);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.cinzaEscuro));
        slidingTabLayout.setViewPager(viewPager);

    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sair:
                deslogarUsuario();
                return  true;
            case R.id.action_configuracoes:
                return true;
            case R.id.action_compartilhar:
                compartilharFoto();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void compartilharFoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Testar processo de retono dos dados
        if (requestCode==1 && resultCode == RESULT_OK && data != null){



            try {




                /* teste*
                * */

                //recuperar local do recurso
                Uri localImagemSelecionada = data.getData();

                //recupera a imagem do local que foi selecionada
                ParcelFileDescriptor parcelFileDescriptor =  getContentResolver().openFileDescriptor(localImagemSelecionada, "r");

                FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;

                Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                options.inSampleSize = calculateInSampleSize(options, 300, 300);
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);

                parcelFileDescriptor.close();

                //comprimir no formato PNG
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,20,stream);

                //Cria um array de bytes da imagem
                byte[] byteArray = stream.toByteArray();



                //criar um arquivo com formato próprio do parse
                SimpleDateFormat dateFormat = new SimpleDateFormat("ddmmaaaahhmmss");
                String nomeImagem = dateFormat.format(new Date());
                ParseFile arquivoParse = new ParseFile(nomeImagem+".png",byteArray);





                //Monta objeto para salvar no parse
                ParseObject parseObject = new ParseObject("Imagem");
                parseObject.put("username",ParseUser.getCurrentUser().getUsername());
                parseObject.put("imagem",arquivoParse);

                //salvar os dados
                parseObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            Toast.makeText(getApplicationContext(),"Sua imagem foi Postada!!! ",Toast.LENGTH_LONG).show();
                            //Atualizar e listagem de itens do Fragmento HomeFragment
                            TabsAdapter adapterNovo = (TabsAdapter) viewPager.getAdapter();
                            Homefragment homeFragmentoNovo= (Homefragment) adapterNovo.getFragment(0);
                            homeFragmentoNovo.atualizaPostagens();


                        }else{//erro
                            Toast.makeText(getApplicationContext(),"Erro ao postar sua imagem - Tente novamente!",Toast.LENGTH_LONG).show();

                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deslogarUsuario(){
        ParseUser.logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
