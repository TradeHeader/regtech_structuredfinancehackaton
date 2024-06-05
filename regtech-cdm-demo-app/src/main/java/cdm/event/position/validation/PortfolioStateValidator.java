package cdm.event.position.validation;

import cdm.event.common.Lineage;
import cdm.event.position.PortfolioState;
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

public class PortfolioStateValidator implements Validator<PortfolioState> {

	private List<ComparisonResult> getComparisonResults(PortfolioState o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("lineage", (Lineage) o.getLineage() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<PortfolioState> validate(RosettaPath path, PortfolioState o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PortfolioState", ValidationType.CARDINALITY, "PortfolioState", path, "", error);
		}
		return success("PortfolioState", ValidationType.CARDINALITY, "PortfolioState", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PortfolioState o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PortfolioState", ValidationType.CARDINALITY, "PortfolioState", path, "", res.getError());
				}
				return success("PortfolioState", ValidationType.CARDINALITY, "PortfolioState", path, "");
			})
			.collect(toList());
	}

}
