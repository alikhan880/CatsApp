package kz.kbtu.catsapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import kz.kbtu.catsapp.Network.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.RecyclerAdapterListener{

    private ArrayList<Image> list;
    private int orientation;
    private RecyclerView recyclerView;
    private Button buttonPrev;
    private Button buttonNext;
    private int chosen;
    private RecyclerAdapter adapter;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        fm = getSupportFragmentManager();
        if(savedInstanceState != null && savedInstanceState.containsKey("images")){
            list = savedInstanceState.getParcelableArrayList("images");
            chosen = savedInstanceState.getInt("chosen");
        }

        orientation = getResources().getConfiguration().orientation;
        bindViews();
        if(list.isEmpty()){
            getCats();
        }
        if(orientation == Configuration.ORIENTATION_LANDSCAPE && !list.isEmpty()){
            itemClicked(chosen);
        }
    }

    private void bindViews() {
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            buttonNext = (Button) findViewById(R.id.button_next);
            buttonPrev = (Button) findViewById(R.id.button_prev);
            buttonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextFragment();
                }
            });
            buttonPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    previousFragment();
                }
            });
        }
        else if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView = (RecyclerView) findViewById(R.id.recycler_fragment_landscape_left);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new RecyclerAdapter(list, this);
            recyclerView.setAdapter(adapter);

            ////adapter
        }
    }

    private void previousFragment() {
        chosen--;
        CatFragment fragment = new CatFragment();
        Bundle bundle = new Bundle();
        check();
        bundle.putParcelable("image", list.get(chosen));
        fragment.setArguments(bundle);
        fm.beginTransaction().replace(R.id.container_fragment, fragment, null).commit();
    }

    private void nextFragment() {
        chosen++;
        CatFragment fragment = new CatFragment();
        Bundle bundle = new Bundle();
        check();
        bundle.putParcelable("image", list.get(chosen));
        fragment.setArguments(bundle);
        fm.beginTransaction().replace(R.id.container_fragment, fragment, null).commit();
    }

    private void check(){
        if(chosen < 0) chosen = list.size() - 1;
        else if(chosen >= list.size()) chosen = 0;
    }



    private void getCats(){
        Call<ResponseCats> call = RestClient.request().getCats("xml", 10);
        call.enqueue(new Callback<ResponseCats>() {
            @Override
            public void onResponse(Call<ResponseCats> call, Response<ResponseCats> response) {
                ResponseCats res = response.body();
                Log.d("hehe", res + "hehe");
                if(res != null){
                    list.clear();
                    list.addAll(res.getData().getImages().getImages());
                    chosen = list.size();
                    if(orientation == Configuration.ORIENTATION_PORTRAIT){
                        nextFragment();
                    }
                    else{
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseCats> call, Throwable t) {
                Log.d("taggy", "failed" + t.toString());
            }
        });
    }

    @Override
    public void itemClicked(int position) {
        CatFragment fragment = new CatFragment();
        Bundle args = new Bundle();
        args.putParcelable("image", list.get(position));
        fragment.setArguments(args);
        fm.beginTransaction().replace(R.id.container_fragment_landscape_right, fragment, null).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("images", list);
        outState.putInt("chosen", chosen);
    }


}
