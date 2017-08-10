package com.startingandroid.sqlitedatabasetutorial.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.startingandroid.sqlitedatabasetutorial.FirstActivity;
import com.startingandroid.sqlitedatabasetutorial.R;
import com.startingandroid.sqlitedatabasetutorial.RecyclerTouchListener;
import com.startingandroid.sqlitedatabasetutorial.VO.Book;
import com.startingandroid.sqlitedatabasetutorial.adapters.BookAdapter;
import com.startingandroid.sqlitedatabasetutorial.database.GetBookDatabase;

import java.util.ArrayList;

/**
 * Created by admin on 1/11/2017.
 */

public class RecyclerviewFragment extends Fragment {

        private static final String TAG = "RecyclerViewFragment";
        private RecyclerView recyclerView;
        private LinearLayoutManager layoutManager;
        private BookAdapter adapter;

        private GetBookDatabase dbHandler;
        protected String[] mDataset;

        @Nullable
        @Override
        public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup
        container, @Nullable Bundle savedInstanceState){
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View rootView = inflater.inflate(R.layout.recyclerview_fragment,container, false);
        rootView.setTag(TAG);
       recyclerView = (RecyclerView) rootView.findViewById(R.id.usersList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
            dbHandler = new GetBookDatabase(getActivity());
            recyclerView.setLayoutManager(layoutManager);

        Log.e("Insert: ", "Inserting...");
        if (dbHandler.getUsersCount() <= 0) {
            try
            {
                dbHandler.createDataBase();
                dbHandler.openDataBase();
                dbHandler.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Reading all contacts
        Log.d("Reading: ", "reading contacts..");
        final ArrayList<Book> books = dbHandler.getAllUsers();
        Log.d("Reading: ", "reading contacts.." + books.size());
        adapter = new BookAdapter(getActivity(),books);
        recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();


            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Book book = books.get(position);
                    Intent intent;
                    intent = new Intent(getActivity(),FirstActivity.class);
                    intent.putExtra("currentBook", book);
                    intent.putExtra("position", position);
                    startActivity(intent);
                }

                @Override
                public void onLongClick(View view, int position)
                {}
            }));


        return rootView;

    }

        @Override
        public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }

        @Override
        public void onViewCreated (View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Story Teller");
    }

    }

