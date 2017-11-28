package com.li.shanfeng.photo.image;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.li.shanfeng.photo.R;
import com.squareup.picasso.Picasso;

public class MyGridAdapter extends BaseAdapter {
	private String[] files;
	private Context context;

	private LayoutInflater mLayoutInflater;

	public MyGridAdapter(String[] files, Context context) {
		this.files = files;
		mLayoutInflater = LayoutInflater.from(context);
		this.context=context;
	}

	@Override
	public int getCount() {
		return files == null ? 0 : files.length;
	}

	@Override
	public String getItem(int position) {
		return files[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MyGridViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new MyGridViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.gridview_item,
					parent, false);
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.album_image);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (MyGridViewHolder) convertView.getTag();
		}
		String url = getItem(position);

		Picasso.with(context).load(url).error(R.color.PGrary).placeholder(R.color.PGrary).into(viewHolder.imageView);

		return convertView;
	}

	private static class MyGridViewHolder {
		ImageView imageView;
	}
}
