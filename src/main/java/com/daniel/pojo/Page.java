package com.daniel.pojo;

public class Page {

    private int start = 0;
    private int end = 0;
    private int count = 16;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void calculateEnd(int total) {
        if (total % count == 0) {
            this.end = total - count;
        }else {
            this.end = total - total % count;
        }
    }
}
