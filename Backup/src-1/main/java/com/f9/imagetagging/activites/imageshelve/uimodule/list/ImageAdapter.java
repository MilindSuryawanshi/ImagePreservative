package com.f9.imagetagging.activites.imageshelve.uimodule.list;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.f9.imagetagging.activites.imageshelve.R;

/**
 * Created by Milind Suryawanshi on 4/27/16.
 */
public class ImageAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mInflater;
    private int count =0;
    private Bitmap thumbnails[];
    private boolean[] thumbnailsselection;
    private String[] arrPath;

    public ImageAdapter(Context _context,Bitmap _thumbnails[],boolean[] _thumbnailsselection,String[] _arrPath) {
        this.context = _context;
        this.thumbnails = _thumbnails;
        this.thumbnailsselection = _thumbnailsselection;
        this.arrPath = _arrPath;
        this.count = thumbnails.length ;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return count;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(
                    R.layout.gallaryitem, null);
            holder.imageview = (ImageView) convertView.findViewById(R.id.thumbImage);
            holder.checkbox = (CheckBox) convertView.findViewById(R.id.itemCheckBox);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.checkbox.setId(position);
        holder.imageview.setId(position);
        holder.checkbox.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                CheckBox cb = (CheckBox) v;
                int id = cb.getId();
                if (thumbnailsselection[id]){
                    cb.setChecked(false);
                    thumbnailsselection[id] = false;
                } else {
                    cb.setChecked(true);
                    thumbnailsselection[id] = true;
                }
            }
        });
        holder.imageview.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                int id = v.getId();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("file://" + arrPath[id]), "image/*");
                context.startActivity(intent);
            }
        });
        holder.imageview.setImageBitmap(thumbnails[position]);
        holder.checkbox.setChecked(thumbnailsselection[position]);
        holder.id = position;
        return convertView;
    }
}
class ViewHolder {
    ImageView imageview;
    CheckBox checkbox;
    int id;
}
