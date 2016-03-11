package com.buf.carloop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zijin on 10/03/16.
 */
public class MessageClass {
    private String name;
    private byte[] avatar;
    private String datetime;
    private String content;

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
    public static int addMessage(String username, String content, int carpoolid) {
        return 0;
    }

    /*
    get message list with given carpoolid
    order by datetime ascending
     */
    public static List<MessageClass> getMessageList(int carpoolid) {
        return generatefakelist();
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
