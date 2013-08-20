package biz.lungo.vacationtask1;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
    EditText input;
    TextView output;
    Spinner spinner;
    CheckBox checkboxCase;
    CheckBox checkboxReverse;
    CheckBox checkboxDuplicate;
    RadioGroup rgColor;
    String rawText;
    String tempText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.editText);
        output = (TextView) findViewById(R.id.result);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setEnabled(false);
        checkboxCase = (CheckBox) findViewById(R.id.checkBox);
        checkboxReverse = (CheckBox) findViewById(R.id.checkBox2);
        checkboxDuplicate = (CheckBox) findViewById(R.id.checkBox3);
        rgColor = (RadioGroup) findViewById(R.id.radioGroup);

        checkboxCase.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (input.getText() != null){
                    if (checkboxCase.isChecked()){
                        rawText = output.getText().toString();
                        output.setText(rawText.toUpperCase());
                    }
                    else{
                        output.setText(rawText);
                    }
                }
            }
        });

        checkboxReverse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (input.getText() != null){
                    if (checkboxReverse.isChecked()){
                        rawText = output.getText().toString();
                        StringBuilder outString = new StringBuilder();
                        for (int i = (input.getText().toString().length()-1); i >= 0; i--){
                            outString.append(rawText.toString().charAt(i));
                        }
                        output.setText(outString);
                    }
                    else{
                        output.setText(rawText);
                    }
                }
            }
        });

        checkboxDuplicate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (input.getText() != null){
                    if (checkboxDuplicate.isChecked()){
                        tempText = output.getText().toString();
                        spinner.setEnabled(true);
                        if (!spinner.getSelectedItem().toString().equals("1")){
                            int count = Integer.parseInt(spinner.getSelectedItem().toString());
                            output.setText("");
                            for (int i = 1; i <= count; i++){
                                output.append(i + ". " + tempText + "\n");
                            }
                        }
                    }
                    else{
                        spinner.setEnabled(false);
                        output.setText(tempText);
                    }
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = (String) adapterView.getItemAtPosition(i);
                int count = Integer.parseInt(value);
                tempText = rawText;
                if (count != 1) {
                    output.setText("");
                    for (int j = 1; j <= count; j++){
                        output.append(j + ". " + rawText + "\n");
                    }
                }
                else
                    output.setText(tempText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rgColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButton){
                    output.setBackgroundColor(Color.RED);
                }
                else if (i == R.id.radioButton2){
                    output.setBackgroundColor(Color.GREEN);
                }
                else if (i == R.id.radioButton3){
                    output.setBackgroundColor(Color.BLUE);
                }
            }
        });

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (output.getText() != null) {
                    rawText = output.getText().toString();
                }
                else
                    rawText = input.getText().toString();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (checkboxDuplicate.isChecked()){
                    rawText = tempText;
                }
                else
                    rawText = input.getText().toString();

                if (checkboxCase.isChecked()){
                    output.setText(rawText.toUpperCase());
                }
                else{
                    output.setText(rawText);
                }
                if (checkboxReverse.isChecked()){
                    StringBuilder outString = new StringBuilder();
                    for (int j = (rawText.length()-1); j >= 0; j--){
                        outString.append(rawText.charAt(j));
                    }
                    output.setText(outString);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


}
