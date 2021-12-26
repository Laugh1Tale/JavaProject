package com.company;

import java.util.Date;

public class Institution {
    private final int id;
    private final String title;
    private final String objectAction;
    private final Date startDateWork;
    private final Date endDateWork;
    private final long sumFunding;
    private final String typeSportComplex;

    public Institution(int id, String title, String objectAction, Date startDateWork, Date endDateWork, long sumFunding, String typeSportComplex) {
        this.id = id;
        this.title = title;
        this.objectAction = objectAction;
        this.startDateWork = startDateWork;
        this.endDateWork = endDateWork;
        this.sumFunding = sumFunding;
        this.typeSportComplex = typeSportComplex;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getObjectAction() {
        return objectAction;
    }

    public Date getStartDateWork() {
        return startDateWork;
    }

    public Date getEndDateWork() {
        return endDateWork;
    }

    public long getSumFunding() {
        return sumFunding;
    }

    public String getTypeSportComplex() {
        return typeSportComplex;
    }

    @Override
    public String toString() {
        return "Institution{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", objectAction='" + objectAction + '\'' +
                ", startDateWork=" + startDateWork +
                ", endDateWork=" + endDateWork +
                ", sumFunding=" + sumFunding +
                ", typeSportComplex='" + typeSportComplex + '\'' +
                '}';
    }
}
