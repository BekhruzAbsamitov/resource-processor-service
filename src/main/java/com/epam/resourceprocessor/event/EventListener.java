package com.epam.resourceprocessor.event;

public interface EventListener<T> {

    void handleEvent(T data);

}
