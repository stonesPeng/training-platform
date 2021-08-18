package me.stone.training.platform.spring4all.lib.jar.core.port;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
import java.util.Optional;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/16
 */
public interface BizRepository {
    Optional<Integer> saveRecord(BizRecord record);

    Optional<BizRecord> loadById(long id);

    @Getter
    @Setter
    @ToString
    class BizRecord {

        private Map<String, String> params;
        private Long id;

        public BizRecord(Map<String, String> params) {
            this.params = params;
        }

        public BizRecord(Map<String, String> params, long id) {
            this.params = params;
            this.id = id;
        }
        //... other fields
    }


}
