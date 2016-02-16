package com.uwaterloo.buf.carloop;

import com.maker.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class sign_up extends Activity {
    private EditText account=null;
    private EditText password=null;
    private EditText password_confirm=null;
    private EditText name=null;
    private RadioGroup sexgroup = null;
    private RadioButton malebutton =null;
    private RadioButton femalebutton = null;
    private Button RegisterButton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        account = (EditText) findViewById(R.id.username);
        password = (EditText)findViewById(R.id.editText2);
        password_confirm = (EditText)findViewById(R.id.editText3);
        name=(EditText)findViewById(R.id.editText4);
        sexgroup = (RadioGroup)findViewById(R.id.radioGroup);
        malebutton = (RadioButton)findViewById(R.id.radioButton1);
        femalebutton = (RadioButton)findViewById(R.id.radioButton2);
        RegisterButton = (Button)findViewById(R.id.button1);
        sexgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if(malebutton.getId() == checkedId)
                    Toast.makeText(sign_up.this, "male",Toast.LENGTH_SHORT).show();
                else if(femalebutton.getId() == checkedId)
                    Toast.makeText(sign_up.this, "female",Toast.LENGTH_SHORT).show();
            }
        });
        RegisterButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(validate()){
                    String result=query();
                    if(result.equals("1"))
                    {
                        Toast.makeText(sign_up.this, "ע��ɹ�", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent();
                        intent.setClass(sign_up.this, MainActivity.class);
                        sign_up.this.startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(sign_up.this, "���粻�ȶ���������", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    private boolean validate(){
        String acc=account.getText().toString();
        if(acc.equals("")){
            Toast.makeText(sign_up.this, "�û�����Ϊ��", Toast.LENGTH_LONG).show();
            return false;
        }
        String pwd=password.getText().toString();
        String pwd_confirm=password_confirm.getText().toString();
        if(pwd.equals("")){
            Toast.makeText(sign_up.this, "���벻��Ϊ��", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!pwd.equals(pwd_confirm)){
            Toast.makeText(sign_up.this, "���벻һ�£�����������", Toast.LENGTH_LONG).show();
            return false;
        }
        String n=name.getText().toString();
        if(n.equals("")){
            Toast.makeText(sign_up.this, "������Ϊ��", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    private String query(){
        String acc=account.getText().toString();
        String pwd=password.getText().toString();
        String n=name.getText().toString();
        String sex=null;
        if(malebutton.isChecked()){
            sex="m";
        }else
            sex="f";
        String queryString="account="+acc+"&password="+pwd+"&name="+n+"&sex="+sex;
        String url=HttpUtil.BASE_URL+"servlet/RegisterServlet?"+queryString;
        return HttpUtil.queryStringForPost(url);
    }
}
