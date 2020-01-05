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

public class HechizoDetailActivity extends AppCompatActivity{
    private ShareActionProvider shareActionProvider;
    public static final String EXTRA_HECHIZONO = "hechizoNo";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int hechizoNo = 0;
        setContentView(R.layout.activity_hechizo_detail);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if(getIntent() != null) {
            hechizoNo = (Integer) getIntent().getExtras().get(EXTRA_HECHIZONO);
        }
        //String hechizoName = Hechizo.hechizos[hechizoNo].getName();
        //TextView textView = (TextView)findViewById(R.id.hechizo_text);
        //textView.setText(hechizoName);

        //int hechizoImage = Hechizo.hechizos[hechizoNo].getImageResourceId();
        //ImageView imageView = (ImageView)findViewById(R.id.hechizo_image);
        //imageView.setImageDrawable(getResources().getDrawable(hechizoImage));
        //imageView.setContentDescription(hechizoName);

        //String hechizoDetail = Hechizo.hechizos[hechizoNo].getDescription();
        //TextView detailView = (TextView)findViewById(R.id.hechizo_detail);
        //detailView.setText(hechizoDetail);
        SQLiteOpenHelper hollowknightDatabaseHelper = new HollowKnightDatabaseHelper(this);
        try{
            SQLiteDatabase db = hollowknightDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("HECHIZO", new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "OBTENIDO"}, "_id = ?", new String[]{Integer.toString(hechizoNo)}, null, null, null);
            if(cursor.moveToFirst()){
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                boolean isObtenido = (cursor.getInt(3)==1);
                TextView name = (TextView)findViewById(R.id.hechizo_text);
                name.setText(nameText);
                TextView description = (TextView)findViewById(R.id.hechizo_detail);
                description.setText(descriptionText);
                ImageView photo = (ImageView)findViewById(R.id.hechizo_image);
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
        int hechizoNo = (Integer)getIntent().getExtras().get(EXTRA_HECHIZONO);
        new UpdateHechizoTask().execute(hechizoNo);
    }
    private class UpdateHechizoTask extends AsyncTask<Integer, Void, Boolean> {
        private ContentValues hechizoValues;
        protected void onPreExecute(){
            CheckBox obtenido = (CheckBox)findViewById(R.id.obtenido);
            hechizoValues = new ContentValues();
            hechizoValues.put("OBTENIDO", obtenido.isChecked());
        }
        protected Boolean doInBackground(Integer... hechizos){
            int hechizoNo = hechizos[0];
            SQLiteOpenHelper hollowknightDatabaseHelper = new HollowKnightDatabaseHelper(HechizoDetailActivity.this);
            try{
                SQLiteDatabase db = hollowknightDatabaseHelper.getWritableDatabase();
                db.update("HECHIZO", hechizoValues, "_id = ?", new String[]{Integer.toString(hechizoNo)});
                db.close();
                return true;
            } catch (SQLiteException e) {
                return false;
            }
        }
        protected void onPostExecute(Boolean success) {
            if(!success){
                Toast toast = Toast.makeText(HechizoDetailActivity.this, "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }else{
                Toast toast = Toast.makeText(HechizoDetailActivity.this, "Save complete", Toast.LENGTH_SHORT);
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
