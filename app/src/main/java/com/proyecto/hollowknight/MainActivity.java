package com.proyecto.hollowknight;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity{
    private ShareActionProvider shareActionProvider;
    private String[] titles;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titles = getResources().getStringArray(R.array.titles);
        drawerList = (ListView)findViewById(R.id.drawer);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.layout_custom, titles));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
        if(savedInstanceState != null){
            currentPosition = savedInstanceState.getInt("position");
            setActionBarTitle(currentPosition);
        }else{
            selectItem(0);
        }
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer){
            @Override
            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    @Override
                    public void onBackStackChanged() {
                        FragmentManager fragMan = getSupportFragmentManager();
                        Fragment fragment = fragMan.findFragmentByTag("visible_fragment");
                        if(fragment instanceof TopFragment){
                            currentPosition = 0;
                        }
                        if(fragment instanceof ArmaMaterialFragment){
                            currentPosition = 1;
                        }
                        if(fragment instanceof HechizoMaterialFragment){
                            currentPosition = 2;
                        }
                        if(fragment instanceof AmuletoMaterialFragment){
                            currentPosition = 3;
                        }
                        if(fragment instanceof EnemigoMaterialFragment){
                            currentPosition = 4;
                        }
                        setActionBarTitle(currentPosition);
                        drawerList.setItemChecked(currentPosition, true);
                    }
                }
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //MenuItem menuItem = menu.findItem(R.id.action_share);
        //shareActionProvider = (ShareActionProvider)MenuItemCompat.getActionProvider(menuItem);
        //setIntent("This is example text");
        return super.onCreateOptionsMenu(menu);
    }
    //private void setIntent(String text){
    //    Intent intent = new Intent(Intent.ACTION_SEND);
    //    intent.setType("text/plain");
    //    intent.putExtra(Intent.EXTRA_TEXT, text);
    //    shareActionProvider.setShareIntent(intent);
    //}
    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            selectItem(position);
        }
    }
    private void selectItem(int position){
        Fragment fragment;
        currentPosition = position;
        switch (position){
            case 1: fragment = new ArmaMaterialFragment(); break;
            case 2: fragment = new HechizoMaterialFragment(); break;
            case 3: fragment = new AmuletoMaterialFragment(); break;
            case 4: fragment = new EnemigoMaterialFragment(); break;
            default: fragment = new TopFragment();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment, "visible_fragment");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        setActionBarTitle(position);
        drawerLayout.closeDrawer(drawerList);
    }
    private void setActionBarTitle(int position){
        String title;
        if(position == 0){
            title = getResources().getString(R.string.app_name);
        }else{
            title = titles[position];
        }
        getSupportActionBar().setTitle(title);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){
            //case R.id.action_create_order:
            //    Intent intent = new Intent(this, OrderActivity.class);
            //    startActivity(intent);
            //    return true;
            //case R.id.action_settings:
            //   return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //@Override
    //public boolean onPrepareOptionsMenu(Menu menu){
    //    boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
    //    menu.findItem(R.id.action_share).setVisible(!drawerOpen);
    //    return super.onPrepareOptionsMenu(menu);
    //}
    @Override
    protected void onPostCreate(Bundle savedInsntanceState){
        super.onPostCreate(savedInsntanceState);
        drawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("position", currentPosition);
    }
}
