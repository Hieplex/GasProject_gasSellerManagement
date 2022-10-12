package gasproject.my.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import gasproject.my.R;
import gasproject.my.User;


public class UserList extends Fragment {

    String[] items = {"12kg","45kg","48kg"};
    String[] items2 = {"Petrolimex","PV gas","SaiGon Petro gas"};
    ArrayAdapter<String> adapterItems;
    AutoCompleteTextView listGasweight;
    AutoCompleteTextView listGastrademark;
    EditText customerName ;
    EditText customerPhoneNumber ;
    EditText customeraddress ;
    RadioButton radiobtn;
    RadioGroup radiogroup;
    ArrayAdapter<String> adapterItems2;
    ArrayAdapter<String> adapterItems3;
    AutoCompleteTextView listgastrademark;
    AutoCompleteTextView listgasproduct;
    ArrayList<String> list;
    ArrayList<String> list2;
    ValueEventListener mListener;
    User user;
    long maxid=0;
    DatabaseReference DBref;
    DatabaseReference DBref2;
    DatabaseReference DBref3;
    @TargetApi(Build.VERSION_CODES.M)

    private void closeKeyboard(EditText editText) {
        View view2 = this.getActivity().getCurrentFocus();
        if (view2 != null) {

            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view2.getApplicationWindowToken(), 0);

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_user_list, container, false);

        DBref3 =  FirebaseDatabase.getInstance("https://projectsgm-fc929-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("GasCan");
        list = new ArrayList<String>();
        listGastrademark = view.findViewById(R.id.listGastrademark);
        adapterItems3 =  new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,list);
        listGastrademark.setAdapter(adapterItems3);

       listGastrademark.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


               String gasname = listGastrademark.getText().toString();
               System.out.println("GasCan/"+gasname);
               DBref2 =  FirebaseDatabase.getInstance("https://projectsgm-fc929-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("GasCan/"+gasname);

               adapterItems3 =  new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,list);
               adapterItems3.notifyDataSetChanged();

               fetchdata2();
                list2.clear();
               adapterItems2.notifyDataSetChanged();
           }

       });

        list2 = new ArrayList<String>();
        listgasproduct = view.findViewById(R.id.listGasproduct);
        adapterItems2 =  new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,list2);
        listgasproduct.setAdapter(adapterItems2);

        listgasproduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



            }
        });





        customerName = view.findViewById(R.id.CustomerName);
        customerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EditText) view.findViewById(R.id.CustomerName)).setText("");
                view.clearFocus();
            }
        });
        customerName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if(i == EditorInfo.IME_ACTION_DONE)
                {
                    closeKeyboard(customerName);
                }

                return false;
            }
        });

        customerPhoneNumber = view.findViewById(R.id.CustomerPhoneNumber);
        customerPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EditText) view.findViewById(R.id.CustomerPhoneNumber)).setText("");
            }
        });
        customeraddress = view.findViewById(R.id.CustomerAddress);
        customeraddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EditText) view.findViewById(R.id.CustomerAddress)).setText("");
                view.clearFocus();

            }
        });

        Button pushdata = view.findViewById(R.id.pushlistuserdata);

        pushdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PushData();


            }

        });
        fetchdata();

        return view;

    }

    private void PushData() {

        User user = new User();
        customerName = getActivity().findViewById(R.id.CustomerName);
        customerPhoneNumber = getActivity().findViewById(R.id.CustomerPhoneNumber);
        customeraddress = getActivity().findViewById(R.id.CustomerAddress);
        listGastrademark = getActivity().findViewById(R.id.listGastrademark);
        listgasproduct = getActivity().findViewById(R.id.listGasproduct);
        radiogroup = getActivity().findViewById(R.id.radioGroup);
        int radioID = radiogroup.getCheckedRadioButtonId();
        radiobtn = getActivity().findViewById(radioID);
        String gender = radiobtn.getText().toString();

        String name = customerName.getText().toString().trim();
        String phone = customerPhoneNumber.getText().toString();
        int phonee = Integer.parseInt(phone);
        String address = customeraddress.getText().toString().trim();

        String product = listgasproduct.getText().toString();
        String gasname = listGastrademark.getText().toString();
        user.setName(name);
        user.setGender(gender);
        user.setPhoneNumber(phonee);
        user.setAddress(address);
        user.setGastrademark(gasname);
        user.setGasproduct(product);
        user.setStatus("not complete");
        user.setID(maxid);


        DBref =  FirebaseDatabase.getInstance("https://projectsgm-fc929-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("User/");
        DBref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               if(snapshot.exists()){
                   int maxid = (int)snapshot.getChildrenCount();
                   System.out.println("number: "+maxid);
                   DBref.child(""+maxid).setValue(user);

               }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public  void fetchdata(){

        mListener = DBref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot mydata: snapshot.getChildren()){
                    list.add(mydata.getKey());
                    adapterItems3.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public  void fetchdata2(){

        mListener = DBref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot mydata: snapshot.getChildren()){
                    list2.add(mydata.getValue().toString());
                    adapterItems2.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}