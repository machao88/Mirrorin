package com.example.mirrorin;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


@SuppressLint("ShowToast")
public class MainActivity extends Activity implements OnClickListener {

	private Button bt_activitycap;
	private Button bt_screencap;
	private TextView text;
	private static int count = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		bt_activitycap = (Button)findViewById(R.id.activitycap);
		bt_screencap = (Button)findViewById(R.id.screencap);
		text = (TextView)findViewById(R.id.textView1);
		
		bt_activitycap.setOnClickListener(this);
		bt_screencap.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.activitycap:
			ScreenShot picture = new ScreenShot();
			picture.shoot(this);
			break;	
		case R.id.screencap:
			getScreen();
			break;
			
		default:
			break;
		}
	}
	
	
    
    //创建文件夹及文件  
    public void CreateText() throws IOException {  
    	
    	String dir1 = "/mnt/sdcard/";
    	String filenameTemp = dir1 + "c.png";
        File file = new File(dir1);  
        if (!file.exists()) {  
            try {  
                //按照指定的路径创建文件夹  
                file.mkdirs();  
            } catch (Exception e) {  
                // TODO: handle exception  
            }  
        }  
        File dir = new File(filenameTemp);  
        if (!dir.exists()) {  
              try {  
                  //在指定的文件夹中创建文件  
                  dir.createNewFile();  
            } catch (Exception e) {  
            }  
        }
        else {
        	Toast.makeText(this, filenameTemp + "exist", 100);
        }
  
    }  
      
	
	public void getScreen()
	{
/*		try {
			CreateText();
		} catch (IOException e1) {
			Toast.makeText(this, "create dir fail", 100);
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		try {
			Process p = Runtime.getRuntime().exec("/system/bin/screencap -p /mnt/sdcard/c.png  \n");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String s = count++ + " OK/n";
	        BufferedReader in = new BufferedReader(  
	                            new InputStreamReader(p.getInputStream()));  
	        String line = null;  
	        while ((line = in.readLine()) != null) {  
	            s += line + "/n";                 
	        }  

            text.setText(s); 
            Toast.makeText(this, "run ok", 200).show();;
           
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "run fail", 200).show();
			e.printStackTrace();
		}
	}


	

}
