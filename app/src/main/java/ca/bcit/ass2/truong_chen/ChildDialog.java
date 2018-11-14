package ca.bcit.ass2.truong_chen;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class ChildDialog extends AppCompatDialogFragment {
    private EditText editTextId;
    private ChildDialogLisenter listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.delete_dialog, null);

        builder.setView(view);
        builder.setTitle("Select ID to Delete From Santa's List");
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String delId = editTextId.getText().toString();
                        listener.applyTexts(delId);
                    }
                });
        editTextId = view.findViewById(R.id.edit_id);
        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ChildDialogLisenter) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement ChildDialogListener");
        }

    }

    public interface ChildDialogLisenter {
        void applyTexts(String id);
    }
}

