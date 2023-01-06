package com.example.todolist;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


public class FragmentDialog extends DialogFragment {

    public interface OnItemDeleteListener {
        void onItemDeleted();
    }

    private OnItemDeleteListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle(R.string.common_delete);
        builder.setMessage("Do you want to delete this item from the list?");
        builder.setCancelable(false);

        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            if (listener != null) {
                listener.onItemDeleted();
            }
            dialogInterface.cancel();
        });

        return builder.create();
    }

    public void setListener(@NonNull OnItemDeleteListener listener) {

        this.listener = listener;

    }

};



