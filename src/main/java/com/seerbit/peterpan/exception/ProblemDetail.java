package com.seerbit.peterpan.exception;

import lombok.Builder;

@Builder
public class ProblemDetail {

    private String title;
    private String detail;
    private int status;

    public ProblemDetail(String title, String detail, int status) {
        this.title = title;
        this.detail = detail;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

