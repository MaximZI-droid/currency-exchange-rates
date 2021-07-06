package ZiMax.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ZiMax.com.View.RecyclerViewItem;

public class ConverterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_ITEM = "extra_item_cur";

    private EditText firstTextView;
    private EditText secondTextView;

    private List<RecyclerViewItem> currencyList;
    private Spinner leftSpinner;
    private Spinner rightSpinner;
    private List<String> SpinnerList;
    ArrayAdapter<String> leftSpinnerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        setTitle("Конвертер валют");

        currencyList = (ArrayList<RecyclerViewItem>) getIntent().getSerializableExtra(EXTRA_ITEM);
        currencyList.add(new RecyclerViewItem("Российский рубль", "1", "1", "RUB", R.drawable.rus));

        createSpinners();
        createAdapter(leftSpinner, leftSpinnerAdapter, SpinnerList);
        createAdapter(rightSpinner, leftSpinnerAdapter, SpinnerList);
    }

    public void createSpinners() {
        leftSpinner = findViewById(R.id.leftConverterSpinner);
        leftSpinner.setOnItemSelectedListener(this);
        rightSpinner = findViewById(R.id.rightConverterSpinner);
        rightSpinner.setOnItemSelectedListener(this);

        SpinnerList = new ArrayList<>();

        for (int i = 0; i < currencyList.size(); i++) {
            SpinnerList.add(i, currencyList.get(i).getCurrencyTicker() + " ("
                    + currencyList.get(i).getCurrencyName() + ")");
        }
    }

    public void createAdapter(Spinner spinner, ArrayAdapter<String> spinnerAdapter, List<String> list) {
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    String leftChooseCurrency;
    String rightChooseCurrency;

    ImageView leftImageView;
    ImageView rightImageView;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        leftImageView = findViewById(R.id.leftConverterImageView);
        rightImageView = findViewById(R.id.rightConverterImageView);

        leftChooseCurrency = leftSpinner.getSelectedItem().toString();
        rightChooseCurrency = rightSpinner.getSelectedItem().toString();

        for (int i = 0; i < currencyList.size(); i++) {
            if (leftChooseCurrency.equals(currencyList.get(i).getCurrencyTicker() + " ("
                    + currencyList.get(i).getCurrencyName() + ")")) {
                leftImageView.setImageResource(currencyList.get(i).getCurrencyFlag());
            }
            if (rightChooseCurrency.equals(currencyList.get(i).getCurrencyTicker() + " ("
                    + currencyList.get(i).getCurrencyName() + ")")) {
                rightImageView.setImageResource(currencyList.get(i).getCurrencyFlag());
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void convert(View view) {

        firstTextView = findViewById(R.id.leftConverterEditTextNumber);
        secondTextView = findViewById(R.id.rightConverterEditTextNumber);

        leftChooseCurrency = leftSpinner.getSelectedItem().toString();
        rightChooseCurrency = rightSpinner.getSelectedItem().toString();

        try {
            int convertationInRub = 0;
            int convertationInCur = 0;

            int leftNumber = Integer.parseInt(firstTextView.getText().toString());

                for (int i = 0; i < currencyList.size(); i++) {
                    if (leftChooseCurrency.equals(currencyList.get(i).getCurrencyTicker() + " ("
                            + currencyList.get(i).getCurrencyName() + ")")) {
                        convertationInRub = (int) (leftNumber / Integer.parseInt(currencyList.get(i).getCurrencyNominal()) * Float.parseFloat(currencyList.get(i).getCurrencyValue()));
                        Log.d("myLOG", convertationInRub + "");
                        for (int j = 0; j < currencyList.size(); j++) {
                            if (rightChooseCurrency.equals(currencyList.get(j).getCurrencyTicker() + " ("
                                    + currencyList.get(j).getCurrencyName() + ")")) {
                                convertationInCur = (int) ((convertationInRub * Integer.parseInt(currencyList.get(j).getCurrencyNominal())) / Float.parseFloat(currencyList.get(j).getCurrencyValue()));
                               Log.d("myLOG", convertationInCur + "");
                            }
                        }
                    }
                    secondTextView.setText(convertationInCur + "", TextView.BufferType.EDITABLE);
                }

        } catch (NumberFormatException e) {
        }
    }
}
