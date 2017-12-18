package com.eatit.user.eatit.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.eatit.user.eatit.Model.Orders;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 19-Dec-17.
 */

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "EatItDB.db";
    private static final int DB_VER = 1;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Orders> getCarts() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ProductID", "ProductName", "Quantity", "Price", "Discount"};
        String sqTable = "OrderDetail";

        qb.setTables(sqTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        final List<Orders> result = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                result.add(new Orders(c.getString(c.getColumnIndex("ProductID")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price")),
                        c.getString(c.getColumnIndex("Discount"))
                ));
            } while (c.moveToNext());
        }
        return result;
    }

    public void addToCart(Orders order) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(ProductID,ProductName,Quantity,Price,Discount) VALUES ('%s','%s','%s','%s','%s')",
                order.getProductID(), order.getProductName(), order.getQuantity(), order.getPrice(), order.getDiscount());
        db.execSQL(query); // Write to SQlite Database
    }

    public void cleanCart() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");
        db.execSQL(query); // Write to SQlite Database
    }
}
