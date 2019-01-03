package com.startingandroid.sqlitedatabasetutorial.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.startingandroid.sqlitedatabasetutorial.Model.Book;
import com.startingandroid.sqlitedatabasetutorial.R;
import com.startingandroid.sqlitedatabasetutorial.Util.RecyclerTouchListener;
import com.startingandroid.sqlitedatabasetutorial.adapters.BookAdapter;
import com.startingandroid.sqlitedatabasetutorial.database.GetBookDatabase;
import com.startingandroid.sqlitedatabasetutorial.fragment.AboutUs;


import java.util.ArrayList;

public class BookmarkActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private BookAdapter adapter;
    private GetBookDatabase dbHandler;
    protected String[] mDataset;
    private Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        initializeVariables();
        connectingWithDatabase();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bookmark");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ArrayList<Book> books = dbHandler.getAllBookmarkedUsers();
        Log.d("Reading: ", "reading contacts.." + books.size());

        adapter = new BookAdapter(BookmarkActivity.this, books);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(BookmarkActivity.this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Book book = books.get(position);
                Intent intent;
                intent = new Intent(BookmarkActivity.this, FirstActivity.class);
                intent.putExtra("currentBook", book);
                intent.putExtra("position", position);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

    }

    private void connectingWithDatabase() {
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

    }

    private void initializeVariables() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.usersList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(BookmarkActivity.this);
        dbHandler = new GetBookDatabase(BookmarkActivity.this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_bookmarked, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void scrollToTop() {
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.scrollTop:
                scrollToTop();
                return true;

            case R.id.AboutApplication:

                Intent intent3 = new Intent(this, com.startingandroid.sqlitedatabasetutorial.Activity.AboutUs.class);
                startActivity(intent3);
                return true;

            case R.id.menu_share:

                Toast.makeText(this, "Share is Selected", Toast.LENGTH_SHORT).show();

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "http://play.google.com/store/apps/details?id=";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
