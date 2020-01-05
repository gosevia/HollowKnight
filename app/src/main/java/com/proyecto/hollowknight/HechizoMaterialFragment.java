package com.proyecto.hollowknight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HechizoMaterialFragment extends Fragment{
    private static final int len = 7;
    public HechizoMaterialFragment(){
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView hechizoRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_hechizo_material, container, false);
        String[] hechizoNames = new String[len];
        for(int i=0; i<hechizoNames.length; i++){
            hechizoNames[i] = Hechizo.hechizos[i].getName();
        }
        int[] hechizoImages = new int[Hechizo.hechizos.length];
        for(int i=0; i<hechizoImages.length; i++){
            hechizoImages[i] = Hechizo.hechizos[i].getImageResourceId();
        }
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(hechizoNames, hechizoImages);
        hechizoRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        hechizoRecycler.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImagesAdapter.Listener(){
            public void onClick(int position){
                Intent intent = new Intent(getActivity(), HechizoDetailActivity.class);
                intent.putExtra(HechizoDetailActivity.EXTRA_HECHIZONO, position+1);
                getActivity().startActivity(intent);
            }
        });
        return hechizoRecycler;
    }
}
