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

public class MainActivity extends ActionBarActivity {

    private File parentDir = null;
    private File[] listOfDir = null;

    private ListView listView = null;
    private TextView textDir = null;

    private String dir = "/sdcard/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        loadingList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] fileNames = parentDir.list();
                dir = parentDir.getPath() + "/" + fileNames[position];
                loadingList();
            }
        });
    }

    private void loadingList() {
        ArrayList<Item> list = getDir(dir);
        setListView(list, dir);
    }

    private void setListView(ArrayList<Item> list, String dir) {
        listView.setAdapter(new CustomAdapter(this, list));
        textDir.setText(dir);
    }

    private void initializeViews() {
        listView = (ListView) findViewById(R.id.listView);
        textDir = (TextView) findViewById(R.id.textDir);
    }

    private ArrayList<Item> getDir(String pathToParentDir) {
        ArrayList<Item> inFiles = new ArrayList<Item>();

        parentDir = new File(dir);
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
