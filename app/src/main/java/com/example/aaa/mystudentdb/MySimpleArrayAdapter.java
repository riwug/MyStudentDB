// tutorial used
// http://www.vogella.com/tutorials/AndroidListView/article.html

package com.example.aaa.mystudentdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public MySimpleArrayAdapter(Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.browsestudent_listview_row_layout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.firstLine);
        TextView textView2 = (TextView) rowView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values[position]);
        textView2.setText("Second Line");
        // change the icon for Windows and iPhone
        String s = values[position];

        System.out.print("var of values:");
        System.out.println(Arrays.toString(values));

        // toDO
        // get him to choose the picture according to field "instrument" (need to be send with)
        //
        // position is Students Lastname. Dont know why it is called position
        // or if we can call any field?
        if (s.startsWith("May") || s.startsWith("Nagel") || s.endsWith("Armin")){
            imageView.setImageResource(R.mipmap.guitar_mipmap_mdpi);
        } else {
            imageView.setImageResource(R.mipmap.piano_mipmap_mdpi);
        }

        return rowView;
    }
}