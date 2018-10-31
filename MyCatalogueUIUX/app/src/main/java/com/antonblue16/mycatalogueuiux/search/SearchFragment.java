package com.antonblue16.mycatalogueuiux.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.antonblue16.mycatalogueuiux.R;

import java.util.Vector;


public class SearchFragment extends Fragment implements LoaderManager.LoaderCallbacks<Vector<FilmItems>>
{
    ListView listView;
    FilmAdapter adapter;
    ImageView ivImageFilm;
    Button btnSearchFilm;
    EditText edFilm;
    View view;

    static final String EXTRA_FILM = "EXTRA_FILM";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        listView = view.findViewById(R.id.lvCataogueFilm);
        edFilm = view.findViewById(R.id.edSearchFilm);
        btnSearchFilm = view.findViewById(R.id.btnSearchFilm);
        ivImageFilm = view.findViewById(R.id.ivSearchImageFilm);

        String filmName = edFilm.getText().toString();

        adapter = new FilmAdapter(getActivity());
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l)
            {
                FilmItems item = (FilmItems) parent.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), DetailFilmActivity.class);

                intent.putExtra(DetailFilmActivity.EXTRA_NAME, item.getFilmName());
                intent.putExtra(DetailFilmActivity.EXTRA_IMAGE,item.getImageFilm());
                intent.putExtra(DetailFilmActivity.EXTRA_LANGUAGE,item.getFilmLanguage());
                intent.putExtra(DetailFilmActivity.EXTRA_AVERAGE,item.getFilmRate());
                intent.putExtra(DetailFilmActivity.EXTRA_SYNOPSIS,item.getFilmSynopsis());
                intent.putExtra(DetailFilmActivity.EXTRA_DATETIME,item.getFilmDateTime());

                startActivity(intent);
            }
        });


        btnSearchFilm.setOnClickListener(filmListener);

        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_FILM, filmName);

        getLoaderManager().initLoader(0, bundle, SearchFragment.this);
        return view;
    }

    @Override
    public Loader<Vector<FilmItems>> onCreateLoader(int i, Bundle bundle)
    {
        String filmName = "";
        if(bundle != null)
        {
            filmName = bundle.getString(EXTRA_FILM);
        }
        return new AsyncTaskLoaderFilm(getActivity(), filmName);
    }

    @Override
    public void onLoadFinished(Loader<Vector<FilmItems>> loader, Vector<FilmItems> filmItems)
    {
        adapter.setData(filmItems);
    }

    @Override
    public void onLoaderReset(Loader<Vector<FilmItems>> loader)
    {
        adapter.setData(null);
    }

    View.OnClickListener filmListener= new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            String filmName = edFilm.getText().toString();
            if(TextUtils.isEmpty(filmName))
            {
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_FILM, filmName);
            getLoaderManager().restartLoader(0, bundle, SearchFragment.this);
        }
    };
}
