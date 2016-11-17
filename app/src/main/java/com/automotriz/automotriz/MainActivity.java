package com.automotriz.automotriz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private FirebaseAuth firebaseauth;

    private TextView textusuariocorreo;
    private Button bDesconectarse;


    ListViewAdapter adapter;

    String[] titulo = new String[]{
            "Nissan",
            "Hyundai",
            "Toyota",
            "Chevrolet",
            "Ford",
    };

    int[] imagenes = {
            R.drawable.nissan,
            R.drawable.hyundai,
            R.drawable.toyota,
            R.drawable.chevrolet,
            R.drawable.ford
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database2.getReference(FirebaseReferences.AUTOMOTRIX_REFERENCE);
        myRef.child(FirebaseReferences.COCHES_REFERENCE);

        registrarEventos2();
        initControl();
        firebaseauth = FirebaseAuth.getInstance();
        if(firebaseauth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,MenuLogin.class));
        }
        FirebaseUser user = firebaseauth.getCurrentUser();

        textusuariocorreo = (TextView)findViewById(R.id.txtCorreoUSUARIO);
        textusuariocorreo.setText("Bienvenido "+user.getEmail());




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Pronto mensaje activada", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {


            Intent x = new Intent(this, MenuPerfil.class);
            startActivity(x);

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

            firebaseauth.signOut();
            finish();
            Intent x = new Intent(this, MenuLogin.class);
            startActivity(x);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





    private void initControl() {



    }



    public void onClick(View v) {

    }







    private void registrarEventos(){

        /// selecciona la lista en pantalla segun su ID
        ListView lista1 = (ListView) findViewById(R.id.listView1);

        // registra una accion para el evento click
        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                /// Obtiene el valor de la casilla elegida

                String itemSeleccionado = adapterView.getItemAtPosition(i).toString();

                // muestra un mensaje
                Toast.makeText( getApplicationContext(), "Te llevaremos a ver el vehiculo " + itemSeleccionado, Toast.LENGTH_SHORT).show();

            }
        });

    }


    private void registrarEventos2() {

        final ListView lista = (ListView) findViewById(R.id.listView1);
        adapter = new ListViewAdapter(this, titulo, imagenes);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(), "presiono " + i, Toast.LENGTH_SHORT).show();
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "presiono LARGO " + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }



}
