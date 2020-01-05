package com.proyecto.hollowknight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AmuletoMaterialFragment extends Fragment{
    private static final int len = 11;
    public AmuletoMaterialFragment(){
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        RecyclerView amuletoRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_amuleto_material, container, false);
        String[] amuletoNames = new String[len];
        for(int i=0; i<amuletoNames.length; i++){
            amuletoNames[i] = Amuleto.amuletos[i].getName();
        }
        int[] amuletoImages = new int[Amuleto.amuletos.length];
        for(int i=0; i<amuletoImages.length; i++){
            amuletoImages[i] = Amuleto.amuletos[i].getImageResourceId();
        }
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(amuletoNames, amuletoImages);
        amuletoRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        amuletoRecycler.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImagesAdapter.Listener(){
            public void onClick(int position){
                Intent intent = new Intent(getActivity(), AmuletoDetailActivity.class);
                intent.putExtra(AmuletoDetailActivity.EXTRA_AMULETONO, position+1);
                getActivity().startActivity(intent);
            }
        });
        return amuletoRecycler;
    }
}
