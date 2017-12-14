package me.forxx.springboot4kt.model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    /**
     *  ,所属表字段为message.id
     */
    private Integer id;

    /**
     *  昵称,所属表字段为message.nick_name
     */
    private String nick_name;

    /**
     *  头像,所属表字段为message.face
     */
    private String face;

    /**
     *  赞,所属表字段为message.praise
     */
    private Integer praise;

    /**
     *  添加时间,所属表字段为message.add_time
     */
    private Date add_time;

    /**
     *  内容,所属表字段为message.content
     */
    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", nick_name=").append(nick_name);
        sb.append(", face=").append(face);
        sb.append(", praise=").append(praise);
        sb.append(", add_time=").append(add_time);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}