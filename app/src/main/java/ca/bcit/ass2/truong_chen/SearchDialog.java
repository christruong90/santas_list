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

public class SearchDialog extends AppCompatDialogFragment {
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private SearchDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.search_dialog, null);

        builder.setView(view);
        builder.setTitle("Search for Child Entry");
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
        .setPositiveButton("submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String firstName = editTextFirstName.getText().toString();
                String lastname = editTextLastName.getText().toString();
                listener.applyTexts(firstName, lastname);

            }
        });
        editTextFirstName = view.findViewById(R.id.edit_firstName);
        editTextLastName = view.findViewById(R.id.edit_lastName);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (SearchDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement SearchDialogListener");
        }
    }

    public interface SearchDialogListener{
        void applyTexts(String firstName, String lastName);
    }
}
