package cdm.observable.asset.validation;

import cdm.base.math.QuantifierEnum;
import cdm.observable.asset.MultipleDebtTypes;
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

public class MultipleDebtTypesValidator implements Validator<MultipleDebtTypes> {

	private List<ComparisonResult> getComparisonResults(MultipleDebtTypes o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("condition", (QuantifierEnum) o.getCondition() != null ? 1 : 0, 1, 1), 
				checkCardinality("debtType", (List<? extends FieldWithMetaString>) o.getDebtType() == null ? 0 : ((List<? extends FieldWithMetaString>) o.getDebtType()).size(), 2, 0)
			);
	}

	@Override
	public ValidationResult<MultipleDebtTypes> validate(RosettaPath path, MultipleDebtTypes o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MultipleDebtTypes", ValidationType.CARDINALITY, "MultipleDebtTypes", path, "", error);
		}
		return success("MultipleDebtTypes", ValidationType.CARDINALITY, "MultipleDebtTypes", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MultipleDebtTypes o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MultipleDebtTypes", ValidationType.CARDINALITY, "MultipleDebtTypes", path, "", res.getError());
				}
				return success("MultipleDebtTypes", ValidationType.CARDINALITY, "MultipleDebtTypes", path, "");
			})
			.collect(toList());
	}

}
