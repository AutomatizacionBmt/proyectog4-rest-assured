package com.company.entities;

import java.util.Map;

public class Project {

    private Integer id;
    private String name;
    private String identifier;
    private String description;
    private String homepage;
    private Integer status;
    private Boolean is_public;
    private Boolean inherit_members;
    private String created_on;
    private String updated_on;
    private Map<Object, Object> parent;


    public Project(){

    }

    public Project(Integer id, String name, String identifier, String description, String homepage, Integer status, Boolean is_public, Boolean inherit_members, String created_on, String updated_on) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.description = description;
        this.homepage = homepage;
        this.status = status;
        this.is_public = is_public;
        this.inherit_members = inherit_members;
        this.created_on = created_on;
        this.updated_on = updated_on;
    }

    public Project(Integer id, String name, String identifier, String description, String homepage, Integer status, Boolean is_public, Boolean inherit_members, String created_on, String updated_on, Map<Object, Object> parent) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.description = description;
        this.homepage = homepage;
        this.status = status;
        this.is_public = is_public;
        this.inherit_members = inherit_members;
        this.created_on = created_on;
        this.updated_on = updated_on;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getInherit_members() {
        return inherit_members;
    }

    public void setInherit_members(Boolean inherit_members) {
        this.inherit_members = inherit_members;
    }

    public Boolean getIs_public() {
        return is_public;
    }

    public void setIs_public(Boolean is_public) {
        this.is_public = is_public;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(String updated_on) {
        this.updated_on = updated_on;
    }

    public Map<Object, Object> getParent() {
        return parent;
    }

    public void setParent(Map<Object, Object> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identifier='" + identifier + '\'' +
                ", description='" + description + '\'' +
                ", homepage='" + homepage + '\'' +
                ", status=" + status +
                ", is_public=" + is_public +
                ", inherit_members=" + inherit_members +
                ", created_on='" + created_on + '\'' +
                ", updated_on='" + updated_on + '\'' +
                '}';
    }
}
