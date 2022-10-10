package gasproject.my.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import gasproject.my.R;
import gasproject.my.RecycleAdapterManager;
import gasproject.my.RecycleViewInterface;
import gasproject.my.User;

public class CustomerListForManager extends Fragment implements RecycleViewInterface {

    private ArrayList<User> userlist;
    private RecyclerView recyclerview;
    DatabaseReference databasereference;
    RecycleAdapterManager adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View view  = inflater.inflate(R.layout.fragment_customer_list_for_manager,container,false);
        userlist = new ArrayList<>();
        recyclerview = view.findViewById(R.id.recyclerView);
        setUserInfo();
        setAdapter();
        databasereference = FirebaseDatabase.getInstance("https://projectsgm-fc929-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("User");
        recyclerview.setHasFixedSize(true);
        userlist = new ArrayList<>();
        recyclerview.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new RecycleAdapterManager(this.getActivity(),userlist,this);
        recyclerview.setAdapter(adapter);

        databasereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot datasnapshot : snapshot.getChildren()){
                    User user = datasnapshot.getValue(User.class);
                    userlist.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        return view;
    }

    private void setAdapter() {

    }

    private void setUserInfo() {


    }


    @Override
    public void onItemClick(int position) {
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                CustomerInfo fragment1 = new CustomerInfo();
//                transaction.replace(R.id.frame_layout, fragment1);
//                transaction.commit();

    }

}