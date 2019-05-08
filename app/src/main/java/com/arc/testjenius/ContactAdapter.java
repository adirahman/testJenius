package com.arc.testjenius;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arc.jenius_api.ContactModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{

    List<ContactModel> listContact = new ArrayList<>();
    Listener listener;
    Context context;

    public ContactAdapter(List<ContactModel> listContact, Listener listener) {
        this.listContact = listContact;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.row_contact,viewGroup,false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {
        final ContactModel data = listContact.get(i);

        contactViewHolder.tvFirstName.setText(String.valueOf(data.fisrtName));
        contactViewHolder.tvLastName.setText(data.lastName);
        contactViewHolder.tvAge.setText(String.valueOf(data.age));

        Glide.with(context).load(data.photo).placeholder(R.mipmap.ic_launcher_round).into(contactViewHolder.imageView);

        contactViewHolder.cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(data.id);
            }
        });

        contactViewHolder.cl.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.delete(data.id);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listContact.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cl)
        ConstraintLayout cl;
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.tvFirstName)
        TextView tvFirstName;
        @BindView(R.id.tvLastName)
        TextView tvLastName;
        @BindView(R.id.tvAge)
        TextView tvAge;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface Listener{
        void onClick(String id);
        void delete(String id);
    }
}
