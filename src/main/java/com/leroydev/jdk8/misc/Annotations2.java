/*
 * More JDK8 Anotation features
 */
package com.leroydev.jdk8.misc;

import org.checkerframework.checker.nullness.qual.NonNull;
//import javax.annotation.Nonnull;
import java.util.List;

/**
 *
 * @author emaphis
 */
public class Annotations2 {

    public static void main(String[] args) {
        // Type annotations
        @NonNull String str;
        @NonNull List<String> strings1;
        List<@NonNull String> strings2;
    }


}
