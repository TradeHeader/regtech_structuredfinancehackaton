package cdm.event.position.validation;

import cdm.base.math.Quantity;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.identifier.AssignedIdentifier;
import cdm.event.position.AvailableInventoryRecord;
import cdm.observable.asset.Price;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.time.ZonedDateTime;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class AvailableInventoryRecordValidator implements Validator<AvailableInventoryRecord> {

	private List<ComparisonResult> getComparisonResults(AvailableInventoryRecord o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("identifer", (AssignedIdentifier) o.getIdentifer() != null ? 1 : 0, 1, 1), 
				checkCardinality("security", (Security) o.getSecurity() != null ? 1 : 0, 1, 1), 
				checkCardinality("expirationDateTime", (ZonedDateTime) o.getExpirationDateTime() != null ? 1 : 0, 0, 1), 
				checkCardinality("availableQuantity", (Quantity) o.getAvailableQuantity() != null ? 1 : 0, 0, 1), 
				checkCardinality("interestRate", (Price) o.getInterestRate() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<AvailableInventoryRecord> validate(RosettaPath path, AvailableInventoryRecord o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AvailableInventoryRecord", ValidationType.CARDINALITY, "AvailableInventoryRecord", path, "", error);
		}
		return success("AvailableInventoryRecord", ValidationType.CARDINALITY, "AvailableInventoryRecord", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AvailableInventoryRecord o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AvailableInventoryRecord", ValidationType.CARDINALITY, "AvailableInventoryRecord", path, "", res.getError());
				}
				return success("AvailableInventoryRecord", ValidationType.CARDINALITY, "AvailableInventoryRecord", path, "");
			})
			.collect(toList());
	}

}
