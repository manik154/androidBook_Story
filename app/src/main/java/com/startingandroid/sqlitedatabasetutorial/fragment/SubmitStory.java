package com.startingandroid.sqlitedatabasetutorial.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.startingandroid.sqlitedatabasetutorial.R;

public class SubmitStory extends Fragment {
    private Button sme_applyButton;
    private Toolbar toolbar;
    private EditText editText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.submit_story, container, false);

        toolbar = rootView.findViewById(R.id.toolbar);
        sme_applyButton = rootView.findViewById(R.id.sme_apply);
        editText = rootView.findViewById(R.id.sme_reason);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        //   getActivity().setTitle("Submit Your Story");
        sme_applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of email");
                intent.putExtra(Intent.EXTRA_TEXT, editText.getText().toString());
                intent.setData(Uri.parse("mailto:manikrastogi479@gmail.com")); // or just "mailto:" for blank
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                startActivity(intent);
                getActivity().finish();
            }
        });
        editText.setText("");

    }

}
