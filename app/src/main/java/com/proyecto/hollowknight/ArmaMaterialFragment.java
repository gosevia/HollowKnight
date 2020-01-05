package com.proyecto.hollowknight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ArmaMaterialFragment extends Fragment{
    private static final int len = 5;
    public ArmaMaterialFragment(){
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        RecyclerView armaRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_arma_material, container, false);
        String[] armaNames = new String[len];
        for(int i=0; i<armaNames.length; i++){
            armaNames[i] = Arma.armas[i].getName();
        }
        int[] armaImages = new int[Arma.armas.length];
        for(int i=0; i<armaImages.length; i++){
            armaImages[i] = Arma.armas[i].getImageResourceId();
        }
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(armaNames, armaImages);
        armaRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        armaRecycler.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImagesAdapter.Listener(){
            public void onClick(int position){
                Intent intent = new Intent(getActivity(), ArmaDetailActivity.class);
                intent.putExtra(ArmaDetailActivity.EXTRA_ARMANO, position+1);
                getActivity().startActivity(intent);
            }
        });
        return armaRecycler;
    }
}
