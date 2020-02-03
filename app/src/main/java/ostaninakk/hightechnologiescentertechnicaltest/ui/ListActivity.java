package ostaninakk.hightechnologiescentertechnicaltest.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ostaninakk.hightechnologiescentertechnicaltest.R;
import ostaninakk.hightechnologiescentertechnicaltest.ui.ListFragment;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new ListFragment())
                    .commit();
        }
    }
}
