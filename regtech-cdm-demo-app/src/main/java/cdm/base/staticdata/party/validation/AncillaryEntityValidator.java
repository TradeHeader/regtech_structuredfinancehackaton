package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.AncillaryEntity;
import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.base.staticdata.party.LegalEntity;
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

public class AncillaryEntityValidator implements Validator<AncillaryEntity> {

	private List<ComparisonResult> getComparisonResults(AncillaryEntity o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("ancillaryParty", (AncillaryRoleEnum) o.getAncillaryParty() != null ? 1 : 0, 0, 1), 
				checkCardinality("legalEntity", (LegalEntity) o.getLegalEntity() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<AncillaryEntity> validate(RosettaPath path, AncillaryEntity o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AncillaryEntity", ValidationType.CARDINALITY, "AncillaryEntity", path, "", error);
		}
		return success("AncillaryEntity", ValidationType.CARDINALITY, "AncillaryEntity", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AncillaryEntity o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AncillaryEntity", ValidationType.CARDINALITY, "AncillaryEntity", path, "", res.getError());
				}
				return success("AncillaryEntity", ValidationType.CARDINALITY, "AncillaryEntity", path, "");
			})
			.collect(toList());
	}

}
