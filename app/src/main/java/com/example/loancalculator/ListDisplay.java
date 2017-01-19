package com.example.loancalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

public class ListDisplay extends ListActivity {
	
	 public void onCreate(Bundle savedInstanceState){
	        super.onCreate(savedInstanceState);
	     //   ArrayList<Map<String, String>> list = buildData();
	        setContentView(R.layout.historylist);
	   
	        final LoanOperation db = new LoanOperation(this);
	         db.open();
	         
	       //  ArrayList<LoanObj> list = (ArrayList<LoanObj>) db.Get_Contacts();
	        Cursor values = (Cursor) db.getAllComments();
	        // use the SimpleCursorAdapter to show the
	        // elements in a ListView
	         String[] from = new String[]{DataBaseWrapper.USER_NAME,DataBaseWrapper.AMOUNT};
	         int[] to = new int[] { R.id.textView1,R.id.textView2};
	         
	         SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.listcontent,values, from,to);
	         
	         setListAdapter(adapter);
	        
	           db.close();
	    }

	
	 
}
