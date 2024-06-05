package cdm.event.common.validation;

import cdm.base.staticdata.identifier.AssignedIdentifier;
import cdm.base.staticdata.identifier.TradeIdentifierTypeEnum;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.event.common.PositionIdentifier;
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

public class PositionIdentifierValidator implements Validator<PositionIdentifier> {

	private List<ComparisonResult> getComparisonResults(PositionIdentifier o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("issuerReference", (ReferenceWithMetaParty) o.getIssuerReference() != null ? 1 : 0, 0, 1), 
				checkCardinality("issuer", (FieldWithMetaString) o.getIssuer() != null ? 1 : 0, 0, 1), 
				checkCardinality("assignedIdentifier", (List<? extends AssignedIdentifier>) o.getAssignedIdentifier() == null ? 0 : ((List<? extends AssignedIdentifier>) o.getAssignedIdentifier()).size(), 1, 0), 
				checkCardinality("identifierType", (TradeIdentifierTypeEnum) o.getIdentifierType() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PositionIdentifier> validate(RosettaPath path, PositionIdentifier o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PositionIdentifier", ValidationType.CARDINALITY, "PositionIdentifier", path, "", error);
		}
		return success("PositionIdentifier", ValidationType.CARDINALITY, "PositionIdentifier", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PositionIdentifier o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PositionIdentifier", ValidationType.CARDINALITY, "PositionIdentifier", path, "", res.getError());
				}
				return success("PositionIdentifier", ValidationType.CARDINALITY, "PositionIdentifier", path, "");
			})
			.collect(toList());
	}

}
