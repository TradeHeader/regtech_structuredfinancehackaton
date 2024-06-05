package cdm.product.collateral.validation;

import cdm.base.staticdata.asset.common.Index;
import cdm.product.collateral.ListingType;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ListingTypeValidator implements Validator<ListingType> {

	private List<ComparisonResult> getComparisonResults(ListingType o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("exchange", (FieldWithMetaString) o.getExchange() != null ? 1 : 0, 0, 1), 
				checkCardinality("sector", (FieldWithMetaString) o.getSector() != null ? 1 : 0, 0, 1), 
				checkCardinality("index", (Index) o.getIndex() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ListingType> validate(RosettaPath path, ListingType o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ListingType", ValidationType.CARDINALITY, "ListingType", path, "", error);
		}
		return success("ListingType", ValidationType.CARDINALITY, "ListingType", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ListingType o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ListingType", ValidationType.CARDINALITY, "ListingType", path, "", res.getError());
				}
				return success("ListingType", ValidationType.CARDINALITY, "ListingType", path, "");
			})
			.collect(toList());
	}

}
