package com.f9.imagetagging.activites.imageshelve.activites;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.f9.imagetagging.activites.imageshelve.R;

public class GallaryViewActivity extends AppCompatActivity implements View.OnClickListener{
    private int count;
    private Bitmap[] thumbnails;
    public boolean[] thumbnailsselection;
    private String[] arrPath;
    private ImageAdapter imageAdapter;
    private ProgressBar spinner;

    private Button btnSmilyTab;
    private Button btnSelectImages;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary_view);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        btnSmilyTab = (Button) findViewById(R.id.btnSmilyView);
        btnSmilyTab.setOnClickListener(this);

        btnSelectImages = (Button) findViewById(R.id.btnSelectImages);
        btnSelectImages.setOnClickListener(this);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSmilyView :
                Intent intent = new Intent(this,SmileyTabActivity.class);
                startActivity(intent);
                break;

            case R.id.btnSelectImages :
                spinner.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        loadImagesFromSDcard();
                    }
                }).start();


                break;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
//        imagecursor.close();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        imagecursor.close();
    }

    private void loadImagesFromSDcard(){

        final String[] columns = { MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID };
        final String orderBy = MediaStore.Images.Media._ID;
        final Cursor imagecursor = managedQuery(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy);
        int image_column_index = imagecursor.getColumnIndex(MediaStore.Images.Media._ID);
        count = imagecursor.getCount();
        thumbnails = new Bitmap[count];
        arrPath = new String[count];
        thumbnailsselection = new boolean[count];
        for (int i = 0; i < count; i++) {
            imagecursor.moveToPosition(i);
            int id = imagecursor.getInt(image_column_index);
            int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);
            thumbnails[i] = MediaStore.Images.Thumbnails.getThumbnail(
                    getApplicationContext().getContentResolver(), id,
                    MediaStore.Images.Thumbnails.MICRO_KIND, null);
            arrPath[i]= imagecursor.getString(dataColumnIndex);
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                GridView imagegrid = (GridView) findViewById(R.id.PhoneImageGrid);
                imageAdapter = new ImageAdapter();
                imagegrid.setAdapter(imageAdapter);
                imagecursor.close();
                spinner.setVisibility(View.GONE);

                final Button selectBtn = (Button) findViewById(R.id.selectBtn);
                selectBtn.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        int len = thumbnailsselection.length;
                        int cnt = 0;
                        String selectImages = "";
                        for (int i = 0; i < len; i++) {
                            if (thumbnailsselection[i]) {
                                cnt++;
                                selectImages = selectImages + arrPath[i] + "|";
                            }
                        }
                        if (cnt == 0) {
                            Toast.makeText(getApplicationContext(),
                                    "Please select at least one image",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "You've selected Total " + cnt + " image(s).",
                                    Toast.LENGTH_LONG).show();
                            Log.d("SelectedImages", selectImages);
                        }
                    }
                });
            }
        });

    }

    public class ImageAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public ImageAdapter() {//Context _context,Bitmap _thumbnails[],boolean[] _thumbnailsselection,String[] _arrPath
//            this.context = _context;
//            this.thumbnails = _thumbnails;
//            this.thumbnailsselection = _thumbnailsselection;
//            this.arrPath = _arrPath;
//            this.count = thumbnails.length ;
            this.mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                    startActivity(intent);
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
}