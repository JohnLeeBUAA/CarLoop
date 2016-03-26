package com.buf.carloop;

import com.buf.database.AsyncSQLLongHaul;
import com.buf.database.AsyncSelectSomeNote;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import static com.buf.carloop.User.selectSQLBlob;

/**
 * Created by zijin on 10/03/16.
 */
public class ReviewClass {

    private String reviewername;
    private String drivername;
    private byte[] reviewavatar;
    private double rate;
    private String review;
    private int review_id;

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public String getReviewername() {
        return reviewername;
    }

    public void setReviewername(String reviewername) {
        this.reviewername = reviewername;
    }

    public byte[] getReviewavatar() {
        return reviewavatar;
    }

    public void setReviewavatar(byte[] reviewavatar) {
        this.reviewavatar = reviewavatar;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public ReviewClass() {
    }

    public ReviewClass(String reviewername, byte[] reviewavatar, double rate, String review) {
        this.reviewername = reviewername;
        this.reviewavatar = reviewavatar;
        this.rate = rate;
        this.review = review;
    }

    /*
    insert into review
     */
    public static int addReview(String reviewername, String drivername, double rate, String review) {
        String sqlComm = "insert into review (r_passengername, r_drivername, r_rate, r_review) values ('" +
                reviewername + "', '" + drivername + "', " + rate + ", '" + review + "');";

        AsyncSQLLongHaul task = new AsyncSQLLongHaul();

        task.execute(sqlComm);
        try {
            int value = (int) task.get(10000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
    select all review records on the given driver
     */
    public static List<ReviewClass> getReviewList(String drivername) {
        String sqlComm =  "select * from review where r_drivername = '" + drivername + "';";
        List<ReviewClass> list = new ArrayList<ReviewClass>();
        ReviewClass review;
        AsyncSelectSomeNote task = new AsyncSelectSomeNote();

        task.execute(sqlComm);
        try {
            Vector value_original = task.get(10000, TimeUnit.MILLISECONDS);
            Vector value;
            if (value_original == null) {
                return null;
            } else if (value_original.size() > 0) {
                for (int i = 0; i < value_original.size(); i++) {
                    value = (Vector) value_original.elementAt(i);
                    review= new ReviewClass();
                    review.setReview_id((int) value.elementAt(0));
                    review.setReviewername((String) value.elementAt(1));
                    review.setDrivername((String) value.elementAt(2));
                    review.setRate((double) value.elementAt(3));
                    review.setReview(value.elementAt(4).toString());
                    review.setReviewavatar(selectSQLBlob(review.getReviewername()));
                    list.add(review);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public static List<ReviewClass> generatefakelist() {
        List<ReviewClass> list = new ArrayList<>();
        list.add(new ReviewClass("Reviewer1", null, 4.5D, "Review_Content_1"));
        list.add(new ReviewClass("Reviewer2", null, 5D, "Review_Content_2"));
        list.add(new ReviewClass("Reviewer3", null, 3D, "Review_Content_3"));
        list.add(new ReviewClass("Reviewer4", null, 3.5D, "Review_Content_4"));
        return list;
    }
}
