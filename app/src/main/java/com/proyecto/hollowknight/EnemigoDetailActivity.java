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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class EnemigoDetailActivity extends AppCompatActivity{
    private ShareActionProvider shareActionProvider;
    public static final String EXTRA_ENEMIGONO = "enemigoNo";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int enemigoNo = 0;
        setContentView(R.layout.activity_enemigo_detail);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if(getIntent() != null) {
            enemigoNo = (Integer) getIntent().getExtras().get(EXTRA_ENEMIGONO);
        }
        //String enemigoName = Enemigo.enemigos[enemigoNo].getName();
        //TextView textView = (TextView)findViewById(R.id.enemigo_text);
        //textView.setText(enemigoName);

        //int enemigoImage = Enemigo.enemigos[enemigoNo].getImageResourceId();
        //ImageView imageView = (ImageView)findViewById(R.id.enemigo_image);
        //imageView.setImageDrawable(getResources().getDrawable(enemigoImage));
        //imageView.setContentDescription(enemigoName);

        //String enemigoLocation = Enemigo.enemigos[enemigoNo].getLocation();
        //TextView locationView = (TextView)findViewById(R.id.enemigo_location);
        //locationView.setText(enemigoLocation);

        //String enemigoHp = Enemigo.enemigos[enemigoNo].getHp();
        //TextView hpView = (TextView)findViewById(R.id.enemigo_hp);
        //hpView.setText(enemigoHp);

        //String enemigoDetail = Enemigo.enemigos[enemigoNo].getDescription();
        //TextView detailView = (TextView)findViewById(R.id.enemigo_detail);
        //detailView.setText(enemigoDetail);
        SQLiteOpenHelper hollowknightDatabaseHelper = new HollowKnightDatabaseHelper(this);
        try{
            SQLiteDatabase db = hollowknightDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("ENEMIGO", new String[]{"NAME", "LOCATION", "HP", "DESCRIPTION", "IMAGE_RESOURCE_ID", "DEFEATED", "DIFFICULTY"}, "_id = ?", new String[]{Integer.toString(enemigoNo)}, null, null, null);
            if(cursor.moveToFirst()){
                String nameText = cursor.getString(0);
                String locationText = cursor.getString(1);
                String hpText = cursor.getString(2);
                String descriptionText = cursor.getString(3);
                int photoId = cursor.getInt(4);
                boolean isDefeated = (cursor.getInt(5)==1);
                float getDifficulty = cursor.getFloat(6);
                TextView name = (TextView)findViewById(R.id.enemigo_text);
                name.setText(nameText);
                TextView location = (TextView)findViewById(R.id.enemigo_location);
                location.setText(locationText);
                TextView hp = (TextView)findViewById(R.id.enemigo_hp);
                hp.setText(hpText);
                TextView description = (TextView)findViewById(R.id.enemigo_detail);
                description.setText(descriptionText);
                ImageView photo = (ImageView)findViewById(R.id.enemigo_image);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);
                CheckBox defeated = (CheckBox)findViewById(R.id.defeated);
                defeated.setChecked(isDefeated);
                RatingBar difficulty = (RatingBar)findViewById(R.id.difficulty);
                difficulty.setRating(getDifficulty);
            }
            cursor.close();
            db.close();
        }catch(SQLiteException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        addListenerOnRatingBar();
    }
    public void addListenerOnRatingBar(){
        final int enemigoNo = (Integer)getIntent().getExtras().get(EXTRA_ENEMIGONO);
        RatingBar ratingBar = (RatingBar)findViewById(R.id.difficulty);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){
                new UpdateEnemigoTask().execute(enemigoNo);
            }
        });
    }
    public void onDefeatedClicked(View view){
        int enemigoNo = (Integer)getIntent().getExtras().get(EXTRA_ENEMIGONO);
        new UpdateEnemigoTask().execute(enemigoNo);
    }
    private class UpdateEnemigoTask extends AsyncTask<Integer, Void, Boolean> {
        private ContentValues enemigoValues;
        protected void onPreExecute(){
            CheckBox defeated = (CheckBox)findViewById(R.id.defeated);
            RatingBar difficulty = (RatingBar)findViewById(R.id.difficulty);
            enemigoValues = new ContentValues();
            enemigoValues.put("DEFEATED", defeated.isChecked());
            enemigoValues.put("DIFFICULTY", difficulty.getRating());
        }
        protected Boolean doInBackground(Integer... enemigos){
            int enemigoNo = enemigos[0];
            SQLiteOpenHelper hollowknightDatabaseHelper = new HollowKnightDatabaseHelper(EnemigoDetailActivity.this);
            try{
                SQLiteDatabase db = hollowknightDatabaseHelper.getWritableDatabase();
                db.update("ENEMIGO", enemigoValues, "_id = ?", new String[]{Integer.toString(enemigoNo)});
                db.close();
                return true;
            }catch(SQLiteException e){
                return false;
            }
        }
        protected void onPostExecute(Boolean success){
            if(!success){
                Toast toast = Toast.makeText(EnemigoDetailActivity.this, "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }else{
                Toast toast = Toast.makeText(EnemigoDetailActivity.this, "Save complete", Toast.LENGTH_SHORT);
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
