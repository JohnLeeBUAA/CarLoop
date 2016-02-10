package com.example.helloworld;

import com.google.zxing.WriterException;
import com.maker.dao.User;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class myqrcode extends Activity{
	private User u=new User();
	private ImageView qrImgImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myqrcode);
		Intent intent=this.getIntent();
		u=(User)intent.getSerializableExtra("user");
		qrImgImageView = (ImageView) this.findViewById(R.id.iv_qr_image);

		try {
			String contentString = String.valueOf(u.getId());
			if (!contentString.equals("")) {
				//根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
				Bitmap qrCodeBitmap = EncodingHandler.createQRCode(contentString, 350);
				qrImgImageView.setImageBitmap(qrCodeBitmap);
			}else {
				Toast.makeText(myqrcode.this, "Text can not be empty", Toast.LENGTH_SHORT).show();
			}
			
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}