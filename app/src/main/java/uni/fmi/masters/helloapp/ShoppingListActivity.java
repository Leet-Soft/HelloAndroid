package uni.fmi.masters.helloapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import uni.fmi.masters.helloapp.databinding.ActivityShoppingListBinding;
import uni.fmi.masters.helloapp.entity.ShoppingItem;

public class ShoppingListActivity extends AppCompatActivity {

    RecyclerView shoppingList;
    FloatingActionButton fab;
    ShoppingListAdapter adapter;
    static List<ShoppingItem> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shopping_list);

        shoppingList = findViewById(R.id.shoppingListRV);
        fab = findViewById(R.id.fab);

        items.add(new ShoppingItem("Мляко", 2,"бр."));
        items.add(new ShoppingItem("Домати", 2.5,"кг."));
        items.add(new ShoppingItem("Луканка", 1,"бр."));
        items.add(new ShoppingItem("Ракия", 5,"л."));
        items.add(new ShoppingItem("Русенско варено", 4,"бр."));
        items.add(new ShoppingItem("Кайма", 1.5,"кг."));

        adapter = new ShoppingListAdapter(items);

        shoppingList.setAdapter(adapter);
        shoppingList.setLayoutManager(new LinearLayoutManager(this));



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AddEditDialog(ShoppingListActivity.this);


                adapter.notifyDataSetChanged();


            }
        });
    }


}