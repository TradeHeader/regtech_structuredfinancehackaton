package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.RegionalGovernmentIssuerType;
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

public class RegionalGovernmentIssuerTypeValidator implements Validator<RegionalGovernmentIssuerType> {

	private List<ComparisonResult> getComparisonResults(RegionalGovernmentIssuerType o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("sovereignRecourse", (Boolean) o.getSovereignRecourse() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<RegionalGovernmentIssuerType> validate(RosettaPath path, RegionalGovernmentIssuerType o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("RegionalGovernmentIssuerType", ValidationType.CARDINALITY, "RegionalGovernmentIssuerType", path, "", error);
		}
		return success("RegionalGovernmentIssuerType", ValidationType.CARDINALITY, "RegionalGovernmentIssuerType", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, RegionalGovernmentIssuerType o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("RegionalGovernmentIssuerType", ValidationType.CARDINALITY, "RegionalGovernmentIssuerType", path, "", res.getError());
				}
				return success("RegionalGovernmentIssuerType", ValidationType.CARDINALITY, "RegionalGovernmentIssuerType", path, "");
			})
			.collect(toList());
	}

}
