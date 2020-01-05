package com.proyecto.hollowknight;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

public class TopFragment extends Fragment{
    public TopFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ScrollView layout = (ScrollView)inflater.inflate(R.layout.fragment_top, container, false);
        return layout;
    }
}