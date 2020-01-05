package com.proyecto.hollowknight;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ArmaDetailActivity extends AppCompatActivity{
    private ShareActionProvider shareActionProvider;
    public static final String EXTRA_ARMANO = "armaNo";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int armaNo = 0;
        setContentView(R.layout.activity_arma_detail);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if(getIntent() != null) {
            armaNo = (Integer)getIntent().getExtras().get(EXTRA_ARMANO);
        }
        //String armaName = Arma.armas[armaNo].getName();
        //TextView textView = (TextView)findViewById(R.id.arma_text);
        //textView.setText(armaName);

        //int armaImage = Arma.armas[armaNo].getImageResourceId();
        //ImageView imageView = (ImageView)findViewById(R.id.arma_image);
        //imageView.setImageDrawable(getResources().getDrawable(armaImage));
        //imageView.setContentDescription(armaName);

        //String armaDamage = Arma.armas[armaNo].getDamage();
        //TextView damageView = (TextView)findViewById(R.id.arma_damage);
        //damageView.setText(armaDamage);

        //String armaDetail = Arma.armas[armaNo].getDescription();
        //TextView detailView = (TextView)findViewById(R.id.arma_detail);
        //detailView.setText(armaDetail);
        SQLiteOpenHelper hollowknightDatabaseHelper = new HollowKnightDatabaseHelper(this);
        try{
            SQLiteDatabase db = hollowknightDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("ARMA", new String[]{"NAME", "DESCRIPTION", "DAMAGE", "IMAGE_RESOURCE_ID", "OBTENIDO"}, "_id = ?", new String[]{Integer.toString(armaNo)}, null, null, null);
            if(cursor.moveToFirst()){
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                String damageText = cursor.getString(2);
                int photoId = cursor.getInt(3);
                boolean isObtenido = (cursor.getInt(4)==1);
                TextView name = (TextView)findViewById(R.id.arma_text);
                name.setText(nameText);
                TextView description = (TextView)findViewById(R.id.arma_detail);
                description.setText(descriptionText);
                TextView damage = (TextView)findViewById(R.id.arma_damage);
                damage.setText(damageText);
                ImageView photo = (ImageView)findViewById(R.id.arma_image);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);
                CheckBox obtenido = (CheckBox)findViewById(R.id.obtenido);
                obtenido.setChecked(isObtenido);
            }
            cursor.close();
            db.close();
        }catch(SQLiteException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void onObtenidoClicked(View view){
        int armaNo = (Integer)getIntent().getExtras().get(EXTRA_ARMANO);
        new UpdateArmaTask().execute(armaNo);
    }
    private class UpdateArmaTask extends AsyncTask<Integer, Void, Boolean>{
        private ContentValues armaValues;
        protected void onPreExecute(){
            CheckBox obtenido = (CheckBox)findViewById(R.id.obtenido);
            armaValues = new ContentValues();
            armaValues.put("OBTENIDO", obtenido.isChecked());
        }
        protected Boolean doInBackground(Integer... armas){
            int armaNo = armas[0];
            SQLiteOpenHelper hollowknightDatabaseHelper = new HollowKnightDatabaseHelper(ArmaDetailActivity.this);
            try{
                SQLiteDatabase db = hollowknightDatabaseHelper.getWritableDatabase();
                db.update("ARMA", armaValues, "_id = ?", new String[]{Integer.toString(armaNo)});
                db.close();
                return true;
            } catch (SQLiteException e) {
                return false;
            }
        }
        protected void onPostExecute(Boolean success) {
            if(!success){
                Toast toast = Toast.makeText(ArmaDetailActivity.this, "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }else{
                Toast toast = Toast.makeText(ArmaDetailActivity.this, "Save complete", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //TextView textView = (TextView)findViewById(R.id.arma_text);
        //CharSequence armaName = textView.getText();
        //MenuItem menuItem = menu.findItem(R.id.action_share);
        //shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        //Intent intent = new Intent(Intent.ACTION_SEND);
        //intent.setType("text/plain");
        //intent.putExtra(Intent.EXTRA_TEXT, armaName);
        //shareActionProvider.setShareIntent(intent);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            default:
                onBackPressed();
                return super.onOptionsItemSelected(item);
        }
    }
}
