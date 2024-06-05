package cdm.product.collateral.validation;

import cdm.base.math.QuantifierEnum;
import cdm.observable.asset.CreditNotation;
import cdm.observable.asset.CreditNotationBoundaryEnum;
import cdm.observable.asset.CreditNotationMismatchResolutionEnum;
import cdm.observable.asset.CreditRatingAgencyEnum;
import cdm.product.collateral.AgencyRatingCriteria;
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

public class AgencyRatingCriteriaValidator implements Validator<AgencyRatingCriteria> {

	private List<ComparisonResult> getComparisonResults(AgencyRatingCriteria o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("qualifier", (QuantifierEnum) o.getQualifier() != null ? 1 : 0, 1, 1), 
				checkCardinality("creditNotation", (List<? extends CreditNotation>) o.getCreditNotation() == null ? 0 : ((List<? extends CreditNotation>) o.getCreditNotation()).size(), 1, 0), 
				checkCardinality("mismatchResolution", (CreditNotationMismatchResolutionEnum) o.getMismatchResolution() != null ? 1 : 0, 0, 1), 
				checkCardinality("referenceAgency", (CreditRatingAgencyEnum) o.getReferenceAgency() != null ? 1 : 0, 0, 1), 
				checkCardinality("boundary", (CreditNotationBoundaryEnum) o.getBoundary() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<AgencyRatingCriteria> validate(RosettaPath path, AgencyRatingCriteria o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AgencyRatingCriteria", ValidationType.CARDINALITY, "AgencyRatingCriteria", path, "", error);
		}
		return success("AgencyRatingCriteria", ValidationType.CARDINALITY, "AgencyRatingCriteria", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AgencyRatingCriteria o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AgencyRatingCriteria", ValidationType.CARDINALITY, "AgencyRatingCriteria", path, "", res.getError());
				}
				return success("AgencyRatingCriteria", ValidationType.CARDINALITY, "AgencyRatingCriteria", path, "");
			})
			.collect(toList());
	}

}
