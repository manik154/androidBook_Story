package com.startingandroid.sqlitedatabasetutorial.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.startingandroid.sqlitedatabasetutorial.Activity.FirstActivity;
import com.startingandroid.sqlitedatabasetutorial.Activity.MainActivity;
import com.startingandroid.sqlitedatabasetutorial.R;
import com.startingandroid.sqlitedatabasetutorial.Util.RecyclerTouchListener;
import com.startingandroid.sqlitedatabasetutorial.Model.Book;
import com.startingandroid.sqlitedatabasetutorial.adapters.BookAdapter;
import com.startingandroid.sqlitedatabasetutorial.database.GetBookDatabase;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static final String TAG = "RecyclerViewFragment";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private BookAdapter adapter;

    private GetBookDatabase dbHandler;
    protected String[] mDataset;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        rootView.setTag(TAG);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.usersList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        dbHandler = new GetBookDatabase(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        setHasOptionsMenu(true);
        Log.e("Insert: ", "Inserting...");
        if (dbHandler.getUsersCount() <= 0) {
            try {
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
        adapter = new BookAdapter(getActivity(), books);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Book book = books.get(position);
                Intent intent;
                intent = new Intent(getActivity(), FirstActivity.class);
                intent.putExtra("currentBook", book);
                intent.putExtra("position", position);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));


        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Story Teller");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void scrollToTop() {
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_bookmark:
                // Single menu_share item is selected do something
                // Ex: launching new activity/screen or show alert message
                Toast.makeText(getActivity(), "Bookmark is Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.scrollTop:
                scrollToTop();
                return true;

            case R.id.AboutApplication:
                Intent intent = new Intent(getActivity(), AboutUs.class);
                startActivity(intent);
                return true;
/*
            case R.id.menu_share:
                Toast.makeText(MainActivity.this, "Share is Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_delete:
                Toast.makeText(MainActivity.this, "Delete is Selected", Toast.LENGTH_SHORT).show();
                return true;
*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

