package gasproject.my.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.Value;

import java.util.ArrayList;

import gasproject.my.Gas;
import gasproject.my.R;
import gasproject.my.User;


public class GasList extends Fragment {

    String[] items = {"12kg","45kg","48kg"};
    String[] items2 = {"Petrolimex","PV gas","SaiGon Petro gas"};
    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adapterItems2;
    AutoCompleteTextView listGasweight;
    AutoCompleteTextView listGastrademark;
    AutoCompleteTextView listGascancolor;
    FirebaseFirestore firestore;
    ValueEventListener mListener;
     ArrayList<String> list;
     Spinner spinner;

    long maxid=1;
    private DatabaseReference  DBref;
    private DatabaseReference DBref2;

    @TargetApi(Build.VERSION_CODES.M)


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        User user = new User();

        View view =  inflater.inflate(R.layout.fragment_gas_list, container, false);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DBref2 =  FirebaseDatabase.getInstance("https://projectsgm-fc929-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("GasAirTankColor");
        list = new ArrayList<String>();
         listGascancolor = view.findViewById(R.id.listGascancolor);
        adapterItems2 =  new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,list);
        listGascancolor.setAdapter(adapterItems2);

        listGasweight = view.findViewById(R.id.listGasweight);
        adapterItems = new ArrayAdapter<String>(getActivity(),R.layout.list_users,items);
        listGasweight.setAdapter(adapterItems);


        listGastrademark = view.findViewById(R.id.listGastrademark);
        adapterItems = new ArrayAdapter<String>(getActivity(),R.layout.list_users,items2);
        listGastrademark.setAdapter(adapterItems);


        listGasweight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listGasweight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String itemgasweight = adapterView.getItemAtPosition(i).toString();
                        listGasweight.setText(itemgasweight);


                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listGastrademark.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listGastrademark.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String itemgastrademark = adapterView.getItemAtPosition(i).toString();
                        listGastrademark.setText(itemgastrademark);

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button pushdata = view.findViewById(R.id.pushlistgasdata);

        pushdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PushData();
                maxid++;
            }

        });

        fetchdata();

        return view;

    }

    private void PushData() {

        Gas gas = new Gas();
        String gastrademark = listGastrademark.getText().toString();
        listGastrademark = getActivity().findViewById(R.id.listGastrademark);
        listGasweight = getActivity().findViewById(R.id.listGasweight);
        listGascancolor = getActivity().findViewById(R.id.listGascancolor);
        DBref =  FirebaseDatabase.getInstance("https://projectsgm-fc929-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("GasCan");
        gas.setGascanweight(listGasweight.getText().toString());
        gas.setGascanColor(listGascancolor.getText().toString());


        DBref.child(listGastrademark.getText().toString()+"/id: "+maxid).setValue(gas);
        DBref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists())
                {

                    DBref.child(listGastrademark.getText().toString()+"/"+maxid+1).setValue(gas);

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public  void fetchdata(){

        mListener = DBref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot mydata: snapshot.getChildren()){
                    list.add(mydata.getValue().toString());
                    adapterItems2.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}