package com.example.listadetareas;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout taskListLayout;
    EditText editTextTask;
    Button btnAddTask;
    Button btnDeleteTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskListLayout = findViewById(R.id.taskListLayout);
        editTextTask = findViewById(R.id.editTextTask);
        btnAddTask = findViewById(R.id.btnAddTask);
        btnDeleteTask = findViewById(R.id.btnDeleteTask);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskText = editTextTask.getText().toString().trim();
                if (!taskText.isEmpty()) {
                    addTaskToList(taskText);
                    editTextTask.setText("");
                }
            }
        });

        btnDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < taskListLayout.getChildCount(); i++) {
                    View view = taskListLayout.getChildAt(i);
                    if (view instanceof LinearLayout) {
                        final LinearLayout layout = (LinearLayout) view;
                        TextView textView = (TextView) layout.getChildAt(0);
                        if (textView.isSelected()) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Confirmación");
                            builder.setMessage("¿Estás seguro de que quieres eliminar esta tarea?");
                            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    taskListLayout.removeView(layout);
                                    dialog.dismiss();
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.show();
                            return;
                        }
                    }
                }
            }
        });
    }

    private void addTaskToList(final String taskText) {
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        final LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(layoutParams);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        final TextView taskTextView = new TextView(this);
        taskTextView.setText(taskText);
        taskTextView.setLayoutParams(layoutParams);
        taskTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                if (v.isSelected()) {
                    // Marcar como completada
                    taskTextView.setPaintFlags(taskTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    // Desmarcar como completada
                    taskTextView.setPaintFlags(taskTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
            }
        });
        taskTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Editar Tarea");
                final EditText input = new EditText(MainActivity.this);
                input.setText(taskTextView.getText());
                builder.setView(input);
                builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newText = input.getText().toString().trim();
                        taskTextView.setText(newText);
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                return true;
            }
        });
        layout.addView(taskTextView);

        taskListLayout.addView(layout);


    }
}
