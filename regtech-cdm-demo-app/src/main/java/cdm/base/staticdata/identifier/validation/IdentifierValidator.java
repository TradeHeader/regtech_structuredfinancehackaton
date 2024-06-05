package cdm.base.staticdata.identifier.validation;

import cdm.base.staticdata.identifier.AssignedIdentifier;
import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
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

public class IdentifierValidator implements Validator<Identifier> {

	private List<ComparisonResult> getComparisonResults(Identifier o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("issuerReference", (ReferenceWithMetaParty) o.getIssuerReference() != null ? 1 : 0, 0, 1), 
				checkCardinality("issuer", (FieldWithMetaString) o.getIssuer() != null ? 1 : 0, 0, 1), 
				checkCardinality("assignedIdentifier", (List<? extends AssignedIdentifier>) o.getAssignedIdentifier() == null ? 0 : ((List<? extends AssignedIdentifier>) o.getAssignedIdentifier()).size(), 1, 0)
			);
	}

	@Override
	public ValidationResult<Identifier> validate(RosettaPath path, Identifier o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Identifier", ValidationType.CARDINALITY, "Identifier", path, "", error);
		}
		return success("Identifier", ValidationType.CARDINALITY, "Identifier", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Identifier o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Identifier", ValidationType.CARDINALITY, "Identifier", path, "", res.getError());
				}
				return success("Identifier", ValidationType.CARDINALITY, "Identifier", path, "");
			})
			.collect(toList());
	}

}
