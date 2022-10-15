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

import gasproject.my.fragments.CustomerInfoForManager;

public class RecycleAdapterManager extends RecyclerView.Adapter<RecycleAdapterManager.MyViewHolder>{
    Context context;
    private final RecycleViewInterface recycleViewInterface;
    private OnItemClickListener listener;
    private ArrayList<User> userlist;

    public RecycleAdapterManager(Context context , ArrayList<User> userlist,RecycleViewInterface recycleViewInterface){
        this.userlist = userlist;
        this.context = context;
        this.recycleViewInterface = recycleViewInterface;
    }
    public class MyViewHolder extends  RecyclerView.ViewHolder{
        private TextView nameText;
        private TextView statusText;
        private CardView layout;
        public MyViewHolder(final View view,RecycleViewInterface recycleViewInterface){
            super(view);
            nameText = view.findViewById(R.id.name);
            statusText = view.findViewById(R.id.status);
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

        holder.nameText.setText(user.getName());
        if(user.getStatus().equals("complete"))
        {
            holder.statusText.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.correct,0,0,0);
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                CustomerInfoForManager fragment1 = new CustomerInfoForManager(user.getName(),user.getPhoneNumber(),user.getAddress(),user.getGender(),user.getGastrademark(),user.getGasproduct(),user.getStatus(),user.getDateTime(),user.getDeliverydatetime());
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
