package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.QuasiGovernmentIssuerType;
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

public class QuasiGovernmentIssuerTypeValidator implements Validator<QuasiGovernmentIssuerType> {

	private List<ComparisonResult> getComparisonResults(QuasiGovernmentIssuerType o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("sovereignEntity", (Boolean) o.getSovereignEntity() != null ? 1 : 0, 1, 1), 
				checkCardinality("sovereignRecourse", (Boolean) o.getSovereignRecourse() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<QuasiGovernmentIssuerType> validate(RosettaPath path, QuasiGovernmentIssuerType o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("QuasiGovernmentIssuerType", ValidationType.CARDINALITY, "QuasiGovernmentIssuerType", path, "", error);
		}
		return success("QuasiGovernmentIssuerType", ValidationType.CARDINALITY, "QuasiGovernmentIssuerType", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, QuasiGovernmentIssuerType o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("QuasiGovernmentIssuerType", ValidationType.CARDINALITY, "QuasiGovernmentIssuerType", path, "", res.getError());
				}
				return success("QuasiGovernmentIssuerType", ValidationType.CARDINALITY, "QuasiGovernmentIssuerType", path, "");
			})
			.collect(toList());
	}

}
