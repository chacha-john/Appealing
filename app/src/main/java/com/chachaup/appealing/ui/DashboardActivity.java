package com.chachaup.appealing.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chachaup.appealing.R;
import com.chachaup.appealing.adapters.BookListAdapter;
import com.chachaup.appealing.models.BookResponse;
import com.chachaup.appealing.network.Api;
import com.chachaup.appealing.network.Client;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    @BindView(R.id.textViewError)
    TextView mErrorTextView;
    @BindView(R.id.recyclerViewHotSales)
    RecyclerView mHotSales;
    private BookListAdapter mAdapter;
    public List<BookResponse> mBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        Api client = Client.getClient();

        Call<BookResponse> call = client.getBooks();
        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.isSuccessful()){
                    //TODO: get list of books as a response
                    mAdapter = new BookListAdapter(DashboardActivity.this, mBooks);
                    mHotSales.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DashboardActivity.this);
                    mHotSales.setLayoutManager(layoutManager);
                    mHotSales.setHasFixedSize(true);

                    showBooks();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                showFailureMessage();

            }
        });
    }
    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showBooks() {
        mHotSales.setVisibility(View.VISIBLE);
    }
}