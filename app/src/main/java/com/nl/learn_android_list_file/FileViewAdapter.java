package com.nl.learn_android_list_file;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class FileViewAdapter extends ArrayAdapter{
    Context context;
    File[] files;
    int resource;
    LayoutInflater inflater;
    public FileViewAdapter(@NonNull Context context, int resource, @NonNull File[] files) {
        super(context, resource, files);
        this.context=context;
        this.files=files;
        this.resource=resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=inflater.inflate(resource,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.imgFile=convertView.findViewById(R.id.imgItem);
            viewHolder.tvPath=convertView.findViewById(R.id.tvPath);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        ImageView imgFile= viewHolder.imgFile;
        TextView tvPath=viewHolder.tvPath;
        File file=files[position];
        if(file.isDirectory()){
            imgFile.setImageResource(R.drawable.ic_folder);
        }
        else{
            imgFile.setImageResource(R.drawable.ic_file);
        }
        tvPath.setText(file.getName());
        return convertView;
    }
    class ViewHolder{
        ImageView imgFile;
        TextView tvPath;
    }
}
