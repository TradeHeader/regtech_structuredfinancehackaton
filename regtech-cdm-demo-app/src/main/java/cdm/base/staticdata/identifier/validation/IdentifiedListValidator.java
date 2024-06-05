package cdm.base.staticdata.identifier.validation;

import cdm.base.staticdata.identifier.IdentifiedList;
import cdm.base.staticdata.identifier.Identifier;
import cdm.observable.asset.Price;
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

public class IdentifiedListValidator implements Validator<IdentifiedList> {

	private List<ComparisonResult> getComparisonResults(IdentifiedList o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("listId", (Identifier) o.getListId() != null ? 1 : 0, 1, 1), 
				checkCardinality("componentId", (List<? extends Identifier>) o.getComponentId() == null ? 0 : ((List<? extends Identifier>) o.getComponentId()).size(), 2, 0), 
				checkCardinality("price", (Price) o.getPrice() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<IdentifiedList> validate(RosettaPath path, IdentifiedList o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("IdentifiedList", ValidationType.CARDINALITY, "IdentifiedList", path, "", error);
		}
		return success("IdentifiedList", ValidationType.CARDINALITY, "IdentifiedList", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, IdentifiedList o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("IdentifiedList", ValidationType.CARDINALITY, "IdentifiedList", path, "", res.getError());
				}
				return success("IdentifiedList", ValidationType.CARDINALITY, "IdentifiedList", path, "");
			})
			.collect(toList());
	}

}
