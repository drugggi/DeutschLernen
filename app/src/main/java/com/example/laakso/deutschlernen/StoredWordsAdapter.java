package com.example.laakso.deutschlernen;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StoredWordsAdapter extends BaseAdapter {

    private LayoutInflater mInflator;
    private final Context mContext;

    private String[] wordSubjects;
    private boolean[] selected;

    public StoredWordsAdapter(Context context, String[] wordSubjects) {
        this.mContext = context;

        this.wordSubjects = wordSubjects;
        this.selected = new boolean[wordSubjects.length];
        this.mInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return wordSubjects.length;
    }

    @Override
    public Object getItem(int i) {
        return wordSubjects.length;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void itemClicked(int i) {
        if (selected[i]) {
            selected[i] = false;
        }
        else {
            selected[i] = true;
        }

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = mInflator.inflate(R.layout.learning_listview,null);
        TextView subjectTextView = (TextView) v.findViewById(R.id.textview);

        if (selected[i] ) {
            subjectTextView.setBackgroundColor(Color.YELLOW);
        }

        String text = wordSubjects[i];

        subjectTextView.setText(text);

        return v;
    }
}
