package sample.pre.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

 @Data @Getter @Setter
 @NoArgsConstructor @AllArgsConstructor
public class UserSearchQueryParam {
	private Integer age;
	private Integer country;
}
