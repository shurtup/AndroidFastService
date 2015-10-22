package br.com.refsoft.refsoft.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import br.com.refsoft.refsoft.R;


public class BaseActivity extends livroandroid.lib.activity.BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // CleanTrash app = CleanTrash.getInstance();
    }

    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }
}