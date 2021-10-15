package biz.galaxygroup.atn.product.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

/**
 * @author blaise irakoze
 */
@Entity
public class AtnProduct {
    @Id
    @Column(name = "id", length = 90)
    private String id;
    @Column(name = "name", length = 90)
    private String name;
    @Column(name = "status", length = 30)
    private String status;
    private Date creationTime;
    private String searchBy;

    @PrePersist
    public void prepare() {
        this.creationTime = this.creationTime == null ? new Date() : this.creationTime;
        this.id = this.id == null ? UUID.randomUUID().toString() : this.id;
        this.searchBy = this.name + "," + this.status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AtnProduct{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", creationTime=" + creationTime +
                ", status='" + status + '\'' +
                '}';
    }
}
