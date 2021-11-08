package me.stone.training.platform.training.common.tool.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ExceptionUtil {
    static <T extends Throwable> Optional<T> findException(Throwable source, Class<T> target) {
        List<Throwable> lasts = new ArrayList<>();
        Throwable found = source;
        while (found != null) {
            found = found.getCause();
            if (found == null) return Optional.empty();
            if (target.isAssignableFrom(found.getClass())) return Optional.of(target.cast(found));
            if (lasts.contains(found)) break;
            lasts.add(found);
        }
        return Optional.empty();
    }
}
