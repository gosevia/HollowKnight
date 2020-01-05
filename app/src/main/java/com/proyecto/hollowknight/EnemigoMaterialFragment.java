package com.proyecto.hollowknight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EnemigoMaterialFragment extends Fragment{
    private static final int len = 18;
    public EnemigoMaterialFragment(){
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        RecyclerView enemigoRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_enemigo_material, container, false);
        String[] enemigoNames = new String[len];
        for(int i=0; i<enemigoNames.length; i++){
            enemigoNames[i] = Enemigo.enemigos[i].getName();
        }
        int[] enemigoImages = new int[Enemigo.enemigos.length];
        for(int i=0; i<enemigoImages.length; i++){
            enemigoImages[i] = Enemigo.enemigos[i].getImageResourceId();
        }
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(enemigoNames, enemigoImages);
        enemigoRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        enemigoRecycler.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImagesAdapter.Listener(){
            public void onClick(int position){
                Intent intent = new Intent(getActivity(), EnemigoDetailActivity.class);
                intent.putExtra(EnemigoDetailActivity.EXTRA_ENEMIGONO, position+1);
                getActivity().startActivity(intent);
            }
        });
        return enemigoRecycler;
    }
}
