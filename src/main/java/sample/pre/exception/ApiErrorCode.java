package sample.pre.exception;

import org.springframework.http.HttpStatus;

/** ---------------------------------------------------------------
 *     비즈니스 로직에서 발생하는 모든 에러는 ApiErrorCode로 정의합니다.
 *   - API_ERROR_SYMBOL_EXAMPLE ( HTTP 상태 코드, "에러 카테고리" )
 * ----------------------------------------------------------------*/
public enum ApiErrorCode {
	/**
	 * Board
	 */
	INVALID_BOARD_CATEGORY 	( HttpStatus.BAD_REQUEST, "게시판 카테고리 오류" ),
	BOARD_NOT_FOUND 		( HttpStatus.NOT_FOUND, "게시판 조회 불가" ),

	/**
	 * Article
	 */
	ARTICLE_NOT_FOUND 		( HttpStatus.NOT_FOUND, "게시글 조회 불가" ),

	/**
	 * Comment
	 */
	COMMENT_NOT_FOUND 		( HttpStatus.NOT_FOUND, "댓글 조회 불가" ),

	/**
	 * Authorization
	 */
	UNAUTHORIZED_VIEWER 	( HttpStatus.UNAUTHORIZED, "권한 없음" ),


	; //-------------------------------------------------------------------------

	private final HttpStatus status;
	private final String errorSubject;

	ApiErrorCode(HttpStatus status, String errorSubject) {
		this.status = status;
		this.errorSubject = errorSubject;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getErrorSubject() {
		return errorSubject;
	}

	@Override
	public String toString() {
		return "ApiErrorCode{" +
			"status=" + status +
			", errorSubject='" + errorSubject + '\'' +
			'}';
	}
}
