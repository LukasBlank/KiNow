package frontend;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class FinishDialog extends Dialog implements android.view.View.OnClickListener {

    public Activity activity;
    public Context context;
    public Dialog dialog;
    public Button finishBtn, cancelBtn;

    public FinishDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.finish_dialog);
        finishBtn = findViewById(R.id.finish_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        finishBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }//onCreate

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finish_btn:
                activity.finish();
                break;
            case R.id.cancel_btn:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

}
