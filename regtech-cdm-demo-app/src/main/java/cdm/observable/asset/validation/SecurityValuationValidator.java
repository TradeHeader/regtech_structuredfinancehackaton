package cdm.observable.asset.validation;

import cdm.base.staticdata.asset.common.Security;
import cdm.observable.asset.SecurityValuation;
import cdm.observable.asset.SecurityValuationModel;
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

public class SecurityValuationValidator implements Validator<SecurityValuation> {

	private List<ComparisonResult> getComparisonResults(SecurityValuation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("securityValuationModel", (SecurityValuationModel) o.getSecurityValuationModel() != null ? 1 : 0, 1, 1), 
				checkCardinality("underlier", (Security) o.getUnderlier() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<SecurityValuation> validate(RosettaPath path, SecurityValuation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SecurityValuation", ValidationType.CARDINALITY, "SecurityValuation", path, "", error);
		}
		return success("SecurityValuation", ValidationType.CARDINALITY, "SecurityValuation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SecurityValuation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SecurityValuation", ValidationType.CARDINALITY, "SecurityValuation", path, "", res.getError());
				}
				return success("SecurityValuation", ValidationType.CARDINALITY, "SecurityValuation", path, "");
			})
			.collect(toList());
	}

}
