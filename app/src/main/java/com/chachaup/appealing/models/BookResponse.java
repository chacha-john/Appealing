
package com.chachaup.appealing.models;

import javax.annotation.Generated;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
@Generated("jsonschema2pojo")
public class BookResponse implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("bookName")
    @Expose
    private String bookName;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("img")
    @Expose
    private Integer img;
    public final static Creator<BookResponse> CREATOR = new Creator<BookResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public BookResponse createFromParcel(android.os.Parcel in) {
            return new BookResponse(in);
        }

        public BookResponse[] newArray(int size) {
            return (new BookResponse[size]);
        }

    }
    ;

    protected BookResponse(android.os.Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.bookName = ((String) in.readValue((String.class.getClassLoader())));
        this.author = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.img = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public BookResponse() {
    }

    /**
     * 
     * @param img
     * @param author
     * @param price
     * @param description
     * @param id
     * @param bookName
     */
    public BookResponse(Integer id, String bookName, String author, String description, Integer price, Integer img) {
        super();
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.description = description;
        this.price = price;
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(bookName);
        dest.writeValue(author);
        dest.writeValue(description);
        dest.writeValue(price);
        dest.writeValue(img);
    }

    public int describeContents() {
        return  0;
    }

}
