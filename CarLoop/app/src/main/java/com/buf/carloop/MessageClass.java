package com.buf.carloop;

import com.buf.database.AsyncSQLLongHaul;
import com.buf.database.AsyncSelectSomeNote;

import java.security.Timestamp;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import static com.buf.carloop.User.selectSQLBlob;

/**
 * Created by zijin on 10/03/16.
 */
public class MessageClass {
    private String name;
    private byte[] avatar;
    private String datetime;
    private String content;
    private int message_id;
    private int carpool_id;

    public int getMessage_id() {
        return this.message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public int getCarpool_id() {
        return this.carpool_id;
    }

    public void setCarpool_id(int carpool_id) {
        this.carpool_id = carpool_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageClass() {}

    public MessageClass(String name, byte[] avatar, String datetime, String content) {
        this.name = name;
        this.avatar = avatar;
        this.datetime = datetime;
        this.content = content;
    }

    /*
    insert record into message table
    need to generate datetime as well
     */
    public static int addMessage(String user_name, String content, int carpoolid) {
        String sqlComm =  "insert into message (m_username, m_carpoolid, m_content) values ('" + user_name + "', "
                + carpoolid + ", '" + content + "');";
        AsyncSQLLongHaul task = new AsyncSQLLongHaul();

        task.execute(sqlComm);
        try {
            int value = (int) task.get(100000, TimeUnit.MILLISECONDS);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
    get message list with given carpoolid
    order by datetime ascending
     */
    public static List<MessageClass> getMessageList(int carpoolid) {
        String sqlComm =  "select * from message where m_carpoolid = " + carpoolid + " and m_datetime > '"+ GlobalVariables.timestamp + "';";
        List<MessageClass> list = new ArrayList<MessageClass>();
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
                    MessageClass message = new MessageClass();
                    message.setMessage_id((int) value.elementAt(0));
                    message.setName((String) value.elementAt(1));
                    message.setCarpool_id((int) value.elementAt(2));
                    message.setContent((String) value.elementAt(3));
                    message.setDatetime(value.elementAt(4).toString());
                    message.setAvatar(selectSQLBlob(message.getName()));
                    list.add(message);
                }
                GlobalVariables.timestamp = list.get(list.size() - 1).getDatetime();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public static List<MessageClass> generatefakelist() {
        List<MessageClass> list = new ArrayList<MessageClass>();
        list.add(new MessageClass("john", null, "2015/03/02 12:22", "Hellozxczxczcxzxczxc"));
        list.add(new MessageClass("james", null, "2015/03/02 12:23", "Hello How are you"));
        list.add(new MessageClass("john", null, "2015/03/02 12:24", "I'm fine thank you. And you?"));
        list.add(new MessageClass("james", null, "2015/03/02 12:25", "blahblahblah"));
        return list;
    }
}
