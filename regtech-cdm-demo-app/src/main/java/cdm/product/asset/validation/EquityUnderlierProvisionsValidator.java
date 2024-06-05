package cdm.product.asset.validation;

import cdm.product.asset.EquityUnderlierProvisions;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class EquityUnderlierProvisionsValidator implements Validator<EquityUnderlierProvisions> {

	private List<ComparisonResult> getComparisonResults(EquityUnderlierProvisions o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("multipleExchangeIndexAnnexFallback", (Boolean) o.getMultipleExchangeIndexAnnexFallback() != null ? 1 : 0, 0, 1), 
				checkCardinality("componentSecurityIndexAnnexFallback", (Boolean) o.getComponentSecurityIndexAnnexFallback() != null ? 1 : 0, 0, 1), 
				checkCardinality("localJurisdiction", (FieldWithMetaString) o.getLocalJurisdiction() != null ? 1 : 0, 0, 1), 
				checkCardinality("relevantJurisdiction", (FieldWithMetaString) o.getRelevantJurisdiction() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<EquityUnderlierProvisions> validate(RosettaPath path, EquityUnderlierProvisions o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EquityUnderlierProvisions", ValidationType.CARDINALITY, "EquityUnderlierProvisions", path, "", error);
		}
		return success("EquityUnderlierProvisions", ValidationType.CARDINALITY, "EquityUnderlierProvisions", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EquityUnderlierProvisions o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EquityUnderlierProvisions", ValidationType.CARDINALITY, "EquityUnderlierProvisions", path, "", res.getError());
				}
				return success("EquityUnderlierProvisions", ValidationType.CARDINALITY, "EquityUnderlierProvisions", path, "");
			})
			.collect(toList());
	}

}
