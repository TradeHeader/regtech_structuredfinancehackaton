package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.SettlementBase;
import cdm.product.common.settlement.SettlementCentreEnum;
import cdm.product.common.settlement.SettlementDate;
import cdm.product.common.settlement.SettlementProvision;
import cdm.product.common.settlement.SettlementTypeEnum;
import cdm.product.common.settlement.StandardSettlementStyleEnum;
import cdm.product.common.settlement.TransferSettlementEnum;
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

public class SettlementBaseValidator implements Validator<SettlementBase> {

	private List<ComparisonResult> getComparisonResults(SettlementBase o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("settlementType", (SettlementTypeEnum) o.getSettlementType() != null ? 1 : 0, 1, 1), 
				checkCardinality("transferSettlementType", (TransferSettlementEnum) o.getTransferSettlementType() != null ? 1 : 0, 0, 1), 
				checkCardinality("settlementCurrency", (FieldWithMetaString) o.getSettlementCurrency() != null ? 1 : 0, 0, 1), 
				checkCardinality("settlementDate", (SettlementDate) o.getSettlementDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("settlementCentre", (SettlementCentreEnum) o.getSettlementCentre() != null ? 1 : 0, 0, 1), 
				checkCardinality("settlementProvision", (SettlementProvision) o.getSettlementProvision() != null ? 1 : 0, 0, 1), 
				checkCardinality("standardSettlementStyle", (StandardSettlementStyleEnum) o.getStandardSettlementStyle() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<SettlementBase> validate(RosettaPath path, SettlementBase o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SettlementBase", ValidationType.CARDINALITY, "SettlementBase", path, "", error);
		}
		return success("SettlementBase", ValidationType.CARDINALITY, "SettlementBase", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SettlementBase o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SettlementBase", ValidationType.CARDINALITY, "SettlementBase", path, "", res.getError());
				}
				return success("SettlementBase", ValidationType.CARDINALITY, "SettlementBase", path, "");
			})
			.collect(toList());
	}

}
