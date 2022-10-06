package gasproject.my.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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

import gasproject.my.MainActivity;
import gasproject.my.R;
import gasproject.my.RecycleAdapter;
import gasproject.my.RecycleViewInterface;
import gasproject.my.User;


public class Customerlist extends AppCompatActivity implements RecycleViewInterface {

    private ArrayList<User> userlist;
    private RecyclerView recyclerview;
    DatabaseReference databasereference;
    RecycleAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_customerlist);
        userlist = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerView);
        setUserInfo();
        setAdapter();
        databasereference = FirebaseDatabase.getInstance("https://projectsgm-fc929-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("User");
        recyclerview.setHasFixedSize(true);
        userlist = new ArrayList<>();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecycleAdapter(this,userlist,this);
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
    }

    private void setAdapter() {

    }

    private void setUserInfo() {


    }


    @Override
    public void onItemClick(int position) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                ShipperView fragment1 = new ShipperView();
//                transaction.replace(R.id.frame_layout, fragment1);
//                transaction.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}