package com.feed.arivusitrus.finalfeed;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHub extends SQLiteOpenHelper {

	private static final String dbname = "demo.db";
	private static final int version = 2;
	public static String Ename = "Ename";
	public static String Eid = "Eid";
	public static String Eadd = "Eadd";
	public static String Emp1 = "Emp1";

    public DataBaseHub(Context context) {
        super(context, dbname, null, version);
    }



    @Override
	public void onCreate(SQLiteDatabase db) {
		String employee1 = "create table " + Emp1 + "(" + Eid
				+ " text," + Ename
				+ " text," + Eadd + " text)";
		
		db.execSQL(employee1);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		if (oldVersion < newVersion) {
			String employee1 = "create table emp1(" + Eid + " text," + Ename
					+ " text," + Eadd + " text)";
			db.execSQL(employee1);
		}
	}
}
