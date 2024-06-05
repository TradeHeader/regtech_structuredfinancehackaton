package cdm.product.template.validation;

import cdm.base.staticdata.party.PayerReceiverEnum;
import cdm.product.template.Strike;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class StrikeValidator implements Validator<Strike> {

	private List<ComparisonResult> getComparisonResults(Strike o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("strikeRate", (BigDecimal) o.getStrikeRate() != null ? 1 : 0, 1, 1), 
				checkCardinality("buyer", (PayerReceiverEnum) o.getBuyer() != null ? 1 : 0, 0, 1), 
				checkCardinality("seller", (PayerReceiverEnum) o.getSeller() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Strike> validate(RosettaPath path, Strike o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Strike", ValidationType.CARDINALITY, "Strike", path, "", error);
		}
		return success("Strike", ValidationType.CARDINALITY, "Strike", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Strike o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Strike", ValidationType.CARDINALITY, "Strike", path, "", res.getError());
				}
				return success("Strike", ValidationType.CARDINALITY, "Strike", path, "");
			})
			.collect(toList());
	}

}
