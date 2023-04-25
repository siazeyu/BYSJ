package com.boot.credit.domain;

import java.util.ArrayList;
import java.util.List;

public class DoubleLinkedNode <D>{

    private DoubleLinkedNode last;

    private DoubleLinkedNode next;

    private List<D> data = new ArrayList<>();

    public DoubleLinkedNode getLast() {
        return last;
    }

    public void setLast(DoubleLinkedNode last) {
        this.last = last;
    }

    public DoubleLinkedNode getNext() {
        return next;
    }

    public void setNext(DoubleLinkedNode next) {
        this.next = next;
    }

    public List<D> getData() {
        return data;
    }

    public void setData(List<D> data) {
        this.data = data;
    }

    public void addData(D data){
        this.data.add(data);
    }

    public DoubleLinkedNode<D> getParent(){
        if (this.last == null){
            return this;
        }
        return this.last.getParent();
    }
}
