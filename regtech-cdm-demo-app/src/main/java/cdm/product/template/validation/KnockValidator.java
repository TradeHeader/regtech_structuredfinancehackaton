package cdm.product.template.validation;

import cdm.observable.event.TriggerEvent;
import cdm.product.template.Knock;
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

public class KnockValidator implements Validator<Knock> {

	private List<ComparisonResult> getComparisonResults(Knock o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("knockIn", (TriggerEvent) o.getKnockIn() != null ? 1 : 0, 0, 1), 
				checkCardinality("knockOut", (TriggerEvent) o.getKnockOut() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Knock> validate(RosettaPath path, Knock o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Knock", ValidationType.CARDINALITY, "Knock", path, "", error);
		}
		return success("Knock", ValidationType.CARDINALITY, "Knock", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Knock o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Knock", ValidationType.CARDINALITY, "Knock", path, "", res.getError());
				}
				return success("Knock", ValidationType.CARDINALITY, "Knock", path, "");
			})
			.collect(toList());
	}

}
