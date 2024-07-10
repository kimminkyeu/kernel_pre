package sample.pre.lombokGroup;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * NOTE: 아래 방식으로 쓰면 어떨까 싶었는데... 구현하기 어려워서 지원이 안되는 듯.
 * @참고: https://groups.google.com/g/project-lombok/c/CnvM6MbRSw8
 */

@Deprecated // 미지원 기능
@Documented // shown in the javadoc text
@Target({ElementType.TYPE}) // for class
@Retention(RetentionPolicy.SOURCE) // need at build time
// @Data @Getter @Setter @AllArgsConstructor
public @interface KBE_QUERY_PARAM {}