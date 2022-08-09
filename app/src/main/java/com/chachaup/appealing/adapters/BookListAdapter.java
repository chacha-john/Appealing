package com.chachaup.appealing.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chachaup.appealing.R;
import com.chachaup.appealing.models.BookResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {
    private Context mContext;
    private List<BookResponse> mBooks;

    public BookListAdapter(Context context, List<BookResponse> books) {
        mContext = context;
        mBooks = books;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageViewBookCover)
        ImageView mBookCover;
        @BindView(R.id.textViewBookName)
        TextView mBookName;
        @BindView(R.id.textViewBookAuthor) TextView mBookAuthor;
        @BindView(R.id.textViewDescription) TextView mBookDescription;
        @BindView(R.id.textViewPrice) TextView mBookPrice;

        private Context mContext;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mContext = itemView.getContext();
        }

        public void bindBook(BookResponse book){
            mBookName.setText(book.getBookName());
            mBookAuthor.setText(book.getAuthor());
            mBookDescription.setText(book.getDescription());
            mBookPrice.setText(book.getPrice());
        }
    }

    @NonNull
    @Override
    public BookListAdapter.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item,parent,false);
        BookViewHolder viewHolder = new BookViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookListAdapter.BookViewHolder holder, int position) {
        holder.bindBook(mBooks.get(position));
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }
}
