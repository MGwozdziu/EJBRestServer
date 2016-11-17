package pl.eds.utils;

import java.util.Collection;
import java.util.Map;

public class CollectionsUtil {

    public static boolean isNullOrEmpty(final Collection<?> c) {
	return c == null || c.isEmpty();
    }

    public static boolean isNullOrEmpty(final Map<?, ?> m) {
	return m == null || m.isEmpty();
    }
}
