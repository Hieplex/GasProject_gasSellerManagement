package gasproject.my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

        public MyViewHolder(final View view,RecycleViewInterface recycleViewInterface){
            super(view);
            nameText = view.findViewById(R.id.name);


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

        holder.nameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                CustomerInfo fragment1 = new CustomerInfo(user.getName(),user.getPhoneNumber(),user.getAddress(),user.getGender(),user.getGastrademark(), user.getGasproduct());
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
