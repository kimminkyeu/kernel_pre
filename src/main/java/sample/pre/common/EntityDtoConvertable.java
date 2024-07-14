package sample.pre.common;

/**
 * @work-in-progress
 * 공통된 Mapper 요소를 분리할 수 있지 않을까 싶었으나...
 * - (1) 분리해서 얻는 이득이 딱히 없다.
 * - (2) 엔티티별로 파라미터를 더 받아야 하는 경우 발생
 *      EX) 1:N | N:1 관계가 있는 Dto/Entity의 경우, 해당 관계 Entity를 함께 전달해야 하는 예외 상황 존재.
 * 	        --> 게시글 생성 Dto 생성
 * 		    --> 게시글 Entity로 변환
 * 		    --> 소속된 게시판 Entity를 게시글에 설정
 */
public interface EntityDtoConvertable<EntityType, DtoType> {
	EntityType toEntity(DtoType dto);
	DtoType toDto(EntityType dto);
}