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

    private File file = null;
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
                File file = listOfDir[position];
                dir = file.getPath();
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

    private ArrayList<Item> getDir(String dir) {
        ArrayList<Item> list = new ArrayList<Item>();

        file = new File(dir);
        listOfDir = file.listFiles();

        if (listOfDir.length > 0) {
            for(int i=0; i < listOfDir.length; i++) {
                File file = listOfDir[i];

                String title = file.getPath();
                int last = title.lastIndexOf("/");
                int size = title.length();

                Item item = new Item();
                item.setTitulo(title.substring(last+1,size));
                item.setDataModificacao(file.getName());

                list.add(item);
            }
        }

        return list;
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
}
