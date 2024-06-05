package cdm.observable.asset.validation;

import cdm.base.math.QuantifierEnum;
import cdm.observable.asset.CreditNotationMismatchResolutionEnum;
import cdm.observable.asset.CreditRatingAgencyEnum;
import cdm.observable.asset.MultipleCreditNotations;
import cdm.observable.asset.metafields.FieldWithMetaCreditNotation;
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

public class MultipleCreditNotationsValidator implements Validator<MultipleCreditNotations> {

	private List<ComparisonResult> getComparisonResults(MultipleCreditNotations o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("condition", (QuantifierEnum) o.getCondition() != null ? 1 : 0, 1, 1), 
				checkCardinality("creditNotation", (List<? extends FieldWithMetaCreditNotation>) o.getCreditNotation() == null ? 0 : ((List<? extends FieldWithMetaCreditNotation>) o.getCreditNotation()).size(), 2, 0), 
				checkCardinality("mismatchResolution", (CreditNotationMismatchResolutionEnum) o.getMismatchResolution() != null ? 1 : 0, 0, 1), 
				checkCardinality("referenceAgency", (CreditRatingAgencyEnum) o.getReferenceAgency() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<MultipleCreditNotations> validate(RosettaPath path, MultipleCreditNotations o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MultipleCreditNotations", ValidationType.CARDINALITY, "MultipleCreditNotations", path, "", error);
		}
		return success("MultipleCreditNotations", ValidationType.CARDINALITY, "MultipleCreditNotations", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MultipleCreditNotations o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MultipleCreditNotations", ValidationType.CARDINALITY, "MultipleCreditNotations", path, "", res.getError());
				}
				return success("MultipleCreditNotations", ValidationType.CARDINALITY, "MultipleCreditNotations", path, "");
			})
			.collect(toList());
	}

}
