package com.example.ad42_nguyenanhtu_day10_1;


        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.content.ContextCompat;
        import androidx.databinding.DataBindingUtil;

        import android.app.DatePickerDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.content.res.Configuration;
        import android.os.Bundle;
        import android.provider.ContactsContract;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.PopupMenu;
        import android.widget.Toast;


        import com.example.ad42_nguyenanhtu_day10_1.databinding.ActivityMainBinding;

        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.mntag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] strings = {"Family", "Game", "Android", "VTC", "Friends"};
                boolean[] booleans = {true, false, false, false, false};
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Choose tags:")
                        .setMultiChoiceItems(strings, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getBaseContext(), "Saved..!", Toast.LENGTH_SHORT).show();
                                binding.mntag.setText("");
                                for (int i = 0; i < strings.length; i++) {
                                    if (booleans[i] == true) {
                                        binding.mntag.setText(binding.mntag.getText().toString() + strings[i] + " ");
                                    }
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getBaseContext(), "Canceled...!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });
        binding.mnweek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] strings = {"Monday", "Tuesday", "Wednesday", "Thusday", "Friday", "Saturday", "Sunday"};
                boolean[] booleans = {true, true, true, true, true, false, false};
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Choose days:")
                        .setMultiChoiceItems(strings, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getBaseContext(), "Saved..!", Toast.LENGTH_SHORT).show();
                                binding.mnweek.setText("");
                                for (int i = 0; i < strings.length; i++) {
                                    if (booleans[i] == true) {
                                        binding.mnweek.setText(binding.mnweek.getText().toString() + strings[i] + " ");
                                    }
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getBaseContext(), "Canceled...!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });
        binding.btnTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getBaseContext(), v);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.one:
                                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                intent.setType("*/*");
                                startActivityForResult(intent, 1);
                                break;
                            case R.id.two:
                                String[] strings = {"Android", "PHP", "iOS", "Windows"};
                                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("Chon 1 trong nhung mon hoc sau: ")
                                        .setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(getBaseContext(), strings[which], Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(getBaseContext(), "Saved..!", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(getBaseContext(), "Canceled", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .create();
                                alertDialog.show();
                                break;

                            default:
                                break;

                        }
                        return false;
                    }
                });
            }
        });
        List<String> list = new ArrayList<>();
        list.add("Android");
        list.add("iOS");
        list.add("Windows");
        list.add("Linux");
        list.add("MacOS");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        binding.spinner.setAdapter(arrayAdapter);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), list.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        binding.tvdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(binding.tvdate);
            }
        });

    }
    public  void showDateDialog(EditText tvdate)
    {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd");
                tvdate.setText(simpleDateFormat.format(calendar));
            }
        };
        new DatePickerDialog(MainActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnSave:
                Toast.makeText(getBaseContext(), "Saved!", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String path = data.getData().getPath();
                    binding.tvpath.setText(path);
                }
                break;
        }
    }
}