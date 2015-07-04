package betweenbits.rar;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    private List<String> item = null;
    private List<String> path = null;

    private String root="/";
    private TextView myPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void getDir(String dirPath)

    {

        myPath.setText("Location: " + dirPath);

        item = new ArrayList<>();
        path = new ArrayList<String>();

        File f = new File(dirPath);
        File[] files = f.listFiles();

        if(!dirPath.equals(root)) {
            item.add(root);
            path.add(root);

            item.add("../");
            path.add(f.getParent());
        }

        for(int i=0; i < files.length; i++) {
            File file = files[i];
            path.add(file.getPath());

            if(file.isDirectory()) {
                item.add(file.getName() + "/");
            } else {
                item.add(file.getName());
            }
        }

        ArrayAdapter<String> fileList = new ArrayAdapter<String>(this, R.layout.row, item);
        setListAdapter(fileList);
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
