package sample.pre.base.entity;

import lombok.Data;

@Data
public abstract class Entity implements PrimaryKey {

	private Long id;
}
