package com.li.shanfeng.photo.image;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by Administrator on 2016/8/4.
 */
public class Myapp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration configuration = null;
        File filePath = getSDFile("/tengguobao");
        if (filePath != null) {
            // 设置 内存缓存手机内存13%,设置 本地缓存路径
            configuration = new ImageLoaderConfiguration.Builder(this).memoryCacheSizePercentage(13)
                    .memoryCache(new WeakMemoryCache()).diskCache(new UnlimitedDiskCache(filePath)).build();
        } else {
            // 没有sd卡
            configuration = ImageLoaderConfiguration.createDefault(this);
        }
        ImageLoader.getInstance().init(configuration);
    }
    // 在sd卡下创建一个目录(传入传参数“/51home”)
    public  File getSDFile(String path) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // 创建一个文件夹对象，赋值为外部存储器的目录
            File sdcardDir = Environment.getExternalStorageDirectory();
            // 得到一个路径，内容是sdcard的文件夹路径和名字
            String pathTemp = sdcardDir.getPath() + path;
            File pathFile = new File(pathTemp);
            if (!pathFile.exists()) {
                // 若不存在，创建目录，可以在应用启动的时候创建
                pathFile.mkdirs();
            }
            return pathFile;
        }
        return null;
    }

}
