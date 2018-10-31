package com.antonblue16.mycatalogueuiux;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class NowPlayingFragment extends Fragment
{
    private RecyclerView rvListFilm;
    private RecyclerView.Adapter adapter;
    private View view;
    private List<FilmItems> filmList;

    private static final String API_KEY = "70da25e37e7e5105ac0b8484979d4056";
    private String URL = "https://api.themoviedb.org/3/movie/now_playing?api_key="+API_KEY+"&language=en-US";

    public NowPlayingFragment()
    {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_now_playing, container, false);

        rvListFilm = view.findViewById(R.id.rvCategoryNowPlaying);
        rvListFilm.setHasFixedSize(true);
        rvListFilm.setLayoutManager(new LinearLayoutManager(getActivity()));
        filmList = new ArrayList<>();

        loadDataFilm();

        return view;
    }

    private void loadDataFilm()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>()
        {

            @Override
            public void onResponse(String response)
            {
                progressDialog.dismiss();

                try
                {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray arrayJson = jsonObject.getJSONArray("results");
                    for(int i=0; i<arrayJson.length(); i++)
                    {
                        FilmItems films = new FilmItems();

                        JSONObject dataFilm = arrayJson.getJSONObject(i);

                        films.setFilmName(dataFilm.getString("title"));
                        films.setFilmSynopsis(dataFilm.getString("overview"));
                        films.setFilmDateTime(dataFilm.getString("release_date"));
                        films.setImageFilm(dataFilm.getString("poster_path"));
                        films.setFilmRate(dataFilm.getString("vote_average"));
                        films.setFilmLanguage(dataFilm.getString("original_language"));
                        filmList.add(films);
                    }

                    adapter = new NowPlayingAdapter(filmList, getActivity());
                    rvListFilm.setAdapter(adapter);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(getActivity(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                loadDataFilm();
            }
        });

        RequestQueue rq = Volley.newRequestQueue(getActivity());
        rq.add(stringRequest);
    }
}
