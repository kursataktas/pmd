/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.rule.internal;

import static net.sourceforge.pmd.properties.PropertyFactory.stringListProperty;

import java.util.Collection;
import java.util.List;

import net.sourceforge.pmd.properties.PropertyDescriptor;

/**
 *
 */
public final class JavaPropertyUtil {

    private JavaPropertyUtil() {
        // utility class
    }

    public static PropertyDescriptor<List<String>> ignoredAnnotationsProperty(Collection<String> defaultValue) {
        return stringListProperty("ignoredAnnotations")
            .desc("Fully qualified names of the annotation types that should be ignored by this rule")
            .defaultValue(defaultValue)
            .build();
    }
}
