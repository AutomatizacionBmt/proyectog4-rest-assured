package com.company.entities;

public class Project {

    private String name;
    private String identifier;
    private String description;
    private Boolean inherit_members;
    private Boolean is_public;

    public Project(){

    }

    public Project(String name, String identifier, String description, Boolean inherit_members, Boolean is_public) {
        this.name = name;
        this.identifier = identifier;
        this.description = description;
        this.inherit_members = inherit_members;
        this.is_public = is_public;
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

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", identifier='" + identifier + '\'' +
                ", description='" + description + '\'' +
                ", inherit_members=" + inherit_members +
                ", is_public=" + is_public +
                '}';
    }
}
