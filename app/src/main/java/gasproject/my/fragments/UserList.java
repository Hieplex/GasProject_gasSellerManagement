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
//   TextInputLayout listGasweight;
//    TextInputLayout listGastrademark;
    User user;
    long maxid=0;
    DatabaseReference DBref;
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
        User user = new User();
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user_list, container, false);


        listGasweight = view.findViewById(R.id.listGasweight);
        adapterItems = new ArrayAdapter<String>(getActivity(),R.layout.list_users,items);
        listGasweight.setAdapter(adapterItems);


        listGastrademark = view.findViewById(R.id.listGastrademark);
        adapterItems = new ArrayAdapter<String>(getActivity(),R.layout.list_users,items2);
        listGastrademark.setAdapter(adapterItems);


        listGasweight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemgasweight = adapterView.getItemAtPosition(i).toString();
                listGasweight.setText(itemgasweight);

            }
        });


        listGastrademark.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemgastrademark = adapterView.getItemAtPosition(i).toString();
                listGastrademark.setText(itemgastrademark);

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


        return view;

    }

    private void PushData() {

        User user = new User();
        customerName = getActivity().findViewById(R.id.CustomerName);
        customerPhoneNumber = getActivity().findViewById(R.id.CustomerPhoneNumber);
        customeraddress = getActivity().findViewById(R.id.CustomerAddress);
        listGastrademark = getActivity().findViewById(R.id.listGastrademark);
        listGasweight = getActivity().findViewById(R.id.listGasweight);
        radiogroup = getActivity().findViewById(R.id.radioGroup);
        int radioID = radiogroup.getCheckedRadioButtonId();
        radiobtn = getActivity().findViewById(radioID);
        String gender = radiobtn.getText().toString();
        DBref =  FirebaseDatabase.getInstance("https://projectsgm-fc929-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("User");
        String name = customerName.getText().toString().trim();
        String phone = customerPhoneNumber.getText().toString();
        int phonee = Integer.parseInt(phone);
        String address = customeraddress.getText().toString().trim();

        user.setName(name);
        user.setGender(gender);
        user.setPhoneNumber(phonee);
        user.setAddress(address);
        user.setGascanweight(listGasweight.getText().toString());
        user.setGascanTrademark(listGastrademark.getText().toString());
        user.setID(maxid+1);


         DBref.child(String.valueOf(maxid+1)).setValue(user);


//        DatabaseReference myRef = database.getReference("User/"+getuser);
//        DatabaseReference myRef1 = database.getReference("User/"+getuser+"/Gender");
//        DatabaseReference myRef2 = database.getReference("User/"+getuser+"/PhoneNumber");
//        DatabaseReference myRef3 = database.getReference("User/"+getuser+"/Address");
//        myRef.setValue(customerName.getText().toString().trim());
//        myRef1.setValue(gender);
//        myRef2.setValue(customerPhoneNumber.getText().toString().trim());
//        myRef3.setValue(customeraddress.getText().toString().trim());

        DBref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    maxid= (snapshot.getChildrenCount());
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}