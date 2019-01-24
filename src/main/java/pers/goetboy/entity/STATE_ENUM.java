package pers.goetboy.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 实体状态
 *
 * @author goetb
 */
@Getter
public enum STATE_ENUM {
    NORMAL(1),
    DISABLE(0);
    @JsonValue
    private Integer value;

    STATE_ENUM(Integer value) {
        this.value = value;
    }

    /**
     * 枚举入参注解
     *
     * @param value 参数值
     */
    @JsonCreator
    public static STATE_ENUM getByValue(Integer value) {
        for (STATE_ENUM state : values()) {
            if (state.getValue().equals(value)) {
                return state;
            }
        }

        return null;
    }

}
