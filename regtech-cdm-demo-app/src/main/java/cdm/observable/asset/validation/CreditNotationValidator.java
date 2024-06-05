package cdm.observable.asset.validation;

import cdm.observable.asset.CreditNotation;
import cdm.observable.asset.CreditRatingAgencyEnum;
import cdm.observable.asset.CreditRatingCreditWatchEnum;
import cdm.observable.asset.CreditRatingDebt;
import cdm.observable.asset.CreditRatingOutlookEnum;
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

public class CreditNotationValidator implements Validator<CreditNotation> {

	private List<ComparisonResult> getComparisonResults(CreditNotation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("agency", (CreditRatingAgencyEnum) o.getAgency() != null ? 1 : 0, 1, 1), 
				checkCardinality("notation", (FieldWithMetaString) o.getNotation() != null ? 1 : 0, 1, 1), 
				checkCardinality("scale", (FieldWithMetaString) o.getScale() != null ? 1 : 0, 0, 1), 
				checkCardinality("debt", (CreditRatingDebt) o.getDebt() != null ? 1 : 0, 0, 1), 
				checkCardinality("outlook", (CreditRatingOutlookEnum) o.getOutlook() != null ? 1 : 0, 0, 1), 
				checkCardinality("creditWatch", (CreditRatingCreditWatchEnum) o.getCreditWatch() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CreditNotation> validate(RosettaPath path, CreditNotation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CreditNotation", ValidationType.CARDINALITY, "CreditNotation", path, "", error);
		}
		return success("CreditNotation", ValidationType.CARDINALITY, "CreditNotation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CreditNotation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CreditNotation", ValidationType.CARDINALITY, "CreditNotation", path, "", res.getError());
				}
				return success("CreditNotation", ValidationType.CARDINALITY, "CreditNotation", path, "");
			})
			.collect(toList());
	}

}
