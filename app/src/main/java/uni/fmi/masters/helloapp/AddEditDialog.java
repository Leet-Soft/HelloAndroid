package uni.fmi.masters.helloapp;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import uni.fmi.masters.helloapp.entity.ShoppingItem;

public class AddEditDialog extends Dialog {

    EditText titleET;
    EditText quantityET;
    EditText measureET;
    Button okB;
    Button cancelB;

    okButtonListener listener;

    public void setListener(okButtonListener listener){
        this.listener = listener;
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = titleET.getText().toString();
            double quantity = Double.parseDouble(quantityET.getText().toString());
            String measure = measureET.getText().toString();

            ShoppingItem item = new ShoppingItem(name,quantity, measure);

            if(listener != null){
                listener.onOkButtonClicked(item);
            }

            hide();
        }
    };

    public AddEditDialog(@NonNull Context context) {
        super(context);
        init();
    }

    private void init(){
        setContentView(R.layout.add_edit_custom_dialog);

        titleET = findViewById(R.id.addEditNameET);
        quantityET = findViewById(R.id.addEditQuantityET);
        measureET = findViewById(R.id.addEditMeasureET);
        okB = findViewById(R.id.addEditOkButton);
        cancelB = findViewById(R.id.addEditCancelB);

        cancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
            }
        });

        okB.setOnClickListener(onClick);

        show();
    }

    public interface okButtonListener  {
        void onOkButtonClicked(ShoppingItem item);
    }

}
