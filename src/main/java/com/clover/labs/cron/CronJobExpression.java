package com.clover.labs.cron;

import java.util.Date;

import org.quartz.CronExpression;

public class CronJobExpression {

    private static final String expression = "";

    public static void main(String[] args) throws Exception {
        CronExpression job = new CronExpression("0 30 17 * * ?");

        Date now = new Date();
        System.out.println(now);

        Date when = job.getTimeAfter(now);
        System.out.println(when);

        Date when1 = job.getTimeAfter(when);
        System.out.println(when1);


        long time = job.getTimeAfter(new Date(System.currentTimeMillis())).getTime();
        System.out.println(time);
        System.out.println(new Date(time));


        time = job.getTimeAfter(new Date(time)).getTime();
        System.out.println(time);
        System.out.println(new Date(time));
    }

}
