package com.example.recyclerviewproject_contacts;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    public ArrayList<String> contacts = new ArrayList<>();
    private static final String TAG = "ContactAdapter";
    private ItemEventListener itemEventListener;

    public ContactAdapter(ItemEventListener itemEventListener) {
        this.itemEventListener = itemEventListener;
        contacts.add("Ruthann Trustrie");
        contacts.add("Peadar Dawtrey");
        contacts.add("Felipe Bradtke");
        contacts.add("Claude Crissil");
        contacts.add("Jacky Girardeau");
        contacts.add("Rubia Dominguez");
        contacts.add("Michaela Churchley");
        contacts.add("Harvey Pentelow");
        contacts.add("Neilla Langton");
        contacts.add("Marco Greaves");
        contacts.add("Liz Batchley");
        contacts.add("Lamond Littlepage");
        contacts.add("Malina Weir");
        contacts.add("Tomlin Lenchenko");
        contacts.add("Hy Pavelin");
        contacts.add("Jenelle Palin");
        contacts.add("Damon Knewstubb");
        contacts.add("Alex Ivanusyev");
        contacts.add("Hamil Callery");
        contacts.add("Karol Syer");

    }

    public void addNewContact(String fullName) {
        contacts.add(0, fullName);
        notifyItemInserted(0);
    }

    public void updateContact(int position, String fullName) {
        contacts.set(position, fullName);
        notifyItemChanged(position);


    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: position=> " + position);
        holder.bindContact(contacts.get(position));


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    public class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView firstCharacterTv;
        private TextView fullNameTv;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            firstCharacterTv = itemView.findViewById(R.id.tv_contact_firstCharacter);
            fullNameTv = itemView.findViewById(R.id.tv_contact_fullName);
        }

        public void bindContact(final String fullName) {
            fullNameTv.setText(fullName);
            firstCharacterTv.setText(fullName.substring(0, 1));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(v.getContext(), fullName, Toast.LENGTH_SHORT).show();
                    itemEventListener.onItemClick(fullName, getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    contacts.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return false;
                }
            });

        }
    }

    public interface ItemEventListener {
        void onItemClick(String fullName, int position);

    }
}

