package com.dome.newmessage;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class MainActivity extends Activity {

    private LinearLayout mContactsLayout;
    private EditText mContactsInput,mInput;
    private ImageButton mback, mAdd;
    private GridView mContacts;

    private boolean isEditEmpty = false;
    private int lastWidth = 0;
    List<Map<String, Object>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    private void setListener() {

        mAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                addContacts(mContactsLayout.getChildCount()+"zhang");
            }
        });

        mContactsLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mContactsInput.setFocusable(true);
                mContactsInput.setFocusableInTouchMode(true);
                mContactsInput.requestFocus();

            }
        });

        mContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                if (data.size() > position) {
                    addContacts(data.get(position).get("name").toString());
                }
            }
        });

        mInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (hasFocus) {
                    hideNowContacts();
                }
            }
        });

        mContactsInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                showNowContacts();
            }
        });

    }

    private void showNowContacts() {
        SimpleAdapter adapter = new SimpleAdapter(this, initData(), R.layout.item_gridview, new String[]{"name"}, new int[]{R.id.name});
        mContacts.setAdapter(adapter);
        mContacts.setVisibility(View.VISIBLE);
    }

    private List<Map<String, Object>> initData() {
        data = new ArrayList<Map<String,Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "zhangsan1");
        data.add(map);
        map = new HashMap<String, Object>();
        map.put("name", "zhangsan2");
        data.add(map);
        map = new HashMap<String, Object>();
        map.put("name", "zhangsan3");
        data.add(map);
        map = new HashMap<String, Object>();
        map.put("name", "zhangsan4");
        data.add(map);
        map = new HashMap<String, Object>();
        map.put("name", "zhangsan5");
        data.add(map);
        map = new HashMap<String, Object>();
        map.put("name", "zhangsan6");
        data.add(map);
        return data;
    }

    private void hideNowContacts() {
        mContacts.setVisibility(View.GONE);
    }

    private void addContacts(String name) {
        mContactsLayout.addView(initContactsItemView(name),
                mContactsLayout.getChildCount() - 1);
    }

    private View initContactsItemView(String name) {
        final View view = LayoutInflater.from(this).inflate(R.layout.item_contacts,
                null);
        assert view != null;
        TextView textView = (TextView) view.findViewById(R.id.name);
        ImageButton button = (ImageButton)view.findViewById(R.id.delete);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mContactsLayout.removeView(view);
            }
        });
        textView.setText(name);
        return view;
    }

    private void initView() {
        mContactsLayout = (LinearLayout) findViewById(R.id.contacts_layout);
        mContactsInput = (EditText) findViewById(R.id.contacts_input);
        mInput = (EditText) findViewById(R.id.input);
        mback = (ImageButton) findViewById(R.id.back);
        mAdd = (ImageButton) findViewById(R.id.contacts);
        mContacts = (GridView)findViewById(R.id.gridview);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
