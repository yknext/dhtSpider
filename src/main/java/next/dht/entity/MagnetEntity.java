package next.dht.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by next on 2016/8/10.
 */
@Entity
@Table(name = "magnet", schema = "dht")
public class MagnetEntity {
    private long id;
    private String magnet;
    private String name;
    private Date updateTime;
    private int mCount;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "magnet")
    public String getMagnet() {
        return magnet;
    }

    public void setMagnet(String magent) {
        this.magnet = magent;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MagnetEntity that = (MagnetEntity) o;

        if (id != that.id) return false;
        if (magnet != null ? !magnet.equals(that.magnet) : that.magnet != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (magnet != null ? magnet.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "m_count")
    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }
}
