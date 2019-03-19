package meehan.matthew.androidcodingchallenge.View;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import meehan.matthew.androidcodingchallenge.Model.Product;
import meehan.matthew.androidcodingchallenge.Network.NetworkClient;
import meehan.matthew.androidcodingchallenge.Network.NetworkInterface;

public class ProductListPresenter implements ProductListContract.Presenter {

    private ProductListContract.View view;

    ProductListPresenter(ProductListContract.View view){
        this.view = view;
    }

    /**
     * Subscribes to network observable that emits a JsonArray of products
     */
    public void getProducts(){
        NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getBooks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonArray>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonArray jsonElements) {
                        parseProducts(jsonElements);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * Takes a JsonArray of products and converts them into the Product class defined in the model
     * @param jsonArray - An array of JsonElements of products
     */
    private void parseProducts(JsonArray jsonArray){
        Gson gson = new Gson();
        ArrayList<Product> products = new ArrayList<>();

        for(JsonElement jsonElement: jsonArray){
            Product product = gson.fromJson(jsonElement, Product.class);
            products.add(product);
        }

        view.showProducts(products);
    }

    //Release reference to the view
    public void destroy(){
        this.view = null;
    }
}
