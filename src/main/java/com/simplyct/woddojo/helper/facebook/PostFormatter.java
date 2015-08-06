package com.simplyct.woddojo.helper.facebook;

import com.simplyct.woddojo.model.Schedule;
import com.simplyct.woddojo.model.Wod;

/**
 * Created by molsen_admin on 8/5/15.
 */
public class PostFormatter {
    public static String formatPost(Schedule schedule) {
        Wod wod = schedule.getWod();
        String wodDescription = notNull(wod.getDescription()).replaceAll("<br/>", "\n");
        String content = String.format("WOD for %1$ta, %1$tb %1te%n" +
                "%s%n" +
                "%s%n" +
                "%s%n",
                schedule.getWodDate(),
                notNull(wod.getName()),
                notNull(wodDescription),
                notNull(wod.getNotes()),
                notNull(schedule.getNotes()));
        return content;
    }

    private static String notNull(String value) {
        return value == null ? "" : value;
    }
}
