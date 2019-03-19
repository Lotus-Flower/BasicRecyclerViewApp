package meehan.matthew.androidcodingchallenge.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import meehan.matthew.androidcodingchallenge.Model.Product;
import meehan.matthew.androidcodingchallenge.R;

public class ProductListActivity extends AppCompatActivity implements ProductListContract.View {

    private RecyclerView recyclerView;
    private ProductListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new ProductListPresenter(this);

        setupUI();
    }

    /**
     * Sets up the recyclerview to be prepared for population
     */
    private void setupUI(){
        recyclerView = findViewById(R.id.product_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new ProductListAdapter(new ArrayList<Product>()));

        presenter.getProducts();
    }

    /**
     * Displays products in recyclerview
     * @param products - an ArrayList of products from the presenter
     */
    public void showProducts(ArrayList<Product> products){
        recyclerView.setAdapter(new ProductListAdapter(products));
        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Release reference to view in presenter to avoid memory leaks
        presenter.destroy();
    }
}
