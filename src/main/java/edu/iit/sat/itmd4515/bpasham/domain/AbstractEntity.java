/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.bpasham.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author bhanu
 */
@MappedSuperclass
public class AbstractEntity {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "BEVERAGE_ID")
    protected Long id;

    @Version
    private long version;

    private LocalDateTime createdTimeStamp;

    private LocalDateTime modifiedTimeStamp;

    /**
     *
     */
    @PrePersist
    public void initializeCreatedtimestamp(){
        
        this.createdTimeStamp = LocalDateTime.now();
    }
    
    /**
     *
     */
    @PreUpdate
    public void initializeModifiedTimestamp(){
        this.modifiedTimeStamp= LocalDateTime.now();
    }
    
    
    
    /**
     * Get the value of modifiedTimeStamp
     *
     * @return the value of modifiedTimeStamp
     */
    public LocalDateTime getModifiedTimeStamp() {
        return modifiedTimeStamp;
    }

    /**
     * Set the value of modifiedTimeStamp
     *
     * @param modifiedTimeStamp new value of modifiedTimeStamp
     */
    public void setModifiedTimeStamp(LocalDateTime modifiedTimeStamp) {
        this.modifiedTimeStamp = modifiedTimeStamp;
    }

    /**
     * Get the value of createdTimeStamp
     *
     * @return the value of createdTimeStamp
     */
    public LocalDateTime getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    /**
     * Set the value of createdTimeStamp
     *
     * @param createdTimeStamp new value of createdTimeStamp
     */
    public void setCreatedTimeStamp(LocalDateTime createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractEntity other = (AbstractEntity) obj;
        if (this.id == null || other.id == null) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Long id) {
        this.id = id;
    }
}
