
package com.startingandroid.sqlitedatabasetutorial.Activity;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.startingandroid.sqlitedatabasetutorial.Model.Book;
import com.startingandroid.sqlitedatabasetutorial.R;
import com.startingandroid.sqlitedatabasetutorial.Util.AppBarStateChangeListener;
import com.startingandroid.sqlitedatabasetutorial.database.GetBookDatabase;

public class FirstActivity extends AppCompatActivity

{
    private Button button_prev, button_menu, button_next;
    //    private ImageView imageView;
    private TextView txtView;
    private FloatingActionButton floatingActionButton;
    private Book currentBook;
    private Toolbar toolbar;
    private int e;
    private NestedScrollView nestedScrollView;
    private static final String SELECT_SQL = "SELECT *  FROM user";
    // private RelativeLayout relativelayout;
    private int f = 1;
    GetBookDatabase dbHelper;
    private Context context = FirstActivity.this;
    TextView content;
    //TextView toolbar_title;
    private AppBarLayout appBarLayout;
    private String getName;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail_activity);
        dbHelper = new GetBookDatabase(getApplicationContext());
        Intent intent = getIntent();
        //   toolbar_title = (TextView) findViewById(R.id.toolbar);
        content = (TextView) findViewById(R.id.content);
        nestedScrollView = findViewById(R.id.scroll);
        floatingActionButton = findViewById(R.id.fab);
//        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto.ttf");
        //      content.setTypeface(custom_font);
        txtView = (TextView) findViewById(R.id.title);
        //      relativelayout=(RelativeLayout)findViewById(R.id.relativelayout);
        //  SwitchCompat switchCompat = (SwitchCompat) findViewById(R.id.switch_compat);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appBarLayout = findViewById(R.id.app_bar_layout);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //  imageView = (ImageView) findViewById(R.id.imageview);
        currentBook = (Book) intent.getSerializableExtra("currentBook");


        if (currentBook != null) {
            getName = (String) currentBook.getName();

            txtView.setText(getName);
            content.setText(currentBook.getContent());

           /*Resources resources = context.getResources();
            final int resourceId = resources.getIdentifier(currentBook.getIcon(), "drawable",
                    context.getPackageName());
            imageView.setImageResource(resourceId);
*/

        }

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
                    //  item.setVisible(true);

                } else {
                    try {
                        //  item.setVisible(false);
                    } catch (Exception e) {

                    }
                }


            }
        });
        OnButtonClickListener();
        /*switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked)
                {
                    Toast.makeText(FirstActivity.this, "Night Mode Enabled", Toast.LENGTH_SHORT).show();
                    content.setTextColor(0xffffffff);
                   button_next.setTextColor(0xffffffff);
                    txtView.setTextColor(0xffffffff);
                    button_prev.setTextColor(0xffffffff);
                    content.setBackgroundColor(0xff000000);
                      relativelayout.setBackgroundColor(0xff000000);
                }
                else
                {
                    Toast.makeText(FirstActivity.this, "Night Mode Disabled", Toast.LENGTH_SHORT).show();
                    content.setTextColor(0xff000000);
                    txtView.setTextColor(0xff000000);

                    button_next.setTextColor(0xff000000);
                    button_prev.setTextColor(0xff000000);

                    content.setBackgroundColor(0xffffffff);
                    relativelayout.setBackgroundColor(0xffffffff);

                }
            }
        });
        */
    }

    public void OnButtonClickListener() {
        button_prev = (Button) findViewById(R.id.button_prev);
        button_next = (Button) findViewById(R.id.button_next);
        //button_menu = (Button) findViewById(R.id.button3);


        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_next.setEnabled(true);
                nestedScrollView.fullScroll(NestedScrollView.FOCUS_UP);
                Book book = dbHelper.getPreviousBook(currentBook.getId());
                if (book != null) {
                    currentBook = book;
                    String getName = (String) currentBook.getName();

                    txtView.setText(getName);
                    getSupportActionBar().setTitle(getName);
                    content.setText(currentBook.getContent());
                   /* Resources resources = context.getResources();
                   final int resourceId = resources.getIdentifier(currentBook.getIcon(), "drawable",
                            context.getPackageName());
                            */
                  /*  imageView.setImageResource(resourceId);
                    scrollView.fullScroll(ScrollView.FOCUS_UP);
*/
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
                Book book = dbHelper.getNextBook(currentBook.getId());
                if (book != null) {
                    currentBook = book;
                    String getName = (String) currentBook.getName();
                    getSupportActionBar().setTitle(getName);
                    txtView.setText(getName);
                    content.setText(currentBook.getContent());
                /*    Resources resources = context.getResources();
                    final int resourceId = resources.getIdentifier(currentBook.getIcon(), "drawable",
                            context.getPackageName());
                    imageView.setImageResource(resourceId);
                    scrollView.fullScroll(ScrollView.FOCUS_UP);*/

                } else {
                    Toast.makeText(context, "Reached End of Book", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_share:
                Toast.makeText(FirstActivity.this, "Share is Selected", Toast.LENGTH_SHORT).show();

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
