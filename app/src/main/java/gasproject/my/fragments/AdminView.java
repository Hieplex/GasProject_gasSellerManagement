package gasproject.my.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import gasproject.my.R;


public class AdminView extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_admin_view, container, false);
        View view  = inflater.inflate(R.layout.fragment_admin_view,container,false);
        return view ;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton shipper = view.findViewById(R.id.imageViewshipper);

        shipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                UserList fragment1 = new UserList();
                transaction.replace(R.id.frame_layout,fragment1);
                transaction.commit();
            }
        });
        ImageButton CustomerList = view.findViewById(R.id.imageCustomer);

        CustomerList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Customerlist.class);
                startActivity(intent);
//
//                FragmentManager manager = getActivity().getSupportFragmentManager();
//                FragmentTransaction transaction = manager.beginTransaction();
//                transaction.replace(R.id.frame_layout, new Customerlist()).commit();


            }
        });

    }
    public  void onBackPressed(){

    }

}