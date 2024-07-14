package sample.pre.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

/**
 * Response Error Message Body Format
 * RFC 7807 - "Problem Detail"
 */
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ApiErrorResponse extends ProblemDetail {
	private final List<String> details;
	private final String reason;

	@Builder
	private ApiErrorResponse(
		HttpStatus httpStatus,
		String mainReason,
		List<String> details
	) {
		super(ProblemDetail.forStatus(httpStatus));
		this.reason = mainReason;
		this.details = details;
	}
}