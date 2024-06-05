package cdm.regulation.validation;

import cdm.regulation.AddtlAttrbts;
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

public class AddtlAttrbtsValidator implements Validator<AddtlAttrbts> {

	private List<ComparisonResult> getComparisonResults(AddtlAttrbts o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("rskRdcgTx", (String) o.getRskRdcgTx() != null ? 1 : 0, 1, 1), 
				checkCardinality("sctiesFincgTxInd", (String) o.getSctiesFincgTxInd() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<AddtlAttrbts> validate(RosettaPath path, AddtlAttrbts o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AddtlAttrbts", ValidationType.CARDINALITY, "AddtlAttrbts", path, "", error);
		}
		return success("AddtlAttrbts", ValidationType.CARDINALITY, "AddtlAttrbts", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AddtlAttrbts o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AddtlAttrbts", ValidationType.CARDINALITY, "AddtlAttrbts", path, "", res.getError());
				}
				return success("AddtlAttrbts", ValidationType.CARDINALITY, "AddtlAttrbts", path, "");
			})
			.collect(toList());
	}

}
