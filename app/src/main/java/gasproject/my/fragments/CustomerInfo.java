package gasproject.my.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import gasproject.my.R;
import gasproject.my.databinding.ActivityMainBinding;


public class CustomerInfo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView capimg;
     Button upimgbtn;
    RecyclerView.Adapter adapter;
    ActivityMainBinding binding;
    Uri uri;
    FirebaseAuth mAuth;
    private StorageReference storageReference;
    String name,phonenumber,address,gender,gasweight,gastrademark;
    public CustomerInfo() {

    }
    public CustomerInfo(String name,int phonenumber,String address,String gender) {
        this.name = name;
        this.phonenumber= String.valueOf(phonenumber);
        this.address = address;
        this.gender = gender;

    }


    // TODO: Rename and change types and number of parameters
    public static CustomerInfo newInstance(String param1, String param2) {
        CustomerInfo fragment = new CustomerInfo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {

                /* perform your actions here*/


            } else {
                signInAnonymously();
            }
        }
    }
    private void signInAnonymously() {
        mAuth.signInAnonymously().addOnSuccessListener(getActivity(), new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //???///
                    }
                })
                .addOnFailureListener(getActivity(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.e("MainActivity", "signFailed****** ", exception);
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_info, container, false);
        TextView textview = view.findViewById(R.id.Name);
        TextView textview2 = view.findViewById(R.id.Phone);
        TextView textview3 = view.findViewById(R.id.Address);
        TextView textview4 = view.findViewById(R.id.Gascanweight);
        TextView textview5 = view.findViewById(R.id.Gastrademark);
        TextView textview6 = view.findViewById(R.id.Gender);
        textview.setText(name);
        textview2.setText(phonenumber);
        textview3.setText(address);
        textview4.setText(gastrademark);
        textview5.setText(gasweight);
        textview6.setText(gender);

            FloatingActionButton cambtn = view.findViewById(R.id.CamIcon);
            capimg = view.findViewById(R.id.CapImg);
                cambtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(open_camera,100);
                    }
                });

        storageReference = FirebaseStorage.getInstance().getReference();

                return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap photo = (Bitmap)data.getExtras().get("data");
        capimg.setImageBitmap(photo);
        upimgbtn=getActivity().findViewById(R.id.btnpushimg);
        upimgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oncaptureImg(data);

            }
        });

    }
    public void oncaptureImg(Intent data){
        Bitmap photo = (Bitmap)data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG,90,bytes);
        byte bb[] = bytes.toByteArray();

        uploadimg(bb);
    }
    public void uploadimg(byte[] bb){
        StorageReference sr = storageReference.child("image/"+name+".jpg");
        sr.putBytes(bb).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

}