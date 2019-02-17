package pers.goetboy.model.vo;

import lombok.Data;

import java.util.Map;

/**
 * 分页查询条件.
 * 只支持 page/pageSize 形式使用 如需limit/offset 可以使用 {@link LimitPageAble}对象
 */
@Data
public class PageAble extends SearchAble {
    private int curPage;
    private int pageSize;
    private Map<String, Object> param;

}
