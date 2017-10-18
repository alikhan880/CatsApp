package kz.kbtu.catsapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class CatFragment extends Fragment {

    private View rootView;
    private Image image;

    public CatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_cat, container, false);
        image = getArguments().getParcelable("image");
        ImageView iv = (ImageView) rootView.findViewById(R.id.iv_cat);
        Picasso.with(getContext()).load(image.getUrl()).fit().centerCrop().into(iv);
        return rootView;
    }

}
