package cdm.event.common.validation;

import cdm.event.common.State;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
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

public class TradeStateValidator implements Validator<TradeState> {

	private List<ComparisonResult> getComparisonResults(TradeState o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("trade", (Trade) o.getTrade() != null ? 1 : 0, 1, 1), 
				checkCardinality("state", (State) o.getState() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<TradeState> validate(RosettaPath path, TradeState o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("TradeState", ValidationType.CARDINALITY, "TradeState", path, "", error);
		}
		return success("TradeState", ValidationType.CARDINALITY, "TradeState", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TradeState o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TradeState", ValidationType.CARDINALITY, "TradeState", path, "", res.getError());
				}
				return success("TradeState", ValidationType.CARDINALITY, "TradeState", path, "");
			})
			.collect(toList());
	}

}
