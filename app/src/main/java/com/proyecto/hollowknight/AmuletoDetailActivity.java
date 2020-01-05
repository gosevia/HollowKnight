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

public class AmuletoDetailActivity extends AppCompatActivity{
    private ShareActionProvider shareActionProvider;
    public static final String EXTRA_AMULETONO = "amuletoNo";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int amuletoNo = 0;
        setContentView(R.layout.activity_amuleto_detail);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if(getIntent() != null) {
            amuletoNo = (Integer) getIntent().getExtras().get(EXTRA_AMULETONO);
        }
        //String amuletoName = Amuleto.amuletos[amuletoNo].getName();
        //TextView textView = (TextView)findViewById(R.id.amuleto_text);
        //textView.setText(amuletoName);

        //int amuletoImage = Amuleto.amuletos[amuletoNo].getImageResourceId();
        //ImageView imageView = (ImageView)findViewById(R.id.amuleto_image);
        //imageView.setImageDrawable(getResources().getDrawable(amuletoImage));
        //imageView.setContentDescription(amuletoName);

        //String amuletoDetail = Amuleto.amuletos[amuletoNo].getDescription();
        //TextView detailView = (TextView)findViewById(R.id.amuleto_detail);
        //detailView.setText(amuletoDetail);
        SQLiteOpenHelper hollowknightDatabaseHelper = new HollowKnightDatabaseHelper(this);
        try{
            SQLiteDatabase db = hollowknightDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("AMULETO", new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "OBTENIDO"}, "_id = ?", new String[]{Integer.toString(amuletoNo)}, null, null, null);
            if(cursor.moveToFirst()){
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                boolean isObtenido = (cursor.getInt(3)==1);
                TextView name = (TextView)findViewById(R.id.amuleto_text);
                name.setText(nameText);
                TextView description = (TextView)findViewById(R.id.amuleto_detail);
                description.setText(descriptionText);
                ImageView photo = (ImageView)findViewById(R.id.amuleto_image);
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
        int amuletoNo = (Integer)getIntent().getExtras().get(EXTRA_AMULETONO);
        new UpdateArmaTask().execute(amuletoNo);
    }
    private class UpdateArmaTask extends AsyncTask<Integer, Void, Boolean>{
        private ContentValues amuletoValues;
        protected void onPreExecute(){
            CheckBox obtenido = (CheckBox)findViewById(R.id.obtenido);
            amuletoValues = new ContentValues();
            amuletoValues.put("OBTENIDO", obtenido.isChecked());
        }
        protected Boolean doInBackground(Integer... amuletos){
            int amuletoNo = amuletos[0];
            SQLiteOpenHelper hollowknightDatabaseHelper = new HollowKnightDatabaseHelper(AmuletoDetailActivity.this);
            try{
                SQLiteDatabase db = hollowknightDatabaseHelper.getWritableDatabase();
                db.update("AMULETO", amuletoValues, "_id = ?", new String[]{Integer.toString(amuletoNo)});
                db.close();
                return true;
            }catch(SQLiteException e){
                return false;
            }
        }
        protected void onPostExecute(Boolean success){
            if(!success){
                Toast toast = Toast.makeText(AmuletoDetailActivity.this, "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }else{
                Toast toast = Toast.makeText(AmuletoDetailActivity.this, "Save complete", Toast.LENGTH_SHORT);
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
