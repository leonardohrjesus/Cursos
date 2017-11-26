package br.com.instagram.cursoandroid.instagram;

/**
 * Created by Amministratore on 18/11/2017.
 */
import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StarterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Habilite armazenamento local.
        Parse.enableLocalDatastore(this);

        // Codigo de configuração do App
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("5s0RwjcXo2KjCY9xDtzrV1xneT9zBCyRbroPwlSp")
                .clientKey("lfVUIcLAtqEmKoMv7h5DXcMLEt6v2BnlJHbAnsd0")
                .server("https://parseapi.back4app.com/")
                .build()
        );




         // ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        defaultACL.setPublicReadAccess(true);
        //ParseACL.setDefaultACL(defaultACL, true);
    }
}
