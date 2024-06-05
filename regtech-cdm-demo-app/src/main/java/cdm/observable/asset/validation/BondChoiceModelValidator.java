package cdm.observable.asset.validation;

import cdm.base.staticdata.asset.common.Bond;
import cdm.base.staticdata.asset.common.ConvertibleBond;
import cdm.observable.asset.BondChoiceModel;
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

public class BondChoiceModelValidator implements Validator<BondChoiceModel> {

	private List<ComparisonResult> getComparisonResults(BondChoiceModel o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("bond", (Bond) o.getBond() != null ? 1 : 0, 0, 1), 
				checkCardinality("convertibleBond", (ConvertibleBond) o.getConvertibleBond() != null ? 1 : 0, 0, 1)
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
			return failure("BondChoiceModel", ValidationType.CARDINALITY, "BondChoiceModel", path, "", error);
		}
		return success("BondChoiceModel", ValidationType.CARDINALITY, "BondChoiceModel", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BondChoiceModel o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BondChoiceModel", ValidationType.CARDINALITY, "BondChoiceModel", path, "", res.getError());
				}
				return success("BondChoiceModel", ValidationType.CARDINALITY, "BondChoiceModel", path, "");
			})
			.collect(toList());
	}

}
