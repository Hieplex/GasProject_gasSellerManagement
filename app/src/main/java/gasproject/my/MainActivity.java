package gasproject.my;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.bottomnavigation.BottomNavigationView ;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import gasproject.my.fragments.AdminView;
import gasproject.my.fragments.CustomerInfo;
import gasproject.my.fragments.Customerlist;
import gasproject.my.fragments.GasList;
import gasproject.my.fragments.GasProductList;
import gasproject.my.fragments.Login;

public class MainActivity extends AppCompatActivity {
    private Button loginbtn ;
    private Button camerabtn ;

    String[] items = {"12kg","45kg","48kg"};
    ArrayAdapter<String> adapterItems;
    AutoCompleteTextView listgas;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        FragmentTransaction transaction = MainActivity.this.getSupportFragmentManager().beginTransaction();
        Customerlist fragment1 = new Customerlist();
        transaction.replace(R.id.frame_layout, fragment1);
        transaction.commit();
        BottomNavigationView bottomnv = findViewById(R.id.bottomnavigationView);

        bottomnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.Shipper:

                        loadfrag(new Customerlist(),1);

                        break;
                    case R.id.Manager:
                        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        if (currentuser.equals("xaMkJu1xCxYoN588r0vsIkhTEvP2")) {
                            FragmentTransaction transaction = MainActivity.this.getSupportFragmentManager().beginTransaction();
                            AdminView fragment1 = new AdminView();
                            transaction.replace(R.id.frame_layout, fragment1);
                            transaction.commit();


                        } else {
                            Toast.makeText(MainActivity.this,"Account don't have authorization to view this page",Toast.LENGTH_SHORT).show();
                            FragmentTransaction transaction = MainActivity.this.getSupportFragmentManager().beginTransaction();
                            GasProductList fragment2 = new GasProductList();
                            transaction.replace(R.id.frame_layout, fragment2);
                            transaction.commit();

                        }

                        break;

                    case R.id.Gas:
                        String currentuser2 = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        if (currentuser2.equals("xaMkJu1xCxYoN588r0vsIkhTEvP2")) {
                            FragmentTransaction transaction = MainActivity.this.getSupportFragmentManager().beginTransaction();
                            GasList fragment1 = new GasList();
                            transaction.replace(R.id.frame_layout, fragment1);
                            transaction.commit();


                        } else {
                            Toast.makeText(MainActivity.this,"Account don't have authorization to view this page",Toast.LENGTH_SHORT).show();
                            FragmentTransaction transaction = MainActivity.this.getSupportFragmentManager().beginTransaction();
                            GasProductList fragment2 = new GasProductList();
                            transaction.replace(R.id.frame_layout, fragment2);
                            transaction.commit();

                        }

                        break;
                }
                return true ;
            }
        } );
    }
    public void loadfrag(Fragment frag, int flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if(flag==0)
        {
            ft.add(R.id.frame_layout, frag );
        }
        else{
            ft.replace(R.id.frame_layout, frag);
        }
        ft.commit();
    }

    }
