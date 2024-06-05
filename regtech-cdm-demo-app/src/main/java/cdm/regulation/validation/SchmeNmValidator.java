package cdm.regulation.validation;

import cdm.regulation.SchmeNm;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SchmeNmValidator implements Validator<SchmeNm> {

	private List<ComparisonResult> getComparisonResults(SchmeNm o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("prtry", (String) o.getPrtry() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<SchmeNm> validate(RosettaPath path, SchmeNm o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SchmeNm", ValidationType.CARDINALITY, "SchmeNm", path, "", error);
		}
		return success("SchmeNm", ValidationType.CARDINALITY, "SchmeNm", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SchmeNm o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SchmeNm", ValidationType.CARDINALITY, "SchmeNm", path, "", res.getError());
				}
				return success("SchmeNm", ValidationType.CARDINALITY, "SchmeNm", path, "");
			})
			.collect(toList());
	}

}
