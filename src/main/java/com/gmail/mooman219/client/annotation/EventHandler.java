package com.gmail.mooman219.client.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.gmail.mooman219.client.enums.GameState;
import com.gmail.mooman219.shared.Order;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
    Order order() default Order.DEFAULT;
    GameState state() default GameState.GLOBAL;
}