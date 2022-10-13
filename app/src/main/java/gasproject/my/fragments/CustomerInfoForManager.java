package gasproject.my.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class CustomerInfoForManager extends Fragment {

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
    String name,phonenumber,address,gender,gasproduct,gastrademark,status,datetime;
    Image img;
    public CustomerInfoForManager() {

    }
    public CustomerInfoForManager(String name, int phonenumber, String address, String gender,String gastrademark,String gasproduct,String status,String datetime) {
        this.name = name;
        this.phonenumber= String.valueOf(phonenumber);
        this.address = address;
        this.gender = gender;
        this.gasproduct = gasproduct;
        this.gastrademark = gastrademark;
        this.status = status;
        this.img = img;
        this.datetime = datetime;
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

        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_info_for_manager, container, false);
        TextView textview = view.findViewById(R.id.Name);
        TextView textview2 = view.findViewById(R.id.Phone);
        TextView textview3 = view.findViewById(R.id.Address);
        TextView textview4 = view.findViewById(R.id.Gascanproduct);
        TextView textview5 = view.findViewById(R.id.Gastrademark);
        TextView textview6 = view.findViewById(R.id.Gender);
        TextView textView7 = view.findViewById(R.id.GasStatus);
        TextView textView8 = view.findViewById(R.id.Datetime);
        ImageView imgview = view.findViewById(R.id.CapImg);
        textview.setText(name);
        textview2.setText(phonenumber);
        textview3.setText(address);
        textview4.setText(gastrademark);
        textview5.setText(gasproduct);
        textview6.setText(gender);
        textView7.setText(status);
        textView8.setText(datetime);
        storageReference = FirebaseStorage.getInstance().getReference();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference imgref = storage.getReference()
                .child("image/"+name+".jpg");

        imgref.getBytes(1024*1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                imgview.setImageBitmap(bitmap);
            }
        });



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