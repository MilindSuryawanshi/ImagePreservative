package com.f9.imagetagging.activites.imageshelve.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.f9.imagetagging.activites.imageshelve.R;

/**
 * Created by Milind Suryawanshi on 4/30/16.
 */
public class HappyFragment extends Fragment {


    public HappyFragment(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_layout_happy, container, false);
    }

}
