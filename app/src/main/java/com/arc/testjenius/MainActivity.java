package com.arc.testjenius;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arc.jenius_api.ContactModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View,ContactAdapter.Listener{

    @BindView(R.id.rvContact)
    RecyclerView rvContact;
    @BindView(R.id.btnAddContact)
    Button btnAddContact;
    @BindView(R.id.sw)
    SwipeRefreshLayout sw;

    List<ContactModel> listContact = new ArrayList<>();
    ContactAdapter contactAdapter;

    MainContract.Presenter presenter;

    ViewLoading loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecycler();
        loading = new ViewLoading(MainActivity.this);

        presenter = new MainPresenter(this);
        presenter.getAllContact();
        loading.showDialog();

        sw.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light);

        sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getAllContact();
            }
        });
    }

    private void initRecycler(){
        contactAdapter = new ContactAdapter(listContact,this);
        rvContact.setAdapter(contactAdapter);
    }

    @OnClick(R.id.btnAddContact)
    public void addContact(){
        //Toast.makeText(MainActivity.this,"Add Contact",Toast.LENGTH_SHORT).show();
        showDialogSendMessage();
    }

    @Override
    public void showGetAllContact(List<ContactModel> listContact) {
        this.listContact.clear();
        this.listContact.addAll(listContact);
        contactAdapter.notifyDataSetChanged();
        loading.hideDialog();

        if(sw.isRefreshing()){
            sw.setRefreshing(false);
        }
    }

    private void showDialogSendMessage(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_contact,null);
        dialogBuilder.setView(dialogView);

        final EditText etFirstName = (EditText) dialogView.findViewById(R.id.etFirstName);
        final EditText etLastName = (EditText) dialogView.findViewById(R.id.etLastName);
        final EditText etAge = (EditText) dialogView.findViewById(R.id.etAge);
        final EditText etUrlPhoto = (EditText) dialogView.findViewById(R.id.etPhoto);
        Button btnAddContact = (Button) dialogView.findViewById(R.id.btnAddContact);

        final AlertDialog b = dialogBuilder.create();
        b.show();

        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.postContact(etFirstName.getText().toString(),etLastName.getText().toString(),Integer.parseInt(etAge.getText().toString()),etUrlPhoto.getText().toString());
                b.dismiss();
            }
        });
    }

    @Override
    public void showPostContactResult(String message) {
        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
        presenter.getAllContact();
        loading.showDialog();
    }

    @Override
    public void showGetContactById(ContactModel contactModelById) {
        Toast.makeText(MainActivity.this,"contact pick: "+contactModelById.fisrtName,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDeleteContactById(String message) {
        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
        presenter.getAllContact();
        loading.showDialog();
    }

    @Override
    public void onClick(String id) {
        presenter.getContactById(id);
        Toast.makeText(MainActivity.this,id,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void delete(String id) {
        presenter.deleteContact(id);
    }
}
