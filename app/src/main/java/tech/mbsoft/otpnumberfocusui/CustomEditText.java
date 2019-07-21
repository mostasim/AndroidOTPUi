package tech.mbsoft.otpnumberfocusui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CustomEditText extends ConstraintLayout {

    ImageButton btIncrease,btDecrease;
    EditText etText;
    Double count;
    public CustomEditText(Context context) {
        super(context);
        init(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(final Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.custom_edit_text, this, true);
        count = 0.00;
        btDecrease = findViewById(R.id.btDecrease);
        btIncrease = findViewById(R.id.btIncrease);
        etText = findViewById(R.id.etText);
        if (etText.getText()==null)
            etText.setText("0");
        if (etText.getText()!=null){
            etText.setText(count.toString());
        }
        btIncrease.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                count+=1;
                etText.setText(count.toString());
            }
        });
        btDecrease.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                count-=1;
                etText.setText(count.toString());
            }
        });

    }
}
