package gasproject.my.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gasproject.my.R;
import gasproject.my.databinding.ActivityMainBinding;


public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       // return inflater.inflate(R.layout.fragment_home, container, false);
        View view  = inflater.inflate(R.layout.fragment_home,container,false);
        return view ;
    }
}