package cdm.product.template.validation;

import cdm.product.template.CancelableProvision;
import cdm.product.template.EarlyTerminationProvision;
import cdm.product.template.EvergreenProvision;
import cdm.product.template.ExtendibleProvision;
import cdm.product.template.TerminationProvision;
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

public class TerminationProvisionValidator implements Validator<TerminationProvision> {

	private List<ComparisonResult> getComparisonResults(TerminationProvision o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("cancelableProvision", (CancelableProvision) o.getCancelableProvision() != null ? 1 : 0, 0, 1), 
				checkCardinality("earlyTerminationProvision", (EarlyTerminationProvision) o.getEarlyTerminationProvision() != null ? 1 : 0, 0, 1), 
				checkCardinality("evergreenProvision", (EvergreenProvision) o.getEvergreenProvision() != null ? 1 : 0, 0, 1), 
				checkCardinality("extendibleProvision", (ExtendibleProvision) o.getExtendibleProvision() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<TerminationProvision> validate(RosettaPath path, TerminationProvision o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("TerminationProvision", ValidationType.CARDINALITY, "TerminationProvision", path, "", error);
		}
		return success("TerminationProvision", ValidationType.CARDINALITY, "TerminationProvision", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TerminationProvision o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TerminationProvision", ValidationType.CARDINALITY, "TerminationProvision", path, "", res.getError());
				}
				return success("TerminationProvision", ValidationType.CARDINALITY, "TerminationProvision", path, "");
			})
			.collect(toList());
	}

}
