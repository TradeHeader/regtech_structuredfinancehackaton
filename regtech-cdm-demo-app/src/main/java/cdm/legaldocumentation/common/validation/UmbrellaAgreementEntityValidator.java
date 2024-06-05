package cdm.legaldocumentation.common.validation;

import cdm.legaldocumentation.common.UmbrellaAgreementEntity;
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

public class UmbrellaAgreementEntityValidator implements Validator<UmbrellaAgreementEntity> {

	private List<ComparisonResult> getComparisonResults(UmbrellaAgreementEntity o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("name", (FieldWithMetaString) o.getName() != null ? 1 : 0, 1, 1), 
				checkCardinality("terms", (String) o.getTerms() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<UmbrellaAgreementEntity> validate(RosettaPath path, UmbrellaAgreementEntity o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("UmbrellaAgreementEntity", ValidationType.CARDINALITY, "UmbrellaAgreementEntity", path, "", error);
		}
		return success("UmbrellaAgreementEntity", ValidationType.CARDINALITY, "UmbrellaAgreementEntity", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, UmbrellaAgreementEntity o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("UmbrellaAgreementEntity", ValidationType.CARDINALITY, "UmbrellaAgreementEntity", path, "", res.getError());
				}
				return success("UmbrellaAgreementEntity", ValidationType.CARDINALITY, "UmbrellaAgreementEntity", path, "");
			})
			.collect(toList());
	}

}
