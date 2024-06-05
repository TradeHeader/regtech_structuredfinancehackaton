package cdm.legaldocumentation.common.validation;

import cdm.legaldocumentation.common.OtherAgreement;
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

public class OtherAgreementValidator implements Validator<OtherAgreement> {

	private List<ComparisonResult> getComparisonResults(OtherAgreement o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("identifier", (FieldWithMetaString) o.getIdentifier() != null ? 1 : 0, 0, 1), 
				checkCardinality("otherAgreementType", (FieldWithMetaString) o.getOtherAgreementType() != null ? 1 : 0, 1, 1), 
				checkCardinality("version", (FieldWithMetaString) o.getVersion() != null ? 1 : 0, 0, 1), 
				checkCardinality("date", (Date) o.getDate() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<OtherAgreement> validate(RosettaPath path, OtherAgreement o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("OtherAgreement", ValidationType.CARDINALITY, "OtherAgreement", path, "", error);
		}
		return success("OtherAgreement", ValidationType.CARDINALITY, "OtherAgreement", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, OtherAgreement o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("OtherAgreement", ValidationType.CARDINALITY, "OtherAgreement", path, "", res.getError());
				}
				return success("OtherAgreement", ValidationType.CARDINALITY, "OtherAgreement", path, "");
			})
			.collect(toList());
	}

}
