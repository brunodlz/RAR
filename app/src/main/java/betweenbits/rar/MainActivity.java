package betweenbits.rar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import betweenbits.rar.adapter.CustomAdapter;
import betweenbits.rar.domain.Item;
import betweenbits.rar.util.Storage;

public class MainActivity extends ActionBarActivity {

    private File parentDir = null;

    private ListView listView = null;
    private TextView textDir = null;

    private String directory = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        directory = Storage.getPath();

        initializeViews();
        loadingList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] fileNames = parentDir.list();
                directory = parentDir.getPath() + "/" + fileNames[position];
                loadingList();
            }
        });
    }

    private void loadingList() {
        ArrayList<Item> list = getDir(directory);
        setListView(list, directory);

    }

    private void setListView(ArrayList<Item> list, String dir) {
        listView.setAdapter(new CustomAdapter(this, list));
        textDir.setText(dir);
    }

    private void initializeViews() {
        listView = (ListView) findViewById(R.id.listView);
        textDir = (TextView) findViewById(R.id.textDir);

        View header = getLayoutInflater().inflate(R.layout.header, null);
        listView.addHeaderView(header);
    }

    private ArrayList<Item> getDir(String pathToParentDir) {
        ArrayList<Item> inFiles = new ArrayList<Item>();

        parentDir = new File(directory);
        String[] fileNames = parentDir.list();

        for (String fileName : fileNames) {

            Item item = new Item();
            File file = new File(parentDir.getPath() + "/" + fileName);

            if (file.isDirectory()) {
                item.setTitulo(fileName);
            } else {
                item.setTitulo(fileName);
            }
            inFiles.add(item);
        }

        return inFiles;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
