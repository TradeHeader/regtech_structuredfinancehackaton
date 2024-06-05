package cdm.regulation.validation;

import cdm.regulation.DerivInstrmAttrbts;
import cdm.regulation.FinInstrmGnlAttrbts;
import cdm.regulation.Othr;
import cdm.regulation.SchmeNm;
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

public class OthrValidator implements Validator<Othr> {

	private List<ComparisonResult> getComparisonResults(Othr o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("finInstrmGnlAttrbts", (FinInstrmGnlAttrbts) o.getFinInstrmGnlAttrbts() != null ? 1 : 0, 1, 1), 
				checkCardinality("derivInstrmAttrbts", (DerivInstrmAttrbts) o.getDerivInstrmAttrbts() != null ? 1 : 0, 1, 1), 
				checkCardinality("id", (String) o.getId() != null ? 1 : 0, 1, 1), 
				checkCardinality("schmeNm", (SchmeNm) o.getSchmeNm() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<Othr> validate(RosettaPath path, Othr o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Othr", ValidationType.CARDINALITY, "Othr", path, "", error);
		}
		return success("Othr", ValidationType.CARDINALITY, "Othr", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Othr o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Othr", ValidationType.CARDINALITY, "Othr", path, "", res.getError());
				}
				return success("Othr", ValidationType.CARDINALITY, "Othr", path, "");
			})
			.collect(toList());
	}

}
