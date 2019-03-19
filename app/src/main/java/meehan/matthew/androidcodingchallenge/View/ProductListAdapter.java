package meehan.matthew.androidcodingchallenge.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import meehan.matthew.androidcodingchallenge.Model.Product;
import meehan.matthew.androidcodingchallenge.R;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>{

    private ArrayList<Product> products;

    ProductListAdapter(ArrayList<Product> products){
        this.products = new ArrayList<>();
        this.products = products;
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list_view_holder, viewGroup, false);
        return new ProductListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder productListViewHolder, int i) {
        productListViewHolder.titleTextView.setText(products.get(i).getTitle());
        Picasso.get()
                .load(products.get(i).getImageURL())
                .placeholder(R.drawable.ic_android_black_24dp)
                .into(productListViewHolder.productImageView);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductListViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView;
        ImageView productImageView;

        ProductListViewHolder(@NonNull View itemView) {
            super(itemView);

            //Holds the title of the product
            titleTextView = itemView.findViewById(R.id.title_text_view);

            //Holds the image of the product
            productImageView = itemView.findViewById(R.id.product_image_view);
        }

    }
}
