package me.stone.training.platform.spring4all.lib.jars.inf.repository;

import me.stone.training.platform.spring4all.lib.jars.core.port.BizRepository;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/16
 */
public class BizRepositoryImpl implements BizRepository {

    static final AtomicReference<SoftReference<Map<Long, BizRecord>>> REFER = new AtomicReference<>();
    static final AtomicLong AUTO_INCREMENT = new AtomicLong(0L);

    public BizRepositoryImpl() {
        REFER.set(new SoftReference<>(new HashMap<>()));
    }

    @Override
    public Optional<Integer> saveRecord(BizRecord record) {
        if (record.getId() != null
            && record.getId() > 0
            && Objects.requireNonNull(REFER.get().get()).containsKey(record.getId())) {
            return Optional.of(1);
        }
        final long id = AUTO_INCREMENT.incrementAndGet();
        record.setId(id);
        Objects.requireNonNull(REFER.get().get()).put(id, record);
        return Optional.of(1);
    }

    @Override
    public Optional<BizRecord> loadById(long id) {
        return Optional.ofNullable(Objects.requireNonNull(REFER.get().get()).get(id));
    }
}
