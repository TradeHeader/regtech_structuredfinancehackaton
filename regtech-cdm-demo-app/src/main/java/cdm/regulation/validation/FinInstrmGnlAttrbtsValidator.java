package cdm.regulation.validation;

import cdm.regulation.FinInstrmGnlAttrbts;
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

public class FinInstrmGnlAttrbtsValidator implements Validator<FinInstrmGnlAttrbts> {

	private List<ComparisonResult> getComparisonResults(FinInstrmGnlAttrbts o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("fullNm", (String) o.getFullNm() != null ? 1 : 0, 1, 1), 
				checkCardinality("clssfctnTp", (String) o.getClssfctnTp() != null ? 1 : 0, 1, 1), 
				checkCardinality("ntnlCcy", (String) o.getNtnlCcy() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<FinInstrmGnlAttrbts> validate(RosettaPath path, FinInstrmGnlAttrbts o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FinInstrmGnlAttrbts", ValidationType.CARDINALITY, "FinInstrmGnlAttrbts", path, "", error);
		}
		return success("FinInstrmGnlAttrbts", ValidationType.CARDINALITY, "FinInstrmGnlAttrbts", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FinInstrmGnlAttrbts o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FinInstrmGnlAttrbts", ValidationType.CARDINALITY, "FinInstrmGnlAttrbts", path, "", res.getError());
				}
				return success("FinInstrmGnlAttrbts", ValidationType.CARDINALITY, "FinInstrmGnlAttrbts", path, "");
			})
			.collect(toList());
	}

}
