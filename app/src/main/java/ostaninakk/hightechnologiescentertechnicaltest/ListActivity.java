package ostaninakk.hightechnologiescentertechnicaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentContainer, ListFragment.newInstance())
                    .commit();
        }
    }
}
