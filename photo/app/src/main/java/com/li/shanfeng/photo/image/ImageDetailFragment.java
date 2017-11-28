package com.li.shanfeng.photo.image;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.li.shanfeng.photo.R;
import com.li.shanfeng.photo.image.photoview.PhotoViewAttacher;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ImageDetailFragment extends Fragment {
	private String mImageUrl;
	private ImageView mImageView;
	private ProgressBar progressBar;
	private PhotoViewAttacher mAttacher;
	private ImageView ivsave;
	private Bitmap bitmap;

	public static ImageDetailFragment newInstance(String imageUrl) {
		final ImageDetailFragment f = new ImageDetailFragment();

		final Bundle args = new Bundle();
		args.putString("url", imageUrl);
		f.setArguments(args);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
		
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.image_detail_fragment, container, false);
		mImageView = (ImageView) v.findViewById(R.id.image);
		ivsave=(ImageView)v. findViewById(R.id.iv_imagepager_save);
		ivsave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(bitmap!=null){
//					Tools.saveImage(ImageDetailFragment.this.getActivity(),bitmap);
				}
			}
		});
		mAttacher = new PhotoViewAttacher(mImageView);

		mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

			@Override
			public void onPhotoTap(View arg0, float arg1, float arg2) {
				getActivity().finish();
			}
		});
		
		mAttacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
			
			@Override
			public void onViewTap(View view, float x, float y) {
				getActivity().finish();
				
			}
		});

		progressBar = (ProgressBar) v.findViewById(R.id.loading);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Picasso.with(getContext()).load(mImageUrl).placeholder(R.color.PGrary).error(R.color.PGrary).into(mImageView, new Callback() {
			@Override
			public void onSuccess() {
				progressBar.setVisibility(View.GONE);
				mAttacher.update();
			}

			@Override
			public void onError() {
				Toast.makeText(getActivity(), "图片加载失败", Toast.LENGTH_SHORT).show();
				progressBar.setVisibility(View.GONE);
			}
		});
/*

		options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).showImageOnLoading(R.mipmap.photo_error).showImageOnFail(R.mipmap.photo_error)
				.bitmapConfig(Bitmap.Config.RGB_565).build();

		ImageLoader.getInstance().displayImage(mImageUrl, mImageView, options, new SimpleImageLoadingListener() {
			@Override
			public void onLoadingStarted(String imageUri, View view) {
				ImageDetailFragment.this.bitmap=null;
				progressBar.setVisibility(View.VISIBLE);
			}

			@Override
			public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
				String message = null;
				switch (failReason.getType()) {
				case IO_ERROR:
					message = "图片加载失败";
					break;
				case DECODING_ERROR:
					message = "图片无法显示";
					break;
				case NETWORK_DENIED:
					message = "网络有问题，无法下载";
					break;
				case OUT_OF_MEMORY:
					message = "图片太大无法显示";
					break;
				case UNKNOWN:
					message = "未知的错误";
					break;
				}
				Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
				progressBar.setVisibility(View.GONE);
			}

			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				ImageDetailFragment.this.bitmap=loadedImage;
				progressBar.setVisibility(View.GONE);
				mAttacher.update();
			}
		});
*/

	}

}
