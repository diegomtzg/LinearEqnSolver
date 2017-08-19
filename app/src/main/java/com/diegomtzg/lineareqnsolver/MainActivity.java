package com.diegomtzg.lineareqnsolver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Input a linear equation..");

        final EditText equationHolder = (EditText)findViewById(R.id.equation_string_holder);
        equationHolder.setEnabled(true);

        final Button button = (Button)findViewById(R.id.solveButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Editable equation = equationHolder.getText();
                String equationString = equation.toString();

                Solver equationSolver = new Solver(equationString);
                String result = equationSolver.solveForX();

                final TextView resultText = (TextView)findViewById(R.id.result);
                resultText.setText(result);
            }
        });
    }
}
