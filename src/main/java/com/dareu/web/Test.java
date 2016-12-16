package com.dareu.web;

import java.util.UUID;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
public class Test {
    public static void main(String[] args) {
        for(int i = 0; i < 5; i ++)
            System.out.println(UUID.randomUUID().toString());
    }
}
