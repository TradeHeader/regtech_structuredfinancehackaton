package cdm.observable.asset.validation;

import cdm.observable.asset.BondChoiceModel;
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

public class BondChoiceModelTypeFormatValidator implements Validator<BondChoiceModel> {

	private List<ComparisonResult> getComparisonResults(BondChoiceModel o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<BondChoiceModel> validate(RosettaPath path, BondChoiceModel o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BondChoiceModel", ValidationType.TYPE_FORMAT, "BondChoiceModel", path, "", error);
		}
		return success("BondChoiceModel", ValidationType.TYPE_FORMAT, "BondChoiceModel", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BondChoiceModel o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BondChoiceModel", ValidationType.TYPE_FORMAT, "BondChoiceModel", path, "", res.getError());
				}
				return success("BondChoiceModel", ValidationType.TYPE_FORMAT, "BondChoiceModel", path, "");
			})
			.collect(toList());
	}

}
