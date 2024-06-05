package cdm.product.template.validation;

import cdm.product.template.TradeLot;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class TradeLotTypeFormatValidator implements Validator<TradeLot> {

	private List<ComparisonResult> getComparisonResults(TradeLot o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<TradeLot> validate(RosettaPath path, TradeLot o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("TradeLot", ValidationType.TYPE_FORMAT, "TradeLot", path, "", error);
		}
		return success("TradeLot", ValidationType.TYPE_FORMAT, "TradeLot", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TradeLot o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TradeLot", ValidationType.TYPE_FORMAT, "TradeLot", path, "", res.getError());
				}
				return success("TradeLot", ValidationType.TYPE_FORMAT, "TradeLot", path, "");
			})
			.collect(toList());
	}

}
