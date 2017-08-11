package com.rocha.agenda.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rocha.agenda.R;
import com.rocha.agenda.fragment.MapaFragment;
import com.rocha.agenda.fragment.PlanetFragment;

public class MapaActivity extends AppCompatActivity {
   /* //teste NavigationLayout
    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

   //teste2 DrawerLayout
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    //teste*/

    private FragmentManager manager;
    private FragmentTransaction tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mapa);

        //getSupportActionBar().setIcon(R.drawable.ic_drawer);

       /* //teste NavigationLayout (menu lateral)
        mTitle = mDrawerTitle = getTitle();
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

       //teste2 botão drawerToggle
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(mTitle);
            }
        };
*/
      /*  // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        // teste2

        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }

            private void selectItem(int position) {
                // Create a new fragment and specify the planet to show based on position
                Fragment fragment = new PlanetFragment();
                Bundle args = new Bundle();
                args.putInt("numero", position);
                fragment.setArguments(args);

                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_mapa, fragment)
                        .addToBackStack(null)
                        .commit();

                // Highlight the selected item, update the title, and close the drawer
                mDrawerList.setItemChecked(position, true);
                setTitle(mPlanetTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        });*/
        //teste

        manager = getSupportFragmentManager();
        tx = manager.beginTransaction();
        tx.replace(R.id.frame_mapa, new MapaFragment());
        tx.commit();

    }

   /* @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }*/



    //configuração do menu na tela do mapa
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mapa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Bundle args = new Bundle();
        MapaFragment mapaFragment = new MapaFragment();

        switch (item.getItemId()){
            case R.id.menu_visualizar_fornecedores:
                Intent vaiParaListaForencedores = new Intent(this, ListaAlunosActivity.class);
                startActivity(vaiParaListaForencedores);

                break;

            case R.id.menu_estilo_mapa_dia:
                args.putInt("tipo", 1);

                mapaFragment.setArguments(args);

                tx = manager.beginTransaction();
                tx.replace(R.id.frame_mapa, mapaFragment);
                tx.commit();

                break;

            case R.id.menu_estilo_mapa_noite:
                args.putInt("tipo", 2);

                mapaFragment.setArguments(args);

                tx = manager.beginTransaction();
                tx.replace(R.id.frame_mapa, mapaFragment);
                tx.commit();

                break;
        }

       /* if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
