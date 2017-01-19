package com.example.loancalculator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText editText1,editText2,editText3,editText4,editText5,editText6,editText7;
	TextView resultText;
    Button button1;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
    }   
               
         public void calculateClickHandler(View view) {
     		if (view.getId() == R.id.newentry) {
     			 LoanOperation db = new LoanOperation(this);
     	         db.open();
     			editText1=(EditText)findViewById(R.id.usname);
     	         editText2=(EditText)findViewById(R.id.ramount);
     	         editText3=(EditText)findViewById(R.id.editText3);
     	         editText4=(EditText)findViewById(R.id.editText4);
     	        editText5=(EditText)findViewById(R.id.agent);
     	       editText6=(EditText)findViewById(R.id.date);
     	      editText7=(EditText)findViewById(R.id.location);
     	         button1=(Button)findViewById(R.id.newentry);
     	         TextView resultText = (TextView)findViewById(R.id.textView5);

     	        String edit1=editText1.getText().toString();
     	       String edit2=editText2.getText().toString();
     	      String edit3=editText3.getText().toString();
     	     String edit4=editText4.getText().toString();  
     	    String edit5=editText5.getText().toString(); 
     	   String edit6=editText6.getText().toString(); 
     	  String edit7=editText7.getText().toString(); 
             
     	    float sum = Float.parseFloat(editText2.getText().toString());
		      float rate = Float.parseFloat(editText3.getText().toString());
		      float time = Float.parseFloat(editText4.getText().toString());
		      
     	 //   float emiValue = calculateEMI(sum, rate,time);
		      Calendar c1 = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
		      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		     try {
		   	  c1.setTime(sdf.parse(edit6));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			}
		      Calendar cal = Calendar.getInstance();         
		      cal.set(2014, 03 - 1, 13, 7, 30);
		      ContentValues l_event = new ContentValues();
		     Intent intent = new Intent(Intent.ACTION_EDIT);
		     intent.setType("vnd.android.cursor.item/event");
		     intent.putExtra("beginTime", c1.getTimeInMillis());
		     intent.putExtra("allDay", false);
		     intent.putExtra("rrule", "FREQ=MONTHLY");
		     intent.putExtra("endTime", c1.getTimeInMillis()+60*60*1000);
		     intent.putExtra("title", edit1);
		     intent.putExtra("description", "Monthly EMI due to paid");
		     intent.putExtra("eventLocation",edit7);
		      
		   //   l_event.put("eventTimezone", "India");
		      startActivity(intent);
		  //    Uri l_eventUri;
		   //   if (Build.VERSION.SDK_INT >= 8) {
		  //        l_eventUri = Uri.parse("content://com.android.calendar/events");
		   //   } else {
		   //       l_eventUri = Uri.parse("content://calendar/events");
		   //   }
		  //    Uri l_uri = MainActivity.this.getContentResolver()
		 //             .insert(l_eventUri, l_event);
		      // now set the value in the result text

		    //  resultText.setText("" +emiValue);
     	     
                    long result=db.addUser(edit1, edit2, edit3,edit4,edit5,edit6,edit7);
                  //  db.close();
                    Toast.makeText(MainActivity.this,"DataSaved",Toast.LENGTH_LONG).show();// TODO Auto-generated method stub
                  	}
               

            }

	//	private float calculateEMI(float sum, float rate, float time) {
			// TODO Auto-generated method stub
	//		return (float) (sum + (sum * rate/100)/12 * time)/time;
	//	}
    }
       

    
