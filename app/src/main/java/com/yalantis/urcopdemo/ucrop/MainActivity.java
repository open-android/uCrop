package com.yalantis.urcopdemo.ucrop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.yalantis.ucrop.UCrop;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIv = (ImageView) findViewById(R.id.iv);
    }

    public void start(View view){
        //需要裁剪的图片路径
        Uri sourceUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() , "icon_18.jpg"));

        //裁剪完毕的图片存放路径
        Uri destinationUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() , "icon_18_2.jpg"));

        UCrop.of(sourceUri, destinationUri) //定义路径
                .withAspectRatio(4, 3) //定义裁剪比例 4:3 ， 16:9
                .withMaxResultSize(100, 100) //定义裁剪图片宽高最大值
                .start(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //裁剪成功后调用
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            //设置裁剪完成后的图片显示
            mIv.setImageURI(resultUri);

            //出错时进入该分支
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }
    }
}
