package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.PhysicalSettlementTerms;
import cdm.product.common.settlement.SettlementCentreEnum;
import cdm.product.common.settlement.SettlementDate;
import cdm.product.common.settlement.SettlementProvision;
import cdm.product.common.settlement.SettlementTerms;
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

public class SettlementTermsValidator implements Validator<SettlementTerms> {

	private List<ComparisonResult> getComparisonResults(SettlementTerms o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("settlementType", (SettlementTypeEnum) o.getSettlementType() != null ? 1 : 0, 1, 1), 
				checkCardinality("transferSettlementType", (TransferSettlementEnum) o.getTransferSettlementType() != null ? 1 : 0, 0, 1), 
				checkCardinality("settlementCurrency", (FieldWithMetaString) o.getSettlementCurrency() != null ? 1 : 0, 0, 1), 
				checkCardinality("settlementDate", (SettlementDate) o.getSettlementDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("settlementCentre", (SettlementCentreEnum) o.getSettlementCentre() != null ? 1 : 0, 0, 1), 
				checkCardinality("settlementProvision", (SettlementProvision) o.getSettlementProvision() != null ? 1 : 0, 0, 1), 
				checkCardinality("standardSettlementStyle", (StandardSettlementStyleEnum) o.getStandardSettlementStyle() != null ? 1 : 0, 0, 1), 
				checkCardinality("physicalSettlementTerms", (PhysicalSettlementTerms) o.getPhysicalSettlementTerms() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<SettlementTerms> validate(RosettaPath path, SettlementTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SettlementTerms", ValidationType.CARDINALITY, "SettlementTerms", path, "", error);
		}
		return success("SettlementTerms", ValidationType.CARDINALITY, "SettlementTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SettlementTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SettlementTerms", ValidationType.CARDINALITY, "SettlementTerms", path, "", res.getError());
				}
				return success("SettlementTerms", ValidationType.CARDINALITY, "SettlementTerms", path, "");
			})
			.collect(toList());
	}

}
