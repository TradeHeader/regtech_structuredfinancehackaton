package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.Loan;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class LoanValidator implements Validator<Loan> {

	private List<ComparisonResult> getComparisonResults(Loan o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("lien", (FieldWithMetaString) o.getLien() != null ? 1 : 0, 0, 1), 
				checkCardinality("facilityType", (FieldWithMetaString) o.getFacilityType() != null ? 1 : 0, 0, 1), 
				checkCardinality("creditAgreementDate", (Date) o.getCreditAgreementDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("tranche", (FieldWithMetaString) o.getTranche() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Loan> validate(RosettaPath path, Loan o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Loan", ValidationType.CARDINALITY, "Loan", path, "", error);
		}
		return success("Loan", ValidationType.CARDINALITY, "Loan", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Loan o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Loan", ValidationType.CARDINALITY, "Loan", path, "", res.getError());
				}
				return success("Loan", ValidationType.CARDINALITY, "Loan", path, "");
			})
			.collect(toList());
	}

}
