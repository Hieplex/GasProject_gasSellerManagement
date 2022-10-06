package gasproject.my;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView ;
import gasproject.my.fragments.AdminView;
import gasproject.my.fragments.HomeFragment;
import gasproject.my.fragments.ShipperView;
import gasproject.my.fragments.SignInFragment;
public class MainActivity extends AppCompatActivity {
    private Button loginbtn ;
    private Button camerabtn ;
    String[] items = {"12kg","45kg","48kg"};
    ArrayAdapter<String> adapterItems;
    AutoCompleteTextView listgas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_sign_in);

        Button loginbtn = (Button) findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                BottomNavigationView bottomnv = findViewById(R.id.bottomnavigationView);
                bottomnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {


                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        loadfrag(new HomeFragment(),0);

                        switch (item.getItemId()){
                            case R.id.Shipper:

                                loadfrag(new ShipperView(),1);

                                break;
                            case R.id.Manager:
                                loadfrag(new AdminView(),1);
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
        });

    }
}