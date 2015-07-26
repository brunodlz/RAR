package betweenbits.rar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.File;

import betweenbits.rar.adapter.Callback;
import betweenbits.rar.adapter.MainAdapter;
import betweenbits.rar.util.Storage;

public class MainActivity extends AppCompatActivity implements Callback{

    private MainAdapter mainAdapter;

    private String directory = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainAdapter = new MainAdapter(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainAdapter);

        directory = Storage.getPath();
        getDir(directory);
    }

    @Override
    public void onItemClick(int position) {

    }

    private void getDir(String pathToParentDir) {
        File parentDir = new File(directory);
        String[] fileNames = parentDir.list();

        for (String fileName : fileNames) {
            File file = new File(parentDir.getPath() + "/" + fileName);
            if (file.isDirectory()) {
                mainAdapter.add(fileName);
            }
        }
    }
}
