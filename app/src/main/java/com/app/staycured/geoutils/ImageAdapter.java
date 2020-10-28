package com.app.staycured.geoutils;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.staycured.Object.MyImage;
import com.app.staycured.R;

import java.io.File;
import java.util.ArrayList;

public class ImageAdapter extends ArrayAdapter<MyImage> {
    private static class ViewHolder {
        ImageView imgIcon;
    }

    public ImageAdapter(Context context, ArrayList<MyImage> images) {
        super(context, 0, images);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        // view lookup cache stored in tag
        ViewHolder viewHolder;
        // Check if an existing view is being reused, otherwise inflate the item view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.childview_grid_images, parent, false);
            viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.my_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Get the data item for this position
        MyImage image = getItem(position);
        // set description text
        // set image icon
        final int THUMBSIZE = 96;
        viewHolder.imgIcon.setImageURI(Uri.fromFile(new File(image.getPath())));
        viewHolder.imgIcon.setImageBitmap(ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(image.getPath()), THUMBSIZE, THUMBSIZE));

        // Return the completed view to render on screen
        return convertView;
    }
}
