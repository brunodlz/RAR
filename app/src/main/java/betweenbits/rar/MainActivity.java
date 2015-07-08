package betweenbits.rar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Item> lista = new ArrayList<Item>();

        File f = new File("/sdcard/");
        File[] files = f.listFiles();

        for(int i=0; i < files.length; i++) {
            File file = files[i];

            String title = file.getPath();
            int last = title.lastIndexOf("/");
            int size = title.length();

            Item item = new Item();
            item.setTitulo(title.substring(last+1,size));
            item.setDataModificacao(file.getName());

            lista.add(item);
        }

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomAdapter(this, lista));
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
