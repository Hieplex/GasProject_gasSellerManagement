package gasproject.my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import gasproject.my.fragments.CustomerInfo;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder>{
    Context context;
    private final RecycleViewInterface recycleViewInterface;
    private OnItemClickListener listener;
    private ArrayList<User> userlist;

    public RecycleAdapter(Context context , ArrayList<User> userlist,RecycleViewInterface recycleViewInterface){
        this.userlist = userlist;
        this.context = context;
        this.recycleViewInterface = recycleViewInterface;
    }
    public class MyViewHolder extends  RecyclerView.ViewHolder{
        private TextView nameText;
        private TextView status;
        private CardView layout;
        public MyViewHolder(final View view,RecycleViewInterface recycleViewInterface){
            super(view);
            nameText = view.findViewById(R.id.name);
            status = view.findViewById(R.id.status);
            layout = view.findViewById(R.id.listname);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(recycleViewInterface != null )
                    {
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            recycleViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemclick(DataSnapshot snapshot, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.customerlistview,parent ,false);
        return new MyViewHolder(itemview,recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = userlist.get(position);
        System.out.println("hi" +user.getID());

        holder.nameText.setText(user.getName());
        if(user.getStatus().equals("not complete"))
        {
            holder.status.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.uncheck,0,0,0);
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                System.out.println("hi2 " +user.getID());

                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                CustomerInfo fragment1 = new CustomerInfo(user.getName(),user.getPhoneNumber(),user.getAddress(),user.getGender(),user.getGastrademark(), user.getGasproduct(),user.getStatus(),user.getID(),user.getDateTime(),user.getDeliverydatetime());
                transaction.replace(R.id.frame_layout, fragment1);
                transaction.commit();
            }
        });
    }



    @Override
    public int getItemCount() {
        return userlist.size();
    }

}
