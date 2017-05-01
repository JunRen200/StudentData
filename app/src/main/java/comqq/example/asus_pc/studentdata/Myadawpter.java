package comqq.example.asus_pc.studentdata;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by asus-pc on 2017/5/1.
 */

public class Myadawpter extends RecyclerView.Adapter<Myadawpter.ViewHolder> {
    private List<student1.StudentBean> list;
    private Context context;
    Myadawpter(List<student1.StudentBean> list, Context context){
        this.list=list;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        student1.StudentBean stu=list.get(position);
        holder.text.setText(stu.getNumber());
        holder.edt_name.setText(stu.getName());
        holder.edt_password.setText(stu.getPassword());
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number= holder.text.getText().toString();
                ((MainActivity)context).onItemclickDelete(number);
            }
        });
        holder.img_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=holder.text.getText().toString();
                String name= holder.edt_name.getText().toString();
                String password=holder.edt_password.getText().toString();
                ((MainActivity)context).onItemclickSet(number,name,password);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setlist(List<student1.StudentBean> list){
        this.list=list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        EditText edt_name;
        EditText edt_password;
        ImageButton img_set;
        ImageButton img_delete;
        public ViewHolder(View itemView) {
            super(itemView);
            text= (TextView) itemView.findViewById(R.id.text_id);
            edt_name= (EditText) itemView.findViewById(R.id.edt_name);
            edt_password= (EditText) itemView.findViewById(R.id.edt_password);
            img_set= (ImageButton) itemView.findViewById(R.id.img_set);
            img_delete= (ImageButton) itemView.findViewById(R.id.img_delete);
        }
    }


    public interface OnDeleteAndSet {
        void onItemclickDelete(String number);

        void onItemclickSet(String number,String name,String password);
    }
}
