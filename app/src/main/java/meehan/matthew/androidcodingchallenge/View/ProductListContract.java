package meehan.matthew.androidcodingchallenge.View;

import java.util.ArrayList;

import meehan.matthew.androidcodingchallenge.Model.Product;

public interface ProductListContract {
    interface View{

        void showProducts(ArrayList<Product> products);

    }

    interface Presenter{

        void getProducts();

        void destroy();

    }
}
