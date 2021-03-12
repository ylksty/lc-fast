package com.ylkget.base.common.validator.group;

import javax.validation.GroupSequence;

/**
 * <p>
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 * </p>
 *
 * @author joe 2021-03-11 09:11
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {
}
