package cdm.product.template.validation;

import cdm.base.staticdata.party.PayerReceiver;
import cdm.product.template.PassThroughItem;
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

public class PassThroughItemValidator implements Validator<PassThroughItem> {

	private List<ComparisonResult> getComparisonResults(PassThroughItem o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("payerReceiver", (PayerReceiver) o.getPayerReceiver() != null ? 1 : 0, 1, 1), 
				checkCardinality("passThroughPercentage", (BigDecimal) o.getPassThroughPercentage() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<PassThroughItem> validate(RosettaPath path, PassThroughItem o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PassThroughItem", ValidationType.CARDINALITY, "PassThroughItem", path, "", error);
		}
		return success("PassThroughItem", ValidationType.CARDINALITY, "PassThroughItem", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PassThroughItem o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PassThroughItem", ValidationType.CARDINALITY, "PassThroughItem", path, "", res.getError());
				}
				return success("PassThroughItem", ValidationType.CARDINALITY, "PassThroughItem", path, "");
			})
			.collect(toList());
	}

}
