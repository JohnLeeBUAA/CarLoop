package com.buf.carloop;

import com.buf.database.AsyncSQLLongHaul;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zijin on 10/03/16.
 */
public class ReviewClass {

    private String reviewername;
    private byte[] reviewavatar;
    private double rate;
    private String review;

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
            int value = task.get(100000, TimeUnit.MILLISECONDS);
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
        return generatefakelist();
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
