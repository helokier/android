package com.example.androidnetworkinglab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterContact extends BaseAdapter {
    Context context;
    ArrayList<Contact> contactlist;
    public AdapterContact(Context context, ArrayList<Contact> contacts){
       this.context = context;
       this.contactlist = contacts;


    }
    @Override
    public int getCount() {
        return contactlist.size();
    }

    @Override
    public Object getItem(int position) {
        return contactlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        if (convertView == null){
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.listitem,null);
               holder.view = (TextView)convertView.findViewById(R.id.textView1);
            holder.email = (TextView)convertView.findViewById(R.id.textView2);
            holder.tvmobile = (TextView)convertView.findViewById(R.id.textView3);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        Contact contact = contactlist.get(position);
         holder.view.setText(contact.getName());
        holder.email.setText(contact.getEmail());
        holder.tvmobile.setText(contact.getEmail());
        return convertView;
    }
    public  static  class ViewHolder{
        TextView view,email,tvmobile;
    }
}
