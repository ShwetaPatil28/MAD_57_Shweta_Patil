package com.example.sugarcanenurseryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "nursery.db";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE users(email TEXT PRIMARY KEY, password TEXT, role TEXT)");
        db.execSQL("CREATE TABLE trays(id INTEGER PRIMARY KEY AUTOINCREMENT, tray_no TEXT, seed_type TEXT, date TEXT, status TEXT)");
        db.execSQL("CREATE TABLE varieties(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, yield TEXT, maturity TEXT, price TEXT)");
        db.execSQL("CREATE TABLE orders(id INTEGER PRIMARY KEY AUTOINCREMENT, variety TEXT, quantity TEXT, customer TEXT, status TEXT)");
        db.execSQL("CREATE TABLE irrigation(id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, notes TEXT)");
        db.execSQL("CREATE TABLE pests(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, symptoms TEXT, treatment TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS trays");
        db.execSQL("DROP TABLE IF EXISTS varieties");
        db.execSQL("DROP TABLE IF EXISTS orders");
        db.execSQL("DROP TABLE IF EXISTS irrigation");
        db.execSQL("DROP TABLE IF EXISTS pests");

        onCreate(db);
    }

    // ---------- USERS ----------
    public boolean registerUser(String email, String password, String role) {

        SQLiteDatabase db = this.getWritableDatabase();

        // 🔥 FIX 1: Check duplicate user
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email=?", new String[]{email});
        if (cursor.getCount() > 0) {
            cursor.close();
            return false;
        }
        cursor.close();

        // 🔥 FIX 2: Standardize role (avoid Owner/owner issue)
        role = role.trim();

        ContentValues cv = new ContentValues();
        cv.put("email", email);
        cv.put("password", password);
        cv.put("role", role);

        long result = db.insert("users", null, cv);
        return result != -1;
    }

    public boolean loginUser(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM users WHERE email=? AND password=?",
                new String[]{email, password});

        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }

    public String getUserRole(String email) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT role FROM users WHERE email=?",
                new String[]{email});

        if (cursor.moveToFirst()) {
            String role = cursor.getString(0);
            cursor.close();
            return role;
        }

        cursor.close();
        return "";
    }

    // ---------- TRAYS ----------
    public boolean addTray(String trayNo, String seedType, String date, String status) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("tray_no", trayNo);
        cv.put("seed_type", seedType);
        cv.put("date", date);
        cv.put("status", status);

        long result = db.insert("trays", null, cv);
        return result != -1;
    }

    public Cursor getTrays() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM trays", null);
    }

    // ---------- VARIETIES ----------
    public boolean addVariety(String name, String yield, String maturity, String price) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("yield", yield);
        cv.put("maturity", maturity);
        cv.put("price", price);

        long result = db.insert("varieties", null, cv);
        return result != -1;
    }

    public Cursor getVarieties() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM varieties", null);
    }

    // ---------- ORDERS ----------
    public boolean placeOrder(String variety, String quantity, String customer) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("variety", variety);
        cv.put("quantity", quantity);
        cv.put("customer", customer);
        cv.put("status", "Pending");

        long result = db.insert("orders", null, cv);
        return result != -1;
    }

    public Cursor getOrders() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM orders", null);
    }

    public void updateOrderStatus(int id, String status) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("status", status);
        db.update("orders", cv, "id=?", new String[]{String.valueOf(id)});
    }

    // ---------- IRRIGATION ----------
    public boolean addIrrigation(String date, String notes) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("date", date);
        cv.put("notes", notes);

        long result = db.insert("irrigation", null, cv);
        return result != -1;
    }

    public Cursor getIrrigation() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM irrigation", null);
    }

    // ---------- PEST ----------
    public boolean addPest(String name, String symptoms, String treatment) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("symptoms", symptoms);
        cv.put("treatment", treatment);

        long result = db.insert("pests", null, cv);
        return result != -1;
    }

    public Cursor getPests() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM pests", null);
    }

    // ---------- REPORTS ----------
    public int getTotalTrays() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM trays", null);

        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        return count;
    }

    public int getSuccessfulPlants() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM trays WHERE status='Ready'", null);

        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        return count;
    }

    public int getTotalOrders() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM orders", null);

        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        return count;
    }
}