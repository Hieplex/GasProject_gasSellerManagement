package gasproject.my.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import gasproject.my.R;


public class ShipperView extends Fragment {

    private  Button camerabtn ;
    private ImageView imgview ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view  = inflater.inflate(R.layout.fragment_shipper_view,container,false);

        camerabtn = view.findViewById(R.id.buttoncamera);
        imgview = view.findViewById(R.id.CaptureImg);
       camerabtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        startActivityForResult(open_camera,100);
                                    }
                                });
        return view ;


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap photo = (Bitmap)data.getExtras().get("data");
        imgview.setImageBitmap(photo);
    }

}