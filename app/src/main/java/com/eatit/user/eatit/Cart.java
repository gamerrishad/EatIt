package com.eatit.user.eatit;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eatit.user.eatit.Common.Common;
import com.eatit.user.eatit.Database.Database;
import com.eatit.user.eatit.Model.Orders;
import com.eatit.user.eatit.Model.Requests;
import com.eatit.user.eatit.ViewHolder.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    TextView totalPriceText;
    FButton placeOrderBtn;

    List<Orders> cart = new ArrayList<>();

    CartAdapter adapter;

    // Init Firebase
    FirebaseDatabase database;
    DatabaseReference requests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize Database
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        // Initialize Recycler View
        recyclerView = findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        totalPriceText = findViewById(R.id.totalPriceText);
        placeOrderBtn = findViewById(R.id.PlaceOrderBtn);

        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart.this);
                alertDialog.setTitle("One More Step");
                alertDialog.setMessage("Enter Shipping Address: ");

                // Address Option
                final EditText address = new EditText(Cart.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                address.setLayoutParams(lp);
                alertDialog.setView(address);
                alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Requests request = new Requests(
                                Common.currentUser.getName(),
                                Common.currentUser.getPhone(),
                                address.getText().toString(),
                                totalPriceText.getText().toString(),
                                cart
                        );

                        requests.child(String.valueOf(System.currentTimeMillis())).setValue(request);
                        new Database(getBaseContext()).cleanCart();
                        Toast.makeText(Cart.this, "Added to Cart!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

        loadFoodList();

    }

    private void loadFoodList() {
        cart = new Database(this).getCarts();
        adapter = new CartAdapter(cart, this);
        recyclerView.setAdapter(adapter);

        int total = 0;
        for (Orders order : cart) {
            total += (Integer.parseInt(order.getPrice())) * (Integer.parseInt(order.getQuantity()));
        }

        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        totalPriceText.setText(fmt.format(total));

    }
}
