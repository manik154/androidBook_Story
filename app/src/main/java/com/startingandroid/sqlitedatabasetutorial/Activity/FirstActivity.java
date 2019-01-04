
package com.startingandroid.sqlitedatabasetutorial.Activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.startingandroid.sqlitedatabasetutorial.Model.Book;
import com.startingandroid.sqlitedatabasetutorial.R;
import com.startingandroid.sqlitedatabasetutorial.Util.AppBarStateChangeListener;
import com.startingandroid.sqlitedatabasetutorial.database.GetBookDatabase;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {
    private static final String TAG = "FirstActivity";
    private Button button_prev, button_next;
    private TextView txtView;
    private FloatingActionButton floatingActionButton;
    private Book currentBook;
    private Toolbar toolbar;
    int c = 0;
    private MenuItem item;
    private NestedScrollView nestedScrollView;
    private static final String SELECT_SQL = "SELECT *  FROM user";
    private RelativeLayout relativelayout;
    private ImageView imageView;
    GetBookDatabase dbHelper;
    private CardView cardView;
    private Context context = FirstActivity.this;
    TextView content;
    private AppBarLayout appBarLayout;
    private String getName;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    Animation slideUp;
    private CoordinatorLayout coordinatorLayout;
    Animation slideDown;
    private ArrayList<Book> books;
    private boolean value = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.book_detail_activity);
        initalizeVariables();

        Intent intent = getIntent();
        slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        imageView = (ImageView) findViewById(R.id.posterPath);
        currentBook = (Book) intent.getSerializableExtra("currentBook");

        if (currentBook != null) {
            setBookContent(currentBook);
        }
        appBarLayout();
        OnButtonClickListener();
        callScrollView();
    }

    private void appBarLayout() {

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(getName);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {

                if ((state.name()).equals("COLLAPSED")) {
                    floatingActionButton.hide();
                    imageView.setImageResource(0);
                    item.setVisible(true);

                } else {

                    Resources resources = context.getResources();
                    final int resourceId = resources.getIdentifier(currentBook.getIcon(), "drawable",
                            context.getPackageName());
                    imageView.setImageResource(resourceId);
                    try {
                        floatingActionButton.show();
                        item.setVisible(false);
                    } catch (Exception e) {

                    }
                }
            }
        });
    }

    private void callScrollView() {
        if (nestedScrollView != null) {

            nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {


                    if (scrollY > oldScrollY) {
                        Log.i(TAG, "Scroll DOWN");
                    }
                    if (scrollY == 0) {
                        Log.i(TAG, "TOP SCROLL");
                    }
                    if (scrollY < oldScrollY) {
                        Log.i(TAG, "Scroll UP");
                        relativelayout.setVisibility(View.GONE);
                    }
                    if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                        Log.i(TAG, "BOTTOM SCROLL");
                        relativelayout.startAnimation(slideUp);
                        relativelayout.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

    }

    private void initalizeVariables() {
        content = (TextView) findViewById(R.id.content);
        nestedScrollView = findViewById(R.id.scroll);
        floatingActionButton = findViewById(R.id.fab);
        txtView = (TextView) findViewById(R.id.title);
        cardView = findViewById(R.id.direction_card_view);
        relativelayout = findViewById(R.id.relative);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appBarLayout = findViewById(R.id.app_bar_layout);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        dbHelper = new GetBookDatabase(getApplicationContext());
        books = dbHelper.getAllBookmarkedUsers();
        coordinatorLayout = findViewById(R.id.coordinator);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void changeTheme() {
        imageView.setAlpha((float) 0.3);
        appBarLayout.setBackgroundColor(Color.BLACK);
        content.setTextColor(getResources().getColor(R.color.lightslategray_book_content_color2));
        cardView.setCardBackgroundColor(Color.BLACK);
        coordinatorLayout.setBackgroundColor(Color.BLACK);
        txtView.setTextColor(getResources().getColor(R.color.lightslategray_book_content_color2));
        txtView.setBackgroundColor(Color.BLACK);
    }

    private void callDefaultTheme() {

        imageView.setAlpha((float) 1.0);
        coordinatorLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        txtView.setBackgroundColor(Color.WHITE);
        cardView.setCardBackgroundColor(Color.WHITE);
        appBarLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        content.setTextColor(getResources().getColor(R.color.lightslategray_book_content_color2));
        txtView.setTextColor(getResources().getColor(R.color.title_color_book_detail_activity));

    }

    public void OnButtonClickListener() {
        button_prev = (Button) findViewById(R.id.button_prev);
        button_next = (Button) findViewById(R.id.button_next);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (value) {
                    changeTheme();
                    value = false;
                } else {
                    callDefaultTheme();
                    value=true;
                }
            }
        });


        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_next.setEnabled(true);
                nestedScrollView.fullScroll(NestedScrollView.FOCUS_UP);
                callScrollView();
                Book book = dbHelper.getPreviousBook(currentBook.getId());
                if (book != null) {
                    setBookContent(book);
                } else {
                    Toast.makeText(context, "No more chapters", Toast.LENGTH_LONG).show();
                }
            }
        });

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nestedScrollView.fullScroll(NestedScrollView.FOCUS_UP);
                //button_prev.setEnabled(true);
                callScrollView();
                Book book = dbHelper.getNextBook(currentBook.getId());
                if (book != null) {
                    setBookContent(book);
                } else {
                    Toast.makeText(context, "Reached End of Book", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void setBookContent(Book book) {
        currentBook = book;
        String getName = (String) currentBook.getName();

        txtView.setText(getName);
        Spanned sp = Html.fromHtml(currentBook.getContent());
        content.setText(sp);

        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(currentBook.getIcon(), "drawable",
                context.getPackageName());
        imageView.setImageResource(resourceId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_share, menu);

        item = menu.findItem(R.id.night);
        item.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_bookmark:

                addBookmarkStory();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateBookmarkStory() {
        dbHelper.updateBookmark(currentBook);
        Toast.makeText(context, "Added to Bookmarked Stories", Toast.LENGTH_SHORT).show();
    }

    private void addBookmarkStory() {

        dbHelper.addBookmark(currentBook);
        Toast.makeText(context, "Added to Bookmarked Stories", Toast.LENGTH_SHORT).show();

    }

}
