package cdm.observable.asset.validation;

import cdm.base.staticdata.asset.common.Equity;
import cdm.observable.asset.BondChoiceModel;
import cdm.observable.asset.BondEquityModel;
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

public class BondEquityModelValidator implements Validator<BondEquityModel> {

	private List<ComparisonResult> getComparisonResults(BondEquityModel o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("bondchoiceModel", (BondChoiceModel) o.getBondchoiceModel() != null ? 1 : 0, 0, 1), 
				checkCardinality("equity", (Equity) o.getEquity() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<BondEquityModel> validate(RosettaPath path, BondEquityModel o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BondEquityModel", ValidationType.CARDINALITY, "BondEquityModel", path, "", error);
		}
		return success("BondEquityModel", ValidationType.CARDINALITY, "BondEquityModel", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BondEquityModel o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BondEquityModel", ValidationType.CARDINALITY, "BondEquityModel", path, "", res.getError());
				}
				return success("BondEquityModel", ValidationType.CARDINALITY, "BondEquityModel", path, "");
			})
			.collect(toList());
	}

}
