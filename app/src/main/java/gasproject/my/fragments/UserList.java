package gasproject.my.fragments;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;
import gasproject.my.R;


public class UserList extends Fragment {

    String[] items = {"12kg","45kg","48kg"};
    String[] items2 = {"Petrolimex","PV gas","SaiGon Petro gas"};
    ArrayAdapter<String> adapterItems;
    AutoCompleteTextView listgas;
    AutoCompleteTextView listgasbrand;
    EditText customerName ;
    EditText customerPhoneNumber ;
    EditText customeraddress ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user_list, container, false);

        listgas = view.findViewById(R.id.listGas);

        adapterItems = new ArrayAdapter<String>(getActivity(),R.layout.list_users,items);

        listgas.setAdapter(adapterItems);

        listgas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
               Toast.makeText(getContext(), "Item:"+item, Toast.LENGTH_SHORT).show();
            }
        });

                listgasbrand = view.findViewById(R.id.listGasbrand);

        adapterItems = new ArrayAdapter<String>(getActivity(),R.layout.list_users,items2);

        listgasbrand.setAdapter(adapterItems);

        listgasbrand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), "Item:"+item, Toast.LENGTH_SHORT).show();
            }
        });

        customerName = view.findViewById(R.id.CustomerName);
        customerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EditText) view.findViewById(R.id.CustomerName)).setText("");

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
        return view;




    }

}