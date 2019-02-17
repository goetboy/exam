package pers.goetboy.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.ToString;

/**
 * 实体状态
 *
 * @author goetb
 */
@ToString
public enum EntityState {
    /**
     * 正常
     */
    NORMAL(1),
    /**
     * 禁用
     */
    DISABLE(0);
    @JsonValue
    private Integer value;

    EntityState(Integer value) {
        this.value = value;
    }

    @JsonCreator
    public Integer getValue() {
        return value;
    }

    /**
     * 枚举入参注解
     *
     * @param value 参数值
     */
    public static EntityState getByValue(Integer value) {
        for (EntityState state : values()) {
            if (state.getValue().equals(value)) {
                return state;
            }
        }

        return null;
    }


}
