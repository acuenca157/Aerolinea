package org.izv.ad.acl.aerolinea2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.izv.ad.acl.aerolinea2.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.top_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.option_travels) {
            gotoWeb("https://www.norwegian.com/es/destinos/");
            return true;
        }
        if (id == R.id.option_embarque){
            gotoWeb("https://www.norwegian.com/es/informacion-sobre-el-viaje/facturacion-y-embarque/");
            return true;
        }
        if (id == R.id.option_embarque){
            gotoWeb("https://www.norwegian.com/es/informacion-sobre-el-viaje/facturacion-y-embarque/");
            return true;
        }
        if (id == R.id.option_infovuelo){
            gotoWeb("https://www.norwegian.com/es/estado-de-vuelos/updates/?promo_name=NAS-corona-topbar&promo_id=info-hub-from-topbar&promo_creative=ES-top-bar/");
            return true;
        }
        if (id == R.id.option_actualizada){
            gotoWeb("https://twitter.com/Norwegian_ES");
            return true;
        }
        if (id == R.id.option_vale){
            gotoWeb("https://es.norwegianreward.com/buygiftcard");
            return true;
        }
        if (id == R.id.option_revistavuelo){
            gotoWeb("https://www.norwegian.com/es/informacion-sobre-el-viaje/a-bordo/entretenimiento-durante-el-vuelo/");
            return true;
        }
        if (id == R.id.option_terms){
            gotoWeb("https://www.norwegian.com/es/reserva/informacion-util-sobre-reservas/aspectos-juridicos/condiciones-de-uso-del-sitio-web/");
            return true;
        }if (id == R.id.option_help){
            gotoWeb("https://www.norwegian.com/es/informacion-sobre-el-viaje/facturacion-y-embarque/");
            return true;
        }if (id == R.id.option_help){
            gotoWeb("https://www.norwegian.com/es/ayuda-y-contacto/");
            return true;
        }if (id == R.id.option_contact){
            gotoWeb("https://www.norwegian.com/es/mis-viajes/#/login");
            return true;
        }if (id == R.id.option_privacy){
            gotoWeb("https://www.norwegian.com/es/reserva/informacion-util-sobre-reservas/aspectos-juridicos/politica-de-privacidad/");
            return true;
        }if (id == R.id.option_confprivacy){
            gotoWeb("https://www.norwegian.com/es/reserva/informacion-util-sobre-reservas/aspectos-juridicos/cookies/");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void gotoWeb(String url){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

}