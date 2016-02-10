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
				//�����ַ������ɶ�ά��ͼƬ����ʾ�ڽ����ϣ��ڶ�������ΪͼƬ�Ĵ�С��350*350��
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