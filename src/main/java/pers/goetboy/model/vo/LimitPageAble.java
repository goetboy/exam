package pers.goetboy.model.vo;

import lombok.Data;

import java.util.Map;

/**
 * 分页查询条件.
 * 只支持 page/pageSize 形式使用 如需limit/offset
 */
@Data
public class LimitPageAble extends SearchAble {
    private int curPage;
    private int pageSize;
    private Map<String, Object> param;

}
