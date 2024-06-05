package cdm.regulation.validation;

import cdm.regulation.OrdrTrnsmssn;
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

public class OrdrTrnsmssnValidator implements Validator<OrdrTrnsmssn> {

	private List<ComparisonResult> getComparisonResults(OrdrTrnsmssn o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("trnsmssnInd", (String) o.getTrnsmssnInd() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<OrdrTrnsmssn> validate(RosettaPath path, OrdrTrnsmssn o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("OrdrTrnsmssn", ValidationType.CARDINALITY, "OrdrTrnsmssn", path, "", error);
		}
		return success("OrdrTrnsmssn", ValidationType.CARDINALITY, "OrdrTrnsmssn", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, OrdrTrnsmssn o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("OrdrTrnsmssn", ValidationType.CARDINALITY, "OrdrTrnsmssn", path, "", res.getError());
				}
				return success("OrdrTrnsmssn", ValidationType.CARDINALITY, "OrdrTrnsmssn", path, "");
			})
			.collect(toList());
	}

}
