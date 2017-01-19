package com.example.loancalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LoanOperation {

	 // Database fields
	    private DataBaseWrapper dbHelper;
	    private String[] LOAN_TABLE_COLUMNS = { DataBaseWrapper.USER_ID, DataBaseWrapper.USER_NAME,DataBaseWrapper.AMOUNT,
	    		                                DataBaseWrapper.RATE,DataBaseWrapper.TIME,DataBaseWrapper.AGENT,DataBaseWrapper.LOCATION,DataBaseWrapper.DATE };
	    private SQLiteDatabase database;
	    private static final String U_NAME = "todo";
	    /******** if debug is set true then it will show all Logcat message *******/
	    public static final boolean DEBUG = true;
	     
	    /******************** Logcat TAG ************/
	    public static final String LOG_TAG = "DBAdapter";
	 
	    public LoanOperation(Context context) {
	    	if (DEBUG)
                Log.i("DBAdapter", context.toString());
	            dbHelper = new DataBaseWrapper(context);
	    
	    }
	    public void open() throws SQLException {
	
	        database = dbHelper.getWritableDatabase();
	    }

	    public void close() {
	        dbHelper.close();
	   }
	
	    public long addUser(String name,String amt,String rate, String time,String agent,String date,String location) {
	
	        ContentValues values = new ContentValues();

	        values.put(DataBaseWrapper.USER_NAME, name);
	        values.put(DataBaseWrapper.AMOUNT, amt);
	        values.put(DataBaseWrapper.RATE, rate);
	        values.put(DataBaseWrapper.TIME, time);
	        values.put(DataBaseWrapper.AGENT, agent);
	        values.put(DataBaseWrapper.DATE, date);
	        values.put(DataBaseWrapper.LOCATION, location);
	        	 
	        long loanId = database.insert(DataBaseWrapper.LOAN, null, values);
        
	        database.close();
           
	        return loanId;

	    }
           
	    //Fetch the records to display 	    
	    public LoanObj findUser(String name) {
	    	Log.v("Field Val", name);
	    	String query = "Select * FROM " + DataBaseWrapper.LOAN + " WHERE " + DataBaseWrapper.USER_NAME + " =  \"" + name + "\"";
	    	
	    	Log.v("LOG", query);  	
	    	Cursor cursor = database.rawQuery(query, null);
	     	LoanObj loanobj = new LoanObj();	    	   	
	    	Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(cursor));
	    	//int count = 0;
	   try {
		   	if (cursor.moveToFirst()){
		      	//cursor.moveToFirst();
		   		 	
		   	     loanobj.setId((cursor.getInt(0)));
		   		 loanobj.setName(cursor.getString(1));
		         loanobj.setAmt(cursor.getInt(2));
		         loanobj.setRate(cursor.getInt(3));
		         loanobj.setTime(cursor.getInt(4));
		         loanobj.setAgent(cursor.getString(5));
		         loanobj.setDate(cursor.getString(6));
		         loanobj.setLocation(cursor.getString(7));
		         	  
	           }
		     return loanobj;
		   }  
	    	 finally {
	    	      if (cursor != null) {
	    	         cursor.close();
	    	      }
	    	   }
	    }
	    
	  //Update the records to display 	    
	    public int UpdateUser(String name,String amt) {
	    	// 1. get reference to writable DB
	     //   SQLiteDatabase db = this.database;
	    	LoanObj loanobj = new LoanObj();
	    	ContentValues values = new ContentValues();
	    	LoanObj loanobj1=findUser(name);
	    	int amt1=  (int) loanobj1.getAmt();
	    	int amt2 = amt1-Integer.parseInt(amt);
	    	  

		    values.put(DataBaseWrapper.AMOUNT,amt2);
		    values.put(DataBaseWrapper.RATE,5);
		        
		    String whereClause = DataBaseWrapper.USER_NAME + " = ? ";// and " + DATE_COLUMN + " = ?";
		    String[] whereArgs = new String[] {name};
		     		
		    return  database.update(DataBaseWrapper.LOAN, values, whereClause, whereArgs);  
		 	       
		       		       
	    }
	    
	    // Getting the total row Count
	    public int getContactsCount() {
	       String countQuery = "SELECT  * FROM " + DataBaseWrapper.LOAN;
	       
	        Cursor cursor = database.rawQuery(countQuery, null);
	       int count = 0;
	       try {
	          if (cursor.moveToFirst()) {
	        	  count= cursor.getCount();
	          }
	          return count;
	       }      
	       finally {
	    	      if (cursor != null) {
	    	         cursor.close();
	    	      }
	    	   }
	       
	    }
	    
	    public Cursor getAllComments() {
	        ArrayList<LoanObj> comments = new ArrayList<LoanObj>();

	        Cursor cursor = database.query(DataBaseWrapper.LOAN,
	        		LOAN_TABLE_COLUMNS, null, null, null, null, null);
	        Log.v("Cursor Details", DatabaseUtils.dumpCursorToString(cursor));
	        cursor.moveToFirst();
	       // LoanObj loan = new LoanObj();
	        while (cursor.moveToNext()) {
	       	LoanObj comment = parseLoanObj(cursor);
	        	
	          comments.add(comment);
	          cursor.moveToNext();
	        }
	        // make sure to close the cursor
	     //   cursor.close();
	        return cursor;
	      }
	    
	    public List<String> Get_Contacts() {
	    	
	     		List<String> test = new ArrayList<String>();
	    	// Select All Query
	    //	String selectQuery = "SELECT * FROM " + DataBaseWrapper.LOAN;
	     		 Cursor cursor = database.query(DataBaseWrapper.LOAN,
	 	        		LOAN_TABLE_COLUMNS, null, null, null, null, null);		
	    	//Log.v("LOG", selectQuery); 
	    	//Cursor cursor = database.rawQuery(selectQuery, null);
	    	LoanObj loanobj = new LoanObj(); 
	    	// looping through all rows and adding to list
	    	Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(cursor));
	    //	try{
	    		while(cursor.moveToFirst()) {
	    		
		   		      test.add(cursor.getString(1));
		   		     cursor.moveToNext();      
	            }
	    	  return test;  	
	    //	}
	    //	  finally {
	    //	      if (cursor != null) {
	    //	         cursor.close();
	   // 	      }
	    //	   }
	    	  
	    	}
	    
	    private LoanObj parseLoanObj(Cursor cursor) {
	    	 LoanObj loan = new LoanObj();
	    		
		        loan.setId((cursor.getInt(0)));
		        loan.setName(cursor.getString(1));
		        loan.setAmt(cursor.getInt(2));
		        loan.setRate(cursor.getInt(3));
		        loan.setTime(cursor.getInt(4));
		        loan.setAgent(cursor.getString(5));
		        loan.setDate(cursor.getString(6));
		        loan.setLocation(cursor.getString(7));
		
		        return loan;
		}
		
	  
	 
	
	 

}
