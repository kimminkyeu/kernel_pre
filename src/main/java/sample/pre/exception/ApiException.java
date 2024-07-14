package sample.pre.exception;

import java.util.List;
import sample.pre.common.ApiErrorResponse;

public class ApiException extends RuntimeException {
	private final ApiErrorCode apiErrorCode;

	public ApiException(ApiErrorCode error, String detailedMessage) {
		super(detailedMessage);
		this.apiErrorCode = error;
	}

	public ApiErrorResponse toApiErrorResponse() {
		return ApiErrorResponse.builder()
							   .httpStatus( apiErrorCode.getStatus() )
							   .mainReason( apiErrorCode.getErrorSubject() )
							   .details( List.of(this.getMessage()) )
							   .build();
	}

	@Override
	public String toString() {
		return "ApiException{" +
			"apiError=" + apiErrorCode +
			'}';
	}
}