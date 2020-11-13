package com.company.entities;

import java.util.List;

public class ListProjects {

    private List<Project> projects;
    private Integer total_count;
    private Integer offset;
    private Integer limit;

    public ListProjects() {
    }

    public ListProjects(List<Project> projects, Integer total_count, Integer offset, Integer limit) {
        this.projects = projects;
        this.total_count = total_count;
        this.offset = offset;
        this.limit = limit;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
