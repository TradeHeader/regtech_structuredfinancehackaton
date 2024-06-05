package cdm.product.asset.validation;

import cdm.base.staticdata.party.LegalEntity;
import cdm.base.staticdata.party.metafields.FieldWithMetaEntityTypeEnum;
import cdm.product.asset.ReferenceObligation;
import cdm.product.asset.ReferencePair;
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

public class ReferencePairValidator implements Validator<ReferencePair> {

	private List<ComparisonResult> getComparisonResults(ReferencePair o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("referenceEntity", (LegalEntity) o.getReferenceEntity() != null ? 1 : 0, 1, 1), 
				checkCardinality("referenceObligation", (ReferenceObligation) o.getReferenceObligation() != null ? 1 : 0, 0, 1), 
				checkCardinality("noReferenceObligation", (Boolean) o.getNoReferenceObligation() != null ? 1 : 0, 0, 1), 
				checkCardinality("entityType", (FieldWithMetaEntityTypeEnum) o.getEntityType() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<ReferencePair> validate(RosettaPath path, ReferencePair o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ReferencePair", ValidationType.CARDINALITY, "ReferencePair", path, "", error);
		}
		return success("ReferencePair", ValidationType.CARDINALITY, "ReferencePair", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ReferencePair o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ReferencePair", ValidationType.CARDINALITY, "ReferencePair", path, "", res.getError());
				}
				return success("ReferencePair", ValidationType.CARDINALITY, "ReferencePair", path, "");
			})
			.collect(toList());
	}

}
