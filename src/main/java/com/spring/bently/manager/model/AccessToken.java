package com.spring.bently.manager.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by wgq on 16-4-9.
 * 保存从微信获得的access_token
 */
@Entity
@Table(name = "accesstoken")
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * access_token
     */
    @NotNull
    @Column(length = 200)
    private String accesstoken;

    @NotNull
    @Column(length = 50)
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccessToken that = (AccessToken) o;

        if (id != that.id) return false;
        if (accesstoken != null ? !accesstoken.equals(that.accesstoken) : that.accesstoken != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (accesstoken != null ? accesstoken.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
